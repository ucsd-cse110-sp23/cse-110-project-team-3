import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.JScrollPane;

import LoadHistory.LoadHistory;
import Mediator.Mediator;
import RecordHistory.RecordHistory;

class Header extends JPanel {

 //   public JButton promptHistoryButton;
    Color backgroundColor = new Color(240, 248, 255);

    Header() {
        this.setPreferredSize(new Dimension(400, 60)); // Size of the header
        this.setBackground(backgroundColor);

        JLabel titleText = new JLabel("Saylt Assistant v1.1"); // Text of the header

        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER); // Align the text to the center
        this.add(titleText); // Add the text to the header
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
    JLabel tLabel;
    PanelList list;
    JScrollPane scroll;

    private Mediator m;

    Sidebar(Mediator m) {
        this.m = m;
        this.setBackground(backgroundColor);
        this.tLabel = new JLabel();
        this.list = new PanelList(m);
        this.scroll = new JScrollPane(list);

        // sets scrollbar features
        scroll.setPreferredSize(new Dimension(400, 780));
        this.add(scroll, BorderLayout.CENTER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //loads prompts into bar
        for (Prompt c : list.loadPrompts()) {
            c.setPreferredSize(new Dimension(300, 100));
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
        repaint();
        this.add(tLabel);
        this.setVisible(true);
    }

    public void addPrompt(String s) {
        Prompt c = new Prompt(s, m);
        c.setPreferredSize(new Dimension(300, 100));
        list.add(c);
        JButton doneButton = c.getDone();
        doneButton.addMouseListener(
                new MouseAdapter() {
                    @override
                    public void mousePressed(MouseEvent e) {
                        c.changeState();
                    }
                });
        repaint();
    }

}

public class MainPage extends JFrame {
    private Header header;
    private Footer footer;
    private ResultUI resultUI;
    private Sidebar sidebar;

    private JButton newQuestionButton;

    private Mediator mediator;

    MainPage(Mediator m){
        mediator = m;
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        header = new Header();
        footer = new Footer();
        resultUI = new ResultUI();
        sidebar = new Sidebar(mediator);

        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(resultUI, BorderLayout.CENTER); // adds question and response to center of screen
        this.add(sidebar, BorderLayout.WEST); // adds sidebar for prompt history

        newQuestionButton = footer.getNewQuestionButton();

        buttonLogicMain();
    }

    public void openMainPage() {
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        this.add(resultUI, BorderLayout.CENTER); // adds question and response to center of screen

        newQuestionButton = footer.getNewQuestionButton();
    }

    public void buttonLogicMain() {
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
        ConfirmationPopUp c = new ConfirmationPopUp(mediator, sidebar);
        
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

    // get mediator
    public Mediator getMediator() {
        return this.mediator;
    }

}
