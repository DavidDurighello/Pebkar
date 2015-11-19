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
                    for(ListeCovoiturageDB lc : listeCovoiturage){
                        System.out.println(lc.getIdListeCovoiturage()+": "+lc.getLieudepart());
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
