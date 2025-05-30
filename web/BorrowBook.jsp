<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrow Book </title>
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
         .message { margin: 20px 0; padding: 10px; background-color: #e7f3fe; border: 1px solid #b3d8fd; color: #31708f; border-radius: 4px; }

    </style>
</head>
<body>


    <!-- Borrow Book Section -->
    <div class="container">
        <h2>Borrow Book</h2>
        <form action="TransactionControllerServlet" method="post">
            <input type="hidden" name="action" value="borrow">
            
            <div class="form-group">
                <label for="book_id">Book ID</label>
                <input type="text" name="isbn" id="book_id" required>
            </div>
            
            <input type="submit" value="Borrow Book">
        </form>
    </div>
 <% String message = (String) request.getAttribute("message");
               if (message != null) { %>
                <div class="message"><%= message %></div>
            <% } %>


</body>
</html>
