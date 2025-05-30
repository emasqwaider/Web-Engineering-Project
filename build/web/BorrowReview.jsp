<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.Transaction" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transactions</title>
    <style>
        /* Global styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        /* Table styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        /* Form styling */
        .review-form {
            margin-top: 20px;
            padding: 15px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 8px;
        }

        .review-form textarea, .review-form input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .review-form button {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .review-form button:hover {
            background-color: #45a049;
        }

        /* Message styling */
        p {
            text-align: center;
            font-size: 18px;
            color: #ff5722;
        }
         a {
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

    <div class="container">
        <h2>Borrow Books Details</h2>

        <%
            List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
            if (transactions != null && !transactions.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Transaction ID</th>
                        <th>ISBN</th>
                        <th>Borrow Date</th>
                        <th>Due Date</th>
                        <th>Return Date</th>
                        <th>Fine Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Transaction transaction : transactions) {
                    %>
                    <tr>
                        <td><%= transaction.getTransactionId() %></td>
                        <td><%= transaction.getISBN() %></td>
                        <td><%= transaction.getBorrowDate() %></td>
                        <td><%= transaction.getDueDate() %></td>
                        <td><%= transaction.getReturnDate() != null ? transaction.getReturnDate() : "Not Returned" %></td>
                        <td><%= transaction.getFine() %></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                
                
        <h2>Leave a Review</h2>
        <form class="review-form" method="post" action="SubmitReviewServlet">
            <label for="bookId">Book ISBN:</label>
            <input type="number" id="bookId" name="bookisbn" required>

            <label for="rating">Rating (1-5):</label>
            <input type="number" id="rating" name="rating" min="1" max="5" step="0.1" required>

            <label for="reviewText">Comment:</label>
            <textarea id="reviewText" name="reviewText" rows="4" required></textarea>

            <button type="submit">Submit Review</button>
           <a href="BookControllerServlet?action=getAllBook">Back</a>

        </form>
                
                
        <%
            } else {
        %>
            <p>No transactions found for this user.</p>
        <%
            }
        %>
    </div>
    <% String message = (String) request.getAttribute("message");
               if (message != null) { %>
                <div class="message"><%= message %></div>
            <% } %>
</body>
</html>