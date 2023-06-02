package Email;

public class EmailSetup {
    String firstName;
    String lastName;
    String displayName;
    String email;
    String SMTP;
    String TLS;
    String password;

    public EmailSetup(String firstName, String lastName, String displayName, String email, String SMTP, String TLS, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.email = email;
        this.SMTP = SMTP;
        this.TLS = TLS;
        this.password = password;
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