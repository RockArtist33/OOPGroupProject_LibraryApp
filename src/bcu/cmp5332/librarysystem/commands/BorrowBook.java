package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.InvalidDateException;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

public class BorrowBook implements Command {

    private final int patronId;
    private final int bookId;
    private final LocalDate dueDate;

    public BorrowBook(int patronId, int bookId, LocalDate dueDate) {
        this.patronId = patronId;
        this.bookId = bookId;
        this.dueDate = dueDate;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        try {
            library.issueBook(bookId, patronId, dueDate);
            System.out.println("Book #" + bookId + " issued to Patron #" + patronId + " until " + dueDate);
        } catch (InvalidDateException e) {
            throw new LibraryException(e.getMessage());
        }
    }
}
