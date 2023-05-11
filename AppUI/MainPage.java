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

class ResultUI extends JPanel{
    JLabel qLabel;
    JLabel aLabel;

    ResultUI(){
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
    Color backgroundColor = new Color(240, 248, 255);

    Footer() {
        
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

    }

    public JLabel getListeningLabel() {
        return listeningLabel;
    }

    public JButton getNewQuestionButton(){
        return newQuestionButton;
    }

    public JButton getPauseButton(){
        return pauseButton;
    }

}

class PromptHeader extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);
    JButton backButton;

    PromptHeader(){
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

class Prompt extends JPanel {

    JTextField taskName;
    
    Color gray = new Color(218, 229, 234);
    Color green = new Color(188, 226, 158);
  
    Prompt(String s) {
      this.setPreferredSize(new Dimension(400, 20)); // set size of task
      this.setBackground(gray); // set background color of task
  
      this.setLayout(new BorderLayout()); // set layout of task
  
      taskName = new JTextField(s); // create task name text field
      taskName.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
      taskName.setBackground(gray); // set background color of text field
  
      this.add(taskName, BorderLayout.CENTER);
    }
}
  
class List extends JPanel {
  
    Color backgroundColor = new Color(240, 248, 255);
  
    List() {
      GridLayout layout = new GridLayout(10, 1);
      layout.setVgap(5); // Vertical gap
  
      this.setLayout(layout); // 10 tasks
      this.setPreferredSize(new Dimension(400, 560));
      this.setBackground(backgroundColor);
    }
  
  }

class PromptBody extends JPanel{
    JLabel tLabel;
    List list;
    Color backgroundColor = new Color(240, 248, 255);

    PromptBody(){
        this.tLabel = new JLabel();
        this.list = new List();

        String history = "";

        ArrayList<String> qa = (new LoadHistory()).loadHistory();
        
        if (qa != null) {
            for (String s: qa) {
                list.add(new Prompt(s));
                revalidate();
                history += s + "\n";
            }
            repaint();
        }

        //this.setPreferredSize(new Dimension(400, 60));
        //tLabel.setText(history);
        //tLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //tLabel.setFont(new Font("Sans-serif", Font.ITALIC, 20));

        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(backgroundColor);
        JTextArea titleText = new JTextArea(history); // Text of the header
        titleText.setPreferredSize(new Dimension(600, 600));
        titleText.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        this.add(titleText); // Add the text to the header
        titleText.setLineWrap(true);
        titleText.setWrapStyleWord(true);
        titleText.setEditable(false);

        this.add(tLabel);
        
    }
}

public class MainPage extends JFrame {
    private Header header;
    private Footer footer;
    private ResultUI resultUI;
    private PromptBody promptBody;
    private PromptHeader promptHeader;

    private JButton pauseButton;
    private JButton newQuestionButton;
    private JButton prompthistoryButton;
    private JButton backButton;

    private Mediator mediator;

    MainPage(){
        mediator = new Mediator();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        header = new Header();
        footer = new Footer();
        resultUI = new ResultUI();

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

        promptBody = new PromptBody();
        promptHeader = new PromptHeader();

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
        footer.getNewQuestionButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!mediator.isRecording()) {
                    startRecording();
                }
            }
        });
        footer.getPauseButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (mediator.isRecording()) {
                    stopRecording();
                }
            }
        });
    }

    // recording related methods
    // starts recording when user clicks "New Question"
    private void startRecording() {
        mediator.switchIsRecording();;
        footer.getListeningLabel().setVisible(true);
        mediator.startRecording();
    }
    // stops recording when user clicks pause
    private void stopRecording() {
        footer.getListeningLabel().setVisible(false);
        mediator.stopRecording();
        mediator.switchIsRecording();
        ConfirmationPopUp c = new ConfirmationPopUp(mediator);
        // once c is closed
        c.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                updateUI();
            }
            public void windowClosing(WindowEvent e) {
                updateUI(); // will update to new question and answer; q and a are only changed by mediatior if accept is clicked in confirmationpopup
            }
        });

    }

    // update question and answer
    public void updateUI() {
        resultUI.qLabel.setText(mediator.getQuestion());
        resultUI.aLabel.setText(mediator.getAnswer());
    }

}
