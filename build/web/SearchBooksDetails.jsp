<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .search-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 600px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .search-form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .search-form label {
            font-size: 1.1rem;
            color: #333;
        }

        .search-form input {
            padding: 10px;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 100%;
            box-sizing: border-box;
        }

        .search-form button {
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            font-size: 1.1rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .search-form button:hover {
            background-color: #2980b9;
        }

        .message {
            color: red;
            text-align: center;
            font-size: 1.1rem;
            margin-top: 15px;
        }
       a {
            text-align: center;
            padding: 10px 10px;
            background-color: black;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
           
        
        
    </style>
</head>
<body>

    <div class="search-container">
        <h2>Search for a Book</h2>

        <form action="BookDetailsSearchServlet" method="POST" class="search-form">
            <label for="isbn">Enter ISBN:</label>
            <input type="text" id="isbn" name="isbn" placeholder="Enter ISBN" required>

            <button type="submit">Search</button>
            <a href="BookControllerServlet?action=getAllBook">Back</a>

        </form>

        <% 
            String message = (String) request.getAttribute("message");
            if (message != null) { 
        %>
            <div class="message"><%= message %></div>
        <% 
            }
        %>
    </div>

</body>
</html>