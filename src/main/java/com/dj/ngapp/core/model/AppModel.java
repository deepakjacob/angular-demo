package com.dj.ngapp.core.model;

import java.io.Serializable;

/**
 * Superclass for all model objects.
 */
public class AppModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    public AppModel() {

    }

    public AppModel(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    private void setId(final int id) {
        this.id = id;
    }

}
