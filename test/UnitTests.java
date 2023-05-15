
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
        // clear prompt history
        try {
            FileWriter history = new FileWriter("./UserData/prompt_history.txt", false);
            history.close();
        } catch (Exception e) {
            System.out.println("Could not reset file");
        }
    }

    @Test
    void testLoadHistory() {
        lh = new LoadHistory();
        rh = new RecordHistory();
        dummyQuestions = new ArrayList<String>();
        for (int i = 0; i < numQuestions; i++) {
            rh.sendToFile("Dummy Question " + i, "Dummy Answer " + i);
            dummyQuestions.add("Dummy Question " + i + ": Dummy Answer " + i);
        }
        // assertEquals(dummyQuestions, lh.loadHistory());
    }

    @Test
    void testRecordHistory() {
        rh = new RecordHistory();
        dummyQuestions = new ArrayList<String>();
        for (int i = 0; i < numQuestions; i++) {
            rh.sendToFile("Dummy Question " + i, "Dummy Answer " + i);
            dummyQuestions.add("Dummy Question " + i + ": Dummy Answer " + i);
        }
        lh = new LoadHistory();
        // assertEquals(lh.loadHistory(), dummyQuestions)
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