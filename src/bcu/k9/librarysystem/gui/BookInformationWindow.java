package bcu.k9.librarysystem.gui;

import bcu.k9.librarysystem.commands.AddBook;
import bcu.k9.librarysystem.commands.Command;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;

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

public class BookInformationWindow extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow mw;
    private Book bookSelected;

    private JButton closeBtn = new JButton("Close");

    public BookInformationWindow(MainWindow mw, Book book) {
        this.mw = mw;
        this.bookSelected = book;
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

        setTitle("Add a New Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Title : "));
        topPanel.add(new JLabel(this.bookSelected.getTitle()));
        topPanel.add(new JLabel("Author : "));
        topPanel.add(new JLabel(this.bookSelected.getAuthor()));
        topPanel.add(new JLabel("Publisher : "));
        topPanel.add(new JLabel(this.bookSelected.getPublisher()));
        topPanel.add(new JLabel("Publishing Date : "));
        topPanel.add(new JLabel(this.bookSelected.getPublicationYear()));
        topPanel.add(new JLabel("Loan Status : "));
        topPanel.add(new JLabel(this.bookSelected.getStatus()));


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 1));
        bottomPanel.add(closeBtn);

        closeBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    	if (ae.getSource() == closeBtn) {
            this.setVisible(false);
        }

    }
}
