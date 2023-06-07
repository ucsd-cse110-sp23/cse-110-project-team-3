import java.lang.String;
import java.util.*;
import java.util.List;

/**
 * Purpose: For checking if a string input starts with a certain command
 */
public class VoiceCommands {
    private int maxCommandSize = 3;
    List<String> input;
    
    /*
     * MAIN METHODS: Voice Command Checkers
     */
    public VoiceCommands(String inputStr) {
        this.input = firstFewWords(inputStr);
    }

    public boolean isQuestionCommand() {
        return isSameCommand(Arrays.asList("question"), input);
    }

    public boolean isDeletePromptCommand() {
        return isSameCommand(Arrays.asList("delete", "prompt"), input);
    }

    public boolean isClearAllCommand() {
        return isSameCommand(Arrays.asList("clear", "all"), input);
    }

    public boolean isSendEmailCommand() {
        return isSameCommand(Arrays.asList("send", "email"), input);
    }

    public boolean isSetupEmailCommand() {
        return isSameCommand(Arrays.asList("setup", "email"), input) || 
               isSameCommand(Arrays.asList("set", "up", "email"), input);
    }

    public boolean isCreateEmailCommand() {
        return isSameCommand(Arrays.asList("create", "email"), input);
    }
    
    /* 
     * HELPER METHODS 
     */
    
    /**
     * Helper method: isSameCommand
     * @param command the command in list of strings where each element is one word
     * @param input list of strings where each element is one word
     * @returns boolean checking if the input and the command are the same
     **/
    private boolean isSameCommand(List<String> command, List<String> input) {
        if (command.size() > input.size()) {
            return false;
        }

        for (int i = 0; i < command.size(); i++) {
            if (!command.get(i).equals(input.get(i))) {
                return false;
            }
        }
        return true;
    }

    /** 
     * Helper method: firstFewWords
     * @param s: audio in string format
     * Gets the first maxCommandSize # of words from the input audio text
    */
    private List<String> firstFewWords(String s) {
        s = s.toLowerCase();
        List<String> words = Arrays.asList(s.split(" "));

        for (int i = 0; i < words.size(); i++) {
            words.set(i, removeNonAlphabet(words.get(i)));
        }

        int endIdx;
        
        if (words.size() < maxCommandSize) {
            endIdx = words.size();
        } else {
            endIdx = maxCommandSize;
        }

        List<String> sub = words.subList(0, endIdx);
        return sub;
    }

    private String removeNonAlphabet(String input) {
        // Replace all non-alphabetic characters with an empty string
        return input.replaceAll("[^a-zA-Z]", "");
    }
}