<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Usage Statistics</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
        }
        h1 {
            margin-bottom: 20px;
            color: #28395B;
        }
        .button-container {
            margin: 20px 0;
        }
        .button-container button {
            background-color: #28395B;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .button-container button:hover {
            background-color: #5D7C84;
        }
        .stat-container {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .stat-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .stat-value {
            font-size: 24px;
            color: #28395B;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h1>Library Usage Statistics</h1>
    <div class="button-container">
        <form action="LibraryStatisticsServlet" method="get">
            <button type="submit">Load Statistics</button>
        </form>
    </div>
    <div class="stat-container">
        <div class="stat-title">Active Borrowers</div>
        <div class="stat-value">
            <%= request.getAttribute("activeBorrowers") != null 
                ? request.getAttribute("activeBorrowers") 
                : "Loading..." %>
        </div>
        <div class="stat-title">Current Reservations</div>
        <div class="stat-value">
            <%= request.getAttribute("currentReservations") != null 
                ? request.getAttribute("currentReservations") 
                : "Loading..." %>
        </div>
    </div>
</body>
</html>