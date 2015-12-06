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
    String FBid;

    public Profil() {
    }


    public Profil(String nom, String prenom, String adresse, String telephone, String user, String FBid) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.user = user;
        this.FBid = FBid;
        put("nom", nom);
        put("prenom",prenom);
        put("adresse",adresse);
        put("telephone",telephone);
        put("user",user);
        put("FBid", FBid);
    }

    /**
     * Quand on crée l'objet via une query, on peut récupérer les infos via get("colonne") mais il est plus aisé d'utiliser des getters classiques
     * Cette méthode mets donc à jour les attributs de l'objet par rapport aux données du ParseObject
     */
    public void updateObject() {
        this.nom = (String) get("nom");
        this.prenom = (String) get("prenom");
        this.adresse = (String) get("adresse");
        this.telephone = (String) get("telephone");
        this.user = (String) get("user");
        this.FBid = (String) get("FBid");
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

    public String getFBid() {
        return FBid;
    }

    public void setFBid(String FBid) {
        this.FBid = FBid;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", user='" + user + '\'' +
                ", FBid='" + FBid + '\'' +
                '}';
    }
}
