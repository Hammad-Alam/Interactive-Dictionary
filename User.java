import java.io.Serializable;

// Abstract class representing a User
abstract class User implements Serializable {
    // private fields for Username and Password
    private String Username;
    private String Password;

    // Constructor for creating a user object with Username and Password
    User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    // Getter method for retrieving the username
    public String getUsername() {
        return Username;
    }

    // Getter method for retrieving the password
    public String getPassword() {
        return Password;
    }
}
