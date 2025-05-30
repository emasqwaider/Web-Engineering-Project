package DataBase;

import classes.Fine;
import controller.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FineReportDAO {

    // Method to get all fines, including due date
    public static List<Fine> getAllFines() throws SQLException {
        String query = "SELECT f.FINEID, f.TRANSACTIONID, f.FINEAMOUNT, f.FINESTATUS, b.DUEDATE " +
                       "FROM Fines f " +
                       "JOIN BORROWTRANSACTIONS b ON f.TRANSACTIONID = b.TRANSACTIONID";
        List<Fine> fines = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fine fine = new Fine();
                fine.setFineId(rs.getInt("FINEID"));
                fine.setTransactionId(rs.getString("TRANSACTIONID"));
                fine.setFineAmount(rs.getDouble("FINEAMOUNT"));
                fine.setFineStatus(rs.getString("FINESTATUS"));
                fine.setDueDate(rs.getDate("DUEDATE"));
                fines.add(fine);
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return fines;
    }

    // Method to get all fines that have been collected (Paid fines)
public static List<Fine> getFinesCollected() throws SQLException {
    // Corrected query with proper column names and aliasing
    String query = "SELECT f.FINEID, f.TRANSACTIONID, f.FINEAMOUNT, f.FINESTATUS,f.PAYMENTDATE " +
                   "FROM Fines f " +
                   "WHERE f.FINESTATUS = 'Paid'";
    
    List<Fine> fines = new ArrayList<>();
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Fine fine = new Fine();
            fine.setFineId(rs.getInt("FINEID"));
            fine.setTransactionId(rs.getString("TRANSACTIONID"));
            fine.setFineAmount(rs.getDouble("FINEAMOUNT"));
            fine.setPaymentDate(rs.getDate("PAYMENTDATE"));
           // fine.setPatronId(rs.getInt("USERID")); // Corrected column alias
            fines.add(fine);
        }
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
    return fines;
}

    // Method to get all unpaid fines
    public static List<Fine> getOutstandingFines() throws SQLException {
        String query = "SELECT FINEID, TRANSACTIONID, FINEAMOUNT, FINESTATUS " +
                       "FROM Fines " +
                       "WHERE FINESTATUS = 'Unpaid'";
        List<Fine> fines = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fine fine = new Fine();
                fine.setFineId(rs.getInt("FINEID"));
                fine.setTransactionId(rs.getString("TRANSACTIONID"));
                fine.setFineAmount(rs.getDouble("FINEAMOUNT"));
                fine.setFineStatus(rs.getString("FINESTATUS"));
                fines.add(fine);
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return fines;
    }

    // Method to get all overdue fines
    public static List<Fine> getOverduePayments() throws SQLException {
        String query = "SELECT f.FINEID, f.TRANSACTIONID, f.FINEAMOUNT, b.DUEDATE " +
                       "FROM Fines f " +
                       "JOIN BORROWTRANSACTIONS b ON f.TRANSACTIONID = b.TRANSACTIONID " +
                       "WHERE f.FINESTATUS = 'Unpaid' AND b.DUEDATE < CURRENT_DATE";
        List<Fine> fines = new ArrayList<>();
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fine fine = new Fine();
                fine.setFineId(rs.getInt("FINEID"));
                fine.setTransactionId(rs.getString("TRANSACTIONID"));
                fine.setFineAmount(rs.getDouble("FINEAMOUNT"));
                fine.setDueDate(rs.getDate("DUEDATE"));
                fines.add(fine);
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return fines;
    }
    
    public static int getActiveBorrowers() throws SQLException {
        String query = "SELECT COUNT(DISTINCT USERID) FROM BORROWTRANSACTIONS " +
                       "WHERE RETURNDATE IS NULL AND DUEDATE >= CURRENT_DATE"; // Active borrowers with unreturned books
        int activeBorrowers = 0;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                activeBorrowers = rs.getInt(1);
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return activeBorrowers;
    }

    // Method to get current book reservations
    public static int getCurrentReservations() throws SQLException {
        String query = "SELECT COUNT(*) FROM BOOK WHERE AVAILABILITY = 'Reserved'";
        int reservedBooks = 0;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                reservedBooks = rs.getInt(1);
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return reservedBooks;
    }
}
