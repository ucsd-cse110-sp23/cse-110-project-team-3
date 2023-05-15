package LoadHistory;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LoadHistory {
    /*
     * Gets ArrayList of Strings where each element is and answer-prompt formatted as
     * [Prompt]: [Answer]
     */
    public ArrayList<String> loadHistory(String filePath) {
        ArrayList<String> history = new ArrayList<>();
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                history.add(line.substring(0, line.length())); //remove '\n' from end of str
            }

            reader.close();
            return history;

        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            history.add(sStackTrace);
            return history;
        }
        // System.out.println("loadHistory() not implemented");
        // return null;
    }

    public static void main(String[] args) {
        LoadHistory lh = new LoadHistory();
        System.out.println(lh.loadHistory("Test_Files/test_prompt.txt"));
    }
    
}
