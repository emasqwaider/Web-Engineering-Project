package classes ;

import java.util.ArrayList;
import java.util.List;

public class Patron extends User {
	private int borrowingLimit;
    private double outstandingFines;

    public Patron() {
        setRole("patron");
    }

    public Patron(int userId, String name, String email) {
        super(userId, name, email);
        setRole("patron");
    }

    // Getters and Setters
    public int getBorrowingLimit() {
        return borrowingLimit;
    }

    public void setBorrowingLimit(int borrowingLimit) {
        this.borrowingLimit = borrowingLimit;
    }

    public double getOutstandingFines() {
        return outstandingFines;
    }

    public void setOutstandingFines(double outstandingFines) {
        this.outstandingFines = outstandingFines;
    }
    
    
    public String getRole() {
	// TODO Auto-generated method stub
	return role;
    }
}

