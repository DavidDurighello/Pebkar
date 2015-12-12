package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pebkar.Modele.Dialog.SearchDialog;
import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.Modele.ListeCovoiturageDB;
import com.app.pebkar.Tools.NetTools;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {

    private ListView listViewSearch;
    private ListeCovoiturageDB listeCovoiturageDB;
    private TextView et_ville, et_ville2;
    public List<String> listSearch;
    public List<ListeCovoiturage> listeCovoiturage;
    public ArrayAdapter<String> arrayAdapterSearch;
    public static List<ListeCovoiturage> listeCovoiturageStatic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listSearch = new ArrayList<String>();
        listeCovoiturage = new ArrayList<>();
        listViewSearch = (ListView) findViewById(R.id.lv_search);
        et_ville = (TextView) findViewById(R.id.et_ville);
        et_ville2 = (TextView) findViewById(R.id.et_ville2);

        // Si on restaure la vue (suite à un changement d'orientation)
        if(savedInstanceState != null) {
            et_ville.setText(savedInstanceState.getString("et_ville"));
            et_ville2.setText(savedInstanceState.getString("et_ville2"));
            listSearch = new ArrayList<String>(savedInstanceState.getStringArrayList("listSearch"));

            // On récupère la liste et on la réaffiche
            for(ListeCovoiturage l: listeCovoiturageStatic) {
                listeCovoiturage.add(l);
            }
        }

        arrayAdapterSearch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSearch);
        listViewSearch.setAdapter(arrayAdapterSearch);

        // Premier chargement de l'activité
        if(savedInstanceState == null) chargerVoyages();


        listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showInfo(l);
            }
        });
    }

    /**
     * Gestion de l'orientation en sauvegardant les données
     * @param bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("et_ville", et_ville.getText().toString());
        bundle.putString("et_ville2", et_ville2.getText().toString());
        bundle.putStringArrayList("listSearch", (ArrayList<String>) listSearch);
        listeCovoiturageStatic = new ArrayList<>(listeCovoiturage);
    }

    private void showInfo(Long i) {
        int index = i.intValue();

        try {

            // Dialog
            SearchDialog details = new SearchDialog();
            details.setVoyage(listeCovoiturage.get(index));
            details.show(getFragmentManager(), "details");
        }
        catch(Exception e) {
            if(!NetTools.checkConnection(this)) {
                Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Récupère tous les voyages et les stocke dans listSearch
     * Méthode utilisée également pour la recherche
     */
    protected void chargerVoyages() {
        ListeCovoiturage filtre = new ListeCovoiturage();
        listeCovoiturageDB = new ListeCovoiturageDB();

        filtre.setLieudepart(et_ville.getText().toString()); // Ville de départ recherchée
        filtre.setLieuarrivee(et_ville2.getText().toString());

        // Vérifier qu'on dispose d'une connexion
        if(NetTools.checkConnection(this)) {

            try {
                listSearch.clear();
                listeCovoiturage.clear();
                listSearch.add(getString(R.string.d_search_loading));
                arrayAdapterSearch.notifyDataSetChanged();

                listeCovoiturageDB.readData(listSearch, arrayAdapterSearch, listeCovoiturage, filtre);
            }
            catch (Exception e) {
                listSearch.clear();
                listeCovoiturage.clear();
                listSearch.add(getString(R.string.d_search_error));
                arrayAdapterSearch.notifyDataSetChanged();

                e.printStackTrace();
            }
        }

        else {
            listSearch.clear();
            listeCovoiturage.clear();
            listSearch.add(getString(R.string.no_connection));
            arrayAdapterSearch.notifyDataSetChanged();
        }
    }

    /**
     * Recharge la liste avec les nouveaux critères de recherche
     * @param view
     */
    public void btn_filtre(View view) {
        chargerVoyages();
    }
}
