package DataBase;
import classes.Patron;
import classes.Transaction;
import java.sql.*;
import controller.*;
import java.util.ArrayList;
import java.util.List;
public class PatronDAO {

    // Method to add a new patron to the database
    public static void addPatron(Patron patron) throws SQLException {
        String query = "INSERT INTO USERS (ID, USERNAME, PASSWORD, EMAIL,role) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, patron.getUserId());
            stmt.setString(2, patron.getName());
            stmt.setString(3, patron.getPassword());  // Store password directly, but consider hashing in real apps
            stmt.setString(4, patron.getEmail());
            stmt.setString(5, "patron");

            stmt.executeUpdate();
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void removePatron(int patronId) throws SQLException {
        String query = "DELETE FROM users WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patronId); // Set the patron ID to the query
            stmt.executeUpdate(); // Execute the delete query
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Patron searchPatronById(int patronId) throws SQLException {
    Patron patron = null;
    String query = "SELECT * FROM users WHERE ID = ?";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, patronId);  // Set the patron ID
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            patron = new Patron();
            patron.setUserId(rs.getInt("ID"));
            patron.setName(rs.getString("USERNAME"));
            patron.setPassword(rs.getString("PASSWORD"));
            patron.setEmail(rs.getString("EMAIL"));
            patron.setRole(rs.getString("role"));
        }
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
    return patron;
}
    public static boolean update(Patron patron) {
    String updateQuery = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

        stmt.setString(1, patron.getName());
        stmt.setString(2, patron.getPassword());
        stmt.setString(3, patron.getEmail());
        stmt.setInt(4, patron.getUserId());

        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0; // Returns true if one or more rows were updated
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
public static List<Transaction> getBorrowingHistory(int patronId) throws SQLException {
        List<Transaction> borrowingHistory = new ArrayList<>();
        String query = "SELECT * FROM BORROWTRANSACTIONS WHERE userid = ?";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patronId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Transaction borrowing = new Transaction();
                borrowing.setTransactionId(resultSet.getString("TRANSACTIONID"));
                borrowing.setISBN(resultSet.getString("isbn"));
                borrowing.setPatronId(resultSet.getInt("userid"));
                borrowing.setBorrowDate(resultSet.getDate("borrowdate"));
                borrowing.setReturnDate(resultSet.getDate("returndate"));
                borrowing.setDueDate(resultSet.getDate("duedate"));
                borrowing.setFine(resultSet.getInt("fineamount"));
                borrowing.setReturnStatus();
                borrowingHistory.add(borrowing);
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return borrowingHistory;
    }

public boolean isPatron(int userId) throws SQLException {
    String query = "SELECT ROLE FROM USERS WHERE ID = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String role = rs.getString("role");
            return "patron".equalsIgnoreCase(role); 
        }
    }
    return false; 
}



}


