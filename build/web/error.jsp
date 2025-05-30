<%-- 
    Document   : error
    Created on : 2 Jan 2025, 6:23:11 PM
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            background-color: #f5c6cb;
            border: 1px solid #f1b0b7;
            border-radius: 5px;
            padding: 20px 30px;
            text-align: center;
            max-width: 600px;
            width: 100%;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-top: 0;
        }
        p {
            margin: 10px 0;
        }
        a {
            color: #721c24;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>An Error Occurred</h1>
        <p>${error}</p>
        <p>
            <a href="index.jsp">Return to Home</a> | 
            <a href="javascript:history.back()">Go Back</a>
        </p>
    </div>
</body>
</html>