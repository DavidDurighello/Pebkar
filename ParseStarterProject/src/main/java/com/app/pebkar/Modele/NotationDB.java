package com.app.pebkar.Modele;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Elykx on 20-11-15.
 */
public class NotationDB extends Notation implements Crud {

    public NotationDB() {
    }

    public NotationDB(int idNotation, float note, int nbnotes) {
        super(idNotation, note, nbnotes);
    }

    @Override
    public void createData() throws Exception {
        ParseQuery<ParseObject> getNewId = ParseQuery.getQuery("TableSeq");
        getNewId.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    ParseObject parseObject = objects.get(0);
                    int idNotation = parseObject.getInt("Notation");
                    parseObject.increment("Notation");
                    parseObject.saveInBackground();
                    Notation notation = new Notation(idNotation, note, nbnotes);
                    notation.saveInBackground();
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public List readData() throws Exception {
        final List<String> liRead = new ArrayList<>();
        ParseQuery<Notation> query = ParseQuery.getQuery(Notation.class);
        query.whereNotEqualTo("idListeCovoiturage", 0);
        query.findInBackground(new FindCallback<Notation>() {
            @Override
            public void done(List<Notation> notations, ParseException e) {

                if (e == null) {
                    for (Notation n : notations) {
                        liRead.add(
                                n.get("idNotation").toString() + " "
                                        + n.get("note").toString() + " "
                                        + n.get("nbnotes").toString() + " "
                        );
                        System.out.println(n.get("idNotation") + ": " + n.get("note") + " " + n.get("nbnotes"));
                    }
                } else {
                    System.out.println(e.getMessage());
                }
            }
        });
        return liRead;
    }

    @Override
    public void updateData() throws Exception {

    }

    @Override
    public void deleteData() throws Exception {

    }
}
