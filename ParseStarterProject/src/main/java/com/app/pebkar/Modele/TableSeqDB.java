package com.app.pebkar.Modele;

import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Lyyn on 23-11-15.
 */
public class TableSeqDB implements Crud {

    private TableSeq seq;

    public TableSeqDB() {
        seq = new TableSeq();
    }

    public TableSeqDB(String nomTable, int idseq) {
        seq = new TableSeq(nomTable, idseq);
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
        List<TableSeq> listeSeq;

        // Récupérer l'id de toutes les tables ou d'une seule ?
        if(!table.isEmpty()) query.whereMatches("NomTable", table);

        // PAS DE FINDINBACKGROUND SINON LE CODE NE SE SUIT PLUS ET TOUT EST CASSAY
        listeSeq = query.find();

        return listeSeq;
    }

    @Override
    public void updateData() throws Exception {

    }

    @Override
    public void deleteData() throws Exception {

    }
}
