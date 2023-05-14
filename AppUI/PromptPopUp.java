import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

class PromptPopUp extends JFrame {
  PromptPopUp(String prompt) {

    JFrame frame = new JFrame ("Full Prompt Text");
    frame.setSize(500,500);
    frame.setResizable(true);
    
    //TEXT AREA
    JTextArea textArea = new JTextArea(prompt);
    textArea.setSize(400,400);    
    
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setEditable(false);
    textArea.setVisible(true);

    // SCROLL BAR
    JScrollPane scroll = new JScrollPane (textArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    frame.add(scroll);
    frame.setVisible(true);
  }
}

@interface override {
}
