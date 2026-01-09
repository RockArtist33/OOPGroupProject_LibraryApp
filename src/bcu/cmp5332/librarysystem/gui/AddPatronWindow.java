package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AddPatronWindow extends JFrame implements ActionListener {

    private final MainWindow mw;

    private final JTextField nameText = new JTextField();
    private final JTextField phoneText = new JTextField();
    private final JTextField emailText = new JTextField();

    private final JButton addBtn = new JButton("Add");
    private final JButton cancelBtn = new JButton("Cancel");

    public AddPatronWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    private void initialize() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        setTitle("Add a New Patron");
        setSize(350, 200);

        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(new JLabel("Name: "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Phone: "));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("Email: "));
        topPanel.add(emailText);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.add(new JLabel(" "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        getContentPane().add(topPanel, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(mw);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) addPatron();
        if (ae.getSource() == cancelBtn) setVisible(false);
    }

    private void addPatron() {
        try {
            String name = nameText.getText().trim();
            String phone = phoneText.getText().trim();
            String email = emailText.getText().trim();

            if (name.isEmpty()) throw new LibraryException("Name cannot be empty.");

            Command cmd = new AddPatron(name, phone, email);
            cmd.execute(mw.getLibrary(), LocalDate.now());

            mw.displayPatrons();
            setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
