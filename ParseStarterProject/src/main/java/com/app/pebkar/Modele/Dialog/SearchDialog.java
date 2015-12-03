package com.app.pebkar.Modele.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.R;

import java.util.Date;

/**
 * Created by Lyyn on 30-11-15.
 * http://developer.android.com/guide/topics/ui/dialogs.html
 */
public class SearchDialog extends DialogFragment {

    TextView tv_lieuarrivee, tv_lieudepart, tv_titre, tv_datedepart, tv_datearrivee;
    ListeCovoiturage voyage;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("[DEBUG][DIALOG] Créé !");
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
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Oui.
                    }
                });
        /*
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Non.
                    }
                });
*/
        return builder.create();
    }


    public ListeCovoiturage getVoyage() {
        return voyage;
    }

    public void setVoyage(ListeCovoiturage voyage) {
        System.out.println("[DEBUG][Dialog] Voyage set");
        this.voyage = voyage;
    }
}
