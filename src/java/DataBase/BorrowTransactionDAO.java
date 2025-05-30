package DataBase;


import classes.Transaction;
import controller.DBConnection;
import static controller.DBConnection.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowTransactionDAO {

    // Select transactions for a specific user
    public List<Transaction> selectAllTransactions(int userId) {
        String SELECT_TRANSACTIONS_BY_USER = "SELECT * FROM BORROWTRANSACTIONS WHERE USERID = ?";
        List<Transaction> transactions = new ArrayList<>();
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTIONS_BY_USER)) {
             
            // Set the userId parameter in the SQL query
            preparedStatement.setInt(1, userId);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            // Iterate through the result set and create BorrowTransaction objects
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getString("TRANSACTIONID"));
                 
                transaction.setPatronId(rs.getInt("USERID"));
                 
                transaction.setBorrowDate(rs.getDate("BORROWDATE"));
              
                transaction.setDueDate(rs.getDate("DUEDATE"));
                  
                transaction.setReturnDate(rs.getDate("RETURNDATE"));
             
                transaction.setFine(rs.getDouble("FINEAMOUNT"));
             
                transaction.setISBN(rs.getString("ISBN")); // Set the ISBN value
               
                transactions.add(transaction);
                  
            }
              
            
        } catch (SQLException e) {
            System.err.println("Error fetching transactions for user ID: " + userId);
            e.printStackTrace();
        }
        return transactions;
    }
}