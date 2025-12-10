package bcu.cmp5332.librarysystem.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class SaveFileHandler {
	
	public static void clearFile(String filepath) throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, false));
			writer.append("");
			writer.close();
		} catch (IOException e) {
			throw new IOException("Cannot access this file, are the permissions correct?\n filepath: "+filepath);
		}
	}
	
	public static void writeStringToFile(String string, String filepath) throws IOException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
			writer.append(string);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			throw new IOException("Cannot access this file, are the permissions correct?\n filepath: "+filepath);
		}
		
	}

}
