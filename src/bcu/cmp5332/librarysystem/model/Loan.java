package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.InvalidDateException;

public class Loan {
    
    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) throws InvalidDateException {
		if (startDate.isAfter(dueDate)) {
			throw new InvalidDateException("Start date cannot be before Due date.");
		}
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) throws InvalidDateException {
		if (dueDate.isBefore(startDate)) {
			throw new InvalidDateException("Due date cannot be before Start date.");
		}
		this.dueDate = dueDate;
	}
}
 