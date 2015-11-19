/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.app.pebkar;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    //DBClass
    ParseObject.registerSubclass(ListeCovoiturageDB.class);
    ParseObject.registerSubclass(NotationDB.class);
    ParseObject.registerSubclass(PassagersDB.class);
    ParseObject.registerSubclass(ProfilDB.class);

    // Add your initialization code here
    Parse.initialize(this, "fc7IlnwGrW6KgP43o6dHNir9W20xzqEhr94EMVyW","BMclWPRzlY4yn5yR7ZdbyOXkohQVJOIoBnBwfi2I");

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
