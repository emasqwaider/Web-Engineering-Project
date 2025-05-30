<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Configuration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            margin-top: 30px;
            color: #28395B;
            font-size: 36px;
            text-align: center;
        }

        .container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            margin-top: 30px;
        }

        h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }

        p {
            color: #555;
            font-size: 18px;
            margin-bottom: 10px;
        }

        label {
            font-size: 16px;
            color: #333;
            display: block;
            margin-bottom: 8px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            background-color: #28395B;
            color: white;
            padding: 15px 20px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #5D7C84;
        }

        .config-summary {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h1>Library System Configuration</h1>

    <div class="container">
        <div class="config-summary">
            <p><strong>Fine Rate:</strong> $<%= application.getAttribute("fineRate") %> per day</p>
            <p><strong>Max Borrowed Books:</strong> <%= application.getAttribute("maxBorrowedBooks") %></p>
            <p><strong>Borrowing Period:</strong> <%= application.getAttribute("borrowingPeriodDays") %> days</p>
        </div>

        <h2>Update Configuration</h2>
        <form action="UpdateConfigServlet" method="post">
            <label for="fineRate">Fine Rate ($):</label>
            <input type="text" id="fineRate" name="fineRate" value="<%= application.getAttribute("fineRate") %>" required>

            <label for="maxBorrowedBooks">Max Borrowed Books:</label>
            <input type="text" id="maxBorrowedBooks" name="maxBorrowedBooks" value="<%= application.getAttribute("maxBorrowedBooks") %>" required>

            <label for="borrowingPeriodDays">Borrowing Period (days):</label>
            <input type="text" id="borrowingPeriodDays" name="borrowingPeriodDays" value="<%= application.getAttribute("borrowingPeriodDays") %>" required>

            <button type="submit">Update Configuration</button>
        </form>
    </div>
</body>
</html>