package bcu.k9.librarysystem.model;

import java.util.*;

import bcu.k9.librarysystem.main.LibraryException;

public class Library {
    
    private final int loanPeriod = 7;
    private final Map<Integer, Patron> patrons = new TreeMap<>();
    private final Map<Integer, Book> books = new TreeMap<>();
    private final Map<Integer, Loan> loans = new TreeMap<>();

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public List<Book> getBooks() {
        List<Book> out = new ArrayList<>(books.values());
        return Collections.unmodifiableList(out);
    }
    
    public List<Patron> getPatrons() {
    	List<Patron> out = new ArrayList<>(patrons.values());
    	return Collections.unmodifiableList(out);
    }
    
    public List<Loan> getLoans() {
    	List<Loan> out = new ArrayList<>(loans.values());
    	return Collections.unmodifiableList(out);
    }

    public Book getBookByID(int id) throws LibraryException {
        if (!books.containsKey(id)) {
            throw new LibraryException("There is no such book with that ID.");
        }
        return books.get(id);
    }

    public Patron getPatronByID(int id) throws LibraryException {
        if (!books.containsKey(id)) {
        	throw new LibraryException("There is no such book with that ID.");
        }
        return patrons.get(id);
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }

    public void addPatron(Patron patron) {
        if (patrons.containsKey(patron.getId())) {
        	throw new IllegalArgumentException("Duplicate patron ID.");
        }
        patrons.put(patron.getId(), patron);
    }
    
    public void addLoan(Loan loan) {
    	if (loans.containsKey(loan.getId())) {
    		throw new IllegalArgumentException("Duplicate loan ID.");
    	}
    }
    
    public void deleteLoan(Integer loanID) {
    	if (!loans.containsKey(loanID)) {
    		throw new IllegalArgumentException("Loan ID not found in library Database.");
    	}
    	Loan loan = loans.get(loanID);
    	loan.completeLoan();
    }
}
 