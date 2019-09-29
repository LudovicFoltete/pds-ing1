package org.ing1.pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOImpl implements DAO {

    private static DAOImpl ourInstance = new DAOImpl();
    private PreparedStatement ps;
    private ResultSet rs;
    private String code;

    private DAOImpl() {}

    static DAOImpl getInstance() {
        return ourInstance;
    }

    public void insertShop(String[] values) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ps = connection.prepareStatement("INSERT INTO Shop VALUES(NULL, ?, ?, ?, ?, ?)");
            ps.setString(1, values[0]);
            ps.setString(2, values[1]);
            ps.setString(3, values[2]);
            ps.setString(4, values[3]);
            ps.setString(5, values[4]);
            ps.executeUpdate();
            code = "100";
        } catch (SQLException e) {
            e.printStackTrace();
            code = "400";
        } finally {
            this.closeStatement(connection);
        }
    }

    public ArrayList<Shop> getShops() {
        ArrayList<Shop> shops = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ps = connection.prepareStatement("SELECT Shop.id, Shop.name, category, location_id, max_desired, min_desired, area FROM Shop LEFT JOIN Location ON Shop.id = Location.id");
            rs = ps.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop();
                Location location = new Location();
                int index = 1;
                shop.setId(rs.getInt(index++));
                shop.setName(rs.getString(index++));
                shop.setCategory(rs.getString(index++));
                location.setId(rs.getInt(index++));
                shop.setMax_area(rs.getInt(index++));
                shop.setMin_area(rs.getInt(index++));
                location.setArea(rs.getInt(index++));
                shop.setLocation(location);
                shops.add(shop);
            }
            code = "100";
        } catch (SQLException e) {
            e.printStackTrace();
            code = "400";
        } finally {
            this.closeStatement(connection);
        }
        return shops;
    }

    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ps = connection.prepareStatement("SELECT id, area FROM Location");
            rs = ps.executeQuery();
            while (rs.next()) {
                Location location = new Location();
                int index = 1;
                location.setId(rs.getInt(index++));
                location.setArea(rs.getInt(index++));
                locations.add(location);
            }
            code = "100";
        } catch (SQLException e) {
            e.printStackTrace();
            code = "400";
        } finally {
            this.closeStatement(connection);
        }
        return locations;
    }

    public String getCode() {
        return code;
    }

    private void closeStatement(Connection c) {
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
        //we release the connection to the pool
        if(!ConnectionPool.getInstance().releaseConnection(c))
            System.err.println("Connection was not released");
    }
}
