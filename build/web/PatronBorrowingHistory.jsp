<%-- 
    Document   : PatronBorrowingHistory
    Created on : Dec 24, 2024, 1:34:21 PM
    Author     : Tasneem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="classes.Transaction" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patron Management</title>
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

        /* Section for managing patrons */
        .patron-management {
            margin-bottom: 30px;
        }

        .patron-management h3 {
            margin-bottom: 10px;
            font-size: 18px;
        }

        .patron-form, .borrow-history {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-size: 16px;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 12px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Borrowing history table */
        table {
            width: 100%;
            border-collapse: collapse;
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

        table tr:hover {
            background-color: #f1f1f1;
        }

        .btn-refresh {
            margin-top: 20px;
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
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
        <!-- Patron Borrowing History -->
        <div class="borrow-history">
            <h3>Patron Borrowing History</h3>
            <!-- Form to submit Patron ID -->
           <form method="POST" action="PatronControllerServlet?action=viewBorrowingHistory">
                <div class="form-group">
                    <label for="historyPatronId">Enter Patron ID to view History</label>
                    <input type="text" id="historyPatronId" name="id" placeholder="Enter Patron ID" required>
                </div>

                <button type="submit">View Borrowing History</button>
            </form>

            <!-- Borrow History Table -->
            <div class="borrow-history-table">
                <table>
                    <thead>
                        <tr>
                            <th>Book ISBN</th>
                            <th>Borrow Date</th>
                            <th>Return Status</th>
                            <th>Pending Fines</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Assuming the borrowing history data is passed from the servlet or backend logic --%>
                        <%
                            // Example data - would be retrieved from the database in a real application
                            List<Transaction> borrowHistoryList = (List<Transaction>) request.getAttribute("borrowingHistory");
                            if (borrowHistoryList != null) {
                                for (Transaction history : borrowHistoryList) {
                        %>
                        <tr>
                            <td><%= history.getISBN() %></td>
                            <td><%= history.getBorrowDate() %></td>
                            <td><%= history.getReturnStatus() %></td>
                            <td><%= history.getFine() %></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>