<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="classes.Patron" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Search Patron</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
            text-align: center;
        }

        form {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 400px;
            max-width: 100%;
            text-align: left;
            margin: 15px;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            font-size: 16px;
            margin-bottom: 8px;
            color: #555;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <h2>Search Patron by ID</h2>

    <!-- Search Form -->
    <form action="PatronControllerServlet" method="Post">
        <input type="hidden" name="action" value="searchPatron">
        <label for="id">Enter Patron ID:</label>
        <input type="text" id="id" name="id" placeholder="Enter Patron ID" required>
        <input type="submit" value="Search">
    </form>

    <% 
        // Display error message if no patron is found
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error-message"><%= errorMessage %></p>
    <% } %>

    <% 
        // Display success message if patron is found
        Patron patron = (Patron) request.getAttribute("patron");
        if (patron != null) {
    %>
        <h3>Patron Details:</h3>
        <p>ID: <%= patron.getUserId() %></p>
        <p>Name: <%= patron.getName() %></p>
        <p>Email: <%= patron.getEmail() %></p>

        <!-- Button to view borrowing history -->
        
    <% } %>

</body>
</html>
