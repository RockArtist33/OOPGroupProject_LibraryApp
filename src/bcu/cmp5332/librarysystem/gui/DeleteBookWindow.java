package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.main.LibraryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookWindow extends JFrame implements ActionListener {

    private final MainWindow mw;

    private final JTextField bookIdText = new JTextField();
    private final JButton deleteBtn = new JButton("Delete");
    private final JButton cancelBtn = new JButton("Cancel");

    public DeleteBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        setTitle("Delete a Book (Soft Delete)");
        setSize(350, 150);

        JPanel top = new JPanel(new GridLayout(2, 2));
        top.add(new JLabel("Book ID: "));
        top.add(bookIdText);

        JPanel bottom = new JPanel(new GridLayout(1, 3));
        bottom.add(new JLabel(" "));
        bottom.add(deleteBtn);
        bottom.add(cancelBtn);

        deleteBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().add(top, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteBtn) doDelete();
        if (e.getSource() == cancelBtn) setVisible(false);
    }

    private void doDelete() {
        try {
            int bookId = Integer.parseInt(bookIdText.getText().trim());

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Soft delete Book #" + bookId + "?", "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            mw.getLibrary().deleteBook(bookId);
            mw.displayBooks();
            JOptionPane.showMessageDialog(this, "Deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
