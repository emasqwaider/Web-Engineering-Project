<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }

        h1, h2 {
            color: #2c3e50;
        }

        h1 {
            text-align: center;
            padding: 20px 0;
            background-color: #3498db;
            color: white;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            font-size: 16px;
            font-weight: bold;
        }

        input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #3498db;
            color: white;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>


    <!-- Return Book Section -->
    <div class="container">
        <h2>Return Book</h2>
        <form action="TransactionControllerServlet" method="post">
            <input type="hidden" name="action" value="return">
            
            <div class="form-group">
                <label for="transaction_id">Transaction ID</label>
                <input type="text" name="transactionid" id="transactionid" required>
                <label for="patron_id">Patron ID</label>
                <input type="text" name="patronid" id="patronid" required>
            </div>
            
            <input type="submit" value="Return Book">
        </form>
         <% 
        // Display error message if no book is found
        String errorMessage = (String) request.getAttribute("message");
        if (errorMessage != null) {
    %>
        <p class="error-message"><%= errorMessage %></p>
    <% } %>
    </div>



</body>
</html>
