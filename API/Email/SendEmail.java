package Email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.ReturnDocument;
import org.bson.types.ObjectId;
import java.util.*;

import Mediator.Mediator;
import EmailData.EmailData;

public class SendEmail {
    Mediator mediator;
    String firstName;
    String lastName;
    String displayName;
    String email;
    String SMTP;
    String TLS;
    String password;

    public SendEmail(Mediator m) {
        this.mediator = m;
        ObjectId id = m.getId();
        EmailData emailData = new EmailData();
        Map<String, String> emailMap = emailData.getEmailData(id);
        this.firstName = emailMap.get("firstName");
        this.lastName = emailMap.get("lastName");
        this.displayName = emailMap.get("displayName");
        this.email = emailMap.get("email");
        this.SMTP = emailMap.get("SMTP");
        this.TLS = emailMap.get("TLS");
        this.password = emailMap.get("password");
    }

    public boolean compose(String toEmail, String subject, String Body) {
	    
	   Properties props = new Properties();

	    props.put("mail.smtp.host", SMTP); //SMTP Host
        props.put("mail.smtp.port", TLS); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };

	    Session session = Session.getInstance(props, auth);
        return EmailUtil.sendEmail(session, email, toEmail, subject, Body);
    }
}
 