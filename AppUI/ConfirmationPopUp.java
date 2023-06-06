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
import RecordHistory.RecordHistory;

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

class ConfirmHeader extends JPanel {

  Color backgroundColor = new Color(240, 248, 255);

  ConfirmHeader() {
    this.setPreferredSize(new Dimension(400, 60)); // Size of the header
    this.setBackground(backgroundColor);
    JLabel titleText = new JLabel("Accept or Cancel Prompt:"); // Text of the header
    titleText.setPreferredSize(new Dimension(400, 60));
    titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
    titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
    this.add(titleText); // Add the text to the header
  }
}

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
    titleText.setEditable(false);

  }
}

class ConfirmationPopUp extends JFrame {

  private ConfirmHeader header;
  private ConfirmFooter footer;
  private Body body;
  private Sidebar sidebar;

  private JButton acceptButton;
  private JButton cancelButton;

  // create recordhistory object
  private RecordHistory rh;

  private String question;

  // mediator handles question and answer, api
  private Mediator mediator;

  ConfirmationPopUp(Mediator mediator, Sidebar sidebar) {
    this.setSize(400, 600); // 400 width and 600 height
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
    this.setVisible(true); // Make visible

    // TODO: get backend away from frontend
    rh = new RecordHistory();

    this.mediator = mediator;
    this.sidebar = sidebar;

    // retrieves text from audio using whisper
    mediator.generateQuestion();

    question = mediator.getNewQuestion();

    header = new ConfirmHeader();
    footer = new ConfirmFooter();
    body = new Body(question);
    
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
            try {
              new RunCommands().runVoiceCommand(mediator, sidebar);
            } catch (IOException e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }
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

@interface override {
}