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
import android.widget.Toast;

import com.app.pebkar.Test.TestListeCovoiturage;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.app.pebkar.MESSAGE";
    private Dialog progressDialog;
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        getApplicationContext();

        UserConnected user = new UserConnected("test", "test", getApplicationContext());

    // Connexion à user test
      //Ajout profil test
      /*
      Profil profilTest = new Profil("test","test","test","055/555555",ParseUser.getCurrentUser().getObjectId());
      profilTest.saveInBackground();
      */

        ParseUser currentUser = ParseUser.getCurrentUser();
        /*
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            // Go to the user info activity
            Log.e("[FB]", AccessToken.getCurrentAccessToken().toString());
            Log.e("[FB]", Profile.getCurrentProfile().getName());
            //Toast.makeText(this, "User connecté à Facebook", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(this, "Non connecté à Facebook", Toast.LENGTH_SHORT).show();
        }
        */

    }

    public void onLoginClickFacebook(View v) {
        progressDialog = ProgressDialog.show(MainActivity.this, "", "Logging in...", true);

        List<String> permissions = Arrays.asList("public_profile", "email");

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
                } else {
                    Log.d(StarterApplication.TAG, "User logged in through Facebook!");
                }

                //ParseFacebookUtils.linkInBackground(user, AccessToken.getCurrentAccessToken());
                //Log.e("[FB]", "Session créée");
            }
        });


    }

    public void fillFBInfo() {
        AccessToken token = AccessToken.getCurrentAccessToken();

        if(token == null) {
            Toast.makeText(this, "Pas de token", Toast.LENGTH_SHORT).show();
        }
        else {
            GraphRequest request = new GraphRequest().newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            // Code
                            System.out.println(object.toString());
                            System.out.println(response.toString());
                        }
                    }
            );
            Log.e("sdqsd", request.toString());
            Log.e("sss", request.getClass().getName());
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
        ParseUser user = ParseUser.getCurrentUser();

        fillFBInfo();
        Log.e("DEBUG", user.getUsername() + " ~ " + user.getEmail() + " ~ " + user.getSessionToken() + " ~ ");
    }
}
