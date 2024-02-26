import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// GUI class for inserting a word
class InsertGUI extends JFrame implements ActionListener {
    String text;

    JPanel panel1, panel2;
    JLabel label1, label2, label3, label4;
    TextField textField1, textField2, textField3;
    JButton button1, button2;

    // Constructor for InsertGUI
    public InsertGUI(String name) {
        text = name;

        this.setSize(800, 700);
        this.setTitle("Insert Word");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        // Initialize components
        textField1 = new TextField();
        textField1.setBounds(100, 100, 250, 30);
        textField2 = new TextField();
        textField2.setBounds(100, 180, 250, 30);
        textField3 = new TextField();
        textField3.setBounds(100, 260, 250, 30);

        label1 = new JLabel();
        label1.setBounds(330, 10, 200, 20);
        label1.setText("Insert Word");
        label1.setFont(font);

        label2 = new JLabel();
        label2.setBounds(100, 70, 160, 30);
        label2.setText("Word");
        label2.setFont(font);

        label3 = new JLabel();
        label3.setBounds(100, 150, 110, 30);
        label3.setText("Meaning");
        label3.setFont(font);

        label4 = new JLabel();
        label4.setBounds(100, 230, 110, 30);
        label4.setText("Synonyms");
        label4.setFont(font);

        button1 = new JButton("Add");
        button1.setBounds(100, 300, 70, 30);
        button1.addActionListener(this);

        button2 = new JButton("Back");
        button2.setBounds(650, 300, 70, 30);
        button2.addActionListener(this);

        // Set up panels
        panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setPreferredSize(new Dimension(100, 50));

        panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setPreferredSize(new Dimension(500, 700));
        panel1.setLayout(null);

        // Add components to the frame
        this.add(label1);

        this.add(panel2, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);

        panel1.add(textField1);
        panel1.add(textField2);
        panel1.add(textField3);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(button1);
        panel1.add(button2);
    }

    // Default constructor
    public InsertGUI() {

    }

    // Set the text of the text fields to null, clearing their contents
    public void clearFields() {
        textField1.setText(null);
        textField2.setText(null);
        textField3.setText(null);
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            if (textField1.getText().isEmpty() && textField2.getText().isEmpty() && textField3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(InsertGUI.this, "Please fill the required fields.");
                return;
            }

            String word = textField1.getText();
            String meaning = textField2.getText();
            String synonymsInput = textField3.getText();
            String[] synonyms = synonymsInput.split(",\\s*");

            if (WordLL.insert(word, meaning, synonyms)) {
                JOptionPane.showMessageDialog(InsertGUI.this, "Word has been successfully inserted!");
                clearFields();
                WordLL.readFromFile("words.txt");
            } else {
                JOptionPane.showMessageDialog(InsertGUI.this, "Word already exists in the dictionary.");
                clearFields();
            }
        } else if (e.getSource() == button2) {
            setVisible(false);
            new AdminHome(text).setVisible(true);
        }
    }
}

// Main class for InsertWord
public class InsertWord {
    public static void main(String[] args) {
        InsertGUI insertGUI = new InsertGUI();
        insertGUI.setVisible(true);
    }
}
