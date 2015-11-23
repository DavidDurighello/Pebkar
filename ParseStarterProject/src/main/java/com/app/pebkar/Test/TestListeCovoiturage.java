package com.app.pebkar.Test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
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

    // Widgets
    TextView tv_result;

    // Classes de test
    private ListeCovoiturageDB lcDB1, lcDB2;
    private ListeCovoiturage lc1, lc2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_liste_covoiturage);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        tv_result = (TextView) findViewById(R.id.tv_debug);

        /*
         * Test AJOUT
         */
        tv_result.setText("");  // Le résultat sera affiché après les traitements.

        lcDB1 = new ListeCovoiturageDB();
        Date datedeparttest = new Date();
        Date datearriveetest = new Date();
        datearriveetest.setTime(datedeparttest.getTime() + 3600000);

        lcDB1 = new ListeCovoiturageDB("lieudeptest","lieuarriveetest",datedeparttest,datearriveetest);
        try {
            lcDB1.createData();
            lcDB2 = new ListeCovoiturageDB();
            lc1 = new ListeCovoiturage();
            lc1.setIdListeCovoiturage(lcDB1.getVoyage().getIdListeCovoiturage());
            lcDB2.setVoyage(lc1); // Il n'y a que l'ID
            lcDB2.fillDataFromFilter(lcDB2.getVoyage().getIdListeCovoiturage()); // on récupère le voyage complet avec l'ID du voyage qu'on a en cours

            System.out.println("[DEBUG] lcDB1 = " + lcDB1.toString());
            System.out.println("[DEBUG] lcDB2 = " + lcDB2.toString());

            // Effacer l'entrée qu'on vient d'ajouter
            try {
                if(lcDB2.equals(lcDB1)) {
                    tv_result.setText(Html.fromHtml("Test <b>AJOUT</b> : <font color=green>Réussi</font>"));  // Le résultat sera affiché après les traitements.
                }
                else {
                    tv_result.setText(Html.fromHtml("Test <b>AJOUT</b> : <font=red>Échoué</font> (lcDB1 != lcDB2)"));  // Le résultat sera affiché après les traitements.
                }
                lcDB1.deleteData();
            }
            catch(Exception e) {}
        }
        catch(Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT);
            System.out.println("[DEBUG] Erreur (TestListeCovoiturage.java) : " + e.getLocalizedMessage());
            e.printStackTrace();
            tv_result.setText(Html.fromHtml("Test <b>AJOUT</b> : <font=red>Échoué</font> (Erreur : " + e.getLocalizedMessage() + ")"));  // Le résultat sera affiché après les traitements.
        }

        // --> Variables à clean : lcDB1, lcDB2, lc1, lc2. Faire une méthode pour.

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
