import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

class Header extends JPanel {

    Color backgroundColor = new Color(240, 248, 255);
  
    Header() {
      this.setPreferredSize(new Dimension(400, 60)); // Size of the header
      this.setBackground(backgroundColor);
      JLabel titleText = new JLabel("Saylt Assistant"); // Text of the header
      titleText.setPreferredSize(new Dimension(200, 60));
      titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
      titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
      this.add(titleText); // Add the text to the header
    }
}

class resultUI extends JPanel{
    JLabel qLabel;
    JLabel aLabel;

    resultUI(){
        this.setPreferredSize(new Dimension(400, 60));
        this.setLayout(new GridLayout(2, 1));

        this.qLabel = new JLabel();
        this.aLabel = new JLabel();

         // add text to label
        qLabel.setText("User inputted question \n");
        qLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qLabel.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        aLabel.setText("Answer to user question");
        aLabel.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        aLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.add(qLabel);
        this.add(aLabel);
    }
    
}

class Footer extends JPanel {
    private JButton newQuestionButton;
    private JButton pauseButton;
    private JLabel listeningLabel;
    private boolean isRecording;
    Color backgroundColor = new Color(240, 248, 255);

    MainPage mainPage;

    Footer(MainPage m) {

        isRecording = false;
        mainPage = m;
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(backgroundColor);

        this.setLayout(new GridLayout(2, 2));

        pauseButton = new JButton("||");
        pauseButton.setFont(new Font("Sans-serif", Font.ITALIC, 15));
        this.add(pauseButton);

        newQuestionButton = new JButton("New Question");
        newQuestionButton.setFont(new Font("Sans-serif", Font.ITALIC, 15));
        this.add(newQuestionButton);

        listeningLabel = new JLabel("Listening...");
        listeningLabel.setFont(new Font("Sans-serif", Font.ITALIC, 15));
        listeningLabel.setForeground(Color.red);
        listeningLabel.setPreferredSize(new Dimension(5, 5));
        listeningLabel.setVisible(false);
        listeningLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(listeningLabel);

        addListeners();
    }

    private void addListeners(){
        // if the new question button is clicked, then display the Listening label
        newQuestionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!isRecording) {
                    startRecording();
                }
                // TODO: start "listening state"
            }
        });
        // if the pause button is clicked, then stop displaying the listenign label
        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (isRecording) {
                    stopRecording();
                }
                // TODO: end "listening state"
            }
        });
    }

    // recording related methods
    // starts recording when user clicks "New Question"
    private void startRecording() {
        isRecording = true;
        listeningLabel.setVisible(true);
    }
    // stops recording when user clicks pause
    private void stopRecording() {
        isRecording = false;
        listeningLabel.setVisible(false);
        new ConfirmationPopUp(mainPage);
    }

    public JButton getNewQuestionButton(){
        return newQuestionButton;
    }

    public JButton getPauseButton(){
        return pauseButton;
    }
}

class MainPage extends JFrame {
    private Header header;
    private Footer footer;
    private resultUI resultUI;

    private JButton pauseButton;
    private JButton newQuestionButton;

    MainPage(){
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        header = new Header();
        footer = new Footer(this);
        resultUI = new resultUI();

        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(resultUI, BorderLayout.CENTER); // adds question and response to center of screen
        
        pauseButton = footer.getPauseButton();
        newQuestionButton = footer.getNewQuestionButton();
    }
    // sets question text
    public void setQuestionText(String question) {
        resultUI.qLabel.setText(question);
    }
}