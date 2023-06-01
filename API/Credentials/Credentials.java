package Credentials;

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

    public Boolean createAccount(String username, String password) {

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            
            // check if account with username already exists
            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("accounts");

            Document doc = collection.find(eq("username", username)).first();
            if (doc != null) {
                return false;
            }

            // add account with username and password
            Document newAccount = new Document("_id", new ObjectId());
            newAccount.append("username", username);
            newAccount.append("password", password);
            
            collection.insertOne(newAccount);

            return true;
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public Boolean login(String username, String password) {
        try {

            MongoClient mongoClient = MongoClients.create(uri);

            // check if account with username exists
            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("accounts");

            Document doc = collection.find(eq("username", username)).first();
            System.out.println(doc);
            System.out.println(doc.get("password").equals(password));
            // check that account exists and password is correct
            if (doc != null && doc.get("password").equals(password)) {
                // TODO: do something
                System.out.println("Logged in successfully");
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    public void deleteAccount(String username, String password) {
        
        try {

            MongoClient mongoClient = MongoClients.create(uri);

            // check if account with username exists
            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("accounts");

            Document doc = collection.find(eq("username", username)).first();

            // check that account exists and password is correct
            if (doc != null && doc.get("password").equals(password)) {
                collection.deleteOne(doc);
                System.out.println("Deleted account successfully");
            } else {
                throw new Exception("Invalid username or password");
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public static void main(String[] args) {
        Credentials c = new Credentials();
        c.login("karannnnnn", "hello");
    }

}
