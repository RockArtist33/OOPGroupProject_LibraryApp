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
    /**<p>
     * Turns a {@link String} into a {@link Patron} object, Used when loading and saving {@link Patron} Objects
     * <p> 
     * <blockquote><pre>
     * {@link String} format
     * |  Patron ID | Patron Name | Patron Phone Number | Patron email |
     * |     00     |  John Smith |        Author       |     1999     |
     * <blockquote>
     * 
     */
    // Patron String: 00;name;;01234567890;test@example.com;;
    public static Patron parse(String patronString) {
    	String[] patronProperties = patronString.split(";");
    	Integer id = Integer.parseInt(patronProperties[0]);
    	String name = patronProperties[1];
    	String phoneNumber = patronProperties[2];
    	String email = patronProperties[3];
    	return new Patron(id, name, phoneNumber, email);
    }
    
    public String toString() {
    	String INTERNALSEPARATOR = "::";
    	String patronString = id + INTERNALSEPARATOR + 
    						name + INTERNALSEPARATOR + 
    						phone + INTERNALSEPARATOR + 
    						email + INTERNALSEPARATOR;
    	return patronString;
    }



	
}
 