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

class EmailErrorHeader extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    EmailErrorHeader() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

        JLabel titleText = new JLabel("Email Error");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 24));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }
}

class EmailErrorBody extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);

    EmailErrorBody() {
        this.setPreferredSize(new Dimension(200, 200)); // Size of the body
        this.setBackground(backgroundColor);
        JTextArea errorText = new JTextArea("Error with sending email. Check email settings to fix error.");
        errorText.setPreferredSize(new Dimension(300, 200));
        errorText.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        this.add(errorText); // Add the text to the header
        errorText.setLineWrap(true);
        errorText.setWrapStyleWord(true);
        errorText.setEditable(false);

    }
}

class EmailErrorFooter extends JPanel {
    private JButton closeButton;

    EmailErrorFooter() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the footer
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(1, 1)); // Use grid layout

        // Create the close error button
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(200, 60));
        closeButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        closeButton.setBackground(new Color(255, 255, 255));
        this.add(closeButton);

    }

    public JButton getCloseButton() {
        return closeButton;
    }

}

public class EmailErrorPopUp extends JFrame {
    EmailErrorHeader header;
    EmailErrorBody body;
    EmailErrorFooter footer;
    JButton closeButton;

    EmailErrorPopUp() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400); // Size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setLayout(new BorderLayout()); // Use border layout

        // Create the header
        header = new EmailErrorHeader();
        this.add(header, BorderLayout.NORTH);

        // Create the body
        body = new EmailErrorBody();
        this.add(body, BorderLayout.CENTER);

        // Create the footer
        footer = new EmailErrorFooter();
        this.add(footer, BorderLayout.SOUTH);

        // Create the close button
        closeButton = footer.getCloseButton();
    
        addListeners();

        this.setVisible(true); // Make the window visible
    }
    public JButton geCloseButton() {
        return closeButton;
    }

    public void addListeners() {
        closeButton.addMouseListener(
            new MouseAdapter() {
              @override
              public void mousePressed(MouseEvent e) {
                dispose(); // Close window
              }
            });
    }

    public static void main(String[] args) {
        new EmailErrorPopUp();
    }
}