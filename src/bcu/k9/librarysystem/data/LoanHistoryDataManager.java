package bcu.k9.librarysystem.data;

import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LoanHistoryDataManager implements DataManager {
    
	private final String RESOURCE = "./resources/data/loanhistory.txt";
	private final String SEPARATOR = "::"; // different to ensure proper read
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                	List<Loan> loanHistory = new ArrayList<>();
                	String[] loanHistoryStringList = properties[1].substring(1, properties[1].length() - 1).split(",");
                	for (String loanString : loanHistoryStringList) {
                		if (loanString.length() > 1) {                			
                			loanHistory.add(Loan.parse(library, loanString.strip()));
                		}
                	}
                	library.getPatronByID(Integer.parseInt(properties[0])).setLoanHistory(loanHistory);
                    
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse loan history for patron " + properties[0] + " on line " + line_idx
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
            for (Patron patron : library.getPatrons()) {
            	String line = patron.getId() + SEPARATOR + 
            			patron.getLoanHistory().toString() + SEPARATOR;
            	SaveFileHandler.writeStringToFile(line, RESOURCE);
            }
        }
    }
}
 