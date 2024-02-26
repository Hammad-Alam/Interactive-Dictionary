import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

// GUI class for deleting a word
class DeleteGUI extends JFrame implements ActionListener {
    String text;

    JPanel panel1, panel2;
    JLabel label1, label2;
    TextField textField1;
    JButton button1, button2;

    // Constructor for DeleteGUI
    public DeleteGUI(String name) {
        text = name;

        this.setSize(800, 700);
        this.setTitle("Delete Word");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        // Initialize components
        textField1 = new TextField();
        textField1.setBounds(100, 100, 250, 30);

        label1 = new JLabel();
        label1.setBounds(330, 10, 200, 20);
        label1.setText("Delete Word");
        label1.setFont(font);

        label2 = new JLabel();
        label2.setBounds(100, 70, 160, 30);
        label2.setText("Word");
        label2.setFont(font);

        button1 = new JButton("Delete");
        button1.setBounds(100, 140, 70, 30);
        button1.addActionListener(this);

        button2 = new JButton("Back");
        button2.setBounds(650, 140, 70, 30);
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
        panel1.add(label2);
        panel1.add(button1);
        panel1.add(button2);
    }

    // Default constructor
    public DeleteGUI() {

    }

    // Method to delete a word
    public boolean delete() {
        boolean chk = false;
        StringBuilder sb = new StringBuilder();
        String word = textField1.getText();
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(word)) {
                    // Skip the word and its associated information (meaning, synonyms)
                    for(int i = 0; i < 3; i++) {
                        br.readLine();
                    }
                    chk = true;
                } else {
                    sb.append(line).append(System.lineSeparator());
                    chk = false;
                }
            }
            br.close();

            // Write the modified content back to the file
            FileWriter fw = new FileWriter("words.txt");
            fw.write(sb.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        WordLL.readFromFile("words.txt");
        return chk;
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            if(textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(DeleteGUI.this, "Please fill the required field.");
                return;
            }

            // Perform deletion and show success message
            boolean isDelete = delete();
            if(isDelete == true) {
                JOptionPane.showMessageDialog(DeleteGUI.this, "Word has been successfully removed!");
            } else {
                JOptionPane.showMessageDialog(DeleteGUI.this, "Word does not exist in Dictionary!");
            }
            clearFields();
        } else if(e.getSource() == button2) {
            // Navigate back to the Admin home
            setVisible(false);
            new AdminHome(text).setVisible(true);
        }
    }

    // Method to clear input fields
    public void clearFields() {
        textField1.setText(null);
    }
}

// Main class for DeleteWord
public class DeleteWord {
    public static void main(String[] args) {
        DeleteGUI deleteGUI = new DeleteGUI();
        deleteGUI.setVisible(true);
    }
}
