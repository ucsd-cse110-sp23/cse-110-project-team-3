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


/**
 * Creates a JSwing UI for the Account Page of the application.
 * Allows user to choose between logging in and signing up.
 * this page handles the transitions between those two pages.
 */
class AccountHeader extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    AccountHeader() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

        JLabel titleText = new JLabel("Prompt History");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }
}

class AccountBody extends JPanel {
    JButton loginButton;
    JButton signupButton;
    Color backgroundColor = new Color(240, 248, 255);

    AccountBody() {
        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(backgroundColor);

        // Create the login button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 60));
        loginButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        loginButton.setBackground(new Color(255, 255, 255));
        this.add(loginButton); // Add the button to the body

        // Create the signup button
        signupButton = new JButton("Sign Up");
        signupButton.setPreferredSize(new Dimension(200, 60));
        signupButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        signupButton.setBackground(new Color(255, 255, 255));
        this.add(signupButton); // Add the button to the body
    }
}

public class AccountPage extends JFrame {
    AccountHeader header;
    AccountBody body;
    Mediator mediator;

    public AccountPage() {
        this.mediator = new Mediator();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setLayout(new BorderLayout()); // Use border layout
        this.setResizable(false); // Disable resizing

        // Create the header
        header = new AccountHeader();
        this.add(header, BorderLayout.NORTH); // Add the header to the window

        // Create the body
        body = new AccountBody();
        this.add(body, BorderLayout.CENTER); // Add the body to the window

        this.setVisible(true); // Make the window visible
    }

    public static void main(String[] args) {
        new AccountPage();
    }
}
