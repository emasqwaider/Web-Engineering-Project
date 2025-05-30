package DataBase;

import controller.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryStatisticsDAO {
    
    // Fetch the count of active borrowers
    public static int getActiveBorrowersCount() throws SQLException {
        String query = "SELECT COUNT(DISTINCT UserId) AS ActiveBorrowers FROM BORROWTRANSACTIONS WHERE RETURNDATE IS NULL";

        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("ActiveBorrowers");
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("No Active Borrowers found"); // Debug log
        return 0;
    }

    // Fetch the count of current book reservations
    public static int getCurrentReservationsCount() throws SQLException {
        String query = "SELECT COUNT(*) AS CurrentReservations FROM BorrowTransactions WHERE ReturnDate IS NULL ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("CurrentReservations");
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}