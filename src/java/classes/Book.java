package classes;

public class Book {
	 
	    private String title;
	    private String author;
	    private String genre;
	    private String isbn;
	    private int yearOfPublication;
	    private String availability;
             private Double rating ;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
            
          

	    // Getters and Setters
	    

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }
	    public String getAuthor() {
	        return author;
	    }

	    public void setAuthor(String author) {
	        this.author = author;
	    }

	    public String getGenre() {
	        return genre;
	    }

	    public void setGenre(String genre) {
	        this.genre = genre;
	    }

	    public String getIsbn() {
	        return isbn;
	    }

	    public void setIsbn(String isbn) {
	        this.isbn = isbn;
	    }

	    public int getYearOfPublication() {
	        return yearOfPublication;
	    }

	    public void setYearOfPublication(int yearOfPublication) {
	        this.yearOfPublication = yearOfPublication;
	    }

	    public String getAvailability() {
	        return availability;
	    }

	    public void setAvailability(String availability) {
	        this.availability = availability; 
            }
            
}
