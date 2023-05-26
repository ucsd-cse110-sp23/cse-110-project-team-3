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

class AccountFooter extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    AccountFooter() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the footer
        this.setBackground(backgroundColor);
    }
}

public class AccountPage extends JFrame {
    AccountHeader header;
    AccountBody body;
    AccountFooter footer;

    JButton loginButton;
    JButton signupButton;

    LoginHeader loginHeader;
    LoginBody loginBody;
    LoginFooter loginFooter;

    JButton confirmButton;
    JButton backLogin;

    CreateAccountHeader createAccountHeader;
    CreateAccountBody createAccountBody;
    CreateAccountFooter createAccountFooter;

    JButton createButton;
    JButton backCreate;

    AccountPage() {
        OpenAccountPage();
    }

    public void OpenAccountPage() {
        this.setSize(600, 600); // Size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        this.setLocationRelativeTo(null); // Center the window on the screen
        this.setVisible(true); // Make the window visible

        // Create the header
        header = new AccountHeader();
        this.add(header, BorderLayout.NORTH); // Add the header to the window

        // Create the body
        body = new AccountBody();
        this.add(body, BorderLayout.CENTER); // Add the body to the window

        // Create the footer
        footer = new AccountFooter();
        this.add(footer, BorderLayout.SOUTH); // Add the footer to the window

        // Get the login and signup buttons from the body
        loginButton = body.getLoginButton();
        signupButton = body.getSignupButton();

        // Add listeners to the buttons
        ButtonLogicAccount();
    }

    public void OpenLoginPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true); // Make the window visible

        // Create the header
        loginHeader = new LoginHeader();
        this.add(loginHeader, BorderLayout.NORTH); // Add the header to the top of the window

        // Create the body
        loginBody = new LoginBody();
        this.add(loginBody, BorderLayout.CENTER); // Add the body to the center of the window
        
        // Create the footer
        loginFooter = new LoginFooter();
        this.add(loginFooter, BorderLayout.SOUTH); // Add the footer to the bottom of the window

        // Add the confirm button to the footer
        confirmButton = loginFooter.getConfirmButton();

        // Add the back button to the footer
        backLogin = loginFooter.getBackButton();

        // Add listeners to the buttons
        ButtonLogicLogin();
    }

    public void OpenSignupPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setVisible(true); // Make the window visible

        // Create the header
        createAccountHeader = new CreateAccountHeader();
        this.add(createAccountHeader, BorderLayout.NORTH); // Add the header to the top of the window

        // Create the body
        createAccountBody = new CreateAccountBody();
        this.add(createAccountBody, BorderLayout.CENTER); // Add the body to the center of the window

        // Create the footer
        createAccountFooter = new CreateAccountFooter();
        this.add(createAccountFooter, BorderLayout.SOUTH); // Add the footer to the bottom of the window

        // Add the create button to the footer
        createButton = createAccountFooter.getCreateAccountButton();

        // Add the back button to the footer
        backCreate = createAccountFooter.getBackButton();

        // Add listeners to the buttons
        ButtonLogicCreate();
    }

    public void ClearPage() {
        this.getContentPane().removeAll();
        this.repaint();
    }

    public void ButtonLogicAccount() {
        // Add a listener to the login button
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClearPage();
                OpenLoginPage(); // Open the login page
                revalidate();
            }
        });

        // Add a listener to the signup button
        signupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClearPage();
                OpenSignupPage();
                revalidate();
            }
        });
    }

    public void ButtonLogicLogin() {
        // Add a listener to the confirm button
        confirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });

        // Add a listener to the back button
        backLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClearPage();
                OpenAccountPage();
                revalidate();
            }
        });
    }

    public void ButtonLogicCreate() {
        // Add a listener to the create button
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });

        // Add a listener to the back button
        backCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ClearPage();
                OpenAccountPage();
                revalidate();
            }
        });
    }

    public static void main(String[] args) {
        AccountPage a = new AccountPage();
        a.revalidate();
    }
}