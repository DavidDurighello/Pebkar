package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.pebkar.Modele.ListeCovoiturage;
import com.app.pebkar.Modele.ListeCovoiturageDB;

import java.util.List;


public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListeCovoiturageDB listeCovoiturageDB = new ListeCovoiturageDB();
        ListView lv_search = (ListView) findViewById(R.id.lv_search);
        List<String> li_search = null;
        try {
            li_search = listeCovoiturageDB.readData();
            for (String lc:li_search) {
                System.out.println(lc);

            }
            ArrayAdapter<String> aa_search;
            aa_search = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, li_search);
            lv_search.setAdapter(aa_search);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
