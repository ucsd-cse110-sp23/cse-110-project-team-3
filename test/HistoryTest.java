import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import History.*;

import org.bson.types.ObjectId;

public class HistoryTest {
    
    History history = new History();

    @Test
    void testHistory() {

        // putting new history
        ObjectId id = new ObjectId();
        String text = "what is the shape of italy?\n a boot\n";
        history.putHistory(id, text);
        assertEquals(history.getHistory(id), text);

        // updating existing history
        text = "what is the shape of italy?\n a boot\n what is the shape of italy?\n a boot\n";
        history.putHistory(id, text);
        assertEquals(history.getHistory(id), text);

        history.deleteHistory(id);

    }

}
