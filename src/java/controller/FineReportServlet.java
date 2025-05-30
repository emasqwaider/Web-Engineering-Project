package controller;

import classes.Fine;
import DataBase.FineReportDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/FineReportServlet")
public class FineReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection connection = DBConnection.getConnection()) {

            if ("viewReports".equals(action)) {
                // Fetch all fines
                List<Fine> allFines = FineReportDAO.getAllFines() ;
                request.setAttribute("fineReports", allFines);

                // Fetch fines collected
                List<Fine> finesCollected = FineReportDAO.getFinesCollected();
                request.setAttribute("finesCollected", finesCollected);

                // Fetch outstanding fines
                List<Fine> outstandingFines = FineReportDAO.getOutstandingFines();
                request.setAttribute("outstandingFines", outstandingFines);

                // Fetch overdue payments
                List<Fine> overduePayments = FineReportDAO.getOverduePayments();
                request.setAttribute("overduePayments", overduePayments);

                // Forward data to JSP
                request.getRequestDispatcher("FineReports.jsp").forward(request, response);
            }
//             else if ("libraryUsageStats".equals(action)) {
//                // Fetch library usage statistics
//                int activeBorrowers = FineReportDAO.getActiveBorrowers();
//                int currentReservations = FineReportDAO.getCurrentReservations();
//
//                // Set statistics as request attributes
//                request.setAttribute("activeBorrowers", activeBorrowers);
//                request.setAttribute("currentReservations", currentReservations);
//
//                // Forward to the library usage statistics JSP
//                request.getRequestDispatcher("LibraryUsageStats.jsp").forward(request, response);
//            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, " error occurred while processing the request.");
        }
        
        
    }
}
