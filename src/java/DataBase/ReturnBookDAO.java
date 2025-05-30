package DataBase;
import controller.*;
import classes.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookDAO {

    public static boolean processReturn(Transaction transaction) {
        boolean isSuccess = false;

        // SQL Queries
        String updateTransactionQuery = "UPDATE BORROWTRANSACTIONS " +
                                        "SET RETURNDATE = ?, FINEAMOUNT = ? " +
                                        "WHERE TRANSACTIONID = ?";
        String updateBooksQuery = "UPDATE BOOKS " +
                                  "SET AVAILABILITY = 'Available' " +
                                  "WHERE ISBN = ?";
        String checkFineExistsQuery = "SELECT COUNT(*) AS FINE_COUNT FROM FINES WHERE TRANSACTIONID = ?";
        String updateFineQuery = "UPDATE FINES SET FINEAMOUNT = ?, FINESTATUS = 'Paid',Paymentdate=? WHERE TRANSACTIONID = ?";
        

        try (Connection con = DBConnection.getConnection()) {
            // Begin transaction
            con.setAutoCommit(false);

            // Update BORROWTRANSACTIONS table
            try (PreparedStatement psTransaction = con.prepareStatement(updateTransactionQuery)) {
                psTransaction.setDate(1, new java.sql.Date(transaction.getReturnDate().getTime()));
                psTransaction.setDouble(2, transaction.getFine());
                psTransaction.setString(3, transaction.getTransactionId());
                psTransaction.executeUpdate();
            }

            // Update BOOKS table
            try (PreparedStatement psBooks = con.prepareStatement(updateBooksQuery)) {
                psBooks.setString(1, transaction.getISBN());
                psBooks.executeUpdate();
            }

            // Check if a fine already exists for this transaction
            boolean fineExists = false;
            try (PreparedStatement psCheckFine = con.prepareStatement(checkFineExistsQuery)) {
                psCheckFine.setString(1, transaction.getTransactionId());
                ResultSet rs = psCheckFine.executeQuery();
                if (rs.next() && rs.getInt("FINE_COUNT") > 0) {
                    fineExists = true;
                }
            }

            // Update or Insert into FINES table
            if (fineExists) {
                try (PreparedStatement psUpdateFine = con.prepareStatement(updateFineQuery)) {
                    psUpdateFine.setDouble(1, transaction.getFine());
                    psUpdateFine.setDate(2, new java.sql.Date(transaction.getReturnDate().getTime()));
                    psUpdateFine.setString(3, transaction.getTransactionId());
                    
                    psUpdateFine.executeUpdate();
                }
            } 

            // Commit transaction
            con.commit();
            isSuccess = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}