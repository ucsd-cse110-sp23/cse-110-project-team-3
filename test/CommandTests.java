import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandTests {
    VoiceCommands vc;

    @Test
    void testQuestionTrue() {
        vc = new VoiceCommands("Question. How many chickens in a foot?");
        assertEquals(true, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
    }

    @Test
    void testDeletePromptTrue() {
        vc = new VoiceCommands("delete prompt. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(true, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
    }

    @Test
    void testClearAllTrue() {
        vc = new VoiceCommands("Clear all. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(true, vc.isClearAllCommand());
    }
    
    @Test
    void noInput() {
        vc = new VoiceCommands("");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
    }

    @Test
    void testNoCommand() {
        vc = new VoiceCommands("clear delete question. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
    }
}
