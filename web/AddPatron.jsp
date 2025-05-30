<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Patron</title>
        <meta charset="UTF-8">
        <style>
            body { 
                font-family: Arial, sans-serif; 
                margin: 20px; 
            }
            form { 
                max-width: 400px; 
                margin: auto; 
            }
            label { 
                display: block; 
                margin-bottom: 5px; 
                font-weight: bold; 
            }
            input, select { 
                width: 100%; 
                padding: 8px; 
                margin-bottom: 10px; 
                border: 1px solid #ccc; 
                border-radius: 4px; 
            }
            button { 
                padding: 10px 15px; 
                font-size: 16px; 
                background-color: #4CAF50; 
                color: white; 
                border: none; 
                border-radius: 4px; 
                cursor: pointer; 
            }
            button:hover { 
                background-color: #45a049; 
            }
           .message { margin: 20px 0; padding: 10px; background-color: #e7f3fe; border: 1px solid #b3d8fd; color: #31708f; border-radius: 4px; }

        </style>
    </head>
    <body>
        <h1>Add a New Patron</h1>
        <% String message = (String) request.getAttribute("message");
               if (message != null) { %>
                <div class="message"><%= message %></div>
            <% } %>

        <!-- Form to add a patron with user ID, username, password, and email -->
        <form action="PatronControllerServlet" method="post">
            <label for="id">Patron ID:</label>
            <input type="text" id="id" name="id" required>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <button type="submit" name="action" value="add">Add Patron</button>
        </form>

    </body>
</html>
