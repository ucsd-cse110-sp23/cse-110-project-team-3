// set class path to . and nothing else

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
// import java.util.Scanner;
import java.io.FileWriter;
import java.util.Scanner;

// import API.GPT.IGPT;
// import API.GPT.MockGPT;
// import AppUI.MainPage;

public class Tests {

    private String testString;

    @BeforeEach
    void setUp() {
        testString = "Ahoy!";
    }

    @Test
    void testTester() {
        assertEquals(testString, "Ahoy!");
    }

    @Test
    void testAppUI() {
        MainPage mainPage = new MainPage();
        assertEquals("User inputted question \n",mainPage.getQuestionText());
    }

    @Test
    void testRecordHistory() {
        // creates prompt_history.txt
        RecordHistory rh = new RecordHistory();
        rh.sendToFile("Dummy Answer", "Dummy Question");

        // creates dummy file to compare to RecordHistory
        File expFile = new File("dummy_history.txt");
        try {
            if (expFile.createNewFile()) {
    
            }
            FileWriter expFileWriter = new FileWriter(expFile);
            expFileWriter.write("Dummy Question: Dummy Answer\n");
            expFileWriter.close();
        } catch (Exception e) {
            //
        }
        // open actually created file
        File actFile = new File("prompt_history.txt");
        
        // reads content of expected and actual file
        String exp = "";
        String act = "";
        try {
            Scanner expScanner = new Scanner(expFile);
            while (expScanner.hasNextLine()) {
                exp = expScanner.nextLine();
            }
            expScanner.close();
        } catch (Exception e) {
            //
        }
        try {
            Scanner actScanner = new Scanner(actFile);
            while (actScanner.hasNextLine()) {
                act = actScanner.nextLine();
            }
            actScanner.close();
        } catch (Exception e) {
            //
        }
        
        assertEquals(true, actFile.exists());
        assertEquals(true, expFile.exists());
        assertEquals(exp, act);
    }

}