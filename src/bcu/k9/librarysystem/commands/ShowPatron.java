package bcu.k9.librarysystem.commands;

import java.time.LocalDate;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;
import bcu.k9.librarysystem.model.Patron;

public class ShowPatron implements Command {

	private final Integer patronID;
	
	public ShowPatron(Integer patronID) {
		this.patronID = patronID;
	}
	
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException, InvalidDateException {
		Patron patron = library.getPatronByID(this.patronID);
		System.out.println("Patron #"+patron.getId()+" | Name: "+patron.getName()+" | Email: "+patron.getEmail());
	}
}
