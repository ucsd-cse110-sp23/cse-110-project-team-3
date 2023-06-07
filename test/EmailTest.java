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

import Credentials.*;
import Mediator.*;
import Email.*;

import org.bson.types.ObjectId;

public class EmailTest {
    @Test
    void testEmail() {
        // checks that emailsetup works
        ObjectId id = new ObjectId("6480c3761faaae3d55287faf");
        Mediator m = new Mediator();
        m.setId(id);
        assertEquals(m.getId(), id);
        assertEquals(m.getId(), id);
        EmailSetup emailSetup = new EmailSetup("Kenneth", "Chung", "k2chung", "k2chung@ucsd.edu", "SMTP", "TLS", "kimchi", m);
        assertEquals(emailSetup.getFirstName(), "Kenneth");
        emailSetup = new EmailSetup("Kevin", "Chung", "k2chung", "k2chung@ucsd.edu", "SMTP", "TLS", "kimchi", m);
        assertEquals(emailSetup.getFirstName(), "Kevin");

        //
    }
}
