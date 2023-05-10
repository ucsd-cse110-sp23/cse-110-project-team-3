import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.swing.*;

class Header extends JPanel {

    public JButton promptHistoryButton;
    Color backgroundColor = new Color(240, 248, 255);
  
    Header() {
      this.setPreferredSize(new Dimension(400, 60)); // Size of the header
      this.setBackground(backgroundColor);
      
      promptHistoryButton = new JButton("prompt history");
      promptHistoryButton.setFont(new Font("Sans-serif", Font.ITALIC, 15));
      this.add(promptHistoryButton);

      JLabel titleText = new JLabel("Saylt Assistant v1.1"); // Text of the header

      titleText.setPreferredSize(new Dimension(200, 60));
      titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
      titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
      this.add(titleText); // Add the text to the header
    }

    
    public JButton getpromptHistoryButton(){
        return promptHistoryButton;
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

    // adding a Recorder object
    private Recorder r;

    MainPage mainPage;

    Footer(MainPage m) {

        isRecording = false;
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

        // initializing recorder object

        r = new Recorder();
        mainPage = m;

        addListeners();
    }

    private void addListeners(){
        // if the new question button is clicked, then display the Listening label
        newQuestionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!isRecording) {
                    startRecording();
                }

                // added recording function

                r.startListening();
            }
        });
        // if the pause button is clicked, then stop displaying the listenign label
        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (isRecording) {
                    stopRecording();
                }

                // added recording function

                r.stopListening();
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

class promptHeader extends JPanel{
    Color backgroundColor = new Color(240, 248, 255);
    JButton backButton;

    promptHeader(){
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);
        backButton = new JButton("back");
        this.add(backButton);
        JLabel titleText = new JLabel("Prompt History");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }

    public JButton getbackButton(){
        return backButton;
    }
}

// class Prompt extends JPanel {

//     JTextField taskName;
    
//     Color gray = new Color(218, 229, 234);
//     Color green = new Color(188, 226, 158);
  
//     Prompt(String s) {
//       this.setPreferredSize(new Dimension(400, 20)); // set size of task
//       this.setBackground(gray); // set background color of task
  
//       this.setLayout(new BorderLayout()); // set layout of task
  
//       taskName = new JTextField(s); // create task name text field
//       taskName.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
//       taskName.setBackground(gray); // set background color of text field
  
//       this.add(taskName, BorderLayout.CENTER);
//     }
// }
  
//   class List extends JPanel {
  
//     Color backgroundColor = new Color(240, 248, 255);
  
//     List() {
//       GridLayout layout = new GridLayout(10, 1);
//       layout.setVgap(5); // Vertical gap
  
//       this.setLayout(layout); // 10 tasks
//       this.setPreferredSize(new Dimension(400, 560));
//       this.setBackground(backgroundColor);
//     }
  
//   }

class promptBody extends JPanel{
    JLabel tLabel;
    List list;
    Color backgroundColor = new Color(240, 248, 255);

    promptBody(){
        this.tLabel = new JLabel();
        this.list = new List();

        for (Prompt c: list.loadPrompts()) {
            list.add(c);
            JButton doneButton = c.getDone();
            doneButton.addMouseListener(
              new MouseAdapter() {
                @override
                public void mousePressed(MouseEvent e) {
                  c.changeState(); // Change color of task
                  list.updateNumbers(); // Updates the numbers of the tasks
                  revalidate(); // Updates the frame
                }
              }
            );
          }
          list.updateNumbers(); // Updates the numbers of the tasks;
          repaint(); // Repaints the list

        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(backgroundColor);

        this.add(tLabel);
        this.add(list, BorderLayout.CENTER);
        this.setSize(400, 600); // 400 width and 600 height
        this.setVisible(true); // Make visible

        this.add(list, BorderLayout.CENTER); // Add list in middle of footer and title
    }
}

class MainPage extends JFrame {
    private Header header;
    private Footer footer;
    private resultUI resultUI;
    private promptBody promptBody;
    private promptHeader promptHeader;

    private JButton pauseButton;
    private JButton newQuestionButton;
    private JButton prompthistoryButton;
    private JButton backButton;

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
        
        prompthistoryButton = header.getpromptHistoryButton();
        pauseButton = footer.getPauseButton();
        newQuestionButton = footer.getNewQuestionButton();

        buttonLogic();
    }

    
    public void openPromptPage(){
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        promptBody = new promptBody();
        promptHeader = new promptHeader();

        this.remove(header);
        this.remove(footer);
        this.remove(resultUI);
        this.add(promptHeader, BorderLayout.NORTH);
        this.add(promptBody, BorderLayout.CENTER);

        backButton = promptHeader.getbackButton();
    }

    public void openMainPage(){
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        this.remove(promptHeader);
        this.remove(promptBody);
        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(resultUI, BorderLayout.CENTER); // adds question and response to center of screen

        prompthistoryButton = header.getpromptHistoryButton();
        pauseButton = footer.getPauseButton();
        newQuestionButton = footer.getNewQuestionButton();
    }

    public void buttonLogic(){
        prompthistoryButton.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    openPromptPage();
                    backButton.addMouseListener(
                        new MouseAdapter() {
                            public void mousePressed(MouseEvent e){
                                openMainPage();
                            }
                        }
                    );
                }
            }
        );
    }
    
    // sets question text
    public void setQuestionText(String question) {
        resultUI.qLabel.setText(question);
    }

    // sets answer text
    public void setAnswerText(String answer) {
        resultUI.aLabel.setText(answer);
    }
}
