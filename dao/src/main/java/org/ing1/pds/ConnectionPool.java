package org.ing1.pds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ConnectionPool {

    private static ConnectionPool ourInstance = new ConnectionPool(2);
    private List<Connection> connectionPool = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();

    private ConnectionPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connectionPool.add(DriverManager.getConnection("jdbc:mysql://192.168.10.2:3306/mall", "pds", "pds"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Driver don't be found !");
            }
        }
    }

    static ConnectionPool getInstance() {
        return ourInstance;
    }

    Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }
}
