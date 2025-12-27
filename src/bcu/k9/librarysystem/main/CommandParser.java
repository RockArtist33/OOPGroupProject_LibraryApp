package bcu.k9.librarysystem.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;


import bcu.k9.librarysystem.commands.*;
import bcu.k9.librarysystem.data.LibraryData;

public class CommandParser {
    
    public static Command parse(String line) throws IOException, LibraryException {
        try {
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            // TODO: Link your implemented features to commands here 
            if (cmd.equals("addbook")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Title: ");
                String title = br.readLine();
                System.out.print("Author: ");
                String author = br.readLine();
                System.out.print("Publisher: ");
                String publisher = br.readLine();
                System.out.print("Publication Year: ");
                String publicationYear = br.readLine();
                
                return new AddBook(title, author, publisher, publicationYear);
            } else if (cmd.equals("addpatron")) {
            	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String name = br.readLine();
                System.out.print("Phone Number: ");
                String phone = br.readLine();
                System.out.print("Email: ");
                String email = br.readLine();
                
                return new AddPatron(name, phone, email);
                
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listbooks")) {
                    return new ListBooks();
                } else if (line.equals("listpatrons")) {
                	return new ListPatrons();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showbook")) {
                	return new ShowBook(id);
                    
                } else if (cmd.equals("showpatron")) {
                    return new ShowPatron(id);
                } else if (cmd.equals("deletebook")) {
                	return new DeleteBook(id);
                } else if (cmd.equals("deletepatron")) {
                	return new DeletePatron(id);
                }
            } else if (parts.length == 3) {
                int patronID = Integer.parseInt(parts[1]);
                int bookID = Integer.parseInt(parts[2]);

                if (cmd.equals("borrow")) {
                	LocalDate dueDate;
                	while (true) {
                		try {
                			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                			System.out.print("Due Date (YYYY-MM-DD): ");
                			dueDate = LocalDate.parse(br.readLine());
                			break;
                		} catch(Exception e) {
                			System.out.println("Please Enter a valid Date.");
                		}
                	}
                	return new Borrow(dueDate, bookID, patronID);
                } else if (cmd.equals("renew")) {
                	LocalDate dueDate;
                	while (true) {
                		try {
                			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                			System.out.print("Due Date (YYYY-MM-DD): ");
                			dueDate = LocalDate.parse(br.readLine());
                			break;
                		} catch(Exception e) {
                			System.out.println("Please Enter a valid Date.");
                		}
                	}
                	return new Renew(dueDate, bookID, patronID);
                    
                } else if (cmd.equals("return")) {
                    return new Return(bookID, patronID);
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new LibraryException("Invalid command.");
    }
}
