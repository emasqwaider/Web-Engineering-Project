<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database Setup</title>
</head>
<body>
    <h1>Database Setup</h1>
    <%
        String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Replace with your database URL
        String user = "system"; // Replace with your database username
        String password = "1234"; // Replace with your database password
        Connection conn = null;
        Statement stmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            conn = DriverManager.getConnection(url, user, password);

            // Create a SQL statement
            stmt = conn.createStatement();
/*
            // Create the 'users' table
           String createTableSQL = "CREATE TABLE users (" +
                        "id NUMBER PRIMARY KEY, " +
                        "username VARCHAR2(255) UNIQUE NOT NULL, " +
                        "password VARCHAR2(255) NOT NULL, " +
                        "email VARCHAR2(255) NOT NULL" +
                        ")";
String createSequenceSQL = "CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1";
String createTriggerSQL = "CREATE OR REPLACE TRIGGER trg_user_id " +
                          "BEFORE INSERT ON users " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT user_seq.NEXTVAL INTO :NEW.id FROM DUAL; " +
                          "END;";


stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'users' has been created successfully!</p>");
            
             createTableSQL = "CREATE TABLE Books (" +
                        "BookID NUMBER PRIMARY KEY, " +
                        "Title VARCHAR2(255) NOT NULL, " +
                        "Author VARCHAR2(255) NOT NULL, " +
                        "ISBN VARCHAR2(13) UNIQUE, " +
                        "Genre VARCHAR2(50), " +
                        "YearPublished NUMBER(4), " +
                        "Availability VARCHAR2(10) DEFAULT 'Available', " +
                        "AverageRating NUMBER(3, 2) DEFAULT 0.0" +
                        ")";

 createSequenceSQL = "CREATE SEQUENCE book_seq START WITH 1 INCREMENT BY 1";

 createTriggerSQL = "CREATE OR REPLACE TRIGGER trg_book_id " +
                          "BEFORE INSERT ON Books " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT book_seq.NEXTVAL INTO :NEW.BookID FROM DUAL; " +
                          "END;";
            
                          stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'books' has been created successfully!</p>");
            
             createTableSQL = "CREATE TABLE Notifications (" +
                        "NotificationID NUMBER PRIMARY KEY, " +
                        "UserID NUMBER NOT NULL, " +
                        "Message CLOB NOT NULL, " +
                        "NotificationDate DATE DEFAULT SYSDATE, " +
                        "IsRead NUMBER(1) DEFAULT 0, " +
                        "CONSTRAINT FK_Notification_User FOREIGN KEY (UserID) REFERENCES Users(ID)" +
                        ")";

 createSequenceSQL = "CREATE SEQUENCE notification_seq START WITH 1 INCREMENT BY 1";

 createTriggerSQL = "CREATE OR REPLACE TRIGGER trg_notification_id " +
                          "BEFORE INSERT ON Notifications " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT notification_seq.NEXTVAL INTO :NEW.NotificationID FROM DUAL; " +
                          "END;";
            stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'notification' has been created successfully!</p>");
            
             createTableSQL = "CREATE TABLE SharedBooks (" +
                        "ShareID NUMBER PRIMARY KEY, " +
                        "SenderID NUMBER NOT NULL, " +
                        "ReceiverID NUMBER NOT NULL, " +
                        "BookID NUMBER NOT NULL, " +
                        "SharedMessage CLOB, " +
                        "ShareDate DATE DEFAULT SYSDATE, " +
                        "CONSTRAINT FK_Sender FOREIGN KEY (SenderID) REFERENCES Users(ID), " +
                        "CONSTRAINT FK_Receiver FOREIGN KEY (ReceiverID) REFERENCES Users(ID), " +
                        "CONSTRAINT FK_Shared_Book FOREIGN KEY (BookID) REFERENCES Books(BookID)" +
                        ")";

 createSequenceSQL = "CREATE SEQUENCE shared_book_seq START WITH 1 INCREMENT BY 1";

 createTriggerSQL = "CREATE OR REPLACE TRIGGER trg_shared_book_id " +
                          "BEFORE INSERT ON SharedBooks " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT shared_book_seq.NEXTVAL INTO :NEW.ShareID FROM DUAL; " +
                          "END;";
            stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'SharedBooks' has been created successfully!</p>");
            
            
             createTableSQL = "CREATE TABLE Reviews (" +
                        "ReviewID NUMBER PRIMARY KEY, " +
                        "UserID NUMBER NOT NULL, " +
                        "BookID NUMBER NOT NULL, " +
                        "ReviewText CLOB, " +
                        "Rating NUMBER CHECK (Rating BETWEEN 1 AND 5), " +
                        "ReviewDate DATE DEFAULT SYSDATE, " +
                        "CONSTRAINT FK_Review_User FOREIGN KEY (UserID) REFERENCES Users(ID), " +
                        "CONSTRAINT FK_Review_Book FOREIGN KEY (BookID) REFERENCES Books(BookID)" +
                        ")";

 createSequenceSQL = "CREATE SEQUENCE review_seq START WITH 1 INCREMENT BY 1";

 createTriggerSQL = "CREATE OR REPLACE TRIGGER trg_review_id " +
                          "BEFORE INSERT ON Reviews " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT review_seq.NEXTVAL INTO :NEW.ReviewID FROM DUAL; " +
                          "END;";
                          
                          stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'review' has been created successfully!</p>");
            
             createTableSQL = "CREATE TABLE BorrowTransactions (" +
                        "TransactionID VARCHAR(10) PRIMARY KEY, " +
                        "UserID INT NOT NULL, " +
                        "BookID INT NOT NULL, " +
                        "BorrowDate DATE NOT NULL, " +
                        "DueDate DATE NOT NULL, " +
                        "ReturnDate DATE, " +
                        "FineAmount DECIMAL(10, 2) DEFAULT 0.0, " +
                        "FOREIGN KEY (UserID) REFERENCES Users(ID), " +
                        "FOREIGN KEY (BookID) REFERENCES Books(BookID)" +
                        ")";

 createSequenceSQL = "CREATE SEQUENCE borrow_txn_seq START WITH 1 INCREMENT BY 1";

 createTriggerSQL = "CREATE OR REPLACE TRIGGER trg_borrow_txn_id " +
                          "BEFORE INSERT ON BorrowTransactions " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT TO_CHAR(borrow_txn_seq.NEXTVAL) INTO :NEW.TransactionID FROM DUAL; " +
                          "END;";
                          
                          stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'BorrowTransactions' has been created successfully!</p>");
            */
          String    createTableSQL = "CREATE TABLE Fines (" +
                        "FineID NUMBER PRIMARY KEY, " +
                        "TransactionID VARCHAR2(10) NOT NULL, " +
                        "FineAmount NUMBER(10, 2) NOT NULL, " +
                        "FineStatus VARCHAR2(10) DEFAULT 'Unpaid', " +
                        "PaymentDate DATE, " +
                        "CONSTRAINT FK_Transaction FOREIGN KEY (TransactionID) REFERENCES BorrowTransactions(TransactionID)" +
                        ")";

 String createSequenceSQL = "CREATE SEQUENCE fine_sequence START WITH 1 INCREMENT BY 1";

  String createTriggerSQL = "CREATE OR REPLACE TRIGGER trgger_fine_id " +
                          "BEFORE INSERT ON Fines " +
                          "FOR EACH ROW " +
                          "BEGIN " +
                          "SELECT fine_seq.NEXTVAL INTO :NEW.FineID FROM DUAL; " +
                          "END;";
                          
                          stmt.executeUpdate(createTableSQL); // Create the table
stmt.executeUpdate(createSequenceSQL); // Create the sequence
stmt.executeUpdate(createTriggerSQL); // Create the trigger


            out.println("<p>Table 'Fines' has been created successfully!</p>");
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                out.println("<p>Error closing connection: " + ex.getMessage() + "</p>");
            }
        }
    %>
</body>
</html>
