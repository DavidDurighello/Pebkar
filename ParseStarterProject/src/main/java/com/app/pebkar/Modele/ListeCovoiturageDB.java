package com.app.pebkar.Modele;

import android.widget.ArrayAdapter;

import com.app.pebkar.Tools.StrTools;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by David Elykx on 19-11-15.
 */
public class ListeCovoiturageDB implements Crud {
    private ListeCovoiturage voyage;

    public ListeCovoiturageDB() {
    }

    public ListeCovoiturageDB(ListeCovoiturage voyage) {
        this.voyage = voyage;
    }

    public ListeCovoiturageDB(String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee, Integer nbPlaces){
        this.voyage = new ListeCovoiturage(lieudepart, lieuarrivee, datedepart, datearrivee, nbPlaces);
    }

    public ListeCovoiturageDB(int idListeCovoiturage, String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee, Integer nbPlaces){
        this.voyage = new ListeCovoiturage(idListeCovoiturage, lieudepart, lieuarrivee, datedepart, datearrivee, nbPlaces);
    }

    public void saveInBackground() {
        this.voyage.saveInBackground();
    }

    public ListeCovoiturage getVoyage() {
        return voyage;
    }

    public void setVoyage(ListeCovoiturage voyage) {
        this.voyage = voyage;
    }

    /**
     * Vérifie si deux voyages sont les mêmes
     * @param other
     * @return
     */
    public boolean equals(ListeCovoiturageDB other) {
        return (
                this.voyage.getIdListeCovoiturage() == other.getVoyage().getIdListeCovoiturage() &&
                this.voyage.getLieudepart().equals(other.getVoyage().getLieudepart()) &&
                this.voyage.getLieuarrivee().equals(other.getVoyage().getLieuarrivee())
        );
    }

    @Override
    public void createData() throws Exception {
        TableSeqDB tableSeqDB = new TableSeqDB();
        List<TableSeq> Aseq; // Pas mouillé
        TableSeq seq;
        Integer idSeq;

        Aseq = tableSeqDB.readData("ListeCovoiturage");
        //System.out.println("[DEBUG] Aseq size : " + Aseq.size());
        //System.out.println("[DEBUG] Aseq print : " + Aseq.toString());

        seq = Aseq.get(0);
        idSeq = seq.getInt("idseq");
        seq.increment("idseq");
        seq.saveInBackground();
        //System.out.println("[DEBUG] idSeq : " + idSeq);
        this.voyage.setIdListeCovoiturage(idSeq);


        ListeCovoiturageDB listeCovoiturageDB = new ListeCovoiturageDB(voyage);
        System.out.println(listeCovoiturageDB.toString());
        listeCovoiturageDB.saveInBackground();
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

    /**
     * Va remplir l'attribut ListeCovoiturage (objet voyage) de cet objet grâce à l'ID reçu
     * @param id
     * @throws Exception
     */
    public void fillDataFromFilter(Integer id) throws Exception {
        List<ListeCovoiturage> result;
        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);
        query.whereEqualTo("idListeCovoiturage", id);

        result = query.find();
        try {
            this.voyage = result.get(0); // On tente de récupérer l'élément
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère la liste des covoiturages disponibles, avec ou sans filtre et mets à jour le listview lié à la table après la récup des données en background.
     * @param listeSTR
     * @param arrayAdapter
     * @param filtre
     * @return
     * @throws Exception
     */
    public void readData(final List<String> listeSTR, final ArrayAdapter<String> arrayAdapter, final List<ListeCovoiturage> liFinal, ListeCovoiturage filtre) throws Exception {
        final List<ListeCovoiturage> liRead = new ArrayList<>();
        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);

        if(!filtre.isEmpty()) {
            //query.whereContains("lieudepart", lieudepart); // Fonctionne mais est sensible à la casse
            query.whereMatches("lieudepartASCII", StrTools.removeAccent(filtre.getLieudepart()), "i"); // le "i" signifie : Recherche non sensible à la casse
            query.whereMatches("lieuarriveeASCII", StrTools.removeAccent(filtre.getLieuarrivee()), "i");
        }

        query.orderByAscending("datedepart");
        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> listeCovoiturage, ParseException e) {

                if (e == null) {
                    for (ListeCovoiturage lc : listeCovoiturage) {
                        lc.updateObject();
                        liRead.add(lc);
                        System.out.println("[DEBUG] rD " + lc.get("idListeCovoiturage") + ": " + lc.get("lieudepart") + " " + lc.get("lieuarrivee") + " " + lc.get("datedepart") + " " + lc.get("datearrivee"));
                    }
                } else {
                    System.out.println(e.getMessage());
                }

                // On vide la liste en cours pour la remplir des nouvelles données
                listeSTR.clear();
                liFinal.clear();
                for (ListeCovoiturage s : liRead) {
                    liFinal.add(s);
                    listeSTR.add(
                            s.get("idListeCovoiturage").toString() + " "
                                    + s.get("lieudepart").toString() + " "
                                    + s.get("lieuarrivee").toString() + " "
                                    + s.get("datedepart").toString() + " "
                                    + s.get("datearrivee").toString() + " "
                    );
                }

                // Si aucune donnée n'a été chargée
                System.out.println("[DEBUG] La recherche a renvoyé " + listeSTR.size() + " résultats.");
                if (listeSTR.size() == 0) listeSTR.add("La recherche n'a renvoyé aucun résultat.");

                // Rafraîchir la liste sur l'UI
                arrayAdapter.notifyDataSetChanged();
                System.out.println("[DEBUG] liFinal : " + liFinal.size());
            }
        });
        //return liRead;
    }

    @Override
    public void updateData() throws Exception {
        /*
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
        */
    }

    @Override
    public void deleteData() throws Exception {

        /*
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
        */
    }
}
