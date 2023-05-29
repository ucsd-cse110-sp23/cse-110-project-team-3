import java.lang.String;
import java.util.*;
import java.util.List;

public class VoiceCommands {
    private int maxCommandSize = 2;
    List<String> input;
    
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
        // TODO, if there are empty spaces before first word, array becomes ["", "", "firstword"]

        for (int i = 0; i < words.size(); i++) {
            words.set(i, removeNonAlphabet(words.get(i)));
        }

        System.out.println(words);

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