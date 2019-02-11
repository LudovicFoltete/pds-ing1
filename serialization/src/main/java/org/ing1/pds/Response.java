package org.ing1.pds;

import java.util.ArrayList;

public class Response {

    private String entity;
    private ArrayList<Object> javaBeans;


    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public ArrayList<Object> getJavaBeans() {
        return javaBeans;
    }

    public void setJavaBeans(ArrayList<Object> javaBeans) {
        this.javaBeans = javaBeans;
    }
}
