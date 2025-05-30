package classes;

import java.util.Date;

/**
 *
 * @author user
 */
public class SharedBook {
      private int shareId;
    private int senderId;
    private int receiverId;
    private String isbn;
    private String sharedMessage;
    private Date sharedDate;

    public SharedBook() {
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSharedMessage() {
        return sharedMessage;
    }

    public void setSharedMessage(String sharedMessage) {
        this.sharedMessage = sharedMessage;
    }

    public Date getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(Date sharedDate) {
        this.sharedDate = sharedDate;
    }
    
    
    
}