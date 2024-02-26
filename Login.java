import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

// Class for handling the login functionality
public class Login extends JFrame implements ActionListener {

    Admin admin; // Instance variable to store admin information
    JPanel panel1, panel2;
    JLabel label1, label2, label3;
    TextField textField1;
    JPasswordField password;
    JCheckBox checkBox;
    JButton button;

    // Constructor for the Login class
    Login() {
        // Set up the JFrame
        this.setSize(800, 700);
        this.setTitle("Login");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        // Initialize components
        textField1 = new TextField();
        textField1.setBounds(310, 100, 160, 30);
        password = new JPasswordField();
        password.setBounds(310,180,160,30);

        label1 = new JLabel();
        label1.setBounds(230, 25, 320, 25);
        label1.setText("Welcome to Interactive Dictionary 📖");
        label1.setFont(font);

        label2 = new JLabel();
        label2.setBounds(310, 70, 160, 30);
        label2.setText("Username");
        label2.setFont(font);

        label3 = new JLabel();
        label3.setBounds(310, 150, 110, 30);
        label3.setText("Password");
        label3.setFont(font);

        checkBox = new JCheckBox();
        checkBox.setBackground(Color.lightGray);
        checkBox.setFont(font);
        checkBox.setText("Show Password");
        checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });
        checkBox.setBounds(300, 230, 200, 30);

        // Set up panels
        panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setPreferredSize(new Dimension(100, 50));

        panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setPreferredSize(new Dimension(500, 700));
        panel1.setLayout(null);

        // Initialize the login button
        button = new JButton("Login");
        button.setBounds(350, 280, 70, 30);
        button.addActionListener(this);

        // Add components to the JFrame
        this.add(label1);
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);
        panel1.add(textField1);
        panel1.add(password);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(checkBox);
        panel1.add(button);
    }

    // Handle checkbox action
    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if (checkBox.isSelected()) {
            password.setEchoChar((char) 0); // Show the data as plain text.
        } else {
            password.setEchoChar('*');  // Hide the text using an asterisk.
        }
    }

    // Handle button click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            // Read words from file
            WordLL.readFromFile("words.txt");

            // Read admin information from file
            try {
                ObjectInputStream objReader = new ObjectInputStream(new FileInputStream("Admin.txt"));
                admin = (Admin) objReader.readObject();
                objReader.close();
            } catch (Exception error) {
                System.out.println(error);
            }

            // Check login credentials
            if (textField1.getText().isEmpty() && password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "<html>Please enter Username and Password.</html>", "Show", JOptionPane.INFORMATION_MESSAGE);
            } else if (textField1.getText().equals(admin.getUsername()) && password.getText().equals(admin.getPassword())) {
                String name = textField1.getText();
                new AdminHome(name).setVisible(true);
                setVisible(false);
            } else if (textField1.getText().equals("Fadi") && password.getText().equals("ASDF456")) {
                String name = textField1.getText();
                new DictionaryUserHome(name).setVisible(true);
                setVisible(false);
            } else if (textField1.getText().equals("Junaid") && password.getText().equals("123XYZ")) {
                String name = textField1.getText();
                new DictionaryUserHome(name).setVisible(true);
                setVisible(false);
            } else if (textField1.getText().equals("Imran") && password.getText().equals("102030")) {
                String name = textField1.getText();
                new DictionaryUserHome(name).setVisible(true);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "<html>Please enter correct credentials.</html>", "Show", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
