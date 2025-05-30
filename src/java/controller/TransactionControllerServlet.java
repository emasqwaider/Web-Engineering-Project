package controller;

import DataBase.ReturnBookDAO;
import classes.Transaction;
import DataBase.TransactionDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/TransactionControllerServlet")
public class TransactionControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession(false); // Avoid creating a new session if it doesn't exist
        if (session == null || session.getAttribute("ID") == null) {
            request.setAttribute("message", "Your session has expired. Please log in again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int patronId = (int) session.getAttribute("ID");
        
        
        if (request.getParameter("action").equals("borrow")) {
            String isbn = request.getParameter("isbn");
        // Check if the patron has already borrowed 3 books
        int borrowedBooks = TransactionDAO.getBorrowedBooksCount(patronId);
        if (borrowedBooks >= (Integer) context.getAttribute("maxBorrowedBooks")) {
           request.setAttribute("message", "You have reached the borrowing limit of "+context.getAttribute("maxBorrowedBooks")+" books.");
           RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowBook.jsp");
           dispatcher.forward(request, response);
            return;
        }

        // Check if the book is available
        if (!TransactionDAO.isBookAvailable(isbn)) {
           // response.getWriter().write("The book is not available for borrowing.");
             request.setAttribute("message", "The book is not available for borrowing.");
           RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowBook.jsp");
           dispatcher.forward(request, response);
            return;
        }

        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setPatronId(patronId);
        transaction.setISBN(isbn);
        transaction.setBorrowDate(Date.valueOf(LocalDate.now()));
        transaction.setDueDate(Date.valueOf(LocalDate.now().plusDays((Integer) context.getAttribute("borrowingPeriodDays")))); 
        transaction.setReturnDate(null); // Not yet returned
        transaction.setReturnStatus(); // Reserved status
        transaction.setTransactionId();

        // Save the transaction in the database
        if (TransactionDAO.addTransaction(transaction)) {
            //response.getWriter().write("Book borrowed successfully!");
           request.setAttribute("message", "Book borrowed successfully! Your Transaction ID  ("+transaction.getTransactionId()+")");
           RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowBook.jsp");
           dispatcher.forward(request, response);
            
        } else {
            //response.getWriter().write("Failed to borrow the book. Please try again.");
            request.setAttribute("message", "Failed to borrow the book. Please try again.");
           RequestDispatcher dispatcher = request.getRequestDispatcher("BorrowBook.jsp");
           dispatcher.forward(request, response);
        }
        
        }else if (request.getParameter("action").equals("return")) {
            
            String transactionId = request.getParameter("transactionid");
            int Id = Integer.parseInt(request.getParameter("patronid"));
            
            LocalDate returnDate = LocalDate.now(); // Assuming book is returned today, or get from the request
            
            // Retrieve the transaction from the database
            Transaction transaction = TransactionDAO.getTransaction(transactionId, Id);

            // If transaction is found, log the return
            if (transaction != null) {
                // Ensure return date is correctly set and fine is calculated
                transaction.setReturnDate(Date.valueOf(returnDate)); // Set the return date
                transaction.calculateFine(); // Calculate any late fee
                transaction.setReturnStatus(); // Update status based on return date

                // Call ReturnBookDAO to log the return and update the transaction
                
                boolean returnSuccess = ReturnBookDAO.processReturn(transaction);

                // Update response message based on return success
                if (returnSuccess) {
                    request.setAttribute("message", "Book returned successfully! Fine: $" + transaction.getFine());
                } else {
                    request.setAttribute("message", "Failed to return the book. Please try again.");
                }
            } else {
                request.setAttribute("message", "Transaction not found or you're not authorized to return this book.");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("Returnbook.jsp");
            dispatcher.forward(request, response);
            
     }
    }
}
