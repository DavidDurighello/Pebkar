package com.app.pebkar.Modele;

import java.util.List;

/**
 * Created by David Elykx on 19-11-15.
 */
public interface Crud {
    public void createData() throws Exception;
    public List readData() throws Exception;
    public void updateData() throws Exception;
    public void deleteData() throws Exception;
}
