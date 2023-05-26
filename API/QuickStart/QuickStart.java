package QuickStart;

import static com.mongodb.client.model.Filters.eq;


import org.bson.Document;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class QuickStart {
   public static void main( String[] args ) {


       // Replace the placeholder with your MongoDB deployment's connection string
       String uri = "mongodb+srv://k2chung:suqNIH8XW2du0NId@sayit.gzgbzwy.mongodb.net/?retryWrites=true&w=majority";


       try (MongoClient mongoClient = MongoClients.create(uri)) {
           MongoDatabase database = mongoClient.getDatabase("userdata");
           MongoCollection<Document> collection = database.getCollection("accounts");


           Document doc = collection.find(eq("username", "k4chung")).first();
           if (doc != null) {
               System.out.println(doc.toJson());
           } else {
               System.out.println("No matching documents found.");
           }
       }
   }
}

