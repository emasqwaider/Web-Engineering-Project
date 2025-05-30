package DataBase;

import classes.Review;
import controller.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
   
    // Get the next available review ID
    public static int getNextReviewId() throws Exception {
        int nextId = 1;  // Default to 1 if no records exist
        Connection conn = DBConnection.getConnection();
        String query = "SELECT MAX(REVIEWID) FROM REVIEWS";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            nextId = rs.getInt(1) + 1;  // Increment the max ID
        }
        rs.close();
        ps.close();
        return nextId;
    }

    // Save review to the database
    public static void saveReview(Review review) throws SQLException {
        String query = "INSERT INTO REVIEWS (REVIEWID, USERID, REVIEWTEXT, RATING, REVIEWDATE, ISBN) VALUES (?, ?, ?, ?, ?, ?)";
        String updateBookQuery = "UPDATE BOOKS " +
                              "SET AVERAGERATING = (SELECT AVG(RATING) FROM REVIEWS WHERE ISBN = ?) " +
                              "WHERE ISBN = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Generate review ID if not set
            int reviewId = (review.getReviewId() > 0) ? review.getReviewId() : getNextReviewId();
            
            stmt.setInt(1, reviewId);
            stmt.setInt(2, review.getUserId());
            stmt.setString(3, review.getReviewText());
            stmt.setDouble(4, review.getRating());
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(6, review.getIsbn());
            
            stmt.executeUpdate();
            
            try (PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery)) {
                updateStmt.setString(1, review.getIsbn());
                 updateStmt.setString(2, review.getIsbn());
                 updateStmt.executeUpdate();
        }catch (Exception e) {
           e.printStackTrace();
        }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error saving review: " + e.getMessage());
        }
    }
    
    public static List<Review> getReviewsByIsbn(String isbn) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM REVIEWS WHERE ISBN = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("REVIEWID"));
                review.setUserId(rs.getInt("USERID"));
                review.setReviewText(rs.getString("REVIEWTEXT"));
                review.setRating(rs.getDouble("RATING"));
                review.setReviewDate(rs.getDate("REVIEWDATE"));
                review.setIsbn(rs.getString("ISBN"));
                reviews.add(review);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving reviews for ISBN: " + e.getMessage());
        }
        return reviews;
    }
}