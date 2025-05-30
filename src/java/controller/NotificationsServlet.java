package controller;

import DataBase.TransactionDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/NotificationsServlet")
public class NotificationsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role"); // Role: "patron" or "librarian"
        int userId = (int) session.getAttribute("ID");   // User's ID

        TransactionDAO.checkAndUpdateFines(userId);
        
        try (Connection con = DBConnection.getConnection()) {
            
            if ("patron".equalsIgnoreCase(role)) {
                
                
                // For patrons: Show notifications for due dates and overdue books
                String patronQuery = "SELECT b.title, bt.duedate, bt.fineamount,bt.transactionid "
                        + "FROM BORROWTRANSACTIONS bt "
                        + "JOIN BOOKS b ON bt.isbn = b.isbn "
                        + "WHERE bt.userid = ? AND bt.returndate IS NULL";

                try (PreparedStatement ps = con.prepareStatement(patronQuery)) {
                    ps.setInt(1, userId);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<String> notifications = new ArrayList<>();
                    ArrayList<String> notificationColors = new ArrayList<>(); // To hold notification colors

                    while (rs.next()) {
                        String title = rs.getString("title");
                        String transactionid=rs.getString("transactionid");
                        Date dueDate = rs.getDate("duedate");
                        double fine = rs.getDouble("fineamount");
                        

                        // Determine if the book is overdue (if due date has passed)
                        boolean isOverdue = dueDate.before(new Date(System.currentTimeMillis())); // Compare with current date
                        String color = isOverdue ? "red" : "green"; // Set color based on overdue status

                        String message = "Book Title: " + title +" Transaction ID ("+transactionid+ "), Due Date: " + dueDate;
                        if (fine > 0) {
                            message += ", Overdue Fine: $" + fine;
                        }

                        notifications.add(message);
                        notificationColors.add(color); // Add the color to the list
                    }

                    // Set notifications and colors in the request scope
                    request.setAttribute("notifications", notifications);
                    request.setAttribute("notificationColors", notificationColors);
                    request.getRequestDispatcher("PatronPage.jsp").forward(request, response);
                }
            } else if ("librarian".equalsIgnoreCase(role)) {
                // For librarians: Show notifications for pending returns and fines
                String librarianQuery = "SELECT b.title, bt.duedate, bt.fineamount, u.username AS patron_name "
                        + "FROM BORROWTRANSACTIONS bt "
                        + "JOIN BOOKS b ON bt.isbn = b.isbn "
                        + "JOIN USERS u ON bt.userid = u.id "
                        + "WHERE bt.returndate IS NULL";

                try (PreparedStatement ps = con.prepareStatement(librarianQuery)) {
                    ResultSet rs = ps.executeQuery();
                    ArrayList<String> notifications = new ArrayList<>();

                    while (rs.next()) {
                        String title = rs.getString("title");
                        Date dueDate = rs.getDate("duedate");
                        double fine = rs.getDouble("fineamount");
                        String patronName = rs.getString("patron_name");

                        String message = "Patron: " + patronName + ", Book Title: " + title + ", Due Date: " + dueDate;
                        if (fine > 0) {
                            message += ", Overdue Fine: $" + fine;
                        }

                        notifications.add(message);
                    }

                    // Set notifications in the request scope
                    request.setAttribute("notifications", notifications);
                    request.getRequestDispatcher("LibrarianPage.jsp").forward(request, response);
                }
            }

            // Forward to the notification page (JSP)
            // request.getRequestDispatcher("notifications.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while fetching notifications.");
        }
    }
}
