package ServerPopup;

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

import RecordHistory.RecordHistory;

class serverFooter extends JFrame{
    JButton okeButton;
    
    Color backgroundColor = new Color(240, 248, 255);
    Border emptyBorder = BorderFactory.createEmptyBorder();
    
    serverFooter() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(backgroundColor);
    
        okeButton = new JButton("ok"); // add task button
        okeButton.setFont(new Font("Sans-serif", Font.ITALIC, 10)); // set font
        this.add(okeButton); // add to footer
    }

    public JButton getAcceptButton() {
        return okeButton;
    }
}

class serverBody extends JFrame{
    Color backgroundColor = new Color(240, 248, 255);
    Border emptyBorder = BorderFactory.createEmptyBorder();
    
    serverBody() {
        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(backgroundColor);
        JTextArea titleText = new JTextArea("Server is down bud :("); // Text of the header
        titleText.setPreferredSize(new Dimension(400, 60));
        titleText.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        this.add(titleText); // Add the text to the header
        titleText.setLineWrap(true);
        titleText.setWrapStyleWord(true);
        titleText.setEditable(false);
    
      }
}

public class ServerPopup extends JFrame{
    JButton okeButton;
    Color backgroundColor = new Color(240, 248, 255);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    public ServerPopup(){
        this.setSize(400, 600); // 400 width and 600 height
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        okeButton = new JButton("ok"); // add task button
        okeButton.setFont(new Font("Sans-serif", Font.ITALIC, 10)); // set font
        this.add(okeButton); // add to footer

        serverFooter serverfooter = new serverFooter();
        serverBody body = new serverBody();
        this.add(serverfooter, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(body, BorderLayout.CENTER); // Add list in middle of footer and title

        addListeners();
    }

    public void addListeners(){
        okeButton.addMouseListener(
        new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            dispose(); // Close window
          }
        });
    }
}

