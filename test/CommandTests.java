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
    void testSetupEmailTrue() {
        vc = new VoiceCommands("setup email. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(true, vc.isSetupEmailCommand());
        assertEquals(false, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());

        vc = new VoiceCommands("set up email. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(true, vc.isSetupEmailCommand());
        assertEquals(false, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());
    }

    @Test
    void testSendEmailTrue() {
        vc = new VoiceCommands("send email to man. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(false, vc.isSetupEmailCommand());
        assertEquals(true, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());
    }

    @Test
    void testSendEmailFalse() {
        vc = new VoiceCommands("send email to");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(false, vc.isSetupEmailCommand());
        assertEquals(false, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());
    }

    @Test
    void testCreateEmailTrue() {
        vc = new VoiceCommands("create email. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(false, vc.isSetupEmailCommand());
        assertEquals(false, vc.isSendEmailCommand());
        assertEquals(true, vc.isCreateEmailCommand());
    }
    
    @Test
    void noInput() {
        vc = new VoiceCommands("");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(false, vc.isSetupEmailCommand());
        assertEquals(false, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());
    }

    @Test
    void testBadCommand() {
        vc = new VoiceCommands("clear delete question. How many chickens in a foot?");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(false, vc.isSetupEmailCommand());
        assertEquals(false, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());
    }

    @Test
    void testGetEmailAddress() {
        vc = new VoiceCommands("send email to mememomo1@gmail.com How many chickens in a foot");
        assertEquals(false, vc.isQuestionCommand());
        assertEquals(false, vc.isDeletePromptCommand());
        assertEquals(false, vc.isClearAllCommand());
        assertEquals(false, vc.isSetupEmailCommand());
        assertEquals(true, vc.isSendEmailCommand());
        assertEquals(false, vc.isCreateEmailCommand());

        assertEquals("mememomo1@gmail.com", vc.getEmailAddress());
    }
}
