/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "LibraryConfigServlet", urlPatterns = {"/LibraryConfigServlet"})
public class LibraryConfigServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ServletContext context = getServletContext();
//
//        // Retrieve configuration values
//        double fineRate = (Double) context.getAttribute("fineRate");
//        int maxBorrowedBooks = (Integer) context.getAttribute("maxBorrowedBooks");
//        int borrowingPeriodDays = (Integer) context.getAttribute("borrowingPeriodDays");
//
//        // Send response
//        response.setContentType("text/html");
//        response.getWriter().println("<h1>Library System Configuration</h1>");
//        response.getWriter().println("<p>Fine Rate: $" + fineRate + " per day</p>");
//        response.getWriter().println("<p>Max Borrowed Books: " + maxBorrowedBooks + "</p>");
//        response.getWriter().println("<p>Borrowing Period: " + borrowingPeriodDays + " days</p>");
    }
}