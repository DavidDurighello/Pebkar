package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        Toast.makeText(this, "Test (" + index + ") : " + listeCovoiturage.get(index).toString() + "\n\nSize : " + listeCovoiturage.size(), Toast.LENGTH_SHORT).show();

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
        //System.out.println("[DEBUG] Filtre : " + filtre.toString());

        try {
            listSearch.clear();
            listeCovoiturage.clear();
            listSearch.add("Chargement des données en cours...");
            arrayAdapterSearch.notifyDataSetChanged();

            System.out.println("[DEBUG] ListeCovoiturage size : " + listeCovoiturage.size());
            listeCovoiturageDB.readData(listSearch, arrayAdapterSearch, listeCovoiturage, filtre);
            System.out.println("[DEBUG] ListeCovoiturage size : " + listeCovoiturage.size());
        }
        catch (Exception e) {
            listSearch.clear();
            listeCovoiturage.clear();
            listSearch.add("Erreur lors du chargement des données");
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

    public void btn_count(View view) {
        Toast.makeText(this, "Count : " + this.listSearch.size(), Toast.LENGTH_SHORT).show();
    }
    public void testList(View view) {
        Toast.makeText(this, "Count : " + this.listSearch.size(), Toast.LENGTH_SHORT).show();
    }
}
