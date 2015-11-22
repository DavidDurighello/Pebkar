package com.app.pebkar.Modele;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("ListeCovoiturage")
public class ListeCovoiturage extends ParseObject {
    Integer idListeCovoiturage;
    String lieudepart;
    String lieuarrivee;
    Date datedepart;
    Date datearrivee;

    public ListeCovoiturage(){

    }

    public ListeCovoiturage(String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee) {
        this.lieudepart = lieudepart;
        this.lieuarrivee = lieuarrivee;
        this.datedepart = datedepart;
        this.datearrivee = datearrivee;
    }

    public ListeCovoiturage(int idListeCovoiturage, String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee){
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

    @Override
    public String toString() {
        return "ListeCovoiturage{" +
                "idListeCovoiturage=" + idListeCovoiturage +
                ", lieudepart='" + lieudepart + '\'' +
                ", lieuarrivee='" + lieuarrivee + '\'' +
                ", datedepart=" + datedepart +
                ", datearrivee=" + datearrivee +
                '}';
    }

    /**
     * Vérifie si l'objet contient quelque chose
     * isEmpty a été utilisé sur les strings car "" n'est pas null
     * @return
     */
    public boolean isEmpty() {
        return (idListeCovoiturage == null && lieudepart.isEmpty() && lieuarrivee.isEmpty() && datedepart == null && datearrivee == null);
    }
}
