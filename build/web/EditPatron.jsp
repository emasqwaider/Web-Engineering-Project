<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="classes.Patron" %> <!-- Add this line to import the Patron class -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Patron</title>
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
 .message { margin: 20px 0; padding: 10px; background-color: #e7f3fe; border: 1px solid #b3d8fd; color: #31708f; border-radius: 4px; }

    </style>
</head>
<body>
    <h2>Edit Patron Details</h2>
     <% String message = (String) request.getAttribute("message");
               if (message != null) { %>
                <div class="message"><%= message %></div>
            <% } %>

    <% 
        // Retrieve the patron details from request attributes
        Patron patron = (Patron) request.getAttribute("patron");

        // If the patron object is found, show the edit form
        if (patron != null) { 
    %>
    <form action="PatronControllerServlet" method="POST">
        <input type="hidden" name="action" value="updatePatron">
        <input type="hidden" name="patronId" value="<%= patron.getUserId() %>">

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="<%= patron.getName() %>" required>

        <label for="password">Password:</label>
        <input type="text" id="password" name="password" value="<%= patron.getPassword() %>" required>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="<%= patron.getEmail() %>" required>

        <input type="submit" value="Update Patron">
    </form>
        
    <% 
        } else if (request.getAttribute("errorMessage") != null) {
            // Show error message if no patron is found
    %>
    <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
    <% 
        }
    %>

</body>
</html>
