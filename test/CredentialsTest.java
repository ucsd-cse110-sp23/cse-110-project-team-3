import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import QuickStart.*;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

// testing log in and create account

public class CredentialsTest {
    
    String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";
    MongoClient mongoClient = MongoClients.create(uri);
    MongoDatabase database = mongoClient.getDatabase("userdata");
    MongoCollection<Document> collection = database.getCollection("accounts");
    Credentials c = new Credentials();

    String testUser = "kimchung";
    String testPassword = "Kelvin273";

    /*
     * Given user creates account
     * And account with username does not exist
     * Then account is added to database
     */
    @Test
    void testCreateAccount() {
        c.createAccount(testUser, testPassword);
        Document doc = collection.find(eq("username", testUser)).first();
        if (doc != null) {
            assertEquals(doc.get("username"), testUser);
            assertEquals(doc.get("password"), testPassword);
        } else {
            assertEquals(true, false); // account with username was not added somehow
        }
    }

    /*
     * Given user creates account
     * And account with username already exists
     * Then account is not added to database
     * And user is told the account username is taken
     */
    @Test
    void testUsernameTaken() {
        //
    }

    /*
     * Given user has created an account
     * And user enters correct username and password
     * Then user is able to log in
     */
    @Test
    void testLogIn() {
        //
    }

    /*
     * Common login L's like:
     *  - wrong password
     *  - wrong username
     *  - account never existed
     */
    @Test
    void testLogInFailure() {
        //
    }

}
