package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class ShowPatron implements Command {

    private final int patronId;

    public ShowPatron(int patronId) {
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron p = library.getPatronByID(patronId);

        System.out.println("Patron #" + p.getId());
        System.out.println("Name: " + p.getName());
        System.out.println("Phone: " + p.getPhone());
        System.out.println("Email: " + p.getEmail());

        System.out.println("Borrowed books:");
        if (p.getBooks().isEmpty()) {
            System.out.println("(none)");
        } else {
            for (Book b : p.getBooks()) {
                System.out.println("- " + b.getDetailsShort() + " (due " + b.getDueDate() + ")");
            }
        }
    }
}
