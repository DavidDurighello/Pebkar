package com.app.pebkar.Modele;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("Passagers")
public class Passagers extends ParseObject{
    int idProfil;
    int idListeCovoiturage;
    boolean estConducteur;

    public Passagers() {

    }

    public Passagers(int idProfil, int idListeCovoiturage, boolean estConducteur) {
        this.idProfil = idProfil;
        this.idListeCovoiturage = idListeCovoiturage;
        this.estConducteur = estConducteur;
        put("idProfil",idProfil);
        put("idListeCovoiturage",idListeCovoiturage);
        put("estConducteur",estConducteur);
    }

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
        put("idProfil",idProfil);
    }

    public int getIdListeCovoiturage() {
        return idListeCovoiturage;
    }

    public void setIdListeCovoiturage(int idListeCovoiturage) {
        this.idListeCovoiturage = idListeCovoiturage;
        put("idListeCovoiturage",idListeCovoiturage);
    }

    public boolean isEstConducteur() {
        return estConducteur;
    }

    public void setEstConducteur(boolean estConducteur) {
        this.estConducteur = estConducteur;
        put("estConducteur",estConducteur);
    }
}
