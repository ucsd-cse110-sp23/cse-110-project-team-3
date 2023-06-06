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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.sound.midi.Track;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.util.List;

import NameValuePair.*;

import Credentials.Credentials;
import History.History;

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
    MainPage mainPage;
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

    JTextField loginField;
    JTextField verifyField;

    CreateAccountHeader createAccountHeader;
    CreateAccountBody createAccountBody;
    CreateAccountFooter createAccountFooter;

    JButton createButton;
    JButton backCreate;

    JTextField usernameField;
    JTextField passwordField;
    JTextField confirmPasswordField;

    AccountPopUp accountPopUp;

    Credentials credentials;

    Mediator m;

    AccountPage() {
        OpenAccountPage();
        m = new Mediator();
        credentials = new Credentials(m);
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

        // Get the text fields from the body
        loginField = loginBody.getUsernameField();
        verifyField = loginBody.getPasswordField();

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

        // Get the text fields from the body
        usernameField = createAccountBody.getUsernameField();
        passwordField = createAccountBody.getPasswordField();
        confirmPasswordField = createAccountBody.getConfirmPasswordField();

        // Add the create button to the footer
        createButton = createAccountFooter.getCreateAccountButton();

        // Add the back button to the footer
        backCreate = createAccountFooter.getBackButton();

        // Add listeners to the buttons
        ButtonLogicCreate();
    }

    public void openPopUp(String text) {
        accountPopUp = new AccountPopUp(text);
        accountPopUp.setVisible(true);
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
                String answer = "false";
                List<NameValuePair> toQuery = new ArrayList<NameValuePair>();
                NameValuePair parameter1 = new NameValuePair("cmdType", "login");
                NameValuePair parameter2 = new NameValuePair("username", loginField.getText());
                NameValuePair parameter3 = new NameValuePair("password", verifyField.getText());

                toQuery.add(parameter1);
                toQuery.add(parameter2);
                toQuery.add(parameter3);
    
                KeyValuePairHandler mySorter = new KeyValuePairHandler();
                String input = "invalid input";
                try {
                    input = mySorter.getQuery(toQuery);
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                TalktoServer speaker = new TalktoServer();
                try {
                    answer = speaker.sendAndReceive(input);
                    //System.out.println("try answer: " + "(" + answer + ")");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                //System.out.println("After answer: " + "(" + answer + ")");
                Boolean check = Boolean.valueOf(answer);
                //System.out.println(check);
                //Boolean check = credentials.login(loginField.getText(), verifyField.getText());
                if (!check) {
                    // Display an error message
                    openPopUp("Username or password is incorrect, please try again!");
                    loginField.setText("Username");
                    verifyField.setText("Password");
                }
                else {
                    ClearPage();
                    dispose();
                    mainPage = new MainPage(m);
                    // saves credential information to mediator
                    m.setId(credentials.getId(loginField.getText(), verifyField.getText()));
                    mainPage.setVisible(true);
                    revalidate();
                    // History history = new History();
                    // String promptHistory = history.getHistory(m.getId());
                }
            }
        });

        loginField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginField.setText("");
            }
        });

        verifyField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyField.setText("");
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
        createButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!passwordField.getText().equals(confirmPasswordField.getText())) {
                    // Display an error message
                    openPopUp("Passwords do not match, please try again!");
                    usernameField.setText("Username");
                    passwordField.setText("Password");
                    confirmPasswordField.setText("Confirm Password");
                }
                else {
                    String answer = "false";
                    List<NameValuePair> toQuery = new ArrayList<NameValuePair>();
                    NameValuePair parameter1 = new NameValuePair("cmdType", "createAcc");
                    NameValuePair parameter2 = new NameValuePair("username", usernameField.getText());
                    NameValuePair parameter3 = new NameValuePair("password", passwordField.getText());

                    toQuery.add(parameter1);
                    toQuery.add(parameter2);
                    toQuery.add(parameter3);
        
                    KeyValuePairHandler mySorter = new KeyValuePairHandler();
                    String input = "invalid input";
                    try {
                        input = mySorter.getQuery(toQuery);
                    } catch (UnsupportedEncodingException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    TalktoServer speaker = new TalktoServer();
                    try {
                        answer = speaker.sendAndReceive(input);
                        //System.out.println("try answer: " + "(" + answer + ")");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    //System.out.println("After answer: " + "(" + answer + ")");
                    Boolean check = Boolean.valueOf(answer);
                    //System.out.println(check);
                    //boolean check = credentials.createAccount(usernameField.getText(), passwordField.getText());
                    if (!check) {
                        // Display an error message
                        openPopUp("Username already exists, please try again!");
                        usernameField.setText("Username");
                        passwordField.setText("Password");
                        confirmPasswordField.setText("Confirm Password");
                    }
                    else {
                        ClearPage();
                        OpenAccountPage();
                        revalidate();
                    }
                }
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

        // Add a listener to the username field
        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usernameField.setText("");
            }
        });

        // Add a listener to the password field
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setText("");
            }
        });

        // Add a listener to the confirm password field
        confirmPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                confirmPasswordField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        AccountPage a = new AccountPage();
        a.revalidate();
    }
}