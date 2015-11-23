package com.app.pebkar.Modele;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lyyn on 23-11-15.
 */
public class TableSeqDB extends TableSeq implements Crud {

    public TableSeqDB() {
        super();
    }

    public TableSeqDB(String nomTable, int idseq) {
        super(nomTable, idseq);
    }

    @Override
    public void createData() throws Exception {

    }

    @Override
    public List readData() throws Exception {
        return readData("");
    }

    public List readData(String table) throws Exception {
        ParseQuery<TableSeq> query = ParseQuery.getQuery(TableSeq.class);
        final List<TableSeq> listeSeq = new ArrayList<TableSeq>();

        // Récupérer l'id de toutes les tables ou d'une seule ?
        if(!table.isEmpty()) query.whereMatches("NomTable", table);

        query.findInBackground(new FindCallback<TableSeq>() {
            @Override
            public void done(List<TableSeq> objects, ParseException e) {
                listeSeq.add((TableSeq) objects);
            }
        });
        return listeSeq;
    }

    @Override
    public void updateData() throws Exception {

    }

    @Override
    public void deleteData() throws Exception {

    }
}
