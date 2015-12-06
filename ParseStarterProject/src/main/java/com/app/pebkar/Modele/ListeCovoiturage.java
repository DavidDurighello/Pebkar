package com.app.pebkar.Modele;

import com.app.pebkar.Tools.StrTools;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("ListeCovoiturage")
public class ListeCovoiturage extends ParseObject {
    Integer idListeCovoiturage;
    String lieudepart;
    String lieuarrivee;
    String lieudepartASCII;
    String lieuarriveeASCII;
    Date datedepart;
    Date datearrivee;
    Integer nbPlaces;

    public ListeCovoiturage(){
    }

    public ListeCovoiturage(String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee, Integer nbPlaces) {
        this.lieudepart = lieudepart;
        this.lieuarrivee = lieuarrivee;
        this.datedepart = datedepart;
        this.datearrivee = datearrivee;
        this.lieudepartASCII = StrTools.removeAccent(this.lieudepart);
        this.lieuarriveeASCII = StrTools.removeAccent(this.lieuarrivee);
        this.nbPlaces = nbPlaces;
        put("lieudepart",lieudepart);
        put("lieuarrivee",lieuarrivee);
        put("datedepart",datedepart);
        put("datearrivee",datearrivee);
        put("lieudepartASCII",lieudepartASCII);
        put("lieuarriveeASCII",lieuarriveeASCII);
        put("nbPlaces",nbPlaces);
    }

    public ListeCovoiturage(int idListeCovoiturage, String lieudepart, String lieuarrivee, Date datedepart, Date datearrivee, Integer nbPlaces){
        this.idListeCovoiturage = idListeCovoiturage;
        this.lieudepart = lieudepart;
        this.lieuarrivee = lieuarrivee;
        this.datedepart = datedepart;
        this.datearrivee = datearrivee;
        this.lieudepartASCII = StrTools.removeAccent(this.lieudepart);
        this.lieuarriveeASCII = StrTools.removeAccent(this.lieuarrivee);
        this.nbPlaces = nbPlaces;
        put("idListeCovoiturage",idListeCovoiturage);
        put("lieudepart",lieudepart);
        put("lieuarrivee",lieuarrivee);
        put("datedepart",datedepart);
        put("datearrivee",datearrivee);
        put("lieudepartASCII",lieudepartASCII);
        put("lieuarriveeASCII",lieuarriveeASCII);
        put("nbPlaces",nbPlaces);
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

    /**
     * Renvoie le format court d'une date, selon la langue de l'appli
     * Les mois et jours seront inversés si en US ou en FR.
     * http://stackoverflow.com/questions/16837923/date-string-from-gregoriancalendar-based-on-locale
     * @param date
     * @return
     */
    public String getBeautifulDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        return DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
    }

    /**
     * Wrapper pour getBeautifulDate
     * @return
     */
    public String getDatearriveeStr() {
        return getBeautifulDate(datearrivee);
    }

    /**
     * Wrapper pour getBeautifulDate
     * @return
     */
    public String getDatedepartStr() {
        return getBeautifulDate(datedepart);
    }

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    /**
     * Quand on crée l'objet via une query, on peut récupérer les infos via get("colonne") mais il est plus aisé d'utiliser des getters classiques
     * Cette méthode mets donc à jour les attributs de l'objet par rapport aux données du ParseObject
     */
    public void updateObject() {
        this.idListeCovoiturage = (Integer) get("idListeCovoiturage");
        this.lieudepart = (String) get("lieudepart");
        this.lieudepartASCII = (String) get("lieudepartASCII");
        this.lieuarrivee = (String) get("lieuarrivee");
        this.lieuarriveeASCII = (String) get("lieuarriveeASCII");
        this.datedepart = (Date) get("datedepart");
        this.datearrivee = (Date) get("datearrivee");
        this.nbPlaces = (Integer) get("nbPlaces");
    }

    @Override
    public String toString() {
        return "ListeCovoiturage{" +
                "idListeCovoiturage=" + this.get("idListeCovoiturage").toString() +
                ", lieudepart='" + this.get("lieudepart").toString() + '\'' +
                ", lieuarrivee='" + this.get("lieuarrivee").toString() + '\'' +
                ", datedepart=" + this.get("datedepart").toString() +
                ", datearrivee=" + this.get("datearrivee").toString() +
                ", nbPlaces=" + this.get("nbPlaces").toString() +

                '}';
    }

    /**
     * Vérifie si l'objet contient quelque chose
     * isEmpty a été utilisé sur les strings car "" n'est pas null
     * @return
     */
    public boolean isEmpty() {
        return (idListeCovoiturage == null && lieudepart.isEmpty() && lieuarrivee.isEmpty() && datedepart == null && datearrivee == null && nbPlaces == null);
    }
}
