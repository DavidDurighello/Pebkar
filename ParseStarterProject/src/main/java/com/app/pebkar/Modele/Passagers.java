package com.app.pebkar.Modele;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("Passagers")
public class Passagers extends ParseObject{
    String profil;
    String listecovoiturage;
    boolean estConducteur;

    public Passagers() {

    }

    public Passagers(String profil, String listecovoiturage, boolean estConducteur) {
        this.profil = profil;
        this.listecovoiturage = listecovoiturage;
        this.estConducteur = estConducteur;
        put("profil",profil);
        put("listecovoiturage",listecovoiturage);
        put("estConducteur",estConducteur);
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
        put("profil",profil);
    }

    public String getListecovoiturage() {
        return listecovoiturage;
    }

    public void setListecovoiturage(String listecovoiturage) {
        this.listecovoiturage = listecovoiturage;
        put("listecovoiturage",listecovoiturage);
    }

    public boolean isEstConducteur() {
        return estConducteur;
    }

    public void setEstConducteur(boolean estConducteur) {
        this.estConducteur = estConducteur;
        put("estConducteur",estConducteur);
    }
}
