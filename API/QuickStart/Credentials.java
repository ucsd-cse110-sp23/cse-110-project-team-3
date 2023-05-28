package QuickStart;

/*
 * This class handles dealing with credentials, namely creating an account 
 * and logging in.
 */

import static com.mongodb.client.model.Filters.eq;


import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

public class Credentials {
    
    private final String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";

    public Credentials() {
        //
    }

    public void createAccount(String username, String password) {

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            
            // check if account with username already exists
            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("accounts");

            Document doc = collection.find(eq("username", username)).first();
            if (doc != null) {
                throw new Exception("Account with username already exists");
            } else {
                System.out.println("Username is free!");
            }

            // add account with username and password
            Document newAccount = new Document("_id", new ObjectId());
            newAccount.append("username", username);
            newAccount.append("password", password);
            
            collection.insertOne(newAccount);
            
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void login(String username, String password) {
        
        try {
            //
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void deleteAccount(String username, String password) {
        //
    }

}
