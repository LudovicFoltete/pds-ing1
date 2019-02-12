package org.ing1.pds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static ConnectionPool ourInstance = new ConnectionPool(2);
    private String url = "jdbc:mysql://192.168.10.2:3306/mall";
    private String user = "pds";
    private String password = "pds";
    private List<Connection> connectionPool = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();

    private ConnectionPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(DRIVER_NAME);
                connectionPool.add(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println(DRIVER_NAME + " does not exist");
            }
        }
    }

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
}
