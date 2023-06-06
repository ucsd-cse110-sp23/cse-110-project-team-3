package Email;

import static com.mongodb.client.model.Filters.eq;

import java.util.Map;

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

import Mediator.Mediator;


public class EmailSetup {
    private final String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";

    String firstName;
    String lastName;
    String displayName;
    String email;
    String SMTP;
    String TLS;
    String password;

    public EmailSetup(String firstName, String lastName, String displayName, String email, String SMTP, String TLS, String password, Mediator m) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.email = email;
        this.SMTP = SMTP;
        this.TLS = TLS;
        this.password = password;
        Map<String, String> emailData = Map.of("firstName", firstName, "lastName", lastName, "displayName", displayName, "email", email, "SMTP", SMTP, "TLS", TLS, "password", password);

        // for each entry in the map, update the corresponding field in the mongoDB document
        for(Map.Entry<String, String> entry : emailData.entrySet()) {
            ObjectId id = m.getId();
            try (MongoClient mongoClient = MongoClients.create(uri)) {
                MongoDatabase database = mongoClient.getDatabase(entry.getKey());
                MongoCollection<Document> collection = database.getCollection("histories");

                Document doc = collection.find(eq("_id", id)).first();

                if (doc == null) {
                    Document newHistory = new Document("_id", id);
                    newHistory.append("text", entry.getValue());
                    collection.insertOne(newHistory);
                } else {
                    Bson update = Updates.set("text", entry.getValue());
                    collection.updateOne(doc, update);
                }

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getSMTP() {
        return SMTP;
    }

    public String getTLS() {
        return TLS;
    }

    public String getPassword() {
        return password;
    }
}