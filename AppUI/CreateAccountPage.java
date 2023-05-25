import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Mediator.Mediator;

class CreateAccountHeader extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    CreateAccountHeader() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

        JLabel titleText = new JLabel("Create Account");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }
}

class CreateAccountBody extends JPanel {
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField confirmPasswordField;

    CreateAccountBody() {
        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(4, 1)); // Use grid layout

        // Create the username field
        usernameField = new JTextField("Username");
        usernameField.setPreferredSize(new Dimension(200, 60));
        usernameField.setFont(new Font("Sans-serif", Font.BOLD, 20));
        usernameField.setBackground(new Color(255, 255, 255));
        this.add(usernameField);

        // Create the password field
        passwordField = new JTextField("Password");
        passwordField.setPreferredSize(new Dimension(200, 60));
        passwordField.setFont(new Font("Sans-serif", Font.BOLD, 20));
        passwordField.setBackground(new Color(255, 255, 255));
        this.add(passwordField);

        // Create the confirm password field
        confirmPasswordField = new JTextField("Confirm Password");
        confirmPasswordField.setPreferredSize(new Dimension(200, 60));
        confirmPasswordField.setFont(new Font("Sans-serif", Font.BOLD, 20));
        confirmPasswordField.setBackground(new Color(255, 255, 255));
        this.add(confirmPasswordField);
    }
}

class CreateAccountFooter extends JPanel {
    private JButton createAccountButton;
    private JButton backButton;

    CreateAccountFooter() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the footer
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(1, 2)); // Use grid layout

        // Create the create account button
        createAccountButton = new JButton("Create Account");
        createAccountButton.setPreferredSize(new Dimension(200, 60));
        createAccountButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        createAccountButton.setBackground(new Color(255, 255, 255));
        this.add(createAccountButton);

        // Create the back button
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        backButton.setBackground(new Color(255, 255, 255));
        this.add(backButton);
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}

public class CreateAccountPage extends JFrame {
    CreateAccountHeader header;
    CreateAccountBody body;
    CreateAccountFooter footer;
    JButton createAccountButton;
    JButton backButton;

    CreateAccountPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setLayout(new BorderLayout()); // Use border layout

        // Create the header
        header = new CreateAccountHeader();
        this.add(header, BorderLayout.NORTH);

        // Create the body
        body = new CreateAccountBody();
        this.add(body, BorderLayout.CENTER);

        // Create the footer
        footer = new CreateAccountFooter();
        this.add(footer, BorderLayout.SOUTH);

        // Create the create account button
        createAccountButton = footer.getCreateAccountButton();

        // Create the back button
        backButton = footer.getBackButton();

        this.setVisible(true); // Make the window visible
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public static void main(String[] args) {
        new CreateAccountPage();
    }
}