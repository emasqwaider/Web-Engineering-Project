package DataBase;

import classes.Admin;
import classes.Librarian;
import classes.Patron;
import classes.User;
import controller.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User validateUser(int id, String password) {
        User user = null;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Users WHERE ID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                String username = rs.getString("username");
                String role = rs.getString("Role");
                

                // Validate password (for production, use a hashing algorithm)
                if (storedPassword.equals(password)) {
                    String email = rs.getString("Email");

                    // Instantiate the appropriate object based on the role
                    switch (role.toLowerCase()) {
                        case "admin":
                            user = new Admin(id, username, email);
                            break;
                        case "librarian":
                            user = new Librarian(id, username, email);
                            break;
                        case "patron":
                            user = new Patron(id, username, email);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown role: " + role);
                    }
                }
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
        
        return user;
    }
}
