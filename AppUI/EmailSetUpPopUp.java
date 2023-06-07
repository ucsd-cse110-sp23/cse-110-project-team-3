import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Email.EmailSetup;
import Mediator.Mediator;

class EmailPopUpHeader extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    EmailPopUpHeader() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

        JLabel titleText = new JLabel("Setup Email");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }
}

class EmailPopUpBody extends JPanel {
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField displayNameField;
    JTextField emailAddressField;
    JTextField SMTPhostField;
    JTextField TLSportField;
    JTextField emailPasswordField;
    Color backgroundColor = new Color(240, 248, 255);

    EmailPopUpBody() {
        this.setPreferredSize(new Dimension(600, 450)); // Size of the body
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(7, 1)); // Use grid layout

        // Create the First Name Field
        firstNameField = new JTextField("First Name");
        firstNameField.setPreferredSize(new Dimension(200, 60));
        firstNameField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        firstNameField.setBackground(new Color(255, 255, 255));
        this.add(firstNameField); // Add the Field to the body

        // Create the Last Name Field
        lastNameField = new JTextField("Last Name");
        lastNameField.setPreferredSize(new Dimension(200, 60));
        lastNameField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        lastNameField.setBackground(new Color(255, 255, 255));
        this.add(lastNameField); // Add the Field to the body

        // Create the Display Name Field
        displayNameField = new JTextField("Display Name");
        displayNameField.setPreferredSize(new Dimension(200, 60));
        displayNameField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        displayNameField.setBackground(new Color(255, 255, 255));
        this.add(displayNameField); // Add the Field to the body

        // Create the Email Address Field
        emailAddressField = new JTextField("Email");
        emailAddressField.setPreferredSize(new Dimension(200, 60));
        emailAddressField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        emailAddressField.setBackground(new Color(255, 255, 255));
        this.add(emailAddressField); // Add the Field to the body

        //Create the SMTP Host Field
        SMTPhostField = new JTextField("SMTP host");
        SMTPhostField.setPreferredSize(new Dimension(200, 60));
        SMTPhostField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        SMTPhostField.setBackground(new Color(255, 255, 255));
        this.add(SMTPhostField); // Add the Field to the body

        //Create TLS port Field
        TLSportField = new JTextField("TLS port");
        TLSportField.setPreferredSize(new Dimension(200, 60));
        TLSportField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        TLSportField.setBackground(new Color(255, 255, 255));
        this.add(TLSportField); // Add the Field to the body

        // Create email password field 
        emailPasswordField = new JTextField("Email Password");
        emailPasswordField.setPreferredSize(new Dimension(200, 60));
        emailPasswordField.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        emailPasswordField.setBackground(new Color(255, 255, 255));
        this.add(emailPasswordField); // Add the button to the body

        ArrayList<JTextField> fields = new ArrayList<JTextField>();
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(displayNameField);
        fields.add(emailAddressField);
        fields.add(SMTPhostField);
        fields.add(TLSportField);  
        fields.add(emailPasswordField);
        addListeners(fields);
    }

    public JTextField getFirstName() {
        return firstNameField;
    }

    public JTextField getLastName() {
        return lastNameField;
    }

    public JTextField getDisplayName() {
        return displayNameField;
    }
    public JTextField getEmailAddress() {
        return emailAddressField;
    }
    public JTextField getSMTPHost() {
        return SMTPhostField;
    }
    public JTextField getTLSPort() {
        return TLSportField;
    }
    public JTextField getEmailPassword() {
        return emailPasswordField;
    }

    public void addListeners(ArrayList<JTextField> fields) {
        for (JTextField field : fields) {
            field.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    field.setText("");
                }
            });
        }
    }
}

class EmailPopUpFooter extends JPanel {
    private JButton saveButton;
    private JButton cancelButton;
    JLabel incorrectPasswordLabel;
    JLabel usernameTakenLabel;

    EmailPopUpFooter() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the footer
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(1, 2)); // Use grid layout

        // Create the save button
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(200, 60));
        saveButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        saveButton.setBackground(new Color(255, 255, 255));
        this.add(saveButton);

        // Create the cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(200, 60));
        cancelButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        cancelButton.setBackground(new Color(255, 255, 255));
        this.add(cancelButton);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
public class EmailSetUpPopUp extends JFrame {
    Mediator mediator;
    EmailPopUpHeader header;
    EmailPopUpBody body;
    EmailPopUpFooter footer;
    JButton saveButton;
    JButton cancelButton;

    String firstName;
    String lastName;
    String displayName;
    String emailAddress;
    String SMTPhost;
    String TLSport;
    String emailPassword; 

    EmailSetup setup;

  //  EmailSetup newEmail;

    EmailSetUpPopUp(Mediator mediator) {
        this.mediator = mediator;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 450); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setLayout(new BorderLayout()); // Use border layout

        // Create the header
        header = new EmailPopUpHeader();
        this.add(header, BorderLayout.NORTH);

        // Create the body
        body = new EmailPopUpBody();
        this.add(body, BorderLayout.CENTER);

        // Create the footer
        footer = new EmailPopUpFooter();
        this.add(footer, BorderLayout.SOUTH);

        // Create the create account button
        saveButton = footer.getSaveButton();
        // Create the cancel button
        cancelButton = footer.getCancelButton();

        this.setVisible(true); // Make the window visible

        addListeners();
    }

    public void addListeners() {
        saveButton.addMouseListener(
            new MouseAdapter() {
              @override
              public void mousePressed(MouseEvent e) {
                //saves user input into a string
                firstName = body.getFirstName().getText();
                lastName = body.getLastName().getText();
                displayName = body.getDisplayName().getText();
                emailAddress = body.getEmailAddress().getText();
                SMTPhost = body.getSMTPHost().getText();
                TLSport = body.getTLSPort().getText();
                emailPassword = body.getEmailPassword().getText();

                //saves email settings to remember
                setup = new EmailSetup(firstName, lastName, displayName, emailAddress, SMTPhost, TLSport, emailPassword, mediator);

                // Need to add: sets up email

                dispose(); // Close window
              }
            });
    
        cancelButton.addMouseListener(
            new MouseAdapter() {
              @override
              public void mousePressed(MouseEvent e) {
                dispose(); // Close window
              }
            });
    }
}
