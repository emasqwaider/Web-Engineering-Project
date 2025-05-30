/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;
/**
 *
 * @author HP
 */
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Load configuration values from web.xml
        String fineRate = context.getInitParameter("fineRate");
        String maxBorrowedBooks = context.getInitParameter("maxBorrowedBooks");
        String borrowingPeriodDays = context.getInitParameter("borrowingPeriodDays");
       

        // Store them in context for global access
        context.setAttribute("fineRate", Double.parseDouble(fineRate));
        context.setAttribute("maxBorrowedBooks", Integer.parseInt(maxBorrowedBooks));
        context.setAttribute("borrowingPeriodDays", Integer.parseInt(borrowingPeriodDays));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // No special cleanup required
    }

    
}