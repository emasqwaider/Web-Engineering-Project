package DataBase;

import java.sql.*;
import classes.Transaction;
import controller.DBConnection;
public class TransactionDAO {


    // Method to insert a new transaction
    public static boolean addTransaction(Transaction transaction) {
    String queryTransaction = "INSERT INTO BORROWTRANSACTIONS (transactionid, userid, borrowdate, duedate, returndate, fineamount, isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String queryUpdateBook = "UPDATE BOOKS SET availability = 'Reserved' WHERE isbn = ?";
    String queryFines = "INSERT INTO FINES (Fineid,TRANSACTIONID, FINEAMOUNT, PAYMENTDATE) VALUES (?,?, ?, ?)";  // Corrected here

    try (Connection con = DBConnection.getConnection()) {
        // Start a transaction to ensure both queries execute successfully
        con.setAutoCommit(false);
        
        try (PreparedStatement psTransaction = con.prepareStatement(queryTransaction);
             PreparedStatement psUpdateBook = con.prepareStatement(queryUpdateBook);
             PreparedStatement psFines = con.prepareStatement(queryFines)) {
            
            // Set values for the transaction query
            psTransaction.setString(1, transaction.getTransactionId());
            psTransaction.setInt(2, transaction.getPatronId());
            psTransaction.setDate(3, transaction.getBorrowDate());
            psTransaction.setDate(4, transaction.getDueDate());
            psTransaction.setDate(5, transaction.getReturnDate());
            psTransaction.setDouble(6, transaction.getFine());
            psTransaction.setString(7, transaction.getISBN());
            
            // Set values for the book availability update
            psUpdateBook.setString(1, transaction.getISBN());
            
            // Set values for the fines query
            psFines.setInt(1,(int)(Math.random() * 1000000));
            psFines.setString(2, transaction.getTransactionId());
            psFines.setDouble(3, transaction.getFine());
            psFines.setDate(4, null); // Set to null if payment hasn't been made yet. Otherwise, you can set the actual payment date.
            
            // Execute the queries
            int transactionResult = psTransaction.executeUpdate();
            int bookUpdateResult = psUpdateBook.executeUpdate();
            int finesResult = psFines.executeUpdate();
            
            // Commit the transaction if all queries succeed
            if (transactionResult > 0 && bookUpdateResult > 0 && finesResult > 0) {
                con.commit();
                return true;
            } else {
                con.rollback(); // Rollback if any of the queries fail
                return false;
            }
        } catch (SQLException e) {
            con.rollback(); // Rollback in case of an exception
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return false;
}


    // Method to get the count of books currently borrowed by a patron
    public static int getBorrowedBooksCount(int patronId) {
       String query = "SELECT COUNT(*) FROM BORROWTRANSACTIONS WHERE userid = ? AND returndate IS NULL";
        try (Connection con = DBConnection.getConnection())
        {
             PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, patronId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // Method to check if a book is available
    public static boolean isBookAvailable(String isbn) {
        String query = "SELECT availability FROM BOOKS WHERE isbn = ?";

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Check if the availability status is 'Available'
                return "Available".equals(rs.getString("availability"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    
  public static void checkAndUpdateFines(int userID) {
    String queryTransactions = "SELECT transactionid, userid, borrowdate, duedate, returndate, fineamount, isbn FROM BORROWTRANSACTIONS WHERE returndate IS NULL AND userid = ?";
    String queryUpdateFine = "UPDATE FINES SET FINEAMOUNT = ?, PAYMENTDATE = NULL WHERE TRANSACTIONID = ?";
    String queryUpdateTransaction = "UPDATE BORROWTRANSACTIONS SET fineamount = ? WHERE transactionid = ?";
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement psTransactions = con.prepareStatement(queryTransactions);
         PreparedStatement psUpdateFine = con.prepareStatement(queryUpdateFine);
         PreparedStatement psUpdateTransaction = con.prepareStatement(queryUpdateTransaction)) {
        
        psTransactions.setInt(1, userID);
        ResultSet rs = psTransactions.executeQuery();

        while (rs.next()) {
            // Retrieve transaction details
            String transactionId = rs.getString("transactionid");
            Date borrowDate = rs.getDate("borrowdate");
            Date dueDate = rs.getDate("duedate");
            Date returnDate = rs.getDate("returndate");
            double fineAmount = rs.getDouble("fineamount");
            String isbn = rs.getString("isbn");

            // Use the calculateFine() method
            Transaction transaction = new Transaction();
            transaction.setBorrowDate(borrowDate);
            transaction.setDueDate(dueDate);
            transaction.setReturnDate(null); // Since the book hasn't been returned yet
            

            // Retrieve the calculated fine
            fineAmount = transaction.getFine();

            // Update the fine in the FINES table
            psUpdateFine.setDouble(1, fineAmount);
            psUpdateFine.setString(2, transactionId);
            psUpdateFine.executeUpdate();

            // Update the fine in the BORROWTRANSACTIONS table
            psUpdateTransaction.setDouble(1, fineAmount);
            psUpdateTransaction.setString(2, transactionId);
            psUpdateTransaction.executeUpdate();
        }
    } catch (SQLException e) {
        System.out.println("im");
    }
}
  
  public static Transaction getTransaction(String transactionId, int patronId) {
        Transaction transaction = null;

        String query = "SELECT bt.transactionId, bt.userid, bt.isbn, bt.borrowdate, bt.duedate, bt.returndate, bt.fineamount " +
               "FROM BORROWTRANSACTIONS bt " +
               "WHERE bt.transactionId = ? AND bt.userid = ?";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set parameters
            ps.setString(1, transactionId);
            ps.setInt(2, patronId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Create a Transaction object and populate its fields
                    transaction = new Transaction();
                    transaction.setTransactionId(rs.getString("transactionId"));
                    transaction.setPatronId(rs.getInt("userid"));
                    transaction.setISBN(rs.getString("isbn"));
                    transaction.setBorrowDate(rs.getDate("borrowdate"));
                    transaction.setDueDate(rs.getDate("duedate"));
                    transaction.setFine(rs.getDouble("fineamount"));
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transaction;
    }

}
