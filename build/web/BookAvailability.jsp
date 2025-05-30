<%-- 
    Document   : ViewAllBook
    Created on : Dec 24, 2024, 12:55:32 PM
    Author     : Tasneem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="classes.Book" %> 
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Availability Tracking</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
        }

        .container {
            width: 80%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #007bff;
            color: white;
        }

        table td {
            background-color: #f9f9f9;
        }

        /* Hover effect for rows */
        table tr:hover {
            background-color: #f1f1f1;
        }

        .book-list {
            margin-top: 20px;
        }

        /* Button to reload or fetch books (optional) */
        .btn-refresh {
            display: block;
            margin: 20px auto;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .btn-refresh:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Book Availability Tracking</h2>

        <!-- Form to submit the request for fetching all books -->
        <form method="POST" action="BookControllerServlet">
            
        </form>

        <div class="book-list">
            <table id="booksTable">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Availability</th>
                    </tr>
                </thead>
                <tbody>
                   
                    <%
                        // Example data - would be retrieved from the database in a real application
                        List<Book> bookList = (List<Book>) request.getAttribute("bookList");
                        if (bookList != null) {
                            for (Book book : bookList) {
                    %>
                    <tr>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getAvailability() %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>