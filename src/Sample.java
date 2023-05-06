import javax.swing.*;

public class Sample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hamburger Menu Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        
        JMenu hamburgerMenu = new JMenu();
        hamburgerMenu.setText("Hamburger Menu");
        
        JMenuItem item1 = new JMenuItem("Option 1");
        JMenuItem item2 = new JMenuItem("Option 2");
        JMenuItem item3 = new JMenuItem("Option 3");
        
        hamburgerMenu.add(item1);
        hamburgerMenu.add(item2);
        hamburgerMenu.add(item3);
        
        menuBar.add(hamburgerMenu);
        
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}

