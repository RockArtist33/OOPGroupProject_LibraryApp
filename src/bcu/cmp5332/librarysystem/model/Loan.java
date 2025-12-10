package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;
import java.util.ArrayList;

import bcu.cmp5332.librarysystem.main.InvalidDateException;
import bcu.cmp5332.librarysystem.main.LibraryException;

/**
 * The Loan class models a Loan of a {@link Book} for a {@link Patron}. The class has 6 variables, 5 of which are required to construct an instance. They are as follows:
 * <ul>
 * 		<li>
 * 			<p>id: {@link Integer} (required) - The primary key for each instance of the class. Used to search for specific instances.</p>
 * 		</li>
 * 		<li>
 * 			<p>patron: {@link Patron} (required) - The pointer to a specific {@link Patron}.</p>
 * 		</li>
 * 		<li>
 * 			<p>book: {@link Book} (required) - The pointer to the loaned {@link Book}.</p>
 * 		</li>
 * 		<li>
 * 			<p>startDate: {@link LocalDate} (required) - The starting date of the loan.</p>
 * 		</li>
 * 		<li>
 * 			<p>dueDate: {@link LocalDate} (required) - The due date of the loan</p>
 *  	</li>
 * 		<li>
 * 			<p>completed: {@link Boolean} - An internal flag to track the current status of the loan.</p>
 * 		</li>
 * </ul>
 * 
 * 
 *
 *
 */
public class Loan {
    
	private Integer id;
    private Patron patron;
    private Book book;
    private LocalDate startDate;
	private LocalDate dueDate;
	private Boolean completed;
	/** 
	 * Create a new Loan instance.
	 * The constructor initialises a new Book instance using all the mandatory fields of the class.
	 *
	 *@param id - the ID of the loan..
	 *@param patron - the patron that borrowed the book.
	 *@param book - the book that is on loan.
	 *@param startDate - the start date of the loan as {@link LocalDate}.
	 *@param dueDate - the due date of the loan as {@link LocalDate}.
	 */
    public Loan(Integer id, Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
    	this.id = id;
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.completed = false;
    }
    
    /** Gets the ID of the loan.
     * @return The ID of the loan.
     */
    public Integer getId() {
		return id;
	}
    /** Get the loan's {@link Patron} object.
     * Get the patron associated with the loan.
     * @return the loan's patron.
     */
	public Patron getPatron() {
		return patron;
	}
	
	/** Sets the loan's {@link Patron} object. 
	 * @param patron - the {@link Patron} object that will be set as the loans patron.
	 */
	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	/** Get the loan's {@link Book} object.
     * Get the book associated with the loan.
     * @return the loan's book.
     */
	public Book getBook() {
		return book;
	}
	/** Sets the loan's {@link Book} object. 
	 * @param book - the {@link Book} object that will be set as the loans book.
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/** Gets the loan's start date. 
	 * @return the loan's start date as {@link LocalDate}.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}
	
	/** Set the loan's start date. 
	 * @param startDate - the {@link LocalDate} value that will be set as the loan's start date.
	 */
	public void setStartDate(LocalDate startDate) throws InvalidDateException {
		if (startDate.isAfter(dueDate)) {
			throw new InvalidDateException("Start date cannot be before Due date.");
		}
		this.startDate = startDate;
	}
	/** Gets the loan's due date. 
	 * @return the loan's due date as {@link LocalDate}.
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}
	/** Set the loan's due date. 
	 * @param dueDate - the {@link LocalDate} value that will be set as the loan's due date.
	 */
	public void setDueDate(LocalDate dueDate) throws InvalidDateException {
		if (dueDate.isBefore(startDate)) {
			throw new InvalidDateException("Due date cannot be before Start date.");
		}
		this.dueDate = dueDate;
	}
	
	/** Fetches the current status of the loan.
	 * This function is used to grab the status of the loan, important when tracking loan history.
	 * @return true for a completed loan, false for an outstanding loan.
	 */
	public boolean getStatus() {
		return this.completed;
	}
	
	/** Sets the status of the loan to complete.
	 * This function sets the completed flag to true.
	 * 
	 */
	public void completeLoan() {
		this.completed = true;
	}
	
	/**<p>
     * Turns a {@link Loan} object into a {@link String}, Used when saving {@link Loan} objects.
     * </p> 
     * <blockquote><pre>
     * |                            {@link String} format                           |
     * +----------+-----------+---------+------------+----------+-----------+
     * |  Loan ID | {@link Patron} ID | {@link Book} ID | Start Date | Due Date | Completed |
     * +----------+-----------+---------+------------+----------+-----------+
     * |    00    |     01    |    02   | 2012-12-21 |2025-12-31|   false   |
     * +----------+-----------+---------+------------+----------+-----------+
     * </pre>
     * </blockquote>
     * @param library - The main library instance for the program.
     * @param loanString - A string representation of a {@link Loan} object.
     * @return A new {@link Loan} object matching the string representation.
     */
	public static Loan parse(Library library, String loanString) throws NumberFormatException, LibraryException {
		String SEPARATOR = "::";
		String[] properties = loanString.split(SEPARATOR);
		
		int id = Integer.parseInt(properties[0]);
        Patron patron = library.getPatronByID(Integer.parseInt(properties[1]));
        Book book = library.getBookByID(Integer.parseInt(properties[2]));
        LocalDate startDate = LocalDate.parse(properties[3]);
        LocalDate endDate = LocalDate.parse(properties[4]);
        return new Loan(id, patron, book, startDate, endDate);
	}
	
	/**<p>
     * Turns a {@link Loan} object into a {@link String}, Used when saving {@link Loan} objects.
     * </p> 
     * <blockquote><pre>
     * |                            {@link String} format                           |
     * +----------+-----------+---------+------------+----------+-----------+
     * |  Loan ID | {@link Patron} ID | {@link Book} ID | Start Date | Due Date | Completed |
     * +----------+-----------+---------+------------+----------+-----------+
     * |    00    |     01    |    02   | 2012-12-21 |2025-12-31|   false   |
     * +----------+-----------+---------+------------+----------+-----------+
     * </pre>
     * </blockquote>
     * @return A string representation of a {@link Loan} object.
     */
	public String toString() {
		String SEPARATOR = "::";
		String loanString = id + SEPARATOR +
							patron.getId() + SEPARATOR + 
							book.getId() + SEPARATOR +
							startDate.toString() + SEPARATOR +
							dueDate.toString() + SEPARATOR +
							completed.toString() + SEPARATOR;
		return loanString;
	}
}
 