package com.app.pebkar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        ListeCovoiturageDB lc = new ListeCovoiturageDB();
        ParseQuery<ListeCovoiturageDB> query = ParseQuery.getQuery(ListeCovoiturageDB.class);
        query.whereNotEqualTo("idListeCovoiturage",0);
        query.findInBackground(new FindCallback<ListeCovoiturageDB>() {
            @Override
            public void done(List<ListeCovoiturageDB> listeCovoiturage, ParseException e) {
                if(e==null){
                    int i = 0;
                    for(ListeCovoiturageDB lc : listeCovoiturage){
                        System.out.println(lc.get("idListeCovoiturage")+": "+lc.get("lieudepart")+" "+lc.get("lieuarrivee")+" "+lc.get("datedepart")+" "+lc.get("datearrivee"));
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
