import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

class Prompt extends JPanel {

  JTextArea promptName;
  JButton doneButton;
  JButton fullPromptButton;

  Color gray = new Color(218, 229, 234);
  Color green = new Color(188, 226, 158);

  private boolean markedDone;

  Prompt(String prompt) {
    this.setPreferredSize(new Dimension(400, 20)); // set size of prompt
    this.setBackground(gray); // set background color of prompt
    this.setLayout(new BorderLayout()); // set layout of prompt

    markedDone = false;

    // See full prompt: create and add
    fullPromptButton = new JButton("Full Prompt");
    fullPromptButton.setPreferredSize(new Dimension(100, 20));
    fullPromptButton.setBorder(BorderFactory.createEmptyBorder());
    fullPromptButton.setFocusPainted(false);
    this.add(fullPromptButton, BorderLayout.WEST);

    // Prompt text: create and add
    promptName = new JTextArea(prompt); // create prompt name text field
    promptName.setBorder(BorderFactory.createEmptyBorder()); // remove border of text field
    promptName.setBackground(gray); // set background color of text field
    promptName.setLineWrap(true);
    promptName.setWrapStyleWord(true);
    promptName.setEditable(false);
    this.add(promptName, BorderLayout.CENTER);

    // Select button: create and add
    doneButton = new JButton("Select");
    doneButton.setPreferredSize(new Dimension(80, 20));
    doneButton.setBorder(BorderFactory.createEmptyBorder());
    doneButton.setFocusPainted(false);
    this.add(doneButton, BorderLayout.EAST);

    // Mouse Listener for see full prompt
    fullPromptButton.addMouseListener(
      new MouseAdapter() {
          @override
          public void mousePressed(MouseEvent e) {
              new PromptPopUp(prompt);
          }
      });
  }

  public JButton getDone() {
    return doneButton;
  }

  public boolean getState() {
    return markedDone;
  }

  public void changeState() {
    if (markedDone == false) {
      this.setBackground(green);
      promptName.setBackground(green);
      markedDone = true;
    } else {
      this.setBackground(gray);
      promptName.setBackground(gray);
      markedDone = false;
    }
    revalidate();
  }
}

class List extends JPanel {

  Color backgroundColor = new Color(240, 248, 255);

  List() {
    GridLayout layout = new GridLayout(0, 1);
    layout.setVgap(5); // Vertical gap

    this.setLayout(layout);
    this.setPreferredSize(new Dimension(400, 600));
    this.setBackground(backgroundColor);
  }

  public void removeCompletedPrompts() {
    for (Component c : getComponents()) {
      if (c instanceof Prompt) {
        if (((Prompt) c).getState()) {
          remove(c); // remove the component
        }
      }
    }
  }

  /**
   * Loads prompts from a file called "prompt_history.txt"
   * 
   * @return an ArrayList of Prompt
   */
  public ArrayList<Prompt> loadPrompts() {
    ArrayList<String> promptStrings = (new LoadHistory()).loadHistory();
    ArrayList<Prompt> prompts = new ArrayList<>();

    for (String promptString : promptStrings) {
      Prompt prompt = new Prompt(promptString);
      prompts.add(prompt);
    }

    return prompts;
  }

  /**
   * Saves prompts to a file called "prompt_history.txt"
   */
  public void savePrompts() {
    try {
      FileWriter outfile = new FileWriter("prompts.txt");

      for (Component c : getComponents()) {
        if (c instanceof Prompt) {
          outfile.write(((Prompt) c).promptName.getText() + '\n');
        }
      }

      outfile.close();

    } catch (IOException e) {
      System.out.println("savePrompts() not implemented");
      e.printStackTrace();
    }
  }
}
