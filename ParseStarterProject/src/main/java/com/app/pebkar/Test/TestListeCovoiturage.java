package com.app.pebkar.Test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.pebkar.Modele.ListeCovoiturageDB;
import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.R;
import com.parse.ParseAnalytics;

import java.util.Date;

/**
 * Created by David Elykx on 21-11-15.
 */
public class TestListeCovoiturage extends AppCompatActivity {

    private ListeCovoiturageDB lcDB1, lcDB2;
    private ListeCovoiturage lc1, lc2;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_liste_covoiturage);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        lcDB1 = new ListeCovoiturageDB();
        Date datedeparttest = new Date();
        Date datearriveetest = new Date();
        datearriveetest.setTime(datedeparttest.getTime() + 3600000);

        lcDB1 = new ListeCovoiturageDB("lieudeptest","lieuarriveetest",datedeparttest,datearriveetest);
        try {
            //lcDB1.save();
            lcDB1.createData();
            lcDB2 = new ListeCovoiturageDB();

        }
        catch(Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT);
            System.out.println("[DEBUG] Erreur : " + e.getLocalizedMessage());
        }

        /*
        try {
            System.out.println("--- Test Ajout ---");
            lcDB1 = new ListeCovoiturageDB("lieudeptest","lieuarriveetest",datedeparttest,datearriveetest);
            lcDB1.createData();
            String lieudepart = lcDB1.getLieudepart();
            //lc2.readData(lieudepart);

            //if(lc2.getLieuarrivee().equals("lieuarriveetest")) System.out.println("--- Test Ajout OK ---");
            //else System.out.println("--- Test Ajout ERREUR ---");
        } catch (Exception e){
            e.printStackTrace();
        }
        */
        /*
        try {
            System.out.println("--- Test Suppression ---");
            lc1.deleteData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */


    }
}
