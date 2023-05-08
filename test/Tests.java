// set source paths to "src" and "test"
// ignore "package label does not match whatever yadad"

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

// import API.GPT.IGPT;
// import API.GPT.MockGPT;
// import AppUI.MainPage;

public class Tests {

    private String testString;

    @BeforeEach
    void setUp() {
        testString = "Ahoy!";
    }

    @Test
    void testTester() {
        assertEquals(testString, "Ahoy!");
    }

    // @Test
    // void testGPT() {
    //     IGPT mockGpt = new MockGPT();
    //     String testString1 = mockGpt.generate("What's the fourth most populous country?");
    //     assertEquals(testString1, "This is a mock response for prompt: What's the fourth most populous country?");
    // }

}