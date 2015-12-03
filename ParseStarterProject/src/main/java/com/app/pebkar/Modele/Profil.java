package com.app.pebkar.Modele;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("Profil")
public class Profil extends ParseObject{
    String nom;
    String prenom;
    String adresse;
    String telephone;
    String user;

    public Profil() {
    }


    public Profil(String nom, String prenom, String adresse, String telephone, String user) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.user = user;
        put("nom",nom);
        put("prenom",prenom);
        put("adresse",adresse);
        put("telephone",telephone);
        put("user",user);
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

    public String getUser() {
        return user;
    }

    public void setUser(String parent) {
        this.user = parent;
        put("user",parent);
    }
}
