package bcu.k9.librarysystem.data;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
	private final String RESOURCE = "./resources/data/loans.txt";
	private final String SEPARATOR = ";;"; // different to ensure proper read
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    Loan temploan = Loan.parse(library, line);
                    temploan.getPatron().borrowBook(temploan, temploan.getBook(), temploan.getDueDate());
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse loan id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                } catch (InvalidDateException e) {
					System.out.println("Saved loan due date was invalid, is it the correct format? | Loan id: "+properties[0]+" on line "+line_idx);
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
            	if (book.isOnLoan()) {
            		String line = book.getLoan().toString();
                    SaveFileHandler.writeStringToFile(line, RESOURCE);
            	}
            }
        }
    }
}
 