<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="classes.Book" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Patron Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
            }
            .navbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #333;
                color: white;
                padding: 10px 20px;
            }
            .navbar .menu {
                display: flex;
                gap: 15px;
            }
            .navbar .menu a, .navbar .dropdown {
                color: white;
                text-decoration: none;
                padding: 5px 10px;
                border-radius: 5px;
                transition: background-color 0.3s ease;
                position: relative;
            }
            .navbar .menu a:hover, .navbar .dropdown:hover {
                background-color: #555;
            }
            .dropdown-content {
                display: none;
                position: absolute;
                top: 100%;
                left: 0;
                background-color: #333;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }
            .dropdown-content a {
                display: block;
                color: white;
                padding: 10px 15px;
                text-decoration: none;
                border-bottom: 1px solid #444;
            }
            .dropdown-content a:hover {
                background-color: #555;
            }
            .dropdown:hover .dropdown-content {
                display: block;
            }
            .navbar .logout {
                background-color: #dc3545;
                padding: 5px 10px;
                border-radius: 5px;
                text-decoration: none;
                color: white;
            }
            .navbar .logout:hover {
                background-color: #a71d2a;
            }
            .content {
                padding: 20px;
                text-align: center;
            }
            .content h1 {
                font-size: 24px;
                color: #333;
            }
            .search-bar {
                margin-bottom: 20px;
                display: flex;
                justify-content: center;
            }
            .search-bar input[type="text"] {
                padding: 10px;
                width: 300px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .search-bar button {
                padding: 10px;
                background-color: #333;
                color: white;
                border: none;
                border-radius: 5px;
                margin-left: 10px;
                cursor: pointer;
            }
            .search-bar button:hover {
                background-color: #555;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table th, table td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }
            table th {
                background-color: #333;
                color: white;
            }
            .notification {
                padding: 10px;
                margin-bottom: 5px;
                border-radius: 5px;
            }

            .notification.red {
                background-color: lightcoral;
                color: black;
            }

            .notification.green {
                background-color: lightgreen;
                color: black;
            }
        </style>
    </head>
    <body>
        <div class="navbar">
            <div class="menu">
                <div class="dropdown">
                    <a href="#">Book Reviews and Ratings</a>
                    <div class="dropdown-content">
                        <a href="BorrowTransactionServlet">Comment on Books</a>
                        <a href="SearchBooksDetails.jsp">Books Details</a>
                        <a href="BorrowBook.jsp">Borrow Books</a>
                    </div>
                </div>
                <div class="dropdown">
                    <a href="#">Sharing Books</a>
                    <div class="dropdown-content">
                        <a href="SharedBook.jsp">Share Book Information</a>
                        <a href="ShareBooksServlet?action=view">View Shared Books</a>
                    </div>
                </div>
                
            </div>
            <a href="index.html" class="logout">Logout</a>
        </div>

        <div class="content">
            <h1>Welcome, <%= request.getSession().getAttribute("username") %></h1>

            <!-- Notifications Section -->
            <h2>Notifications</h2>

            <%
        List<String> notifications = (List<String>) request.getAttribute("notifications");
        List<String> notificationColors = (List<String>) request.getAttribute("notificationColors");
        if (notifications != null && !notifications.isEmpty()) {
            %>
            <div>
                <% for (int i = 0; i < notifications.size(); i++) { 
                    String notification = notifications.get(i);
                    String color = notificationColors.get(i); // Get the color for this notification
                %>
                <div class="notification <%= color %>">
                    <%= notification %>
                </div>
                <% } %>
            </div>
            <% } else { %>
            <p>No notifications available.</p>
            <% } %>
            <!-- Search Bar -->
            <p>Here are the books available in the library, sorted by year of publication. Use the search bar to filter by title or author.</p>
            <div class="search-bar">
                <form action="BookControllerServlet" method="post">
                    <input type="text" name="query" placeholder="Search by title or author">
                    <button type="submit">Search</button>
                    <input type="hidden" name="action" value="searchBooks">
                </form>
            </div>

            <!-- Books Table -->
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
