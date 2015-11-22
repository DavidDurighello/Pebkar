package com.app.pebkar.Test;

import com.app.pebkar.Modele.ListeCovoiturageDB;

import java.util.Date;

/**
 * Created by Lyyn on 22-11-15.
 */
public class TestLC  {

    public static void main(String args[]) {

        ListeCovoiturageDB lc1, lc2;
        Date datedepart, datearrivee;

        try {
            System.out.println("--- Test Ajout ---");

            datedepart = new Date();
            datearrivee = new Date();
            datearrivee.setTime(datedepart.getTime() + 3600000);

            lc1 = new ListeCovoiturageDB("Ville1", "Ville2", datedepart, datearrivee);
            lc1.createData();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
