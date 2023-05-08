package Whisper;

public interface IWhisper {
    public String generate(String filePath) throws IOException, InterruptedException;
}