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

        // Gestion de l'user FB / Parse
        currentUser = ParseUser.getCurrentUser();
        userProfile = new ProfilDB();

        // Changer le texte du bouton de connexion FB selon le statut connecté ou non
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser) && Profile.getCurrentProfile() != null) {
            FBButton.setText(getString(R.string.disconnect));
        }
        else {
            FBButton.setText(getString(R.string.connect));
        }

    }

    public void onLoginClickFacebook(View v) {
        List<String> permissions = Arrays.asList("public_profile", "email");

        //L'user n'est pas connecté à FB
        if(Profile.getCurrentProfile() == null) {
            progressDialog = ProgressDialog.show(MainActivity.this, "", "Logging in...", true);
            // NOTE: for extended permissions, like "user_about_me", your app must be reviewed by the Facebook team
            // (https://developers.facebook.com/docs/facebook-login/permissions/)

            // Si un token est toujours présent, il peut être invalide
            if(AccessToken.getCurrentAccessToken() != null) {
                LoginManager.getInstance().logOut();
            }

            ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException err) {
                    progressDialog.dismiss();
                    if (user == null) {
                        Log.d(StarterApplication.TAG, "Uh oh. The user cancelled the Facebook login.");
                    }
                    else if (user.isNew()) {
                        Log.d(StarterApplication.TAG, "User signed up and logged in through Facebook!");
                        FBButton.setText(getString(R.string.disconnect));
                    }
                    else {
                        Log.d(StarterApplication.TAG, "User logged in through Facebook!");
                        FBButton.setText(getString(R.string.disconnect));
                    }
                    ParseFacebookUtils.linkInBackground(user, AccessToken.getCurrentAccessToken());
                }
            });
        }

        //L'user est connecté et a demandé une déconnexion
        else {
            ParseFacebookUtils.unlinkInBackground(currentUser, new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        LoginManager.getInstance().logOut();
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
            Toast.makeText(this, getString(R.string.todo), Toast.LENGTH_SHORT).show();
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
            if(FBUser != null) {
                tmp += "User : " + FBUser.getId() + " - " + FBUser.getName() + " ~ " + FBUser.getLinkUri() + "\nToken : " + token.getToken();
            }
            else {
                tmp += "User déconnecté de FB mais token toujours présent ?!";
            }
        }

        Profile tmpProfile = Profile.getCurrentProfile();
        tmp += "\nProfile : ";
        if(tmpProfile != null) {
            tmp += tmpProfile.toString();
        }
        else {
            tmp += "null";
        }

        tmp += "\nParseUser ID : " + ParseUser.getCurrentUser().getObjectId();

        Toast.makeText(this, tmp, Toast.LENGTH_SHORT).show();
    }
}