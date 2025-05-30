package DataBase;

import classes.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DBConnection;

 public class BookDAO {

    // Method to add a book to the database
    public static void addBook(Book book) throws SQLException {
    String query = "INSERT INTO BOOKS (TITLE, AUTHOR, ISBN, GENRE, AVAILABILITY, YEARPUBLISHED) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getIsbn());
        stmt.setString(4, book.getGenre());
        stmt.setString(5, book.getAvailability());
        stmt.setInt(6, book.getYearOfPublication()); // Adding yearOfPublication
        stmt.executeUpdate();
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}


    // Method to update a book in the database
    public static void updateBook(String isbn, Book book) throws SQLException {
    String query = "UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, GENRE = ?, AVAILABILITY = ? WHERE ISBN = ?";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        // Set the parameters to update
        stmt.setString(1, book.getTitle());        // Set the book title
        stmt.setString(2, book.getAuthor());       // Set the author name
        stmt.setString(3, book.getGenre());        // Set the genre
        stmt.setString(4, book.getAvailability()); // Set the availability
        stmt.setString(5, isbn);                   // Use ISBN to identify the book
        
        // Execute the update query
        stmt.executeUpdate();                      // Update the book details in the database
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}



    // Method to delete a book from the database
    public static void deleteBook(String isbn) throws SQLException {
        String query = "DELETE FROM BOOKS WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve all books from the database
    public static List<Book> getAllBooks() throws SQLException {
    List<Book> books = new ArrayList<>();
    // Modify the query to order by YEARPUBLISHED
    String query = "SELECT * FROM BOOKS ORDER BY YEARPUBLISHED";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Book book = new Book();
            book.setTitle(rs.getString("TITLE"));
            book.setAuthor(rs.getString("AUTHOR"));
            book.setIsbn(rs.getString("ISBN"));
            book.setGenre(rs.getString("GENRE"));
            book.setYearOfPublication(rs.getInt("YEARPUBLISHED"));
            book.setAvailability(rs.getString("Availability"));
            books.add(book);
        }
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return books;
}


    // Method to search for books by a query string
    public static List<Book> searchBooks(String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM BOOKS WHERE TITLE LIKE ? OR AUTHOR LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                   // book.setBookId(rs.getInt("BOOK_ID"));
                    book.setTitle(rs.getString("TITLE"));
                    book.setAuthor(rs.getString("AUTHOR"));
                    book.setIsbn(rs.getString("ISBN"));
                    book.setGenre(rs.getString("GENRE"));
                    book.setYearOfPublication(rs.getInt("YEARPUBLISHED"));
                    book.setAvailability(rs.getString("Availability"));
                    books.add(book);
                }
            }
        }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return books;
    }
    
    public static Book findByISBN(String isbn) throws SQLException {
    String query = "SELECT * FROM Books WHERE ISBN = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, isbn);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setIsbn(rs.getString("ISBN"));
                book.setGenre(rs.getString("Genre"));
                book.setAvailability(rs.getString("Availability"));
                book.setRating(rs.getDouble("AVERAGERATING"));
                return book;
            }
        }
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return null; // Return null if no book is found
}
    public static List<Book> BookAVA() throws SQLException {
    List<Book> bookList = new ArrayList<>();
    String query = "SELECT TITLE, AVAILABILITY FROM BOOKS"; // Fetch only the required columns

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Book book = new Book();
            book.setTitle(rs.getString("TITLE"));
            book.setAvailability(rs.getString("AVAILABILITY"));
            bookList.add(book);
        }
    }finally {
            try {
                DBConnection.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return bookList;
}

}
