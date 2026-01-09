package bcu.k9.librarysystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Loan;
import bcu.k9.librarysystem.model.Patron;

class Patrontests {

	@Test
	void checkGetSetFunctions() throws LibraryException, InvalidDateException {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John Smith", "07755678246", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		assertEquals(patron.getId(), 10);
		assertEquals(patron.getName(), "John Smith");
		assertEquals(patron.getPhone(), "07755678246");
		assertEquals(patron.getEmail(), "test@example.com");
		
		patron.setName("1");
		patron.setPhone("2");
		patron.setEmail("3");
		
		assertEquals(patron.getName(), "1");
		assertEquals(patron.getPhone(), "2");
		assertEquals(patron.getEmail(), "3");
		
	}
	
	@Test
	void testBorrowing() throws LibraryException, InvalidDateException {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John Smith", "07755678246", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		patron.borrowBook(loan, book, LocalDate.parse("2066-11-12"));
		assertEquals(patron.getBooks().get(0), book);
	}
	
	@Test
	void testRenewal() throws LibraryException, InvalidDateException {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John Smith", "07755678246", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		patron.borrowBook(loan, book, LocalDate.parse("2066-11-12"));
		patron.renewBook(book, LocalDate.parse("2099-11-12"));
		assertEquals(patron.getBooks().get(0).getDueDate().toString(), "2099-11-12");
	}
	
	@Test
	void testReturn() throws LibraryException, InvalidDateException {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John Smith", "07755678246", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		patron.borrowBook(loan, book, LocalDate.parse("2066-11-12"));
		patron.returnBook(book);
		assertEquals(patron.getBooks().size(), 0);
	}
	
	@Test
	void testParsing() {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John Smith", "07755678246", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		assertEquals(patron.toString(), "10::John Smith::07755678246::test@example.com::{}::");
		assertEquals(Patron.parse("10::John Smith::07755678246::test@example.com::{}::").toString(), "10::John Smith::07755678246::test@example.com::{}::");
		
	}

}
