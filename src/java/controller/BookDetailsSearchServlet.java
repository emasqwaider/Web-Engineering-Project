package controller;
import DataBase.BookDAO;
import DataBase.ReviewDAO;
import classes.Book;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import classes.Review;
import jakarta.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/BookDetailsSearchServlet")
public class BookDetailsSearchServlet extends HttpServlet {
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
      
        if (isbn != null && !isbn.isEmpty()) {
            try {
                BookDAO bookDAO = new BookDAO();
                Book books = bookDAO.findByISBN(isbn);
                
                if (books != null) {
                    List<Review> reviews =ReviewDAO.getReviewsByIsbn(isbn);
                    request.setAttribute("books", books);
                    request.setAttribute("reviews", reviews);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("BookDetailsReview.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("message", "Book not found");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("SearchBooksDetails.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookDetailsSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("message", "Please enter a valid ISBN");
            RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
            dispatcher.forward(request, response);
        }
           // RequestDispatcher dispatcher = request.getRequestDispatcher("sucess.jsp");
           // dispatcher.forward(request, response);
      
        
    }
}