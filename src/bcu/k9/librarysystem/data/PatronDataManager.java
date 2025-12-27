package bcu.k9.librarysystem.data;

import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;
import bcu.k9.librarysystem.model.Loan;
import bcu.k9.librarysystem.model.Patron;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PatronDataManager implements DataManager {

private final String RESOURCE = "./resources/data/patrons.txt";
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR);
                try {
                	Patron patron = Patron.parse(line);
                	library.addPatron(patron);
                	
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse patron id " + properties[0] + " on line " + line_idx
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
                String line = patron.toString();
                SaveFileHandler.writeStringToFile(line, RESOURCE);
            }
        }
    }
}
 