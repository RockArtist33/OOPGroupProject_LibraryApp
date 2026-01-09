package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;

public class DeleteBook implements Command {
	private final Integer bookID;

	public DeleteBook(Integer bookID) {
		this.bookID = bookID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		if (library.getBookByID(this.bookID).isOnLoan()) {
			throw new LibraryException("You cannot delete a book on loan.");
		}
		library.getBookByID(this.bookID).delete();
		System.out.println("Book successfully deleted");
	}
	
}
