package com.app.pebkar.Modele;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Elykx on 03-12-15.
 */
public class PassagersDB implements Crud {
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


    /**
     * Vérifie si un user est déjà enregistré dans un voyage donné
     * @param userID
     * @param voyageID
     * @return
     */
    public static boolean isUserDansVoyage(String userID, String voyageID) {
        ParseQuery<Passagers> query = ParseQuery.getQuery(Passagers.class);
        List<Passagers> result;
        Log.e("[isUserDansVoyage]", userID + " ~ " + voyageID);
        query.whereEqualTo("profil", userID);
        query.whereEqualTo("listecovoiturage", voyageID);

        try {
            result = query.find();
            if(result.size() == 0) {
                return false;
            }
            Log.e("[isUserDansVoyage]",  "result.size : " + result.size());
            return true;
        }
        // Renvoie une exception si aucun résultat trouvé
        catch(Exception e) { return false; }
    }

    public static ArrayList readDataForCurrentUser(ParseUser currentUser) throws Exception {
        final ArrayList<Passagers> liRead = new ArrayList<>();
        ProfilDB profilDB = new ProfilDB();
        try {
            profilDB.getProfilFromUser(currentUser);


            ParseQuery<Passagers> query = ParseQuery.getQuery(Passagers.class);
            query.whereEqualTo("profil", profilDB.getProfil().getObjectId());

            query.findInBackground(new FindCallback<Passagers>() {
                @Override
                public void done(List<Passagers> lpassagers, ParseException e) {

                    if (e == null) {
                        for (Passagers p : lpassagers) {
                            liRead.add(p);
                            System.out.println("Recupération passagers"+p.toString());
                        }
                    } else {
                        System.out.println(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("[DEBUG] Récupération du profil échouée");
            e.printStackTrace();
        }
        return liRead;
    }
}
