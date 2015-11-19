package com.app.pebkar;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("Profil")
public class ProfilDB extends ParseObject{
    int idProfil;
    String nom;
    String prenom;
    String adresse;
    String telephone;

    public ProfilDB() {
    }

    public ProfilDB(int idProfil, String nom, String prenom, String adresse, String telephone) {
        this.idProfil = idProfil;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        put("idProfil",idProfil);
        put("nom",nom);
        put("prenom",prenom);
        put("adresse",adresse);
        put("telephone",telephone);
    }

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
        put("idProfil",idProfil);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        put("nom",nom);
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
        put("prenom",prenom);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
        put("adresse",adresse);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        put("telephone",telephone);
    }
}
