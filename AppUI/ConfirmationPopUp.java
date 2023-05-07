/**
 * This code was refactored from the original code found at:
 * https://copyassignment.com/to-do-list-app-in-java/
 */
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

class ConfirmFooter extends JPanel {

  JButton acceptButton;
  JButton cancelButton;
  
  Color backgroundColor = new Color(240, 248, 255);
  Border emptyBorder = BorderFactory.createEmptyBorder();

  ConfirmFooter() {
    this.setPreferredSize(new Dimension(400, 60));
    this.setBackground(backgroundColor);
   
    acceptButton = new JButton("Accept"); // add task button
    acceptButton.setFont(new Font("Sans-serif", Font.ITALIC, 10)); // set font
    this.add(acceptButton); // add to footer

    cancelButton = new JButton("Cancel"); // clear button
    cancelButton.setFont(new Font("Sans-serif", Font.ITALIC, 10)); // set font
    this.add(cancelButton); // add to footer
  }

  public JButton getAcceptButton() {
    return acceptButton;
  }

  public JButton getCancelButton() {
    return cancelButton;
  }
}

class Header extends JPanel {

  Color backgroundColor = new Color(240, 248, 255);

  Header() {
    this.setPreferredSize(new Dimension(400, 60)); // Size of the header
    this.setBackground(backgroundColor);
    JLabel titleText = new JLabel("Accept or Cancel Prompt:"); // Text of the header
    titleText.setPreferredSize(new Dimension(400, 60));
    titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
    titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
    this.add(titleText); // Add the text to the header
  }
}

/*
 * TODO: Make it so that long bodies of text appear in full
 */
class Body extends JPanel {
  
  Color backgroundColor = new Color(240, 248, 255);

  Body(String textFromAudio) {
    this.setPreferredSize(new Dimension(400, 400)); // Size of the body
    this.setBackground(backgroundColor);
    JTextArea titleText = new JTextArea(textFromAudio); // Text of the header
    titleText.setPreferredSize(new Dimension(400, 60));
    titleText.setFont(new Font("Sans-serif", Font.PLAIN, 14));
    this.add(titleText); // Add the text to the header
    titleText.setLineWrap(true);
    titleText.setWrapStyleWord(true);

  }
}

class ConfirmationPopUp extends JFrame {

  private Header header;
  private ConfirmFooter footer;
  private Body body;

  private JButton acceptButton;
  private JButton cancelButton;

  private MainPage mainPage;

  String promptText;

  ConfirmationPopUp(MainPage m) {
    this.setSize(400, 600); // 400 width and 600 height
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
    this.setVisible(true); // Make visible

    promptText = "there once was a ship that put to sea and the name of the ship was the Billy-O'-Tea";
    mainPage = m;

    header = new Header();
    footer = new ConfirmFooter();
    body = new Body(promptText);
    /* TODO: Get and put audio text there */

    this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
    this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
    this.add(body, BorderLayout.CENTER); // Add list in middle of footer and title

    acceptButton = footer.getAcceptButton();
    cancelButton = footer.getCancelButton();

    addListeners();
  }

  public void addListeners() {
    acceptButton.addMouseListener(
      new MouseAdapter() {
        @override
        public void mousePressed(MouseEvent e) {
          /*
           * TODO: 
           * call method that sends question into chat gpt
           * refactor displaying text on home screen 
           * (editing the text does nothing to prompt that gets displayed)
           * (also some kind of design principle is violated by passing mainpage into constructors)
           */
          mainPage.setQuestionText(promptText);
          dispose(); // Close window
        }
      }
    );

    cancelButton.addMouseListener(
      new MouseAdapter() {
        @override
        public void mousePressed(MouseEvent e) {
          dispose(); // Close window
        }
      }
    );
  }
}

@interface override {
}