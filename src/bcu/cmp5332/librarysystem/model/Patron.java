package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.InvalidDateException;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Patron class models a patron of a library. The class has 5 variables, 4 of which are required to construct an instance. They are as follows:
 * <ul>
 * 		<li>
 * 			<p>id: {@link Integer} (required) - The primary key for each instance of the class. Used to search for specific instances.</p>
 * 		</li>
 * 		<li>
 * 			<p>name: {@link String} (required) - The name of the patron.</p>
 * 		</li>
 * 		<li>
 * 			<p>phone: {@link String} (required) - The phone number of the patron.</p>
 * 		</li>
 * 		<li>
 * 			<p>email: {@link String} (required) - The email of the patron.</p>
 * 		</li>
 * 		<li>
 * 			<p>books: {@link ArrayList} of {@link Book}s - An internal list to track the books the user has on loan.</p>
 * 		</li>
 * </ul>
 * 
 * 
 *
 *
 */
public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();
    
    /** Create a new Patron instance.
     * The constructor initialises a Patron object using the basic mandatory fields a patron should have to exist in the library.
     * @param idValue - the patron's id.
     * @param nameValue - the patron's name.
     * @param phoneNumber - the patron's phone
     * @param emailValue - the patron's email
     */
    public Patron(Integer idValue, String nameValue, String phoneNumber, String emailValue) {
    	this.id = idValue;
    	this.name = nameValue;
    	this.phone = phoneNumber;
    	this.email = emailValue;
    }
    /** Get the patron's id
     * @return the patron's id as integer
     */
    public int getId() {
		return id;
	}
    
    /** Set the patron's ID. Use only in emergency situations.
     * @return id - the integer value that will be set as the patron's id.
     */
	public String getName() {
		return name;
	}
	
	/** Sets the patron's name.
	 * @param newName = the {@link String} value that will be set as the patron's name.
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	/** Get the patron's phone.
	 * @return the patron's phone as String.
	 */
	public String getPhone() {
		return phone;
	}
	
	/** Set the patron's phone.
	 * @param newPhone - The {@link String} value that will be set as patron's phone.
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}
	
	/** Get the patron's email.
	 * @return the patron's email as String.
	 */
	public String getEmail() {
		return email;
	}
	
	/** Set the patron's email.
	 * @param newEmail - The {@link String} value that will be set as patron's email.
	 */
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	/***
	 *Get a list of books that the patron has borrowed.
	 *@return an unmodifiable list of books that the patron has borrowed. 
	 */
	public List<Book> getBooks() {
		return books;
	}
    /** Borrow a book from the library.
     *  The method uses a newly created Loan object that associates a book with the patron and updates the book with this loan information. The function also adds the book to the patron's list of borrowed books.
     *  If the book is on loan by another patron, an exception is thrown with a message saying that the book is currently on loan.
     * @param loan - the newly created loan object to associate with the book and patron. 
     * @param book - the book that will be borrowed by the patron.
     * @param dueDate - the {@link LocalDate} value that will be set as the loan's due Date
     * @throws LibraryException if the book is currently on loan to another patron.
     * @throws InvalidDateException If the due date is before the start date.
     */
    public void borrowBook(Loan loan, Book book, LocalDate dueDate) throws LibraryException, InvalidDateException {
    	if (dueDate.isAfter(LocalDate.now())) {
    		throw new InvalidDateException("Date is before or equal to the current date.");
    	}
    	
    }
    
    /**
     * Renew the loan's due date for a book that is already on loan. 
     * The method changes (renews) the due date of a book that is currently on loan by the patron. 
     * If the book to be renewed is not on loan by the patron, an exception is thrown with a message saying that the book is not on loan to this patron.
     * @param book - the book to be renewed.
     * @param dueDate - the {@link LocalDate} value that will be set as the loan's new due date.
     * @throws LibraryException If the book is not on loan to this patron.
     * @throws InvalidDateException If the due date is set before or on the start date.
     */
    public void renewBook(Book book, LocalDate dueDate) throws LibraryException, InvalidDateException {
    	if (dueDate.isAfter(LocalDate.now())) {
    		throw new InvalidDateException("Date is before or equal to the current date.");
    	}
    	// TODO: implementation here
    }
    
    /** Return a book to the library.
     * The method removes the book from the patron's list of borrowed books and returns the book to the library by removing any loan information from the book. 
     * If the book to be renewed is not on loan by the patron, an exception is thrown with a message saying that the book is not on loan to this patron.
     * @param book - the book to be returned to the library.
     * @throws LibraryException If the book is not on loan to this patron.
     * 
     */
    public void returnBook(Book book) throws LibraryException {
    	// TODO: implementation here
    }
    
    /** Add a book to the patron's list of borrowed books.
     * This method is used to add books that are on loan by the patron when loading data from the text file storage. The book given as argument to this method should already have the loan information added to it.
     * @param book - the book to be added to the patron's list of borrowed books
     */
    public void addBook(Book book) {
        // TODO: implementation here
    }
    /**<p>
     * Turns a {@link String} into a {@link Patron} object, Used when loading {@link Patron} Objects
     * </p> 
     * <blockquote><pre>
     * |                         {@link String} format                         |
     * +------------+-------------+---------------------+--------------+
     * |  Patron ID | Patron Name | Patron Phone Number | Patron email |
     * +------------+-------------+---------------------+--------------+
     * |     00     |  John Smith |        Author       |     1999     |
     * +------------+-------------+---------------------+--------------+
     * </pre>
     * </blockquote>
     * @param patronString - The string representation of a {@link Patron} object
     * @return A new Patron object matching the string representation.
     */
    public static Patron parse(String patronString) {
    	String[] patronProperties = patronString.split(";");
    	Integer id = Integer.parseInt(patronProperties[0]);
    	String name = patronProperties[1];
    	String phoneNumber = patronProperties[2];
    	String email = patronProperties[3];
    	return new Patron(id, name, phoneNumber, email);
    }
    
    /**<p>
     * Turns a {@link Patron} into a {@link String} representation of itself, Used saving {@link Patron} Objects
     * </p> 
     * <blockquote><pre>
     * |                         {@link String} format                         |
     * +------------+-------------+---------------------+--------------+
     * |  Patron ID | Patron Name | Patron Phone Number | Patron email |
     * +------------+-------------+---------------------+--------------+
     * |     00     |  John Smith |        Author       |     1999     |
     * +------------+-------------+---------------------+--------------+
     * </pre>
     * </blockquote>
     * 
     * @return A String representation of a {@link Patron} object.
     */
    public String toString() {
    	String INTERNALSEPARATOR = "::";
    	String patronString = id + INTERNALSEPARATOR + 
    						name + INTERNALSEPARATOR + 
    						phone + INTERNALSEPARATOR + 
    						email + INTERNALSEPARATOR;
    	return patronString;
    }



	
}
 