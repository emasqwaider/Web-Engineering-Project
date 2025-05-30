package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "1234");
            }
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection error", e);
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null; // Reset the connection reference
        }
    }
}
