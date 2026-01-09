package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Library;

public class Return implements Command {

	private final Integer bookID;
	private final Integer patronID;
	
	public Return(Integer bookID, Integer patronID) {
		this.bookID = bookID;
		this.patronID = patronID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		Book loanedBook = library.getBookByID(bookID);
		library.getPatronByID(this.patronID).returnBook(loanedBook);
		System.out.println("Book #"+loanedBook.getId()+" successfully Returned.");
		
	}

}
