package com.app.pebkar.Modele;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by David Elykx on 19-11-15.
 */

@ParseClassName("Notation")
public class Notation extends ParseObject {
    int idNotation;
    float note;
    int nbnotes;

    public Notation() {
    }

    public Notation(int idNotation, float note, int nbnotes) {
        this.idNotation = idNotation;
        this.note = note;
        this.nbnotes = nbnotes;
        put("idNotation",idNotation);
        put("note",note);
        put("nbnotes",nbnotes);
    }

    public int getIdNotation() {
        return idNotation;
    }

    public void setIdNotation(int idNotation) {
        this.idNotation = idNotation;
        put("idNotation",idNotation);
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
        put("note",note);
    }

    public int getNbnotes() {
        return nbnotes;
    }

    public void setNbnotes(int nbnotes) {
        this.nbnotes = nbnotes;
        put("nbnotes",nbnotes);
    }
}
