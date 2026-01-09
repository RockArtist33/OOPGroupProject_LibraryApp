package bcu.k9.librarysystem.gui;

import bcu.k9.librarysystem.commands.AddPatron;
import bcu.k9.librarysystem.commands.Command;
import bcu.k9.librarysystem.commands.DeleteBook;
import bcu.k9.librarysystem.commands.DeletePatron;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Patron;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class DeletePatronWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField emailText = new JTextField();
    private JTextField phoneText = new JTextField();

    private JButton acceptBtn = new JButton("Accept");
    private JButton cancelBtn = new JButton("Cancel");
	private Patron patron;

    public DeletePatronWindow(MainWindow mw, Patron patron) {
        this.mw = mw;
        this.patron = patron;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        } 

        setTitle("Delete Patron?");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.add(new JLabel("Are you sure you want to delete:"));
        topPanel.add(new JLabel(patron.getName()));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(acceptBtn);
        bottomPanel.add(cancelBtn);

        acceptBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == acceptBtn) {
            deletePatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void deletePatron() {
        try {
            // create and execute the DeletePatron Command
            Command deletePatron = new DeletePatron(patron.getId());
            deletePatron.execute(mw.getLibrary(), LocalDate.now());
            // refresh the view with the list of patrons
            mw.displayBooks();
            // hide (close) the AddPatronWindow
            this.setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}