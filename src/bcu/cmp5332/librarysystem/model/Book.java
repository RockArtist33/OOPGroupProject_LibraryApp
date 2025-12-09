package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.InvalidDateException;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;

    private Loan loan = null;

    public Book(int id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
        return "Book #" + id + ",\nTitle: "+ title +",\nAuthor: "+ author + ",\nPublished in "+ publicationYear+".";
    }
    
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    public String getStatus() {
        if (loan != null) {
        	return "Out on loan.";
        }
        return "In library.";
    }
    
    public LocalDate getDueDate() {
        if (loan != null) {
        	return loan.getDueDate();
        }  
        return null;
    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException, InvalidDateException {
    	if (loan.getStartDate().isAfter(dueDate)) {
    		throw new InvalidDateException("Due date cannot be before Start date");
    	}
    	
    	loan.setDueDate(dueDate);
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
    
    /**<p>
     * Turns a {@link String} into a book object, Used when loading and saving {@link Book} Objects
     * <p> 
     * <blockquote><pre>
     * {@link String} format
     * |  Book ID | Book Title | Book Author | Book Publication Year |
     * |    00    ;  The Book  ;    Author   ;          1999         ;
     * <blockquote>
     * 
     */
    public static Book parse(String bookString) {
    	String[] bookProperties = bookString.split(";;");
    	Integer id = Integer.parseInt(bookProperties[0]);
    	String title = bookProperties[1];
    	String author = bookProperties[2];
    	String publicationYear = bookProperties[3];
    	Book book = new Book(id, title, author, publicationYear);
    	return book;
    }
    
    public String toString() {
    	String INTERNALSEPARATOR = "::";
    	String bookString = id + INTERNALSEPARATOR +
    						title + INTERNALSEPARATOR +
    						author + INTERNALSEPARATOR +
    						publicationYear + INTERNALSEPARATOR;
    	return bookString;
    }
}
