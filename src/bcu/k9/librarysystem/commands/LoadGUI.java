package bcu.k9.librarysystem.commands;

import bcu.k9.librarysystem.gui.MainWindow;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;

import java.time.LocalDate;

public class LoadGUI implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        new MainWindow(library);
    }
    
}
 