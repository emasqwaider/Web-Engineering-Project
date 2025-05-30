<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Librarian Dashboard</title>
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
        .notification {
                padding: 10px;
                margin-bottom: 5px;
                border-radius: 5px;
                background-color: lightblue
            }

         
    </style>
</head>
<body>
    <div class="navbar">
        <div class="menu">
            <div> JUST Library </div>
            <div class="dropdown">
                <a href="#">Manage Books</a>
                <div class="dropdown-content">
                    <a href="AddBook.jsp">Add Book</a>
                    <a href="RemoveBook.jsp">Remove Book</a>
                    <a href="SearchBook.jsp">Edit Book</a>
                    <a href="BookControllerServlet?action=getAllBooks">View All Books</a>
                    <a href="BookControllerServlet?action=BooksAva">Book Availability</a>
                </div>
            </div>
            <div class="dropdown">
                <a href="#">Manage Patrons</a>
                <div class="dropdown-content">
                    <a href="AddPatron.jsp">Add Patron</a>
                    <a href="RemovePatron.jsp">Remove Patron</a>
                    <a href="SearchPatron.jsp">Edit Patron</a>
                    <a href="PatronBorrowingHistory.jsp">View Patron Borrowing History</a>
                </div>
            </div>
            <div class="dropdown">
                <a href="#">Return Book</a>
                <div class="dropdown-content">
                    <a href="Returnbook.jsp">Return Book</a>
                </div>
            </div>
            <div class="dropdown">
                <a href="#">Fine Management</a>
                <div class="dropdown-content">
                    <a href="FineReports.jsp">Fine Reports</a>
                    <a href="LibraryStatisticsServlet">Library Usage Statistics</a>
                </div>
            </div>
        </div>
        <a href="index.html" class="logout">Logout</a>
    </div>
    <div class="content">
        <h1>Welcome to the Librarian Dashboard</h1>
        <p>Select an option from the menu to proceed.</p>
       <%
            List<String> notifications = (List<String>) request.getAttribute("notifications");
            if (notifications != null && !notifications.isEmpty()) {
        %>
            <div>
                <% for (String notification : notifications) { %>
                    <div class="notification">
                        <%= notification %>
                    </div>
                <% } %>
            </div>
        <% } else { %>
            <p>No notifications available.</p>
        <% } %>
    </div>
</body>
</html>