
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
import java.io.IOException;
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
        // //assertEquals("", System.getProperty("user.dir"));
        // lh = new LoadHistory();
        // dummyQuestions = new ArrayList<String>();
        // for (int i = 0; i < numQuestions; i++) {
        //     dummyQuestions.add("Dummy Question " + i + ": Dummy Answer " + i);
        // }
        // assertEquals(dummyQuestions, lh.loadHistory("test_prompt.txt"));
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
    }

    @Test
    void testVoiceRecorder() {
        vr = new VoiceRecorder();
        vr.startListening();
    }

    @Test
    void testMediator() throws IOException, NullPointerException{
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