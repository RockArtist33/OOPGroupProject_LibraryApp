package bcu.k9.librarysystem.gui;

import bcu.k9.librarysystem.commands.Command;
import bcu.k9.librarysystem.commands.Renew;
import bcu.k9.librarysystem.commands.Return;
import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Patron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 8.3 GUI: Return a book (mouse-only)
public class ReturnBookWindow extends JFrame implements ActionListener {

    private final MainWindow mw;

    private final JComboBox<String> patronCombo = new JComboBox<>();
    private final JComboBox<String> bookCombo = new JComboBox<>();

    private final JButton returnBtn = new JButton("Return");
    private final JButton cancelBtn = new JButton("Cancel");

    private final List<Integer> patronIds = new ArrayList<>();
    private final List<Integer> bookIds = new ArrayList<>();

    public ReturnBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
        loadDropdowns();
    }

    private void initialize() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        setTitle("Return a Book (Mouse-only)");
        setSize(450, 200);

        JPanel top = new JPanel(new GridLayout(3, 2, 8, 8));
        top.add(new JLabel("Select Patron: "));
        top.add(patronCombo);

        top.add(new JLabel("Select Book (on loan): "));
        top.add(bookCombo);

        JPanel bottom = new JPanel(new GridLayout(1, 3));
        bottom.add(new JLabel(" "));
        bottom.add(returnBtn);
        bottom.add(cancelBtn);

        returnBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().add(top, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    private void loadDropdowns() {
        patronCombo.removeAllItems();
        bookCombo.removeAllItems();
        patronIds.clear();
        bookIds.clear();

        for (Patron p : mw.getLibrary().getPatrons()) {
            patronCombo.addItem("#" + p.getId() + " - " + p.getName());
            patronIds.add(p.getId());
        }

        for (Book b : mw.getLibrary().getBooks()) {
            if (b.isOnLoan()) {
                bookCombo.addItem("#" + b.getId() + " - " + b.getTitle());
                bookIds.add(b.getId());
            }
        }

        if (patronCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No patrons found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
        if (bookCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No books are currently on loan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) {
            setVisible(false);
            return;
        }
        if (e.getSource() == returnBtn) {
            doReturn();
        }
    }

    private void doReturn() {
        try {
            int patronIndex = patronCombo.getSelectedIndex();
            int bookIndex = bookCombo.getSelectedIndex();

            if (patronIndex < 0 || bookIndex < 0) {
                JOptionPane.showMessageDialog(this, "Select a patron and a book.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int patronId = patronIds.get(patronIndex);
            int bookId = bookIds.get(bookIndex);

            Command returnCommand = new Return(bookId, patronId);
            try {
				returnCommand.execute(mw.getLibrary(), LocalDate.now());
			} catch (InvalidDateException e) {
				e.printStackTrace();
			}

            mw.displayBooks();
            JOptionPane.showMessageDialog(this, "Returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);

        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

