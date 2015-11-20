package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.app.pebkar.Modele.ListeCovoiturageDB;

import java.util.List;


public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListeCovoiturageDB listeCovoiturageDB = new ListeCovoiturageDB();
        ListView listViewSearch = (ListView) findViewById(R.id.lv_search);
        List<String> listSearch = null;
        try {
            listSearch = listeCovoiturageDB.readData();
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
}
