package classes;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Transaction {
	 private String transactionId;

    public void setTransactionId() {
        transactionId= generateTransId();
    }
    public void setTransactionId(String tran) {
        transactionId=tran;
    }
	    private int patronId;
	    private String isbn;
	    private Date borrowDate;
	    private Date dueDate;
	    private Date returnDate;
	    private double fine;
             private String returnStatus;

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus() {
        if(returnDate==null)
        this.returnStatus ="reserved" ;
        else  this.returnStatus ="returned" ;
    }
 
	    // Getters and Setters
	    public String getTransactionId() {
	        return transactionId;
	    }


	    public int getPatronId() {
	        return patronId;
	    }

	    public void setPatronId(int patronId) {
	        this.patronId = patronId;
	    }
	    public String getISBN() {
	        return isbn;
	    }

	    public void setISBN(String bookId) {
	        this.isbn = bookId;
	    }

	    public Date getBorrowDate() {
	        return borrowDate;
	    }

	    public void setBorrowDate(Date borrowDate) {
	        this.borrowDate = borrowDate;
	    }

	    public Date getDueDate() {
	        return dueDate;
	    }

	    public void setDueDate(Date dueDate) {
	        this.dueDate = dueDate;
	    }

	    public Date getReturnDate() {
	        return returnDate;
	    }
	    public void setReturnDate(Date returnDate) {
	        this.returnDate = returnDate;
	    }

	    public double getFine() {
                calculateFine();
	        return fine;
	    }

	    public void setFine(double fine) {
	        this.fine = fine;
	    }
    public static String generateTransId() {
        return "BT" + (int) (Math.random() * 1000000);
    }
    
    public void calculateFine() {
    Calendar currentCalendar = Calendar.getInstance();
    java.util.Date currentDate = currentCalendar.getTime(); // Get current date and time (no need to cast)

    // Check if the current date is after the due date
    if (currentDate.after(dueDate)) {
        // Calculate the duration in days
        long borrowDurationDays = (currentDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24); // Convert from milliseconds to days
        fine = borrowDurationDays; // Apply fine based on the number of overdue days
    } else {
        fine = 0; // No fine if not overdue
    }
}

}
	

    
