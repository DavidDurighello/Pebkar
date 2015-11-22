package com.app.pebkar.Modele;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by David Elykx on 22-11-15.
 */

@ParseClassName("TableSeq")
public class TableSeq extends ParseObject {
    String nomTable;
    int idseq;

    public TableSeq() {
    }

    public TableSeq(String nomTable, int idseq) {
        this.nomTable = nomTable;
        this.idseq = idseq;
        put("NomTable",nomTable);
        put("idseq",idseq);
    }

    public String getNomTable() {
        return nomTable;
    }

    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
        put("NomTable",nomTable);
    }

    public int getIdseq() {
        return idseq;
    }

    public void setIdseq(int idseq) {
        this.idseq = idseq;
        put("idseq",idseq);
    }
}
