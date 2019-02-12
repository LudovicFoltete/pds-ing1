package org.ing1.pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

    private static DAO ourInstance = new DAO();
    private PreparedStatement ps;
    private ResultSet rs;

    private DAO() {}

    public static DAO getInstance() {
        return ourInstance;
    }

    public void addShop(String[] values) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ps = connection.prepareStatement("INSERT INTO Shop VALUES(NULL, ?, ?, ?, ?, ?)");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setInt(3, Integer.parseInt(values[2]));
            ps.setString(4, values[3]);
            ps.setString(5, values[4]);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Shop> getShops() {
        ArrayList<Shop> shops = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ps = connection.prepareStatement("SELECT * FROM Shop");
            rs = ps.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop();
                int index = 1;
                shop.setId(rs.getInt(index++));
                shop.setName(rs.getString(index++));
                shop.setCategory(rs.getString(index++));
                shop.setLocation_id(rs.getInt(index++));
                shop.setPhone(rs.getString(index++));
                shop.setEmail(rs.getString(index++));
                shops.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return shops;
    }
}
