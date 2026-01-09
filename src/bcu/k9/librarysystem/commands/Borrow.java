package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;
import bcu.k9.librarysystem.model.Loan;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Patron;

public class Borrow implements Command {
	private final Integer bookID;
	private final Integer patronID;
	private final LocalDate dueDate;
	
	public Borrow(LocalDate dueDate, Integer bookID, Integer patronID) {
		this.dueDate = dueDate;
		this.bookID = bookID;
		this.patronID = patronID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		Book loanedBook = library.getBookByID(bookID);
		Patron patron = library.getPatronByID(patronID);
		Loan newLoan = new Loan(patron, loanedBook, currentDate, dueDate);
		try {			
			if (patron.getBooks().size() == 2) {
				throw new LibraryException("A single patron cannot loan more than 2 books at a time.");
			}
			patron.borrowBook(newLoan, loanedBook, dueDate);
			System.out.println("Book #"+loanedBook.getId()+" successfully borrowed. Due on "+loanedBook.getDueDate());			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
