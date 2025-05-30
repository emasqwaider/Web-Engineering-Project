package classes;

import java.sql.Date;

public class Fine {
    private int fineId; 
    private String transactionId;
    private int patronId;
    private double fineAmount;
    private Date dueDate;
    private Date PaymentDate;
    private String fineStatus;

    public int getFineId() { return fineId; }
    
    public void setFineId(int fineId) { this.fineId = fineId; }
    
    public String getTransactionId() { return transactionId; }

    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
	    
    public int getPatronId() { return patronId; }
	    
    public void setPatronId(int patronId) { this.patronId = patronId; }
	    
    public double getFineAmount() { return fineAmount; }
	    
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }
	    
    public Date getDueDate() { return dueDate; }

    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    
    public Date getPaymentDate() { return PaymentDate; }

    public void setPaymentDate(Date PaymentDate) { this.PaymentDate = PaymentDate;}
    
    public String getFineStatus() { return fineStatus;}

    public void setFineStatus(String fineStatus) { this.fineStatus = fineStatus; }    
}