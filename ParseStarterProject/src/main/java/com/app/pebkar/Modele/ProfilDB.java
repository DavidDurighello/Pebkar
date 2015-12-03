package com.app.pebkar.Modele;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by David Elykx on 03-12-15.
 */
public class ProfilDB implements Crud {
    private Profil profil;

    public ProfilDB() {
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
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
