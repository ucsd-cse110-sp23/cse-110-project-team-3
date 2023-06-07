package EmailData;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class EmailData {
    private final String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";

    public EmailData() {
    }

    public void setEmailData(String firstName, String lastName, String displayName, String email, String SMTP, String TLS, String password, ObjectId id) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            
            // check if account with username already exists
            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("email");

            Document doc = collection.find(eq("_id", id)).first();

            // resets email if it already exists
            if (doc != null) {
                collection.deleteOne(doc);
            }

            // add account with username and password
            Document newEmail = new Document("_id", id);
            newEmail.append("firstName", firstName);
            newEmail.append("lastName", lastName);
            newEmail.append("displayName", displayName);
            newEmail.append("email", email);
            newEmail.append("SMTP", SMTP);
            newEmail.append("TLS", TLS);
            newEmail.append("password", password);
            collection.insertOne(newEmail);
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    // Create a method to retrieve the email data with the given id
    public Map<String, String> getEmailData(ObjectId id) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("email");

            Document doc = collection.find(eq("_id", id)).first();

            if (doc != null) {
                Map<String, String> emailData = new HashMap<String, String>();
                emailData.put("firstName", (String) doc.get("firstName"));
                emailData.put("lastName", (String) doc.get("lastName"));
                emailData.put("displayName", (String) doc.get("displayName"));
                emailData.put("email", (String) doc.get("email"));
                emailData.put("SMTP", (String) doc.get("SMTP"));
                emailData.put("TLS", (String) doc.get("TLS"));
                emailData.put("password", (String) doc.get("password"));
                return emailData;
            } else {
                return null;
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

}
