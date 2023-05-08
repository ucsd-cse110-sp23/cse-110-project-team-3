package GPT;

public class MockGPT implements IGPT {
    int filler;
    public MockGPT() {
        filler = 0;
    }
    public String generate(String prompt) {
        return "This is a mock response for prompt: " + prompt + "";
    }
}