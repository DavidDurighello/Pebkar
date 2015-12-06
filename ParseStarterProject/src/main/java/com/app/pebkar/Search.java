package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.app.pebkar.Modele.Dialog.SearchDialog;
import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.Modele.ListeCovoiturageDB;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {

    private ListView listViewSearch;
    private ListeCovoiturageDB listeCovoiturageDB;
    public List<String> listSearch;
    public List<ListeCovoiturage> listeCovoiturage;
    public ArrayAdapter<String> arrayAdapterSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listSearch = new ArrayList<String>();
        listeCovoiturage = new ArrayList<>();
        listViewSearch = (ListView) findViewById(R.id.lv_search);
        arrayAdapterSearch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSearch);
        listViewSearch.setAdapter(arrayAdapterSearch);

        chargerVoyages();

        listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showInfo(l);
            }
        });
    }

    private void showInfo(Long i) {
        int index = i.intValue();
        for(ListeCovoiturage l: listeCovoiturage) {
            System.out.println(l.get("lieudepart"));
        }

        // Dialog
        SearchDialog details = new SearchDialog();
        details.setVoyage(listeCovoiturage.get(index));
        details.show(getFragmentManager(), "details");
    }

    /**
     * Récupère tous les voyages et les stocke dans listSearch
     * Méthode utilisée également pour la recherche
     */
    protected void chargerVoyages() {
        ListeCovoiturage filtre = new ListeCovoiturage();
        listeCovoiturageDB = new ListeCovoiturageDB();

        filtre.setLieudepart(((EditText) findViewById(R.id.et_ville)).getText().toString()); // Ville de départ recherchée
        filtre.setLieuarrivee(((EditText) findViewById(R.id.et_ville2)).getText().toString());

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

    /**
     * Recharge la liste avec les nouveaux critères de recherche
     * @param view
     */
    public void btn_filtre(View view) {
        chargerVoyages();
    }
}
