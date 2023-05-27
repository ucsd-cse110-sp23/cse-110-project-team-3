import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

import LoadHistory.LoadHistory;
import Mediator.Mediator;
import RecordHistory.RecordHistory;

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

    public JButton getpromptHistoryButton() {
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

        pauseButton = new JButton("Stop Recording");
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

    public JButton getNewQuestionButton() {
        return newQuestionButton;
    }

    public JButton getPauseButton() {
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

    public JButton getbackButton() {
        return backButton;
    }
}

class PromptFooter extends JPanel {
    Color backgroundColor = new Color(240, 248, 255);
    JButton clearAllButton;

    PromptFooter() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(backgroundColor);
        clearAllButton = new JButton("clear all");
        clearAllButton.setFont(new Font("Sans-serif", Font.ITALIC, 15));
        this.add(clearAllButton);
    }

    public JButton getClearAllButton() {
        return clearAllButton;
    }
}

class PromptBody extends JPanel{
    JLabel tLabel;
    PanelList list;
    Color backgroundColor = new Color(240, 248, 255);

    PromptBody(){
        this.tLabel = new JLabel();
        this.list = new PanelList();

        for (Prompt c : list.loadPrompts()) {
            list.add(c);
            JButton doneButton = c.getDone();
            doneButton.addMouseListener(
                    new MouseAdapter() {
                        @override
                        public void mousePressed(MouseEvent e) {
                            c.changeState();
                        }
                    });
        }
        repaint(); // Repaints the list

        this.setPreferredSize(new Dimension(400, 400)); // Size of the body
        this.setBackground(backgroundColor);

        this.add(tLabel);
        this.add(list, BorderLayout.CENTER);
        this.setSize(400, 600); // 400 width and 600 height
        this.setVisible(true); // Make visible
    }

    public PanelList getPanelList() {
        return list;
    }
}

public class MainPage extends JFrame {
    private Header header;
    private Footer footer;
    private ResultUI resultUI;
    private PromptBody promptBody;
    private PromptHeader promptHeader;
    private PromptFooter promptFooter;

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

        buttonLogicMain();
    }

    public void openPromptPage() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        promptBody = new PromptBody();
        promptHeader = new PromptHeader();
        promptFooter = new PromptFooter();

        this.remove(header);
        this.remove(resultUI);
        this.add(promptHeader, BorderLayout.NORTH);
        this.add(promptBody, BorderLayout.CENTER);
        
        backButton = promptHeader.getbackButton();
    }

    public void openMainPage() {
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        this.remove(promptHeader);
        this.remove(promptBody);
        this.remove(promptFooter);
        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(resultUI, BorderLayout.CENTER); // adds question and response to center of screen

        prompthistoryButton = header.getpromptHistoryButton();
        pauseButton = footer.getPauseButton();
        newQuestionButton = footer.getNewQuestionButton();
    }

    public void buttonLogicMain() {
        prompthistoryButton.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    openPromptPage();
                    backButton.addMouseListener(
                        new MouseAdapter() {
                            public void mousePressed(MouseEvent e) {
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

    // sets question text
    public void setQuestionText(String question) {
        resultUI.qLabel.setText(question);
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
        System.out.println("In stopRecording()" + mediator.getIsConfirmed());
        
        if (mediator.getIsConfirmed()) {
            System.out.println("In mediator.getIsConfirmed()");
            mediator.generateAnswer();
            mediator.updateQuestionAndAnswer();
            mediator.setIsConfirmedFalse();

            VoiceCommands vc = new VoiceCommands(mediator.getQuestion());
             // if is question
            if (vc.isQuestionCommand()) {               
                System.out.println("Isquestion");
                RecordHistory rh = new RecordHistory();
                rh.sendToFile(mediator.getQuestion(), mediator.getAnswer(), "UserData/prompt_history.txt");
            }

            // if is delete prompt
            else if (vc.isDeletePromptCommand()) { 
                System.out.println("In isDeletePromptCommand()");
                PanelList list = promptBody.getPanelList();
                list.removeCompletedPrompts();
                promptBody.repaint();
            }

            // if is delete all
            else if (vc.isDeleteAllCommand()) {
                ClearHistory clearHistory = new ClearHistory();
                clearHistory.clearHistory();
                promptBody.list.removeAll();
                promptBody.repaint();
            } 
            
            else {
                // TODO call error message
            }
        }

       
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
