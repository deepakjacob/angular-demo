package com.dj.ngapp.test.model;


import com.dj.ngapp.core.model.AppModel;

public class Model extends AppModel {

    private String first;

    private String last;

    private String email;


    public Model() {
    }


    public Model(int id, String first, String last, String email) {
        super(id);
        this.first = first;
        this.last = last;
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
