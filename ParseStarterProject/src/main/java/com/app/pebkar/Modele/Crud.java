package com.app.pebkar.Modele;

import java.util.List;

/**
 * Created by David Elykx on 19-11-15.
 */
public interface Crud {
    void createData() throws Exception;
    List readData() throws Exception;
    void updateData() throws Exception;
    void deleteData() throws Exception;
}
