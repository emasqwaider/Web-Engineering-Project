<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database Setup</title>
</head>
<body>
    <h1>Database Setup</h1>
    <%
        String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Replace with your database URL
        String user = "system"; // Replace with your database username
        String password = "1234"; // Replace with your database password

        Connection conn = null;
        Statement stmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Create a SQL statement
            stmt = conn.createStatement();

            // Create the 'users' table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                    "username VARCHAR(255) UNIQUE NOT NULL, " +
                                    "password VARCHAR(255) NOT NULL, " +
                                    "email VARCHAR(255) NOT NULL" +
                                    ")";
            stmt.executeUpdate(createTableSQL);

            out.println("<p>Table 'users' has been created successfully!</p>");
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                out.println("<p>Error closing connection: " + ex.getMessage() + "</p>");
            }
        }
    %>
</body>
</html>
