/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateConfigServlet", urlPatterns = {"/UpdateConfigServlet"})

public class UpdateConfigServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();

        // Retrieve updated values from the form
        double fineRate = Double.parseDouble(request.getParameter("fineRate"));
        int maxBorrowedBooks = Integer.parseInt(request.getParameter("maxBorrowedBooks"));
        int borrowingPeriodDays = Integer.parseInt(request.getParameter("borrowingPeriodDays"));

        // Update the context attributes
        context.setAttribute("fineRate", fineRate);
        context.setAttribute("maxBorrowedBooks", maxBorrowedBooks);
        context.setAttribute("borrowingPeriodDays", borrowingPeriodDays);
        

        // Redirect back to the configuration page
        response.sendRedirect("LibraryConfig.jsp");
    }
}
