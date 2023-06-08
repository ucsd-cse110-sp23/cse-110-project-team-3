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
        if (isSameCommand(Arrays.asList("send", "email", "to"), input) && (input.size() >= 4)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSetupEmailCommand() {
        return isSameCommand(Arrays.asList("setup", "email"), input) || 
               isSameCommand(Arrays.asList("set", "up", "email"), input);
    }

    public boolean isCreateEmailCommand() {
        return isSameCommand(Arrays.asList("create", "email"), input);
    }

    public String getEmailAddress() {
        if (input.size() < 4) {
            return "Bad call to get email address";
        } else {
            String address = input.get(3);

            // concatonate everything after command into email address
            for (int i = 4; i < input.size(); i ++) {
                if (removeNonAlphabet(input.get(i).toLowerCase()).equals("at")) {
                    address += "@";
                } else if (input.get(i).toLowerCase().equals("dot")) {
                    address += ".";
                } else {
                    address += input.get(i);
                }
            }

            // get rid of period at the end of email address
            if (address.length() >= 1) {
                if (address.charAt(address.length()-1) == '.') {
                    address = address.substring(0, address.length()-1);
                }
            }
            return address;
        }
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
        
        // Remove non alphabet for maximum maxWordsChanged
        int maxWordsChanged;
        if (words.size() < maxCommandSize) {
            maxWordsChanged = words.size();
        } else {
            maxWordsChanged = maxCommandSize;
        }

        for (int i = 0; i < maxWordsChanged; i++) {
            words.set(i, removeNonAlphabet(words.get(i)));
        }

        return words;
    }

    private String removeNonAlphabet(String input) {
        // Replace all non-alphabetic characters with an empty string
        return input.replaceAll("[^a-zA-Z]", "");
    }
}