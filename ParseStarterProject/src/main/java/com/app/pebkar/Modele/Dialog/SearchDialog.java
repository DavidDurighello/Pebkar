package com.app.pebkar.Modele.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.Modele.Passagers;
import com.app.pebkar.Modele.ProfilDB;
import com.app.pebkar.R;
import com.parse.ParseUser;

/**
 * Created by Lyyn on 30-11-15.
 * http://developer.android.com/guide/topics/ui/dialogs.html
 */
public class SearchDialog extends DialogFragment {

    TextView tv_lieuarrivee, tv_lieudepart, tv_titre, tv_datedepart, tv_datearrivee;
    ListeCovoiturage voyage;

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

        if(voyage != null) {
            tv_titre.append(" #" + voyage.getIdListeCovoiturage());
            tv_lieudepart.setText(voyage.getLieudepart());
            tv_lieuarrivee.setText(voyage.getLieuarrivee());
            tv_datedepart.setText(voyage.getDatedepartStr());
            tv_datearrivee.setText(voyage.getDatearriveeStr());
            tv_lieudepart.setText(voyage.getLieudepart());
        }


        // Inflate et set le layout pour le dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setPositiveButton(this.getString(R.string.inscription), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (voyage.getNbPlaces() > 0) {
                            System.out.println("[DEBUG] "+voyage.getNbPlaces());

                            //Récupération du profil lié à l'utilisateur
                            ProfilDB profilDB = new ProfilDB();
                            try {
                                profilDB.getProfilFromUser(ParseUser.getCurrentUser());
                            } catch (Exception e) {
                                System.out.println("[DEBUG] Récupération du profil échouée");
                                e.printStackTrace();
                            }

                            try {
                                Passagers passagers = new Passagers(profilDB.getProfil().getObjectId(), voyage.getObjectId(), false);
                                passagers.saveInBackground();
                                voyage.increment("nbPlaces", -1);
                                voyage.saveInBackground();
                                voyage.setNbPlaces(voyage.getNbPlaces()-1);
                                Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getResources().getText(R.string.inscriptionValidee), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                System.out.println("[DEBUG] Ajout du passager échoué");
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), getActivity().getApplicationContext().getResources().getText(R.string.notEnoughPlaces), Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .setNegativeButton(this.getString(R.string.retourner), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }


    public ListeCovoiturage getVoyage() {
        return voyage;
    }

    public void setVoyage(ListeCovoiturage voyage) {
        this.voyage = voyage;
    }
}
