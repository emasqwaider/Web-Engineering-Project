<%-- 
    Document   : RemoveBook
    Created on : Dec 24, 2024, 11:54:12 AM
    Author     : Tasneem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>/* Reset some default styles */
* /* Reset some default styles */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Set a background color and padding for the entire page */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f9;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    text-align: center; /* Align text in the center */
}

/* Style the container of the form */
form {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    padding: 30px;
    width: 300px;
    text-align: center;
    
}

/* Style the form title */
h2 {
    margin-bottom: 500px;  /* Adjust this value to move the title closer or farther */

    color: #333;
}
/* Style the labels */
label {
    display: block;
    font-size: 16px;
    margin: 10px 0 5px;
    color: #555;
}

/* Style the input fields */
input[type="text"] {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    margin-bottom: 20px;
    border: 1px solid #ddd;
    border-radius: 4px;
}

input[type="checkbox"] {
    margin-right: 5px;
}

/* Style the submit button */
input[type="submit"] {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 12px 20px;
    font-size: 16px;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
    transition: background-color 0.3s ease;
}

/* Change button color on hover */
input[type="submit"]:hover {
    background-color: #0056b3;
}

/* Style the confirmation text */
input[type="checkbox"] + label {
    font-size: 14px;
    color: #555;
}

/* Add a margin between form elements */
br {
    margin-bottom: 10px;
}

</style>
    <title>Remove Book</title>
    
</head>
<body>
    <h2  >Remove Book</h2>
    <form action="BookControllerServlet?action=deleteBook" method="POST">
        <label for="id">Book ID:</label>
        <input type="text" id="id" name="isbn" required><br><br>

        <label for="confirm">Are you sure you want to remove this book?</label>
        <input type="checkbox" id="confirm" name="confirm" value="yes" required>
        <label for="confirm">Yes, remove this book</label><br><br>

        <input type="submit" value="Remove Book">
    </form>
</body>
</html>
