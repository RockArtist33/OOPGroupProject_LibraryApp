package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Library;

public class Renew implements Command {
	
	private final LocalDate dueDate;
	private final Integer bookID;
	private final Integer patronID;
	
	public Renew(LocalDate dueDate, Integer bookID, Integer patronID) {
		this.dueDate = dueDate;
		this.bookID = bookID;
		this.patronID = patronID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		Book loanedBook = library.getBookByID(bookID);
		library.getPatronByID(patronID).renewBook(loanedBook, this.dueDate);
		System.out.println("Book #"+loanedBook.getId()+" successfully renewed. Due on "+loanedBook.getDueDate());
	}

}
