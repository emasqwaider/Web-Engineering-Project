package controller;

import DataBase.BorrowTransactionDAO;

import classes.Transaction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/BorrowTransactionServlet")

public class BorrowTransactionServlet extends HttpServlet {

    private BorrowTransactionDAO borrowTransactionDAO;

    public BorrowTransactionServlet() {
        super();
    }

    public void init() {
        borrowTransactionDAO = new BorrowTransactionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve userId from session (assuming user is logged in and session exists)
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID");

        if (userId == null) {
            // If userId is not found in session, redirect to login page
            response.sendRedirect("index.html");
            return;
        }

        try {
            // Get the transactions for the specific user
            List<Transaction> transactions = borrowTransactionDAO.selectAllTransactions(userId);
            // Set the transactions as a request attribute to pass to the JSP
            // Check if the list is empty
            if (transactions.isEmpty()) {
                response.getWriter().println("No transactions found for User ID: " + userId);
            } else {
                // Process transactions (e.g., set as request attribute or display them)
                request.setAttribute("transactions", transactions);
            }
            request.setAttribute("transactions", transactions);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error retrieving transactions.");
        }

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowReview.jsp");
        dispatcher.forward(request, response);
    }
}
