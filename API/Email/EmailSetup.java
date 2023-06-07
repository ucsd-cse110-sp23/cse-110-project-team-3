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
import EmailData.EmailData;


public class EmailSetup {
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

        // for each entry in the map, update the corresponding field in the mongoDB document
        ObjectId id = m.getId();
        EmailData emailData = new EmailData();
        emailData.setEmailData(firstName, lastName, displayName, email, SMTP, TLS, password, id);
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
