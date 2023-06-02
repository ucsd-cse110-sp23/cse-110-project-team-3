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

class LoginHeader extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    LoginHeader() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

        JLabel titleText = new JLabel("Login");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }
}

class LoginBody extends JPanel {
    private JTextField usernameField;
    private JTextField passwordField;

    LoginBody() {
        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(3, 1)); // Use grid layout

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
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }
}

class LoginFooter extends JPanel {
    JButton confirmButton;
    JButton backButton;

    LoginFooter() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the footer
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(1, 2));

        // Create the confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setPreferredSize(new Dimension(200, 60));
        confirmButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        confirmButton.setBackground(new Color(255, 255, 255));
        this.add(confirmButton);

        // Create the back button
        backButton = new JButton("back");
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        backButton.setBackground(new Color(255, 255, 255));
        this.add(backButton);
    }
    
    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}

public class LoginPage extends JFrame {
    LoginHeader header;
    LoginBody body;
    LoginFooter footer;
    JButton confirmButton;
    JButton backButton;

    LoginPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600); // Size of the window
        this.setLocationRelativeTo(null); // Center the window

        // Create the header
        header = new LoginHeader();
        this.add(header, BorderLayout.NORTH); // Add the header to the top of the window

        // Create the body
        body = new LoginBody();
        this.add(body, BorderLayout.CENTER); // Add the body to the center of the window
        
        // Create the footer
        footer = new LoginFooter();
        this.add(footer, BorderLayout.SOUTH); // Add the footer to the bottom of the window

        // Add the confirm button to the footer
        confirmButton = footer.getConfirmButton();

        // Add the back button to the footer
        backButton = footer.getBackButton();

        this.setVisible(true);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public static void main(String[] args) throws Exception {
        new LoginPage();
    }
}