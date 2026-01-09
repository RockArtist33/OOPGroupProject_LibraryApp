package bcu.k9.librarysystem.gui;

import bcu.k9.librarysystem.commands.Borrow;
import bcu.k9.librarysystem.commands.Command;
import bcu.k9.librarysystem.commands.Renew;
import bcu.k9.librarysystem.main.InvalidDateException;
import bcu.k9.librarysystem.main.LibraryException;
import bcu.k9.librarysystem.model.Book;
import bcu.k9.librarysystem.model.Loan;
import bcu.k9.librarysystem.model.Patron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 8.2 GUI: Issue a book to a patron (mouse-only)
public class IssueBookWindow extends JFrame implements ActionListener {

    private final MainWindow mw;

    private final JComboBox<String> patronCombo = new JComboBox<>();
    private final JComboBox<String> bookCombo = new JComboBox<>();

    private final JButton due7Btn = new JButton("+7 days");
    private final JButton due14Btn = new JButton("+14 days");
    private final JButton due21Btn = new JButton("+21 days");

    private final JLabel dueLabel = new JLabel("Due date: (choose one)");

    private final JButton issueBtn = new JButton("Issue");
    private final JButton cancelBtn = new JButton("Cancel");

    private LocalDate selectedDueDate = null;

    private final List<Integer> patronIds = new ArrayList<>();
    private final List<Integer> bookIds = new ArrayList<>();

    public IssueBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
        loadDropdowns();
    }

    private void initialize() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        setTitle("Issue a Book (Mouse-only)");
        setSize(450, 250);

        JPanel top = new JPanel(new GridLayout(5, 2, 8, 8));

        top.add(new JLabel("Select Patron: "));
        top.add(patronCombo);

        top.add(new JLabel("Select Book (available): "));
        top.add(bookCombo);

        top.add(new JLabel("Set Due Date: "));
        JPanel duePanel = new JPanel(new GridLayout(1, 3, 8, 8));
        duePanel.add(due7Btn);
        duePanel.add(due14Btn);
        duePanel.add(due21Btn);
        top.add(duePanel);

        top.add(new JLabel(""));
        top.add(dueLabel);

        JPanel bottom = new JPanel(new GridLayout(1, 3));
        bottom.add(new JLabel(" "));
        bottom.add(issueBtn);
        bottom.add(cancelBtn);

        due7Btn.addActionListener(this);
        due14Btn.addActionListener(this);
        due21Btn.addActionListener(this);
        issueBtn.addActionListener(this);
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

        // patrons (non-deleted)
        for (Patron p : mw.getLibrary().getPatrons()) {
            patronCombo.addItem("#" + p.getId() + " - " + p.getName());
            patronIds.add(p.getId());
        }

        // only AVAILABLE books
        for (Book b : mw.getLibrary().getBooks()) {
            if (!b.isOnLoan()) {
                bookCombo.addItem("#" + b.getId() + " - " + b.getTitle());
                bookIds.add(b.getId());
            }
        }

        if (patronCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No patrons found. Add a patron first.", "Info", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
        if (bookCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No available books to issue.", "Info", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelBtn) {
            setVisible(false);
            return;
        }

        if (e.getSource() == due7Btn) {
            selectedDueDate = LocalDate.now().plusDays(7);
            dueLabel.setText("Due date: " + selectedDueDate);
            return;
        }
        if (e.getSource() == due14Btn) {
            selectedDueDate = LocalDate.now().plusDays(14);
            dueLabel.setText("Due date: " + selectedDueDate);
            return;
        }
        if (e.getSource() == due21Btn) {
            selectedDueDate = LocalDate.now().plusDays(21);
            dueLabel.setText("Due date: " + selectedDueDate);
            return;
        }

        if (e.getSource() == issueBtn) {
            issueSelected();
        }
    }

    private void issueSelected() {
        try {
            if (selectedDueDate == null) {
                JOptionPane.showMessageDialog(this, "Pick a due date (+7/+14/+21).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int patronIndex = patronCombo.getSelectedIndex();
            int bookIndex = bookCombo.getSelectedIndex();

            if (patronIndex < 0 || bookIndex < 0) {
                JOptionPane.showMessageDialog(this, "Select a patron and a book.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int patronId = patronIds.get(patronIndex);
            int bookId = bookIds.get(bookIndex);
            Command borrow = new Borrow(selectedDueDate, bookId, patronId);
            borrow.execute(mw.getLibrary(), LocalDate.now());

            mw.displayBooks();
            JOptionPane.showMessageDialog(this, "Issued successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);

        } catch (InvalidDateException | LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
