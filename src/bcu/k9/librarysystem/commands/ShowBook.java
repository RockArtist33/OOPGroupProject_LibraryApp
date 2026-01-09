package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;

public class ShowBook implements Command {

	private final Integer bookID;
	
	public ShowBook(Integer bookID) {
		this.bookID = bookID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		System.out.println(library.getBookByID(this.bookID).getDetailsLong());
	}
}
