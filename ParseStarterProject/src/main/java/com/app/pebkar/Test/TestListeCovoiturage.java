package com.app.pebkar.Test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.pebkar.Modele.ListeCovoiturageDB;
import com.parse.ParseAnalytics;

import java.util.Date;

/**
 * Created by David Elykx on 21-11-15.
 */
public class TestListeCovoiturage extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        ListeCovoiturageDB lc1 = null , lc2 = null;
        Date datedeparttest = new Date();
        Date datearriveetest = new Date();
        datearriveetest.setTime(datedeparttest.getTime() + 3600000);

        try {
            System.out.println("--- Test Ajout ---");
            lc1 = new ListeCovoiturageDB("lieudeptest","lieuarriveetest",datedeparttest,datearriveetest);
            lc1.createData();
            String lieudepart = lc1.getLieudepart();
            lc2.readData(lieudepart);

            if(lc2.getLieuarrivee().equals("lieuarriveetest")) System.out.println("--- Test Ajout OK ---");
            else System.out.println("--- Test Ajout ERREUR ---");
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            System.out.println("--- Test Suppression ---");
            lc1.deleteData();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
