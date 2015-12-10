package com.app.pebkar.Modele;

import android.util.Log;

import com.facebook.Profile;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by David Elykx on 03-12-15.
 */
public class ProfilDB implements Crud {
    private Profil profil;

    public ProfilDB() {
        this.profil = new Profil();
    }

    public ProfilDB(Profil profil) {
        this.profil = profil;
    }

    @Override
    public void createData() throws Exception {

    }

    @Override
    public List readData() throws Exception {
        return null;
    }

    @Override
    public void updateData() throws Exception {

    }

    @Override
    public void deleteData() throws Exception {

    }


    public void getProfilFromUser(ParseUser currentUser) throws Exception {
        List<Profil> result;
        ParseQuery<Profil> query = ParseQuery.getQuery(Profil.class);
        query.whereEqualTo("user", currentUser.getObjectId());

        result = query.find();
        try {
            this.profil = result.get(0); // On tente de récupérer l'élément
            this.profil.updateObject();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupérer un profil lié à l'ID FB
     * @param FBProfile Le profile FB récupéré via la connexion FB
     * @return true si profil trouvé, false si profil introuvable
     */
    public boolean getProfilFromFB(Profile FBProfile) {
        List<Profil> result;
        ParseQuery<Profil> query = ParseQuery.getQuery(Profil.class);
        query.whereEqualTo("FBid", FBProfile.getId());

        Log.e("[FBProfile]", FBProfile.toString());

        try {
            result = query.find();
            Log.e("Debug", "Result size : " + result.size());
            for(Object o: result) {
                Log.e("Result object", o.toString());
            }
            this.profil = result.get(0);
            this.profil.updateObject();
            return true;
        }
        catch(Exception e) {
            // Aucun résultat, on doit créer l'objet dans la bdd
            Log.e("getProfilFromFB : catch", "Aucun résultat trouvé > on crée l'user");
            this.profil = new Profil(FBProfile.getLastName(), FBProfile.getFirstName(), "", "", ParseUser.getCurrentUser().getUsername(), FBProfile.getId());
            try {
                this.profil.save();
            }
            catch(Exception ex) { /* @TODO Parse n'a pas su sauvegarder (server inaccessible) > Gérer ce cas */ }
            return false;
        }

    }

    public void logOut() {
        this.profil = new Profil();
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return this.profil.toString();
    }
}
