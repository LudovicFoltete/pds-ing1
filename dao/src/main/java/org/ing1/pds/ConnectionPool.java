package org.ing1.pds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ConnectionPool {

    private static ConnectionPool ourInstance = new ConnectionPool();
    private List<Connection> connectionPool = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();

    private ConnectionPool() {
        int nbConnection = PropertiesLoader.getInstance().getNbConnectionDatabase();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < nbConnection; i++) {
                connectionPool.add(DriverManager.getConnection("jdbc:mysql://192.168.10.2:3306/mall?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "pds", "pds"));
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver don't be found !");
        } catch (SQLException e) {
            e.printStackTrace();
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
