package com.app.pebkar.Modele;

import android.widget.ArrayAdapter;

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
        super(idListeCovoiturage, lieudepart, lieuarrivee, datedepart, datearrivee);
    }

    @Override
    public void createData() throws Exception {
        ParseQuery<ParseObject> getNewId = ParseQuery.getQuery("TableSeq");
        getNewId.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    ParseObject parseObject = objects.get(0);
                    int idListeCovoiturage = parseObject.getInt("ListeCovoiturage");
                    parseObject.increment("ListeCovoiturage");
                    parseObject.saveInBackground();
                    ListeCovoiturage listeCovoiturage = new ListeCovoiturage(idListeCovoiturage, lieudepart, lieuarrivee, datedepart, datearrivee);
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
        query.orderByAscending("datedepart");
        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> listeCovoiturage, ParseException e) {

                if (e == null) {
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

    public List readData(String lieudepart) throws Exception {
        final List<String> liRead = new ArrayList<>();
        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);
        query.whereMatches("lieudepart", lieudepart);
        query.whereMatches("lieudepart", lieudepart.toUpperCase());
        query.orderByAscending("datedepart");
        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> listeCovoiturage, ParseException e) {

                if (e == null) {
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

    /**
     * Fallback pour le nouveau readData. On ne filtre pas la recherche ici.
     * @param liLink
     * @param arrayAdapter
     * @return
     * @throws Exception
     */
    public List<String> readData(final List<String> liLink, final ArrayAdapter<String> arrayAdapter) throws Exception {
        return readData(liLink, arrayAdapter, "");
    }

    /**
     * Récupère la liste des covoiturages disponibles, avec ou sans filtre et mets à jour le listview lié à la table après la récup des données en background.
     * @param liLink
     * @param arrayAdapter
     * @param lieudepart
     * @return
     * @throws Exception
     */
    public List<String> readData(final List<String> liLink, final ArrayAdapter<String> arrayAdapter, String lieudepart) throws Exception {
        final List<String> liRead = new ArrayList<>();

        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);

        if(!lieudepart.isEmpty()) {
            //query.whereContains("lieudepart", lieudepart); // Fonctionne mais est sensible à la casse
            query.whereMatches("lieudepart", lieudepart, "i"); // Recherche non sensible à la casse
        }

        query.orderByAscending("datedepart");

        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> listeCovoiturage, ParseException e) {

                if (e == null) {
                    for (ListeCovoiturage lc : listeCovoiturage) {
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

                liLink.clear();
                for (String s : liRead) {
                    liLink.add(s);
                }

                // Si aucune donnée n'a été chargée
                if(liLink.size() == 0) liLink.add("La recherche n'a renvoyé aucun résultat.");

                // Rafraîchir la liste sur l'UI
                arrayAdapter.notifyDataSetChanged();
            }
        });
        return liLink;
    }




    @Override
    public void updateData() throws Exception {
        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);
        query.whereEqualTo("idListeCovoiturage", idListeCovoiturage);
        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> objects, ParseException e) {
                if (e == null) {
                    for(ParseObject object:objects){
                        object.put("lieudepart",lieudepart);
                        object.put("lieuarrivee", lieuarrivee);
                        object.put("datedepart", datedepart);
                        object.put("datearrivee", datearrivee);
                        object.saveInBackground();
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

        @Override
    public void deleteData() throws Exception {
            ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);
            query.whereEqualTo("idListeCovoiturage", idListeCovoiturage);
            query.findInBackground(new FindCallback<ListeCovoiturage>() {
                @Override
                public void done(List<ListeCovoiturage> objects, ParseException e) {
                    if(e == null){
                        for(ParseObject object:objects){
                            object.deleteInBackground();
                        }
                    } else {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }
}
