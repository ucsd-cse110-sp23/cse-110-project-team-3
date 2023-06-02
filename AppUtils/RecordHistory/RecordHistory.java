package RecordHistory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class RecordHistory {
    
    public void sendToFile(String prompt, String question, String filePath) {
        try {
            String outtext = removeNewLines(prompt) + ": " + removeNewLines(question) + "\n";
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
            
            out.write(outtext);
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("saveToFile() not implemented");
            e.printStackTrace();
        }
    }

    private String removeNewLines(String text) {
        if (text == null) {
            return "";
        }
        char[] c = text.toCharArray();
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (c[i] != '\n') {
                result += c[i];
            }
        }

        return result;
    }

}
