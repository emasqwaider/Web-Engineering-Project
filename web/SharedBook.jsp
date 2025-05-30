<%-- 
    Document   : SharedBook
    Created on : Jan 2, 2025, 8:30:43 PM
    Author     : Thinkpad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Share a Book</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            font-size: 2.5rem;
        }
        form {
            background: #ffffff;
            padding: 30px;
            width: 100%;
            max-width: 500px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        div {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: 600;
            color: #555;
        }
        input, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 1rem;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 14px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 1.2rem;
            cursor: pointer;
            margin-top: 10px;
        }
        button:hover {
            background-color: #45a049;
        }
       .message {
            margin: 20px 0;
         padding: 10px;
         background-color: #e7f3fe; 
         border: 1px solid #b3d8fd; 
         color: #31708f;
         border-radius: 4px; 

        }
        .message.error {
            margin: 20px 0;
         padding: 10px;
         background-color: #e7f3fe; 
         border: 1px solid #b3d8fd; 
         color: red;
         border-radius: 4px; 

        }
        .message.success {
         margin: 20px 0;
         padding: 10px;
         background-color: #e7f3fe; 
         border: 1px solid #b3d8fd; 
         color: #31708f;
         border-radius: 4px; 

        }
        .back-button {
            display: inline-block;
            margin-top: 30px;
            padding: 15px 30px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1.2rem;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #2980b9;
        }
        
        
    </style>
</head>
<body>
    <% 
        String error = (String) request.getAttribute("error");
        String success = (String) request.getAttribute("message");
    %>
    <% if (error != null) { %>
        <div class="message error"><%= error %></div>
    <% } %>
    <% if (success != null) { %>
        <div class="message success"><%= success %></div>
    <% } %>
    
    <form action="ShareBooksServlet" method="post">
        <h1>Share a Book</h1>
        <input type="hidden" name="action" value="share" />
        
        <div>
            <label for="receiverId">Patron Receiver ID</label>
            <input type="number" id="receiverId" name="receiverId" required />
        </div>
        <div>
            <label for="isbn">Book ISBN</label>
            <input type="text" id="isbn" name="isbn" required />
        </div>
        <div>
            <label for="sharedMessage">Book Description</label>
            <textarea id="sharedMessage" name="sharedMessage" rows="4" required></textarea>
        </div>
        <button type="submit" name="action" value="share">Share</button>
         <a href="BookControllerServlet?action=getAllBook" class="back-button">Back</a>
    </form>
    
    
   
    
</body>
</html>
