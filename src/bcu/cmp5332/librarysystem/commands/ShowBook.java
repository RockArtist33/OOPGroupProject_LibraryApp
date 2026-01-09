package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

public class ShowBook implements Command {

    private final int bookId;

    public ShowBook(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book b = library.getBookByID(bookId);
        System.out.println(b.getDetailsLong());
        if (b.isOnLoan()) {
            System.out.println("Status: On loan");
            System.out.println("Due: " + b.getDueDate());
            System.out.println("Borrowed by: Patron #" + b.getLoan().getPatron().getId()
                    + " - " + b.getLoan().getPatron().getName());
        } else {
            System.out.println("Status: Available");
        }
    }
}
