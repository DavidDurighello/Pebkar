package com.app.pebkar;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("ListeCovoiturage")
public class ListeCovoiturageDB extends ParseObject {
    int idListeCovoiturage;
    String lieudepart;
    String lieuarrivee;
    Date datedepart;
    Date datearrivee;

    public ListeCovoiturageDB(){

    }

    public ListeCovoiturageDB(int idListeCovoiturage, String lieudepart, String lieuarrivee,  Date datedepart, Date datearrivee){
        this.idListeCovoiturage = idListeCovoiturage;
        this.lieudepart = lieudepart;
        this.lieuarrivee = lieuarrivee;
        this.datedepart = datedepart;
        this.datearrivee = datearrivee;
        put("idListeCovoiturage",idListeCovoiturage);
        put("lieudepart",lieudepart);
        put("lieuarrivee",lieuarrivee);
        put("datedepart",datedepart);
        put("datearrivee",datearrivee);
    }

    public int getIdListeCovoiturage() {
        return idListeCovoiturage;
    }

    public void setIdListeCovoiturage(int idListeCovoiturage) {
        this.idListeCovoiturage = idListeCovoiturage;
        put("idListeCovoiturage",idListeCovoiturage);
    }

    public String getLieudepart() {
        return lieudepart;
    }

    public void setLieudepart(String lieudepart) {
        this.lieudepart = lieudepart;
        put("lieudepart",lieudepart);
    }

    public String getLieuarrivee() {
        return lieuarrivee;
    }

    public void setLieuarrivee(String lieuarrivee) {
        this.lieuarrivee = lieuarrivee;
        put("lieuarrivee",lieuarrivee);
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
        put("datedepart",datedepart);
    }

    public Date getDatearrivee() {
        return datearrivee;
    }

    public void setDatearrivee(Date datearrivee) {
        this.datearrivee = datearrivee;
        put("datearrivee",datearrivee);
    }
}
