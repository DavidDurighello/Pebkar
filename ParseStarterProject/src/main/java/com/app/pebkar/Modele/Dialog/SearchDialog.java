package com.app.pebkar.Modele.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.Modele.Passagers;
import com.app.pebkar.Modele.PassagersDB;
import com.app.pebkar.Modele.ProfilDB;
import com.app.pebkar.R;
import com.facebook.Profile;

/**
 * Created by Lyyn on 30-11-15.
 * http://developer.android.com/guide/topics/ui/dialogs.html
 */
public class SearchDialog extends DialogFragment {

    TextView tv_lieuarrivee, tv_lieudepart, tv_titre, tv_datedepart, tv_datearrivee;
    Profile FBProfile;
    ListeCovoiturage voyage;
    public static ListeCovoiturage voyagestatic;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Récupérer l'inflater de Layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_search, null);
        tv_titre = (TextView) view.findViewById(R.id.tv_d_search_title);
        tv_lieuarrivee = (TextView) view.findViewById(R.id.tv_d_search_lieuarrivee);
        tv_lieudepart = (TextView) view.findViewById(R.id.tv_d_search_lieudepart);
        tv_datedepart = (TextView) view.findViewById(R.id.tv_d_search_datedepart);
        tv_datearrivee = (TextView) view.findViewById(R.id.tv_d_search_datearrivee);

        if(savedInstanceState == null){

            if(voyage != null) {
                tv_titre.append(" #" + voyage.getIdListeCovoiturage());
                tv_lieudepart.setText(voyage.getLieudepart());
                tv_lieuarrivee.setText(voyage.getLieuarrivee());
                tv_datedepart.setText(voyage.getDatedepartStr());
                tv_datearrivee.setText(voyage.getDatearriveeStr());
            }
        } else {

            tv_titre.setText(savedInstanceState.getString("tv_titre"));
            tv_lieudepart.setText(savedInstanceState.getString("tv_lieudepart"));
            tv_lieuarrivee.setText(savedInstanceState.getString("tv_lieuarrivee"));
            tv_datedepart.setText(savedInstanceState.getString("tv_datedepart"));
            tv_datearrivee.setText(savedInstanceState.getString("tv_datearrivee"));
            voyage = voyagestatic;
        }

        builder.setView(view);
        FBProfile = Profile.getCurrentProfile();

        // User non connecté à FB ?
        if(FBProfile == null) {
            builder.setPositiveButton(this.getString(R.string.ok), null);
        }
        else {
            builder.setPositiveButton(this.getString(R.string.inscription), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (voyage.getNbPlaces() > 0) {
                        System.out.println("[DEBUG] " + voyage.getNbPlaces());

                        //Récupération du profil lié à l'utilisateur
                        ProfilDB profilDB = new ProfilDB();

                        try {
                            //profilDB.getProfilFromUser(ParseUser.getCurrentUser());
                            profilDB.getProfilFromFB(FBProfile);
                            Log.e("[SearchDialog - 67]", profilDB.toString());
                        } catch (Exception e) {
                            Log.e("[DEBUG]", "Récupération du profil échouée");
                            e.printStackTrace();
                        }

                        try {
                            Passagers passagers = new Passagers(profilDB.getProfil().getObjectId(), voyage.getObjectId(), false);

                            // Vérification : un user ne peut participer qu'à un seul voyage
                            if (PassagersDB.isUserDansVoyage(profilDB.getProfil().getObjectId(), voyage.getObjectId())) {
                                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getResources().getText(R.string.inscriptionDejaFaite), Toast.LENGTH_SHORT).show();
                            }
                            else {
                                passagers.saveInBackground();
                                voyage.increment("nbPlaces", -1);
                                voyage.saveInBackground();
                                voyage.setNbPlaces(voyage.getNbPlaces() - 1);
                                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getResources().getText(R.string.inscriptionValidee), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("[DEBUG]", "Ajout du passager échoué");
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getResources().getText(R.string.notEnoughPlaces), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton(this.getString(R.string.retourner), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        }
        // Inflate et set le layout pour le dialog
        // Pass null as the parent view because its going in the dialog layout

        return builder.create();
    }

    /**
     * Gestion de l'orientation en sauvegardant les données
     * @param bundle
     */
    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("tv_titre", tv_titre.getText().toString());
        bundle.putString("tv_lieuarrivee", tv_lieuarrivee.getText().toString());
        bundle.putString("tv_lieudepart", tv_lieudepart.getText().toString());
        bundle.putString("tv_datedepart", tv_datedepart.getText().toString());
        bundle.putString("tv_datearrivee", tv_datearrivee.getText().toString());
        voyagestatic = voyage;
    }

    public ListeCovoiturage getVoyage() {
        return voyage;
    }

    public void setVoyage(ListeCovoiturage voyage) {
        this.voyage = voyage;
    }
}
