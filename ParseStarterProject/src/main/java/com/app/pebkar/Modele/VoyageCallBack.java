package com.app.pebkar.Modele;

import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.List;

/**
 * Created by Lyyn on 20-11-15.
 */
public class VoyageCallBack implements FindCallback {
    ArrayAdapter<String> arrayAdapter;
    List<String> liRead;

    public VoyageCallBack(List<String> liRead, ArrayAdapter<String> arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
        this.liRead = liRead;
    }

    @Override
    public void done(List listeCovoiturage, ParseException e) {
        if (e == null) {
            for (ListeCovoiturage lc : (List<ListeCovoiturage>) listeCovoiturage) {
                liRead.add(
                        lc.get("idListeCovoiturage").toString() + " "
                                + lc.get("lieudepart").toString() + " "
                                + lc.get("lieuarrivee").toString() + " "
                                + lc.get("datedepart").toString() + " "
                                + lc.get("datearrivee").toString() + " "
                );
                System.out.println("[DEBUG] rD " + lc.get("idListeCovoiturage") + ": " + lc.get("lieudepart") + " " + lc.get("lieuarrivee") + " " + lc.get("datedepart") + " " + lc.get("datearrivee"));
            }
        } else {
            System.out.println(e.getMessage());
        }

        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void done(Object o, Throwable throwable) {

    }
}
