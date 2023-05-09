
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class StoryTests {

    // story 1 test
    @Test
    void testStoryAskQuestion() {

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
        
    }

}