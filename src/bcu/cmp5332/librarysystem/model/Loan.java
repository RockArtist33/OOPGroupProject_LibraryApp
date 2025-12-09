package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.InvalidDateException;
import bcu.cmp5332.librarysystem.main.LibraryException;

public class Loan {
    
	private Integer id;
    private Patron patron;
    private Book book;
    private LocalDate startDate;
	private LocalDate dueDate;
	private Boolean completed;

    public Loan(Integer id, Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
    	this.id = id;
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.completed = false;
    }
    
    public Integer getId() {
		return id;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) throws InvalidDateException {
		if (startDate.isAfter(dueDate)) {
			throw new InvalidDateException("Start date cannot be before Due date.");
		}
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) throws InvalidDateException {
		if (dueDate.isBefore(startDate)) {
			throw new InvalidDateException("Due date cannot be before Start date.");
		}
		this.dueDate = dueDate;
	}
	
	/** This function is used to grab the status of the loan, important when tracking loan history 
	 * 
	 */
	public boolean getStatus() {
		return this.completed;
	}
	
	public void completeLoan() {
		this.completed = true;
	}
	
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
     * Turns a {@link Loan} object into a {@link String}, Used when saving {@link Loan} objects
     * <p> 
     * <blockquote><pre>
     * {@link String} format
     * |  Loan ID | {@link Patron} ID | {@link Book} ID | Start Date | Due Date | Completed |
     * |    00    |     00    |    00   | 2012-12-21 |2025-12-31|   false   |
     * <blockquote>
     * 
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
 