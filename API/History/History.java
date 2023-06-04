package History;

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

// to be called by LoadHistory and RecordHistory

public class History {
    
    private final String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";

    public History() {
        //
    }

    // gets string of textfile given id of user's account
    // NOTICE: creates a new empty history document if one does not exist yet
    public String getHistory(ObjectId id) {

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("histories");

            Document doc = collection.find(eq("_id", id)).first();

            if (doc != null) {
                return (String) doc.get("text");
            } else {
                Document newHistory = new Document("_id", id);
                newHistory.append("text", "");
                collection.insertOne(newHistory);
                return (String) newHistory.get("text");
            }
            
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    // puts history in database, or creates new history if not there
    public void putHistory(ObjectId id, String text) {
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("histories");

            Document doc = collection.find(eq("_id", id)).first();

            if (doc == null) {
                Document newHistory = new Document("_id", id);
                newHistory.append("text", text);
                collection.insertOne(newHistory);
            } else {
                Bson update = Updates.set("text", text);
                collection.updateOne(doc, update);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        
    }

    public void deleteHistory(ObjectId id) {
        
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("userdata");
            MongoCollection<Document> collection = database.getCollection("histories");

            Document doc = collection.find(eq("_id", id)).first();

            if (doc != null) {
                collection.deleteOne(doc);
            } else {
                throw new Exception("deleting history failed; history not there");
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    // converts prompt_history file to string
    public String fileToString(File f) {
        String rVal = "";
        try {
            FileReader fr = new FileReader(f);
            int i;
            while ( (i = fr.read()) != -1 ) {
                rVal = rVal + ((char) i);
            }
            fr.close();
            return rVal;
        } catch (Exception e) {
            System.err.println(e);
            return "fileToString in History.java crashed and burned";
        }
    }

    // converts string to be put into file f
    public void stringToFile(String historyString, File f) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < historyString.length(); i++) {
                fw.append(historyString.charAt(i));
            }
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
