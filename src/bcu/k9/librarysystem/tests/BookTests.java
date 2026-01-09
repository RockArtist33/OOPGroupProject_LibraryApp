package bcu.k9.librarysystem.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Loan;
import bcu.k9.librarysystem.model.Patron;

class BookTests {

	@Test
	void checkGetSetFunctions() {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John", "Smith", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		assertEquals(book.getId(), 0);
		assertEquals(book.getTitle(), "Book Title");
		assertEquals(book.getAuthor(), "Book Author");
		assertEquals(book.getPublisher(), "Publisher");
		assertEquals(book.getPublicationYear(), "1970");
		
		book.setTitle("1");
		book.setAuthor("2");
		book.setPublisher("3");
		book.setPublicationYear("4");
		book.setLoan(loan);
		
		assertEquals(book.getTitle(), "1");
		assertEquals(book.getAuthor(), "2");
		assertEquals(book.getPublisher(), "3");
		assertEquals(book.getPublicationYear(), "4");
		assertEquals(book.getLoan().getPatron().getId(), 10);
		
	}
	
	@Test
	void testLoanStatus() {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John", "Smith", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		book.setLoan(loan);
		assertEquals(book.getStatus(), "Out on loan.");
		assertEquals(book.isOnLoan(), true);
	}
	
	@Test
	void testParsing() {
		Book book = new Book(0, "Book Title", "Book Author", "Publisher", "1970");
		Patron patron = new Patron(10, "John", "Smith", "test@example.com");
		Loan loan = new Loan(patron, book, LocalDate.now(), LocalDate.parse("2066-01-01"));
		
		book.setLoan(loan);
		
		assertEquals(book.toString(), "0::Book Title::Book Author::Publisher::1970::");
		assertEquals(Book.parse("0::Book Title::Book Author::Publisher::1970::").toString(), "0::Book Title::Book Author::Publisher::1970::");
		
	}

}
