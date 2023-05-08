
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

public class StoryTests {

    private String testString;

    @BeforeEach
    void setUp() {
        testString = "Ahoy!";
    }

    @Test
    void testAppUI() {
        MainPage mainPage = new MainPage();
        assertEquals("User inputted question \n",mainPage.getQuestionText());
    }

    
    // story 1 test
    @Test
    void testStoryAskQuestion() {

        // UI
        MainPage mainPage = new MainPage();
        assertEquals("User inputted question \n",mainPage.getQuestionText());

        // mock process recording into text prompt
        IWhisper iWhisper = new MockWhisper();
        String mockRecordingFilePath = "";
        String mockPrompt = "";
        try {
            mockPrompt = iWhisper.generate(mockRecordingFilePath);
        } catch (Exception e) {
            //
        }
        
        // mock process text prompt into text answer
        IGPT mockGpt = new MockGPT();
        String mockAnswer = mockGpt.generate(mockPrompt);
        assertEquals("Mock Prompt: " + mockPrompt, mockAnswer);

        // simulate user experience
        assertEquals("User inputted question \n", mainPage.getQuestionText());
        mainPage.setQuestionText(mockPrompt);
        assertEquals(mockPrompt, mainPage.getQuestionText());
    }

}