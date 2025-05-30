package controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import classes.Patron;
import DataBase.PatronDAO;
import classes.Transaction;
import jakarta.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/PatronControllerServlet")
public class PatronControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                {
                    // Extract patron details from the request
                    int id =Integer.parseInt(request.getParameter("id")) ;
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String email = request.getParameter("email");

                    // Create a Patron object and set its fields
                    Patron patron = new Patron();
                    patron.setUserId(id);
                    patron.setName(username);
                    patron.setPassword(password);  
                    patron.setEmail(email);

                    // Save patron to the database
                    try {
                        PatronDAO.addPatron(patron);  // Insert the patron into the database
                        request.setAttribute("message", "Patron added successfully!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        request.setAttribute("message", "Error adding patron: " + e.getMessage());
                    }

                    // Forward to the AddPatron.jsp page to show a success or error message
                    RequestDispatcher dispatcher = request.getRequestDispatcher("AddPatron.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                  case "delete":
                  {
                    // Get the patron ID from the request
                    int patronId = Integer.parseInt(request.getParameter("id"));

                    // Check if the ID is valid
                    if (patronId != 0) {
                        try {
                            // Call the PatronDAO to remove the patron from the database
                            PatronDAO.removePatron(patronId);

                            // Set a success message
                            request.setAttribute("message", "Patron deleted successfully!");
                        } catch (Exception e) {
                            // Handle any errors that occur during patron deletion
                            e.printStackTrace();
                            request.setAttribute("message", "Error deleting patron: " + e.getMessage());
                        }
                    } else {
                        // If no valid patron ID is provided
                        request.setAttribute("message", "Invalid patron ID.");
                    }

                    // Forward the request and response to a JSP to show the result message
                   RequestDispatcher dispatcher = request.getRequestDispatcher("RemovePatron.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "searchPatron": 
                {
                    // Get the patron ID from the request
                    int patronId =Integer.parseInt(request.getParameter("id")) ;

                    // Validate the input
                    if (patronId != 0 ) {
                        try {
                            // Call PatronDAO to get the patron details
                           Patron  patron = PatronDAO.searchPatronById(patronId);

                            // Check if patron was found
                            if (patron != null && patron.getRole().equals("patron") ) {
                                request.setAttribute("patron", patron);  // Set the found patron as a request attribute
                            } else {
                                request.setAttribute("errorMessage", "No patron found with the provided ID.");
                            }
                        } catch (Exception e) {
                            // Handle errors during the database query
                            e.printStackTrace();
                            request.setAttribute("errorMessage", "Error searching patron: " + e.getMessage());
                        }
                    } else {
                        request.setAttribute("errorMessage", "Please provide a valid patron ID.");
                    }

                    // Forward to the JSP to display the result
                    RequestDispatcher dispatcher = request.getRequestDispatcher("EditPatron.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "updatePatron": {
                    // Get the form parameters
                    int patronId =Integer.parseInt(request.getParameter("patronId")) ;
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String  email = request.getParameter("email");
                    Patron patron =null;

                try {
                    // Create a Patron object with the updated data
                    patron = PatronDAO.searchPatronById(patronId);
                } catch (SQLException ex) {
                    Logger.getLogger(PatronControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    patron.setName(username);
                    patron.setPassword(password);
                    patron.setEmail(email);

                    // Call PatronDAO to update the patron's data
                    
                     PatronDAO.update(patron);
                     
                    request.setAttribute("message", "Patron Info Updated.");
                    RequestDispatcher  dispatcher = request.getRequestDispatcher("EditPatron.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                
                case "viewBorrowingHistory": {
                    // Get the patron ID from the request
                    int patronId =Integer.parseInt(request.getParameter("id"));

                    // Validate the input
                    if (patronId != 0) {
                        try {
                            List<Transaction> borrowingHistory = PatronDAO.getBorrowingHistory(patronId);

                            // Check if any borrowing history was found
                            if (borrowingHistory != null && !borrowingHistory.isEmpty()) {
                                request.setAttribute("borrowingHistory", borrowingHistory);  // Set the borrowing history as a request attribute
                            } else {
                                request.setAttribute("errorMessage", "No borrowing history found for the provided patron ID.");
                            }
                        } catch (Exception e) {
                            // Handle errors during the database query
                            e.printStackTrace();
                            request.setAttribute("errorMessage", "Error retrieving borrowing history: " + e.getMessage());
                        }
                    } else {
                        request.setAttribute("errorMessage", "Please provide a valid patron ID.");
                    }

                    // Forward to the JSP to display the result
                    RequestDispatcher dispatcher = request.getRequestDispatcher("PatronBorrowingHistory.jsp");
                    dispatcher.forward(request, response);
                    break;
                }

          

                default:
                    request.setAttribute("message", "Invalid action.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("AddPatron.jsp");
                    dispatcher.forward(request, response);
            }
            
            
        }
        
        
    }
}
