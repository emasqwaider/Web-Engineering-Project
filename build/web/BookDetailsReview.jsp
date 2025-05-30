<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="classes.Book" %>
<%@ page import="classes.Review" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Details</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f1f1;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            background-color: white;
            width: 80%;
            max-width: 1000px;
            border-radius: 12px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.1);
            padding: 40px;
            margin-top: 30px;
        }

        h2 {
            color: #2c3e50;
            font-size: 2.5rem;
            margin-bottom: 20px;
            text-align: center;
        }

        .book-details {
            border-bottom: 2px solid #ecf0f1;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }

        .book-details h3 {
            font-size: 2rem;
            color: #2980b9;
            margin-bottom: 10px;
        }

        .book-details p {
            font-size: 1.2rem;
            line-height: 1.6;
            color: #555;
            margin-bottom: 8px;
        }

        .book-details p strong {
            color: #2c3e50;
        }

        .reviews {
            padding-top: 20px;
        }

        .review-item {
            background-color: #ecf0f1;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s ease, box-shadow 0.3s ease;
        }

        .review-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
        }

        .review-item p {
            font-size: 1rem;
            color: #333;
        }

        .review-item strong {
            color: #2980b9;
        }

        .message {
            color: #e74c3c;
            text-align: center;
            font-size: 1.2rem;
            margin-top: 20px;
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

        @media (max-width: 768px) {
            .container {
                width: 90%;
            }

            h2 {
                font-size: 2rem;
            }
        }
        
        
        
        
    </style>
</head>
<body>

    <div class="container">
        <h2>Book Details</h2>

        <% 
            // Fetch the book from request attributes
            Book book = (Book) request.getAttribute("books");
            List<Review> reviews = (List<Review>) request.getAttribute("reviews");
            String message = (String) request.getAttribute("message");

            if (book != null) {
        %>
            <div class="book-details">
                <h3><%= book.getTitle() %></h3>
                <p><strong>ISBN:</strong> <%= book.getIsbn() %></p>
                <p><strong>Author:</strong> <%= book.getAuthor() %></p>
                <p><strong>Genre:</strong> <%= book.getGenre() %></p>
                <p><strong>Rating:</strong> <%= book.getRating() %></p>
                <p><strong>Availability:</strong> <%= book.getAvailability() %></p>
            </div>

            <div class="reviews">
                <h3>Reviews</h3>
                <% if (reviews != null && !reviews.isEmpty()) { %>
                    <% for (Review review : reviews) { %>
                        <div class="review-item">
                            <p><strong>User ID:</strong> <%= review.getUserId() %></p>
                            <p><strong>Rating:</strong> <%= review.getRating() %></p>
                            <p><strong>Review:</strong> <%= review.getReviewText() %></p>
                            <p><strong>Date:</strong> <%= review.getReviewDate() %></p>
                        </div>
                    <% } %>
                <% } else { %>
                    <p class="message">No reviews available for this book.</p>
                <% } %>
            </div>

            <a href="SearchBooksDetails.jsp" class="back-button">Back to Search</a>

        <% 
            } else if (message != null) { 
        %>
            <div class="message"><%= message %></div>
        <% 
            }
        %>
    </div>

</body>
</html>