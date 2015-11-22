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

import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.Modele.ListeCovoiturageDB;
import com.app.pebkar.Modele.Notation;
import com.app.pebkar.Modele.Passagers;
import com.app.pebkar.Modele.Profil;
import com.app.pebkar.Modele.TableSeq;
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
    ParseObject.registerSubclass(TableSeq.class);
    ParseObject.registerSubclass(ListeCovoiturageDB.class);
    ParseObject.registerSubclass(ListeCovoiturage.class);
    ParseObject.registerSubclass(Notation.class);
    ParseObject.registerSubclass(Passagers.class);
    ParseObject.registerSubclass(Profil.class);

    // Add your initialization code here
    Parse.initialize(this, "fc7IlnwGrW6KgP43o6dHNir9W20xzqEhr94EMVyW","BMclWPRzlY4yn5yR7ZdbyOXkohQVJOIoBnBwfi2I");

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
