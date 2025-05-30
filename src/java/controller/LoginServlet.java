package controller;

import DataBase.UserDao;
import classes.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;


//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt( request.getParameter("id"));
        String password = request.getParameter("password");
        
        User user = UserDao.validateUser(id, password); // Validate user from the database
        
        response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getName());
            session.setAttribute("role", user.getRole());
            session.setAttribute("user", user);
            session.setAttribute("ID", id);  // Set the patron ID in the session

          
            // Assuming 'role' is a field in your User class
            RequestDispatcher dispatcher =null;
        
            if ("admin".equalsIgnoreCase(user.getRole())) {
                dispatcher = request.getRequestDispatcher("Admin.jsp");
                dispatcher.forward(request, response);
            } else if ("patron".equalsIgnoreCase(user.getRole())) {
                dispatcher = request.getRequestDispatcher("BookControllerServlet?action=getAllBook");
                dispatcher.forward(request, response);
            } else if("Librarian".equalsIgnoreCase(user.getRole())){
                dispatcher = request.getRequestDispatcher("NotificationsServlet");
                dispatcher.forward(request, response);
            }
        } else {
            out.print("Sorry UserName or Password Error!");  
        RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
        rd.include(request, response);   
        }
         
    }
}
