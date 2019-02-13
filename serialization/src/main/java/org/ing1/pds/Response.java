package org.ing1.pds;

import java.util.ArrayList;

class Response {

    private String code;
    private ArrayList<Shop> shops;

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    ArrayList<Shop> getShops() {
        return shops;
    }

    void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }
}
