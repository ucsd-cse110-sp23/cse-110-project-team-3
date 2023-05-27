import java.lang.String;
import java.util.*;

public class VoiceCommands {
    private int maxCommandSize = 2;
    List<String> input;
    
    public VoiceCommands(String inputStr) {
        input = firstFewWords(inputStr);
    }

    public boolean isQuestionCommand() {
        return isSameCommand(Arrays.asList("question"), input);
    }

    public boolean isDeletePromptCommand() {
        return isSameCommand(Arrays.asList("delete", "prompt"), input);
    }

    public boolean isDeleteAllCommand() {
        return isSameCommand(Arrays.asList("clear", "all"), input);
    }
    
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

    private List<String> firstFewWords(String s) {
        s = s.toLowerCase();
        List<String> words = Arrays.asList(s.split(" "));
        // TODO, if there are empty spaces before first word, array becomes ["", "", "word"]

        int endIdx;
        
        if (words.size() < maxCommandSize) {
            endIdx = words.size();
        } else {
            endIdx = maxCommandSize;
        }

        List<String> sub = words.subList(0, endIdx);
        return sub;
    }

    // public static void main (String[] args) {
    //     VoiceCommands vc = new VoiceCommands();
    //     vc.mediateVoiceCommands("hello");
    //     vc.mediateVoiceCommands("Question me mo me mo");
    //     vc.mediateVoiceCommands("Delete Prompt me no mo meo meo");
    //     vc.mediateVoiceCommands("Clear all boop bo boop bo");
    // }

}