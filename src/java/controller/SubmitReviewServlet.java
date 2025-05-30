package controller;
import DataBase.ReviewDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import classes.Review;
import jakarta.servlet.annotation.WebServlet;
import java.util.Date;

@WebServlet("/SubmitReviewServlet")
public class SubmitReviewServlet extends HttpServlet {
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("bookisbn");
        Double rating = Double.parseDouble(request.getParameter("rating"));
        String reviewText = request.getParameter("reviewText");

        // Get user ID from session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID");

        if (userId == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not logged in
            return;
        }

        try {
            int newReviewId = ReviewDAO.getNextReviewId();  // Fetch next ID
            // Create Review object and set its fields
            Review review = new Review();
            review.setReviewId(newReviewId);  // Set the generated review ID
            review.setUserId(userId);
            review.setRating(rating);
            review.setReviewText(reviewText);  
            review.setIsbn(isbn);

            // Save to the database
            ReviewDAO.saveReview(review);  // Insert the review into the database
            request.setAttribute("message", "Review added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error adding review: " + e.getMessage());
        }

        // Forward to the page to show a success or error message
        RequestDispatcher dispatcher = request.getRequestDispatcher("sucess.jsp");
        dispatcher.include(request, response);  
    }
}