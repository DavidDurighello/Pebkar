package com.app.pebkar.Modele;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by David Elykx on 19-11-15.
 */
public class ListeCovoiturageDB extends ListeCovoiturage implements Crud {

    public ListeCovoiturageDB() {
    }

    public ListeCovoiturageDB(int idListeCovoiturage, String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee){
        super(idListeCovoiturage,lieudepart, lieuarrivee, datedepart, datearrivee);
    }

    @Override
    public void createData() throws Exception {
        ParseQuery<ParseObject> getNewId = ParseQuery.getQuery("TableSeq");
        getNewId.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    ParseObject parseObject = objects.get(0);
                    int idListeCovoiturage = parseObject.getInt("idListeCovoiturage");
                    parseObject.increment("idListeCovoiturage");
                    parseObject.saveInBackground();
                    ListeCovoiturage listeCovoiturage = new ListeCovoiturage(idListeCovoiturage,lieudepart,lieuarrivee,datedepart,datearrivee);
                    listeCovoiturage.saveInBackground();
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public List readData() throws Exception {
        final List<String> liRead = new ArrayList<>();
        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);
        query.whereNotEqualTo("idListeCovoiturage", 0);
        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> listeCovoiturage, ParseException e) {

                if (e == null) {
                    int i = 0;
                    for (ListeCovoiturage lc : listeCovoiturage) {
                        liRead.add(
                                lc.get("idListeCovoiturage").toString() + " "
                                        + lc.get("lieudepart").toString() + " "
                                        + lc.get("lieuarrivee").toString() + " "
                                        + lc.get("datedepart").toString() + " "
                                        + lc.get("datearrivee").toString() + " "
                        );
                        System.out.println(lc.get("idListeCovoiturage") + ": " + lc.get("lieudepart") + " " + lc.get("lieuarrivee") + " " + lc.get("datedepart") + " " + lc.get("datearrivee"));
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
        return liRead;
    }

    @Override
    public void updateData() throws Exception {

    }

    @Override
    public void deleteData() throws Exception {

    }
}
