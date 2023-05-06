import java.awt.*;
import java.io.*;
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

class Footer extends JPanel {
    JButton newQuestionButton;
    JButton pauseButton;
    Color backgroundColor = new Color(240, 248, 255);

    Footer() {
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(backgroundColor);
        // TODO: Set the layout of the footer to a GridLayout with 1 row and 4 columns

        this.setLayout(new GridLayout(1, 2));

        pauseButton = new JButton("||");
        pauseButton.setFont(new Font("Sans-serif", Font.ITALIC, 10));
        this.add(pauseButton);

        newQuestionButton = new JButton("New Question");
        newQuestionButton.setFont(new Font("Sans-serif", Font.ITALIC, 10));
        this.add(newQuestionButton);
    }

    public JButton getnewQuestionButton(){
        return newQuestionButton;
    }

    public JButton getPauseButton(){
        return pauseButton;
    }
}

class AppFrame extends JFrame {
    private Header header;
    private Footer footer;

    private JButton pauseButton;
    private JButton newQuestionButton;

    AppFrame(){
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on exit
        this.setVisible(true); // Make visible

        header = new Header();
        footer = new Footer();

        this.add(header, BorderLayout.NORTH); // Add title bar on top of the screen
        this.add(footer, BorderLayout.SOUTH); // Add footer on bottom of the screen
        
        pauseButton = footer.getPauseButton();
        newQuestionButton = footer.getnewQuestionButton();
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        new MainPage();
    }
}