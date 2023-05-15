
import org.junit.jupiter.api.Test;

import LoadHistory.LoadHistory;
import Mediator.Mediator;
import RecordHistory.RecordHistory;
import VoiceRecorder.VoiceRecorder;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import java.util.ArrayList;

public class UnitTests {

    LoadHistory lh;
    RecordHistory rh;
    VoiceRecorder vr;
    ArrayList<String> dummyQuestions;
    int numQuestions = 3;
    Mediator mediator;

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void testLoadHistory() {
        File testHistory = new File("test_prompt.txt");
        try {
            FileWriter fw = new FileWriter(testHistory);
            fw.write("Dummy Question lh: Dummy Answer lh");
            fw.close();
        } catch (Exception e) {
            //
        }
        assertEquals(testHistory.exists(), true);
        dummyQuestions = new ArrayList<String>();
        dummyQuestions.add("Dummy Question lh: Dummy Answer lh");
        lh = new LoadHistory();
        assertEquals(lh.loadHistory("test_prompt.txt"), dummyQuestions);
    }

    @Test
    void testRecordHistory() {
        // rh = new RecordHistory();
        // dummyQuestions = new ArrayList<String>();
        // for (int i = 0; i < numQuestions; i++) {
        //     //rh.sendToFile("Dummy Question " + i, "Dummy Answer " + i);
        //     dummyQuestions.add("Dummy Question " + i + ": Dummy Answer " + i);
        // }
        // String line;
        // ArrayList<String> actualQuestions = new ArrayList<String>();
        
        // try {
        //     Scanner myReader = new Scanner(new File("Test_Files/test_prompt.txt"));
        //     while (myReader.hasNextLine()) {
        //       line = myReader.nextLine();
        //       actualQuestions.add(line);
        //     }
        //     myReader.close();
        // } catch (Exception e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }
        // assertEquals(actualQuestions, dummyQuestions);

        rh = new RecordHistory();
        rh.sendToFile("Dummy Question 0", "Dummy Answer 0", "test_prompt.txt");
    }

    @Test
    void testVoiceRecorder() {
        vr = new VoiceRecorder();
        vr.startListening();
    }

    @Test
    void testMediator() {
        mediator = new Mediator();
        mediator.generateQuestion();
        mediator.generateAnswer();
        assertEquals("User inputted question \n", mediator.getQuestion());
        assertEquals("Answer to user question", mediator.getAnswer());
        //mediator.updateQuestionAndAnswer();
        //assertEquals("UserData/recording.wav", mediator.getQuestion());
        //assertEquals("Mock Prompt: UserData/recording.wav", mediator.getAnswer());
    }

}