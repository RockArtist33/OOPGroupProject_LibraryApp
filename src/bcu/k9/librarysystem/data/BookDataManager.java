package bcu.k9.librarysystem.data;

import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookDataManager implements DataManager {
    
    private final String RESOURCE = "./resources/data/books.txt";
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR);
                try {
                    Book book = Book.parse(line);
                    library.addBook(book);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
        	SaveFileHandler.clearFile(RESOURCE);
        	for (Book book : library.getBooks()) {
                String line = book.toString();
                SaveFileHandler.writeStringToFile(line, RESOURCE);
            }
        }
    }
}
 