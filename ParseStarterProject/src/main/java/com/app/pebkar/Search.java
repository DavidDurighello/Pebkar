package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pebkar.Modele.ListeCovoiturageDB;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {

    private ListView listViewSearch;
    private ListeCovoiturageDB listeCovoiturageDB;
    public List<String> listSearch;
    public ArrayAdapter<String> arrayAdapterSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listSearch = new ArrayList<String>();
        listViewSearch = (ListView) findViewById(R.id.lv_search);
        arrayAdapterSearch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSearch);
        listViewSearch.setAdapter(arrayAdapterSearch);

        chargerVoyages();
    }

    /**
     * Récupère tous les voyages et les stocke dans listSearch
     * Méthode utilisée également pour la recherche
     */
    protected void chargerVoyages() {
        listeCovoiturageDB = new ListeCovoiturageDB();
        String rech_ville = ((EditText) findViewById(R.id.et_ville)).getText().toString(); // Ville recherchée
        System.out.println("[DEBUG] rech_ville : " + rech_ville);

        try {
            listSearch.clear();
            listSearch.add("Chargement des données en cours...");
            arrayAdapterSearch.notifyDataSetChanged();

            if(rech_ville.isEmpty()) {
                listeCovoiturageDB.readData(listSearch, arrayAdapterSearch);
            }
            else {
                listSearch = listeCovoiturageDB.readData(listSearch, arrayAdapterSearch, rech_ville);
            }
        } catch (Exception e) {
            listSearch.clear();
            listSearch.add("Erreur lors du chargement des données");
            arrayAdapterSearch.notifyDataSetChanged();

            e.printStackTrace();
        }
    }

    public void btn_filtre(View view) {
        chargerVoyages();
    }

    public void btn_count(View view) {
        Toast.makeText(this, "[DEBUG] List count : " + listSearch.size(), Toast.LENGTH_SHORT).show();
    }
}
