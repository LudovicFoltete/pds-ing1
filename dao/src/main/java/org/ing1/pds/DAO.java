package org.ing1.pds;

import java.util.ArrayList;

public interface DAO {

    void insertShop(String[] values);

    ArrayList<Shop> getShops();

    String getCode();
}
