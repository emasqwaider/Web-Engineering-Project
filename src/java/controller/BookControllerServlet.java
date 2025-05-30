package controller;

import DataBase.BookDAO;
import DataBase.PatronDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import classes.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = (String) request.getSession().getAttribute("role"); // Get role from session
        String action = request.getParameter("action");

        try {
            switch (role) {
                case "admin":
                case "librarian":
                    handleAdminLibrarianActions(request, response, action);
                    break;
                case "patron":
                    handlePatronActions(request, response, action);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void handleAdminLibrarianActions(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException, SQLException {
        List<Book> books = BookDAO.getAllBooks();
        request.setAttribute("books", books);

        switch (action) {
            case "add": {
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String isbn = request.getParameter("isbn");
                String genre = request.getParameter("genre");
                String availability = request.getParameter("availability");
                int yearOfPublication = Integer.parseInt(request.getParameter("yearOfPublication"));

                Book book = new Book();

                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setGenre(genre);
                book.setAvailability(availability);
                book.setYearOfPublication(yearOfPublication);

                BookDAO.addBook(book);

                request.setAttribute("message", "Book added successfully!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("AddBook.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "searchBook": {
                String isbn = request.getParameter("isbn");
                if (isbn == null || isbn.isEmpty()) {
                    request.setAttribute("error", "ISBN is required to search for the book.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("SearchBook.jsp");
                    dispatcher.forward(request, response);
                } else {
                    Book book = BookDAO.findByISBN(isbn);
                    if (book != null) {
                        // Set the book object as a request attribute
                        request.setAttribute("book", book);
                        request.setAttribute("isbn", book.getIsbn());
                        request.setAttribute("message", "Book found successfully!");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("EditBook.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        request.setAttribute("errorMessage", "No book found with the provided ISBN.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchBook.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            }
            break;
            case "updateBook": {

                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String isbn = request.getParameter("bookId");
                String genre = request.getParameter("genre");
                String availability = request.getParameter("availability");

                Book book = BookDAO.findByISBN(isbn);
                book.setAuthor(author);
                book.setAvailability(availability);
                book.setGenre(genre);
                book.setTitle(title);

                BookDAO.updateBook(book.getIsbn(), book);

                request.setAttribute("book", book);
                request.setAttribute("message", "Book Updated successfully!");
                books = BookDAO.getAllBooks();
                request.setAttribute("books", books);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllBook.jsp");
                dispatcher.forward(request, response);

                break;
            }
            case "getAllBooks": {
                try {
                    books = BookDAO.getAllBooks();
                    request.setAttribute("books", books);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Error retrieving books.");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllBook.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "BooksAva":
                try {
                    // Fetch all books from the database
                    List<Book> bookList = BookDAO.BookAVA();

                    // Set the books as a request attribute
                    request.setAttribute("bookList", bookList);

                    // Forward to the ViewAllBooks.jsp
                    RequestDispatcher dispatcher = request.getRequestDispatcher("BookAvailability.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Set error message in case of failure
                    request.setAttribute("message", "Error fetching book data. Please try again later.");

                    // Forward back to admin page with error
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case "deleteBook":
                try {
                    String bookId = request.getParameter("isbn");
                    BookDAO.deleteBook(bookId);
                    request.setAttribute("message", "Book deleted successfully!");
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Invalid book ID provided for deletion.");
                } catch (Exception e) {
                    request.setAttribute("error", "An error occurred while deleting the book.");
                }
                break;
            default:
                request.setAttribute("books", BookDAO.getAllBooks());
        }
        books = BookDAO.getAllBooks();
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAllBook.jsp");
        dispatcher.forward(request, response);
    }

    private void handlePatronActions(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException, SQLException {
       RequestDispatcher dispatcher ;
       ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        int patronId = (int) session.getAttribute("ID");
        
        switch (action) {
            case "searchBooks":
                request.setAttribute("books", BookDAO.searchBooks(request.getParameter("query")));
                dispatcher = request.getRequestDispatcher("PatronPage.jsp");
                dispatcher.forward(request, response);
                break;
            case "getAllBook":
                request.setAttribute("books", BookDAO.getAllBooks());
                dispatcher = request.getRequestDispatcher("NotificationsServlet");
                dispatcher.forward(request, response);
        }
        
    }
}
