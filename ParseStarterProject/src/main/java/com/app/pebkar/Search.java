package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        List<String> tempList = new ArrayList<>();

        listeCovoiturageDB = new ListeCovoiturageDB();
        String rech_ville = ((EditText) findViewById(R.id.et_ville)).getText().toString(); // Ville recherchée
        System.out.println("[DEBUG] rech_ville : " + rech_ville);

        try {

            if(rech_ville.isEmpty()) {
                //listSearch = listeCovoiturageDB.readData();
                listSearch.add("Test1");
                listeCovoiturageDB.readData(listSearch, arrayAdapterSearch);

                listSearch.add("Test2");
            }
            else {
                listSearch = listeCovoiturageDB.readData(listSearch, arrayAdapterSearch, rech_ville);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //arrayAdapterSearch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSearch);
        //listViewSearch.setAdapter(arrayAdapterSearch);
        //arrayAdapterSearch.notifyDataSetChanged();
    }

    /*
    public void btn_filtre(View view){
        EditText editTextSearch = (EditText) findViewById(R.id.et_ville);
        ListeCovoiturageDB listeCovoiturageDB = new ListeCovoiturageDB();
        ListView listViewSearch = (ListView) findViewById(R.id.lv_search);
        List<String> listSearch = null;
        try {
            listSearch = listeCovoiturageDB.readData(editTextSearch.getText().toString());
            for (String lc:listSearch) {
                System.out.println(lc);

            }
            ArrayAdapter<String> arrayAdapterSearch;
            arrayAdapterSearch = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, listSearch);
            listViewSearch.setAdapter(arrayAdapterSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    public void btn_filtre(View view) {
        chargerVoyages();
    }

    public void btn_count(View view) {
        System.out.println("[DEBUG] List count : " + listSearch.size());
    }
}
