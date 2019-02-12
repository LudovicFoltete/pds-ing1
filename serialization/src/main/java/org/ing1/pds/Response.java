package org.ing1.pds;

import java.util.ArrayList;

public class Response {

    private String entity;
    private ArrayList<Shop> javaBeans;


    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public ArrayList<Shop> getJavaBeans() {
        return javaBeans;
    }

    public void setJavaBeans(ArrayList<Shop> javaBeans) {
        this.javaBeans = javaBeans;
    }
}
