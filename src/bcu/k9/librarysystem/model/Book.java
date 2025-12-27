package bcu.k9.librarysystem.model;

import java.time.LocalDate;
import java.util.Objects;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;

/**
 * The Book class models a book inside of a library. The class has 7 variables, 6 of which are required to construct an instance. They are as follows:
 * <ul>
 * 		<li>
 * 			<p>id: {@link Integer} (required) - The primary key for each instance of the class. Used to search for specific instances.</p>
 * 		</li>
 * 		<li>
 * 			<p>title: {@link String} (required) - The title of the book.</p>
 * 		</li>
 * 		<li>
 * 			<p>author: {@link String} (required) - The individual who wrote the book.</p>
 * 		</li>
 * 		<li>
 * 			<p>publisher: {@link String} (required) - The individual/group who published the book.</p>
 * 		</li>
 * 		<li>
 * 			<p>publicationYear: {@link String} (required) - The year the book was first published.</p>
 * 		</li>
 * 		<li>
 * 			<p>loan: {@link Loan} - An internal variable used to point to the loan that the book is currently a part of.</p>
 * 		</li>
 * 		<li>
 * 			<p>deleted: {@link Boolean} - An internal variable used to determine deleted status.</p>
 * 		</li>
 * </ul>
 * It is important to remember that whilst the Book class has functionality to change the due date
 * of the loan that it is currently a part of, you should instead fetch the loan object and change
 * the due date using that object.
 * 
 *
 *
 */
public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private Boolean deleted = false;
    private Loan loan = null;
    
    /**
     * Create a new Book instance.
     * The constructor initialises a Book object using the basic mandatory fields a book should have to exist in the library. Additional fields can be set after initialisation using the class's setter methods (e.g. setLoan(Loan) .
     * @param id - The book's ID
     * @param title - The book's title
     * @param author - The book's author
     * @param publisher - The book's publisher
     * @param publicationYear - The book's publication year
     */
    public Book(int id, String title, String author, String publisher, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }
    
    /** Get the book's id. 
     * @return The ID of the book
     */
    public int getId() {
        return id;
    } 
    
    /** Set the book's id.
     * This function should only be used in emergency situations. 
     * @param id - The Id of the book
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /** Get the book's title. 
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }
    
    /** Set the book's title. 
     *
     * @param title - The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /** Get the book's author. 
     * @return The author of the book
     */
    public String getAuthor() {
        return author;
    }
    
    /** Set the book's author. 
     * @param author - The author of the book 
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /** Gets the book's publisher
     *  @return The publisher of the Book
     */
    public String getPublisher() {
    	return publisher;
    }
    
    /** Sets the book's publisher
     * @param publisher - the publisher of the book
     */
    public void setPublisher(String publisher) {
    	this.publisher = publisher;
    }
    
    /** Get the book's publication year. 
     * @return The publication year of the book 
     */
    public String getPublicationYear() {
        return publicationYear;
    }
    
    /** Set the book's publication year. 
     * @param publicationYear - Sets the publication year of the book. 
     */
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    /** Get short description for the book. 
     * @return A short description of the book containing the book ID, title.  
     */
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }
    
    /** Get detailed info for the book. 
     * @return A complete description containing the title, author, publisher and publication year of the book. 
     */
    public String getDetailsLong() {
        return "Book #" + id + ",\nTitle: "+ title +",\nAuthor: "+ author + ",\nPublished By "+ publisher +",\nPublished in "+ publicationYear+".";
    }
    
    /** Check if the book is on loan. 
     * The function checks if the book has a Loan associated with it and returns the results.
     * @return true if the book is on loan and false if the book is available
     */
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    /** Get the book status. 
     * The function returns "On loan" if the book is on loan and "Available" if the book is available.
     * @return A string representing the status of the book.
     */
    public String getStatus() {
        if (loan != null) {
        	return "Out on loan.";
        }
        return "In library.";
    }
    
    /** Get the due date of a book that is on loan. 
     * @return the due date of the loan.
     */
    public LocalDate getDueDate() {
        if (loan != null) {
        	return loan.getDueDate();
        }  
        return null;
    }
    
    /** Set the due date of a book. 
     * @param - The new due date of the loan
     */
    public void setDueDate(LocalDate dueDate) throws LibraryException, InvalidDateException {
    	if (loan.getStartDate().isAfter(dueDate)) {
    		throw new InvalidDateException("Due date cannot be before Start date");
    	}
    	
    	loan.setDueDate(dueDate);
    }
    
    /** Get the book's Loan object that holds information about a book that is on loan. 
     * @return returns the pointer to the loan.
     */
    public Loan getLoan() {
        return loan;
    }
    
    /** Sets the loan parameter to a new Loan object referencing this class.
     * @param loan - The pointer to a new Loan object
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
    
    /** Retrieves the deleted status of the Book
     * 	This function retrieves the deleted status of the book
     *  @return true if it is deleted, false if otherwise
     */
    public Boolean getDeleteStatus() {
    	return this.deleted;
    }
    
    /** Sets the deleted flag to true
     *  This function sets the deleted flag to true, hiding it from the user until it is undeleted
     */
    
    public void delete() {
    	this.deleted = true;
    }
    
    /** Sets the deleted flag to true
     *  This function sets the deleted flag to false, showing it from the user until it is deleted
     */
    
    public void unDelete() {
    	this.deleted = false;
    }
    
    /** Return the book to the library. 
     * This function is used to unset any loan information that exists for the book. 
     */
    
    public void returnToLibrary() {
        loan = null;
    }
    
    /**<p>
     * Turns a {@link String} into a book object, Used when loading {@link Book} Objects
     * </p> 
     * <blockquote><pre>
     * |                        {@link String} format                        |
     * +----------+------------+-------------+-----------------------+
     * |  Book ID | Book Title | Book Author | Book Publication Year |
     * +----------+------------+-------------+-----------------------+
     * |    00    |  The Book  |    Author   |          1999         |
     * +----------+------------+-------------+-----------------------+
     * </pre>
     * </blockquote>
     * @param bookString - A string representation of a book object
     * @return A book object matching the string representation
     */
    public static Book parse(String bookString) {
    	String[] bookProperties = bookString.split("::");
    	Integer id = Integer.parseInt(bookProperties[0]);
    	String title = bookProperties[1];
    	String author = bookProperties[2];
    	String publisher = bookProperties[3];
    	String publicationYear = bookProperties[4];
    	Book book = new Book(id, title, author, publisher, publicationYear);
    	if (Objects.equals(bookProperties[5], "true")) {
    		book.delete();
    	}
    	return book;
    }
    
    /**<p>
     * Turns a {@link Book} into a {@link String} representation of itself Used when saving {@link Book} Objects.
     * </p> 
     * <blockquote><pre>
     * |                        {@link String} format                        |
     * +----------+------------+-------------+-----------------------+
     * |  Book ID | Book Title | Book Author | Book Publication Year |
     * +----------+------------+-------------+-----------------------+
     * |    00    |  The Book  |    Author   |          1999         |
     * +----------+------------+-------------+-----------------------+
     * </pre>
     * </blockquote>
     * @return A string representation of a {@link Book} Object.
     * 
     */
    public String toString() {
    	String SEPARATOR = "::";
    	String bookString = id + SEPARATOR +
    						title + SEPARATOR +
    						author + SEPARATOR +
    						publisher + SEPARATOR +
    						publicationYear + SEPARATOR +
    						deleted.toString() + SEPARATOR;
    	return bookString;
    }
}
