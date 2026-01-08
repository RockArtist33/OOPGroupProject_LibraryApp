package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.InvalidDateException;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

public class RenewBook implements Command {

    private final int bookId;
    private final LocalDate newDueDate;

    public RenewBook(int bookId, LocalDate newDueDate) {
        this.bookId = bookId;
        this.newDueDate = newDueDate;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        try {
            library.renewBook(bookId, newDueDate);
            System.out.println("Book #" + bookId + " renewed until " + newDueDate);
        } catch (InvalidDateException e) {
            throw new LibraryException(e.getMessage());
        }
    }
}
