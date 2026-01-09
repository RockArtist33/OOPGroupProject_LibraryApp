package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class LoanHistory implements Command {

    private final int patronId;

    public LoanHistory(int patronId) {
        this.patronId = patronId;
    }

    // 8.1 Loan history for a given patron (CLI command: loanhistory <patronId>)
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron p = library.getPatronByID(patronId);
        System.out.println("Loan history for Patron #" + p.getId() + " - " + p.getName());

        if (p.getLoanHistory().isEmpty()) {
            System.out.println("(no loans yet)");
            return;
        }

        for (Loan loan : p.getLoanHistory()) {
            System.out.println("- Book #" + loan.getBook().getId() + " (" + loan.getBook().getTitle() + ")"
                    + " from " + loan.getStartDate()
                    + " due " + loan.getDueDate());
        }
    }
}
