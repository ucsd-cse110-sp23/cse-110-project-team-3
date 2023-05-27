import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.JScrollPane;

import LoadHistory.LoadHistory;
import Mediator.Mediator;

class Header extends JPanel {

 //   public JButton promptHistoryButton;
    Color backgroundColor = new Color(240, 248, 255);

    Header() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

    /*    promptHistoryButton = new JButton("prompt history");
        promptHistoryButton.setFont(new Font("Sans-serif", Font.ITALIC, 15));
        this.add(promptHistoryButton); */

        JLabel titleText = new JLabel("Saylt Assistant v1.1"); // Text of the header

        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
    }

   /* public JButton getpromptHistoryButton() {
        return promptHistoryButton;
    }*/
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
    private JLabel listeningLabel;
    Color backgroundColor = new Color(240, 248, 255);

    Footer() {
        
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(backgroundColor);

        this.setLayout(new GridLayout(2, 2));

        newQuestionButton = new JButton("Start");
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
}

class Sidebar extends JPanel {
    Color backgroundColor = new Color(209, 216, 222);
    private JLabel sideBarLabel;

    Sidebar() {
        this.setPreferredSize(new Dimension(250,600));
        this.setBackground(backgroundColor);

        JScrollPane historyBar = new JScrollPane();
        historyBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        sideBarLabel = new JLabel("Prompt History");
        sideBarLabel.setFont(new Font("Sans-serif", Font.ITALIC, 15));
        sideBarLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(sideBarLabel, BorderLayout.NORTH);
        panel.add(historyBar, BorderLayout.WEST);


        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(5); // Vertical gap
        this.setLayout(layout);

      //  titleLabel = new JLabel("Prompt History");
      //  titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
                            list.removeCompletedPrompts(c);
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
}

public class MainPage extends JFrame {
    private Header header;
    private Footer footer;
    private ResultUI resultUI;
    private Sidebar sidebar;
    private PromptBody promptBody;
    private PromptHeader promptHeader;
    private PromptFooter promptFooter;

    private JButton newQuestionButton;
  //  private JButton prompthistoryButton;
    private JButton backButton;
    private JButton clearAllButton;

    private Mediator mediator;

    MainPage(){
        mediator = new Mediator();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        header = new Header();
        footer = new Footer();
        resultUI = new ResultUI();
        sidebar = new Sidebar();

        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(resultUI, BorderLayout.CENTER); // adds question and response to center of screen
        this.add(sidebar, BorderLayout.WEST); // adds sidebar for prompt history

    //    prompthistoryButton = header.getpromptHistoryButton();
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
        this.remove(footer);
        this.remove(resultUI);
        this.add(promptHeader, BorderLayout.NORTH);
        this.add(promptBody, BorderLayout.CENTER);
        this.add(promptFooter, BorderLayout.SOUTH);

        backButton = promptHeader.getbackButton();
        clearAllButton = promptFooter.getClearAllButton();

        buttonLogicPrompt();
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

     //   prompthistoryButton = header.getpromptHistoryButton();
        newQuestionButton = footer.getNewQuestionButton();
    }

    public void buttonLogicMain() {
       /*  prompthistoryButton.addMouseListener(
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
        ); */
        footer.getNewQuestionButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!mediator.isRecording()) {
                    startRecording();
                    mediator.setIsRecording(true);
                    newQuestionButton.setText("Stop");
                }
                else {
                    stopRecording();
                    mediator.setIsRecording(false);
                    newQuestionButton.setText("Start");
                }
            }
        });
    }

    public void buttonLogicPrompt() {
        clearAllButton.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    ClearHistory clearHistory = new ClearHistory();
                    clearHistory.clearHistory();
                    promptBody.list.removeAll();
                    promptBody.repaint();
                }
            }
        );
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
