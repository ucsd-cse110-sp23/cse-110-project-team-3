import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;


public class ClearHistory {

    public void clearHistory() {
    
        try { 
            /* TODO: open prompt_history.txt from UserApps folder */

            /* TODO: delete prompt_history */
            // delete file then make a new one?

            // edit file to be an empty string?
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserData/prompt_history.txt", true));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
    
}
