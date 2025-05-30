<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.Book" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View All Books</title>
        <style>
            body { font-family: Arial, sans-serif; margin: 20px; }
            table { width: 100%; border-collapse: collapse; margin-top: 20px; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f4f4f4; }
            .container { max-width: 800px; margin: auto; }
            .message { margin: 20px 0; padding: 10px; background-color: #e7f3fe; border: 1px solid #b3d8fd; color: #31708f; border-radius: 4px; }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>View All Books</h2>

            <!-- Display messages (success or error) -->
            <% String message = (String) request.getAttribute("message");
               if (message != null) { %>
                <div class="message"><%= message %></div>
            <% } %>

            <!-- Book List Table -->
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                        <th>ISBN</th>
                        <th>Genre</th>
                        <th>Year of Publication</th>
                        <th>Availability</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Book> books = (List<Book>) request.getAttribute("books");
                        if (books != null && !books.isEmpty()) {
                            for (Book book : books) {
                    %>
                    <tr>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getAuthor() %></td>
                        <td><%= book.getIsbn() %></td>
                        <td><%= book.getGenre() %></td>
                        <td><%= book.getYearOfPublication() %></td>
                        <td><%= book.getAvailability() %></td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="6" style="text-align: center;">No books found.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </body>
</html>
