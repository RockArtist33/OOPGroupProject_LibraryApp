package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
private final String RESOURCE = "./resources/data/loans.txt";
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    Loan loan = Loan.parse(library, line);
                    loan.getBook().setLoan(loan);;
                    library.addLoan(loan);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse loan id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Loan loan : library.getLoans()) {
            	String line = loan.toString();
            	SaveFileHandler.clearFile(RESOURCE);
                SaveFileHandler.writeStringToFile(line, RESOURCE);
            }
        }
    }
}
 