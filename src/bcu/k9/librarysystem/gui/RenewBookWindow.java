package bcu.k9.librarysystem.gui;

import bcu.k9.librarysystem.commands.Borrow;
import bcu.k9.librarysystem.commands.Command;
import bcu.k9.librarysystem.commands.Renew;
import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 8.4 GUI: Renew a book (mouse-only)
public class RenewBookWindow extends JFrame implements ActionListener {

    private final MainWindow mw;

    private final JComboBox<String> bookCombo = new JComboBox<>();

    private final JButton plus7Btn = new JButton("+7 days");
    private final JButton plus14Btn = new JButton("+14 days");
    private final JButton plus21Btn = new JButton("+21 days");

    private final JLabel newDueLabel = new JLabel("New due date: (choose one)");

    private final JButton renewBtn = new JButton("Renew");
    private final JButton cancelBtn = new JButton("Cancel");

    private LocalDate selectedNewDue = null;

    private final List<Integer> bookIds = new ArrayList<>();

    public RenewBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
        loadDropdown();
    }

    private void initialize() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        setTitle("Renew a Book (Mouse-only)");
        setSize(450, 220);

        JPanel top = new JPanel(new GridLayout(4, 2, 8, 8));
        top.add(new JLabel("Select Book (on loan): "));
        top.add(bookCombo);

        top.add(new JLabel("Set New Due Date: "));
        JPanel duePanel = new JPanel(new GridLayout(1, 3, 8, 8));
        duePanel.add(plus7Btn);
        duePanel.add(plus14Btn);
        duePanel.add(plus21Btn);
        top.add(duePanel);

        top.add(new JLabel(""));
        top.add(newDueLabel);

        JPanel bottom = new JPanel(new GridLayout(1, 3));
        bottom.add(new JLabel(" "));
        bottom.add(renewBtn);
        bottom.add(cancelBtn);

        plus7Btn.addActionListener(this);
        plus14Btn.addActionListener(this);
        plus21Btn.addActionListener(this);
        renewBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().add(top, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    private void loadDropdown() {
        bookCombo.removeAllItems();
        bookIds.clear();

        for (Book b : mw.getLibrary().getBooks()) {
            if (b.isOnLoan()) {
                bookCombo.addItem("#" + b.getId() + " - " + b.getTitle() + " (due " + b.getDueDate() + ")");
                bookIds.add(b.getId());
            }
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

        if (e.getSource() == plus7Btn) {
            selectedNewDue = LocalDate.now().plusDays(7);
            newDueLabel.setText("New due date: " + selectedNewDue);
            return;
        }
        if (e.getSource() == plus14Btn) {
            selectedNewDue = LocalDate.now().plusDays(14);
            newDueLabel.setText("New due date: " + selectedNewDue);
            return;
        }
        if (e.getSource() == plus21Btn) {
            selectedNewDue = LocalDate.now().plusDays(21);
            newDueLabel.setText("New due date: " + selectedNewDue);
            return;
        }

        if (e.getSource() == renewBtn) {
            renewSelected();
        }
    }

    private void renewSelected() {
        try {
            if (selectedNewDue == null) {
                JOptionPane.showMessageDialog(this, "Pick a new due date (+7/+14/+21).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int bookIndex = bookCombo.getSelectedIndex();
            if (bookIndex < 0) {
                JOptionPane.showMessageDialog(this, "Select a book.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int bookId = bookIds.get(bookIndex);
            int patronId = mw.getLibrary().getBookByID(bookId).getLoan().getPatron().getId();
            Command renew = new Renew(selectedNewDue, bookId, patronId);
            renew.execute(mw.getLibrary(), LocalDate.now());

            mw.displayBooks();
            JOptionPane.showMessageDialog(this, "Renewed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);

        } catch (InvalidDateException | LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

