package LoadHistory;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadHistory {
    /*
     * Gets ArrayList of Strings where each element is and answer-prompt formatted as
     * [Prompt]: [Answer]
     */
    public ArrayList<String> loadHistory() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("UserData/prompt_history.txt"));
            String line;
            ArrayList<String> history = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                history.add(line.substring(0, line.length()-1)); //remove '\n' from end of str
            }

            reader.close();
            return history;

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("loadHistory() not implemented");
        return null;
    }
    
}
