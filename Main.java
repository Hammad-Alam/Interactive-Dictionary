import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        new Login();
// Create a new instance of the Login class, presumably to start the login process

        Admin admin = new Admin("Sami", "221044");
// Create a new Admin object with the name "Sami" and password "221044"

        try {
            ObjectOutputStream ObjectWriter = new ObjectOutputStream(new FileOutputStream("Admin.txt"));
            // Create an ObjectOutputStream to write objects to the "Admin.txt" file

            ObjectWriter.writeObject(admin);
            // Write the admin object to the file

            ObjectWriter.close();
            // Close the ObjectOutputStream

        } catch (Exception e) {
            System.out.println(e);
        }
// Catch any exceptions that occur during the file write operation and print the error message
    }
}