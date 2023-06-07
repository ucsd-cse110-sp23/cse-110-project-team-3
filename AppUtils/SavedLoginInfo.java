import javax.swing.*;  
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.File;

public class SavedLoginInfo {
    JCheckBox checkBox;
    JTextField userField;
    JTextField passwordField;

    public SavedLoginInfo(){}
    
    public SavedLoginInfo(LoginBody loginBody) {
        this.checkBox = loginBody.getSaveLoginBox();
        this.userField = loginBody.getUsernameField();
        this.passwordField = loginBody.getPasswordField();
    }

    /**
     * Text file:
     * boolean (if true loggin saved, if false login not saved)
     * username
     * password
     */
    public void saveLogin() {
        try {
            FileWriter writer = new FileWriter("UserData/user_login_info.txt");
            BufferedWriter buffwriter = new BufferedWriter(writer);
            buffwriter.write(""); //clear content
            
            if (checkBox.isSelected()) {
                writer.write("true\n");
                writer.write(userField.getText() + "\n");
                writer.write(passwordField.getText());
            } else {
                writer.write("false");
            }

            writer.close();
            buffwriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("saveLogin() error");
        }
    }
    
    public ArrayList<String> loadLogin() {
        ArrayList<String> loginInfo = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("UserData/user_login_info.txt"));
            //loads information onto the the fields
            String line;
            while ((line = reader.readLine()) != null) {
                loginInfo.add(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("loadLogin() error");
        }
        return loginInfo;
    }

    public static void main (String[] args) {
        File tmpDir = new File("./UserData/user_login_inf.txt");
        boolean exists = tmpDir.exists();
        System.out.println(exists);
    }
}
