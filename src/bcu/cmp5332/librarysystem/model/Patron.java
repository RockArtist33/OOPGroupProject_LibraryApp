package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.InvalidDateException;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();
    
    public Patron(Integer idValue, String nameValue, String phoneNumber, String emailValue) {
    	this.id = idValue;
    	this.name = nameValue;
    	this.phone = phoneNumber;
    	this.email = emailValue;
    }
    
    public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}

	public List<Book> getBooks() {
		return books;
	}
    
    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException, InvalidDateException {
    	if (dueDate.isAfter(LocalDate.now())) {
    		throw new InvalidDateException("Date is before or equal to the current date.");
    	}
    	
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException, InvalidDateException {
    	if (dueDate.isAfter(LocalDate.now())) {
    		throw new InvalidDateException("Date is before or equal to the current date.");
    	}
    	// TODO: implementation here
    }

    public void returnBook(Book book) throws LibraryException {
    	// TODO: implementation here
    }
    
    public void addBook(Book book) {
        // TODO: implementation here
    }



	
}
 