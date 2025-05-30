package DataBase;

import classes.SharedBook;
import controller.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SharedBookDAO {
    private static final Logger LOGGER = Logger.getLogger(SharedBookDAO.class.getName());

    public List<SharedBook> getSharedBooksReceived(int receiverId) throws SQLException {
    List<SharedBook> sharedBooks = new ArrayList<>();
    String query = "SELECT * FROM SharedBooks WHERE receiverId = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, receiverId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            SharedBook sharedBook = new SharedBook();
            sharedBook.setShareId(rs.getInt("shareId"));
            sharedBook.setSenderId(rs.getInt("senderId"));
            sharedBook.setReceiverId(rs.getInt("receiverId"));
            sharedBook.setIsbn(rs.getString("isbn"));
            sharedBook.setSharedMessage(rs.getString("sharedMessage"));
            sharedBook.setSharedDate(rs.getTimestamp("shareDate"));
            sharedBooks.add(sharedBook);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error retrieving shared books: " + e.getMessage());
    }
    return sharedBooks;
}
    
    
    
    
    public boolean isIsbnExists(String isbn) throws Exception {
        String query = "SELECT * FROM Books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving ISBN '" + isbn + "': {0}", e.getMessage());
            throw new SQLException("Error retrieving ISBN '" + isbn + "': " + e.getMessage(), e);
        }
    }

    public void createSharedBook(SharedBook sharedBook) throws Exception {
        String query = "INSERT INTO SharedBooks (shareId, senderId, receiverId, isbn, sharedMessage, shareDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, sharedBook.getShareId());
            stmt.setInt(2, sharedBook.getSenderId());
            stmt.setInt(3, sharedBook.getReceiverId());
            stmt.setString(4, sharedBook.getIsbn());
            stmt.setString(5, sharedBook.getSharedMessage());
            stmt.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating shared book: {0}", e.getMessage());
            throw new SQLException("Error creating shared book: " + e.getMessage(), e);
        }
    }

    public static int getNextSharedId() throws Exception {
        int nextId = 1;
        String query = "SELECT MAX(shareId) FROM SharedBooks";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving next shared ID: {0}", e.getMessage());
            throw new SQLException("Error retrieving next shared ID: " + e.getMessage(), e);
        }
        return nextId;
    }
}