package bcu.k9.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;
import bcu.k9.librarysystem.model.Patron;

public class ListPatrons implements Command {

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		List<Patron> patronList = library.getPatrons();
		for (Patron patron : patronList) {
			System.out.println("Patron #"+patron.getId()+" | Name: "+patron.getName());
		}
		
	}

}
