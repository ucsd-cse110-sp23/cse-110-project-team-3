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

        JLabel titleText = new JLabel("Login");
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

        this.setLayout(new GridLayout(3, 1)); // Use grid layout

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

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}

class Account extends JFrame {
    AccountHeader header;
    AccountBody body;

    JButton loginButton;
    JButton signupButton;

    Account() {
        this.setSize(600, 600); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true); // Make the window visible
        this.setLayout(new BorderLayout()); // Use border layout

        // Create the header
        header = new AccountHeader();
        this.add(header, BorderLayout.NORTH); // Add the header to the window

        // Create the body
        body = new AccountBody();
        this.add(body, BorderLayout.CENTER); // Add the body to the window

        // Add the login button to the body
        loginButton = body.getLoginButton();

        // Add the signup button to the body
        signupButton = body.getSignupButton();
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}

public class AccountPage extends JFrame {

    // THE page
    JFrame page;

    Account account;

    JButton loginButton;
    JButton signupButton;

    LoginPage loginPage;
    JButton confirmButton;
    JButton backLogin;

    CreateAccountPage createAccountPage;
    JButton createButton;
    JButton backCreate;

    Mediator mediator;

    public AccountPage() {
        this.mediator = new Mediator();
        OpenAccountPage();
    }

    public void OpenAccountPage() {
        if (page != null) {
            page.dispose();
        }
        page = new Account();

        // Add the login button to the account page
        loginButton = ((Account) page).getLoginButton();

        // Add the signup button to the account page
        signupButton = ((Account) page).getSignupButton();

        ButtonLogicAccount(); // Add logic to the buttons
    }

    public void OpenLoginPage() {
        if (page != null) {
            page.dispose();
        }
        page = new LoginPage();

        // Add the confirm button to the login page
        confirmButton = ((LoginPage) page).getConfirmButton();

        // Add the back button to the login page
        backLogin = ((LoginPage) page).getBackButton();

        this.setVisible(false); // Hide the account page
        ButtonLogicLogin(); // Add logic to the buttons

    }

    public void OpenCreateAccountPage() {
        if (page != null) {
            page.dispose();
        }
        page = new CreateAccountPage();

        // Add the create button to the create account page
        createButton = ((CreateAccountPage) page).getCreateAccountButton();

        // Add the back button to the create account page
        backCreate = ((CreateAccountPage) page).getBackButton(); //
        ButtonLogicCreate(); // Add logic to the buttons
    }

    public void ButtonLogicAccount() {
        // Add a listener to the login button
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OpenLoginPage();
            }
        });

        // Add a listener to the signup button
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OpenCreateAccountPage();
            }
        });
    }

    public void ButtonLogicLogin() {
        // Add a listener to the back button
        backLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Go back to the account page
                OpenAccountPage();
            }
        });
    }

    public void ButtonLogicCreate() {
        // Add a listener to the back button
        backCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Go back to the account page
                OpenAccountPage();
            }
        });
    }

    public static void main(String[] args) {
        new AccountPage();
    }
}
