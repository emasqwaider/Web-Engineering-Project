package controller;
import DataBase.LibraryStatisticsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LibraryStatisticsServlet")
public class LibraryStatisticsServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch statistics from the DAO
            int activeBorrowers = LibraryStatisticsDAO.getActiveBorrowersCount();
            int currentReservations = LibraryStatisticsDAO.getCurrentReservationsCount();

            // Set attributes to pass to JSP
            request.setAttribute("activeBorrowers", activeBorrowers);
            request.setAttribute("currentReservations", currentReservations);

            
            request.getRequestDispatcher("LibraryUsageStatistics.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving library statistics.");
        }
    }
}