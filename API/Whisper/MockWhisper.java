package Whisper;

public class MockWhisper implements IWhisper {
    public String generate(String filePath) throws IOException, InterruptedException{
        return "This is a mock whisper";
    }
}