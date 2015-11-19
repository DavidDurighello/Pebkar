package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.app.pebkar.Modele.ListeCovoiturage;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListeCovoiturage lc = new ListeCovoiturage();
        ParseQuery<ListeCovoiturage> query = ParseQuery.getQuery(ListeCovoiturage.class);
        query.whereNotEqualTo("idListeCovoiturage", 0);
        query.findInBackground(new FindCallback<ListeCovoiturage>() {
            @Override
            public void done(List<ListeCovoiturage> listeCovoiturage, ParseException e) {

                ListView lv_search = (ListView) findViewById(R.id.lv_search);
                List<String> li_search = new ArrayList<String>();
                ArrayAdapter<String> aa_search;

                if (e == null) {
                    int i = 0;
                    for (ListeCovoiturage lc : listeCovoiturage) {
                        li_search.add(
                                lc.get("idListeCovoiturage").toString() + " "
                                        + lc.get("lieudepart").toString() + " "
                                        + lc.get("lieuarrivee").toString() + " "
                                        + lc.get("datedepart").toString() + " "
                                        + lc.get("datearrivee").toString() + " "
                        );
                        System.out.println(lc.get("idListeCovoiturage") + ": " + lc.get("lieudepart") + " " + lc.get("lieuarrivee") + " " + lc.get("datedepart") + " " + lc.get("datearrivee"));
                    }
                } else {
                    System.out.println(e.getMessage());
                }

                aa_search = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, li_search);
                lv_search.setAdapter(aa_search);
            }
        });
        /*
        ListView lv_search = (ListView) findViewById(R.id.lv_search);
        List<String> li_search = new ArrayList<String>();
        for(int i = 0; i < 20; i++) {
            li_search.add("test " + ((Integer) i).toString());
            System.out.println(i);
        }
        ArrayAdapter<String> aa_search = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, li_search);
        lv_search.setAdapter(aa_search);
        */
    }
}
