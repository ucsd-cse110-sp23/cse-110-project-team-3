import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClearHistory {

    /**
     * Clears the prompt history file
     */
    ClearHistory() {
    }

    public void clearHistory() {

        try { 
            //gets file to edit
            FileWriter ph_file = new FileWriter("UserData/prompt_history.txt");
            BufferedWriter writer = new BufferedWriter(ph_file);

            //writes an empty string to clear content
            writer.write("");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error in clearHistory()");
            e.printStackTrace();
        }
    }
}
