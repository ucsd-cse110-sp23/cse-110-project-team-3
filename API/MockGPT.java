public class MockGPT implements IGPT {
    public String generate(String prompt) {
        return "Mock Prompt: " + prompt;
    }
}