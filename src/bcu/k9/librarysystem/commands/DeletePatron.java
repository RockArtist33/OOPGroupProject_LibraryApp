package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;

public class DeletePatron implements Command {
	private final Integer patronID;

	public DeletePatron(Integer patronID) {
		this.patronID = patronID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		if (library.getPatronByID(this.patronID).getBooks().size() > 0) {
			throw new LibraryException("You cannot delete a patron with currently loaned books.");
		}
		library.getPatronByID(this.patronID).delete();
		System.out.println("Patron successfully deleted");
	}
	
}
