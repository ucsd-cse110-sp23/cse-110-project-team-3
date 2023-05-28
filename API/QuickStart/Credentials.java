package QuickStart;

import static com.mongodb.client.model.Filters.eq;


import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Credentials {
    
    private final String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";

    Credentials() {
        //
    }

    public void createAccount(String username, String password) {

        // check if account with username already exists
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("accounts");

            Document doc = collection.find(eq("username", username)).first();
            if (doc != null) {
                System.out.println("Username is already taken");
            } else {
                System.out.println("Username is free!");
            }

        }

        // add account with username and password
        //

    }

    public static void main(String args[]) {
        Credentials c = new Credentials();
        c.createAccount("k4chung", "Rotary552");
    }

}
