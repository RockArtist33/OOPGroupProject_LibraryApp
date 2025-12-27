package bcu.k9.librarysystem.data;

import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**LibraryData class is responsible for loading data to and storing data from the library, using the text file storage.
 * The class has a static list of objects that implement the {@link DataManager} interface. The list gets populated by a static block of code (runs only once when the object is loaded to memory) that adds different implementations of the {@link DataManager} interface - one for each different entity of the library system (e.g. {@link BookDataManager} is an implementation that loads and stores data for {@link Book} entities using the text file storage).
 * The class has the static methods load() to load all data to the library and store(Library) to store all data from the library to the text file storage.
 * 
 * 
 */
public class LibraryData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new BookDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
        dataManagers.add(new PatronDataManager());
        dataManagers.add(new LoanDataManager());
        dataManagers.add(new LoanHistoryDataManager());
    }
    /**Load data from all data managers to the library.
     * This static method iterates through the list of data managers and runs their implementation of the DataManager.loadData(Library) method to load data for all the entities from the file storage to a Library object.
     * @return the new {@link Library} object loaded with data for all entities.
     * @throws LibraryException If an exception is thrown by the execution of any different loadData function implementations.
     * @throws java.io.IOException If an exception is thrown by the execution of any different loadData function implementations.
     */
    public static Library load() throws LibraryException, IOException {

        Library library = new Library();
        for (DataManager dm : dataManagers) {
            dm.loadData(library);
        }
        return library;
    }
    /**Store data from the library to the file storage using all data managers.
     * This static method iterates through the list of data managers and runs their implementation of the DataManager.loadData(Library) method to load data for all the entities from the file storage to a {@link Library} object.
     * 
     * @param library - the {@link Library} object whose data will be written to the text file storage.
     * @throws java.io.IOException if an exception is thrown by the execution of any of the different storeData function implementations.
     */
    public static void store(Library library) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(library);
        }
    }
    
}
 