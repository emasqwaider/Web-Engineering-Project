package controller;

import DataBase.PatronDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.SharedBook;
import DataBase.SharedBookDAO;

@WebServlet("/ShareBooksServlet")
public class ShareBooksServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
      
        SharedBookDAO dao = new SharedBookDAO();
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("ID");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if (action != null) {
            try {
                switch (action) {
                    case "share":
                        handleShareAction(request, response, dao, userId);
                        break;

                    case "view":
                        handleViewAction(request, response, dao, userId);
                        break;

                    default:
                        request.setAttribute("error", "Invalid action specified.");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                        break;
                }
            } catch (Exception ex) {
                Logger.getLogger(ShareBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", "An unexpected error occurred: " + ex.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "No action specified.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void handleShareAction(HttpServletRequest request, HttpServletResponse response, SharedBookDAO dao, Integer userId)
            throws ServletException, IOException {
        try {
            int receiverId = Integer.parseInt(request.getParameter("receiverId"));
            String isbn = request.getParameter("isbn");
            String sharedMessage = request.getParameter("sharedMessage");

            PatronDAO patronDao = new PatronDAO();
            if (!patronDao.isPatron(receiverId)) {
                request.setAttribute("error", "The reciver id is not an exist patron");
                request.getRequestDispatcher("SharedBook.jsp").forward(request, response);
                return;
            }
            
            
            
            if (!dao.isIsbnExists(isbn)) {
                request.setAttribute("error", "The provided ISBN does not exist.");
                request.getRequestDispatcher("SharedBook.jsp").forward(request, response);
                return;
            }

            int newSharedId = dao.getNextSharedId();

            SharedBook sharedBook = new SharedBook();
            sharedBook.setShareId(newSharedId);
            sharedBook.setSenderId(userId);
            sharedBook.setReceiverId(receiverId);
            sharedBook.setSharedMessage(sharedMessage);
            sharedBook.setIsbn(isbn);

            dao.createSharedBook(sharedBook);
            request.setAttribute("message", "Book shared successfully!");
            request.getRequestDispatcher("SharedBook.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid receiver ID format.");
            request.getRequestDispatcher("SharedBook.jsp").forward(request, response);
        } catch (SQLException e) {
            Logger.getLogger(ShareBooksServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("error", "Database error while sharing book: " + e.getMessage());
            request.getRequestDispatcher("SharedBook.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ShareBooksServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleViewAction(HttpServletRequest request, HttpServletResponse response, SharedBookDAO dao, Integer userId)
            throws ServletException, IOException {

         try {
            List<SharedBook> sharedBooks = dao.getSharedBooksReceived(userId);
            request.setAttribute("sharedBooks", sharedBooks);
            request.getRequestDispatcher("ViewSharedBooks.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error retrieving shared books: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
        
        
        
    }

