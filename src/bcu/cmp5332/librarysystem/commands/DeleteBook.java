package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

public class DeleteBook implements Command {
    private final int bookId;
    public DeleteBook(int bookId) { this.bookId = bookId; }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        library.deleteBook(bookId);
        System.out.println("Book #" + bookId + " deleted (soft delete).");
    }
}
