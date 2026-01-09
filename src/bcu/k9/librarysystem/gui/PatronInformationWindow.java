package bcu.k9.librarysystem.gui;

import bcu.k9.librarysystem.commands.AddBook;
import bcu.k9.librarysystem.commands.Command;
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

public class PatronInformationWindow extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow mw;
    private Patron patronSelected;

    private JButton closeBtn = new JButton("Close");

    public PatronInformationWindow(MainWindow mw, Patron patron) {
        this.mw = mw;
        this.patronSelected = patron;
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
        topPanel.add(new JLabel("Name : "));
        topPanel.add(new JLabel(this.patronSelected.getName()));
        topPanel.add(new JLabel("Email : "));
        topPanel.add(new JLabel(this.patronSelected.getEmail()));
        topPanel.add(new JLabel("Phone Number : "));
        topPanel.add(new JLabel(this.patronSelected.getPhone()));
        topPanel.add(new JLabel("Books on loan : "));
        topPanel.add(new JLabel(String.valueOf(this.patronSelected.getBooks().size())));
        topPanel.add(new JLabel("Total Past Books Loaned : "));
        topPanel.add(new JLabel(String.valueOf(this.patronSelected.getLoanHistory().size())));


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
