package bcu.k9.librarysystem.commands;

import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Library;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        for (Book book : books) {
            System.out.println(book.getDetailsShort());
        }
        System.out.println(books.size() + " book(s)");
    }
}
 