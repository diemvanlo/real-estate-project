package service;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DBConnector {
    public static Connection Connection() throws SQLException {
        Connection connection = getConnection("jdbc:sqlserver://localhost:1433;databaseName=javafxbds;", "sa", "songlong");
        return connection;
    }
}
