import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextField;

/*
 *  Saves and load user's email preferences to Email Setup Popup
 */
public class SaveEmailPreferences{

    // saves user's email preferences for when setting up email and puts it in text file
    public void savePreferences(String txt_file, ArrayList<String> preferences) {
        try {
            FileWriter writer = new FileWriter(txt_file);
            for (String line : preferences) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("savePreferences() error");
        }
    }

    // loads user's email prefernces for when reloading into pop up in EmailSetUpPop
    public void loadPreferences(String fileName, EmailPopUpBody body) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //gets fields from email pop up
            JTextField[] fields = {
                body.getFirstName(),
                body.getLastName(),
                body.getDisplayName(),
                body.getEmailAddress(),
                body.getSMTPHost(),
                body.getTLSPort(),
                body.getEmailPassword()
            };

            //loads information onto the the fields
            String line;
            int idx = 0;
            while ((line = reader.readLine()) != null && idx < fields.length) {
                fields[idx].setText(line);
                idx++;
            }
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("loadPreferences() error");
        }
    }


}