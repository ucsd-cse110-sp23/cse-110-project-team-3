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

class PopUpBody extends JPanel {
    JLabel bodyText;

    PopUpBody(String text) {
        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(4, 1)); // Use grid layout

        // Create the username field
        bodyText = new JLabel(text);
        bodyText.setPreferredSize(new Dimension(200, 60));
        bodyText.setFont(new Font("Sans-serif", Font.BOLD, 12));
        bodyText.setBackground(new Color(255, 255, 255));
        this.add(bodyText);
    }
}

class PopUpFooter extends JPanel {
    JButton okButton;

    PopUpFooter() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the footer
        this.setBackground(new Color(240, 248, 255));
        this.setLayout(new GridLayout(1, 1)); // Use grid layout

        // Create the ok button
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(200, 60));
        okButton.setFont(new Font("Sans-serif", Font.BOLD, 20));
        okButton.setBackground(new Color(255, 255, 255));
        this.add(okButton);
    }

    public JButton getOkButton() {
        return okButton;
    }
}

public class AccountPopUp extends JFrame {
    private PopUpBody body;
    private PopUpFooter footer;
    private JButton okButton;

    AccountPopUp(String text) {
        this.setSize(500, 300); // Size of the frame
        this.setLocationRelativeTo(null);// center the frame
        this.setLayout(new BorderLayout()); // Use border layout
        this.setVisible(true);
        this.setResizable(false); // Make the frame not resizable

        // Create the body
        body = new PopUpBody(text);

        // Create the footer
        footer = new PopUpFooter();

        // Add the body and footer to the frame
        this.add(body, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);

        okButton = footer.getOkButton();
        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

    }
}