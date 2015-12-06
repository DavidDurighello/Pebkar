/*
* Copyright (c) 2015-present, Parse, LLC.
* All rights reserved.
*
* This source code is licensed under the BSD-style license found in the
* LICENSE file in the root directory of this source tree. An additional grant
* of patent rights can be found in the PATENTS file in the same directory.
*/
package com.app.pebkar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.pebkar.Modele.ProfilDB;
import com.app.pebkar.Test.TestListeCovoiturage;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.app.pebkar.MESSAGE";
    private Dialog progressDialog;
    ProfilDB userProfile;
    ParseUser currentUser;

    Button FBButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        getApplicationContext();

        // Récupération des widgets
        FBButton = (Button) findViewById(R.id.btn_fb);

        //UserConnected user = new UserConnected("test","test",getApplicationContext());

        // Connexion à user test
        //Ajout profil test
        /*
        Profil profilTest = new Profil("test","test","test","055/555555",ParseUser.getCurrentUser().getObjectId());
        profilTest.saveInBackground();
        */

        currentUser = ParseUser.getCurrentUser();
        userProfile = new ProfilDB();

        //ParseFacebookUtils.unlinkInBackground(currentUser);
        Log.e("[???]", currentUser.toString() + " \n" + ParseFacebookUtils.isLinked(currentUser));

        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            // Go to the user info activity
            //Log.e("[FB]", AccessToken.getCurrentAccessToken().toString());
            //Log.e("[FB]", Profile.getCurrentProfile().getName());
            //Log.e("[FB]", Profile.getCurrentProfile().getId());
            //Log.e("[FB]", AccessToken.ACCESS_TOKEN_KEY);
            //Toast.makeText(this, "User connecté à Facebook", Toast.LENGTH_SHORT).show();

            // Si connecté à Facebook : on regarde si un profil existe
            // S'il existe : on le récupère
            // S'il n'existe pas : on le crée avec les infos qu'on a de FB

            if(userProfile.getProfilFromFB(Profile.getCurrentProfile())) {
                Toast.makeText(this, "User connecté via FB (pas la 1ère connexion)", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "User connecté via FB (1ère connexion)", Toast.LENGTH_SHORT).show();
            }

            Log.e("[FBUser - onCreate]", userProfile.toString());
            FBButton.setText(getString(R.string.disconnect));

        }
        else {
            // Toast.makeText(this, "Non connecté à Facebook", Toast.LENGTH_SHORT).show();
            FBButton.setText(getString(R.string.connect));
        }

    }

    public void onLoginClickFacebook(View v) {

        List<String> permissions = Arrays.asList("public_profile", "email");


        /**
         * L'user n'est pas connecté à FB
         */
        //if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
        if(Profile.getCurrentProfile() == null) {
            progressDialog = ProgressDialog.show(MainActivity.this, "", "Logging in...", true);
            // NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team
            // (https://developers.facebook.com/docs/facebook-login/permissions/)

            ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException err) {
                    progressDialog.dismiss();
                    if (user == null) {
                        Log.d(StarterApplication.TAG, "Uh oh. The user cancelled the Facebook login.");
                    } else if (user.isNew()) {
                        Log.d(StarterApplication.TAG, "User signed up and logged in through Facebook!");
                        FBButton.setText(getString(R.string.disconnect));
                        userProfile.getProfilFromFB(Profile.getCurrentProfile());
                    } else {
                        Log.d(StarterApplication.TAG, "User logged in through Facebook!");
                        FBButton.setText(getString(R.string.disconnect));
                        userProfile.getProfilFromFB(Profile.getCurrentProfile());
                    }
                    ParseFacebookUtils.linkInBackground(user, AccessToken.getCurrentAccessToken());

                    //Log.e("[FB]", "Session créée");
                }
            });
        }
        /**
         * L'user est connecté et a demandé une déconnexion
         */
        else {
            ParseFacebookUtils.unlinkInBackground(currentUser, new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        LoginManager.getInstance().logOut();
                        userProfile.logOut();
                        Toast.makeText(getApplicationContext(), getString(R.string.fb_disconnected), Toast.LENGTH_SHORT).show();
                        FBButton.setText(getString(R.string.connect));
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.fb_disconnection_error), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_testCRUD) {
            startActivity(new Intent(this, TestListeCovoiturage.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnRecherche(View view){
        Intent intent = new Intent(this, Search.class);
        intent.putExtra(EXTRA_MESSAGE, "Activity Search");
        startActivity(intent);
    }



    public void debugInfo(View view) {
        AccessToken token = AccessToken.getCurrentAccessToken();

        String tmp = "";

        if(token == null) {
            tmp += "Pas de token";
        }
        else {
            Profile FBUser = Profile.getCurrentProfile();
            tmp += "User : " + FBUser.getId() + " - " + FBUser.getName() + " ~ " + FBUser.getLinkUri() + "\nToken : " + token.getToken();
        }

        Profile tmpProfile = Profile.getCurrentProfile();
        tmp += "\nProfile : ";
        if(tmpProfile != null) {
            tmp += tmpProfile.toString();
        }
        else {
            tmp += "null";
        }

        Toast.makeText(this, tmp, Toast.LENGTH_SHORT).show();
        Log.e("test", userProfile.toString());
    }

    public void decoFB(View view) {

    }
}
