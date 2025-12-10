package bcu.k9.librarysystem.commands;

import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Library;

import java.time.LocalDate;

public class AddBook implements  Command {

    private final String title;
    private final String author;
    private final String publisher;
    private final String publicationYear;

    public AddBook(String title, String author, String publisher, String publicationYear) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int maxId = 0;
    	if (library.getBooks().size() > 0) {
    		int lastIndex = library.getBooks().size() - 1;
            maxId = library.getBooks().get(lastIndex).getId();
    	}
        Book book = new Book(++maxId, title, author, publisher, publicationYear);
        library.addBook(book);
        System.out.println("Book #" + book.getId() + " added.");
    }
}
 