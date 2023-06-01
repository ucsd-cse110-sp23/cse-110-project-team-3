import java.io.FileWriter;
import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;

public class DeletePrompt {
    
    public void deletePrompt(List<String> data) {
        // take an array  list of strings clear data from a file and write the new data to the file
        try {
            FileWriter ph_file = new FileWriter("UserData/prompt_history.txt");
            ph_file.write("");
            ph_file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter ph_file = new FileWriter("UserData/prompt_history.txt");
            BufferedWriter writer = new BufferedWriter(ph_file);
            for (String s : data) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
