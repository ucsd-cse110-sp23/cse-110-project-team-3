import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testMainPage() {
        MainPage m = new MainPage();
    }

    @Test
    void testGPT() {
        MockGpt mockGpt = new mockGpt();
        // i can't get it to f*cking resolve the type and i don't know how
        // censored for the sake of graders
    }

}
