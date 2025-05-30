<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="classes.SharedBook" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Shared Books</title>
    <style>
        /* General page styling */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        /* Container that wraps the content */
        .container {
            width: 80%;
            margin: 50px auto;
        }

        h1 {
            text-align: center;
            color: #333;
            font-size: 2rem;
            margin-bottom: 30px;
            font-weight: bold;
        }

        /* Styling for each shared book box */
        .sharedBookBox {
            background-color: #ffffff;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            border-left: 4px solid #4CAF50; /* Green left border for contrast */
        }

        /* Headings inside each shared book box */
        .sharedBookBox h3 {
            margin-top: 0;
            color: #333;
            font-size: 1.5rem;
        }

        /* Labels for better clarity */
        .sharedBookBox p {
            margin: 8px 0;
            color: #555;
            font-size: 1rem;
        }

        .sharedBookBox .label {
            font-weight: bold;
            color: #333;
            font-size: 1.1rem;
        }

        /* Message when no books are shared */
        .message {
            padding: 20px;
            background-color: #fce8e6;
            border: 1px solid #f7b4b3;
            color: #d9534f;
            border-radius: 4px;
            text-align: center;
            font-size: 1.2rem;
            font-weight: 500;
        }

        /* Styling for the back button */
        .back-button {
            display: inline-block;
            padding: 12px 25px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 30px;
            text-align: center;
            font-size: 1rem;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .back-button:hover {
            background-color: #45a049;
            transform: scale(1.05);
        }

        /* Responsive styling */
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }

            h1 {
                font-size: 1.5rem;
            }

            .sharedBookBox {
                padding: 15px;
            }

            .back-button {
                padding: 10px 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Books Shared With You</h1>

        <% 
            List<SharedBook> sharedBooks = (List<SharedBook>) request.getAttribute("sharedBooks");
            if (sharedBooks != null && !sharedBooks.isEmpty()) {
                for (SharedBook book : sharedBooks) {
        %>
            <div class="sharedBookBox">
                <h3>Shared Book <%= book.getShareId() %></h3>
                <p><span class="label">Sender ID:</span> <%= book.getSenderId() %></p>
                <p><span class="label">Book ISBN:</span> <%= book.getIsbn() %></p>
                <p><span class="label">Message:</span> <%= book.getSharedMessage() %></p>
                <p><span class="label">Shared Date:</span> <%= book.getSharedDate() %></p>
            </div>
        <% 
                }
            } else {
        %>
            <div class="message">No books have been shared with you yet.</div>
        <% } %>
        
        <a href="BookControllerServlet?action=getAllBook" class="back-button">Back to Library</a>
    </div>
</body>
</html>