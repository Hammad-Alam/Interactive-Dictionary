import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI class for updating words
class UpdateGUI extends JFrame implements ActionListener {
    String text;

    JPanel panel1, panel2;
    JLabel label1, label2;
    TextField textField1;
    JButton button1, button2;

    // Constructor for UpdateGUI
    public UpdateGUI(String name) {
        text = name;

        this.setSize(800, 700);
        this.setTitle("Update Word");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        textField1 = new TextField();
        textField1.setBounds(100, 100, 250, 30);

        label1 = new JLabel();
        label1.setBounds(330, 10, 200, 20);
        label1.setText("Update Word");
        label1.setFont(font);

        label2 = new JLabel();
        label2.setBounds(100, 70, 160, 30);
        label2.setText("Word");
        label2.setFont(font);

        button1 = new JButton("Update");
        button1.setBounds(100, 140, 100, 30);
        button1.addActionListener(this);

        button2 = new JButton("Back");
        button2.setBounds(650, 140, 70, 30);
        button2.addActionListener(this);

        panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setPreferredSize(new Dimension(100, 50));

        panel1 = new JPanel();
        panel1.setBackground(Color.lightGray);
        panel1.setPreferredSize(new Dimension(500, 700));
        panel1.setLayout(null);

        this.add(label1);

        this.add(panel2, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);

        panel1.add(textField1);
        panel1.add(label2);
        panel1.add(button1);
        panel1.add(button2);
    }

    // Default constructor
    public UpdateGUI() {

    }

    // Clear text fields
    public void clearFields() {
        textField1.setText(null);
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1) {
            // Check if the word field is empty
            if(textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(UpdateGUI.this, "Please fill the required field.");
                return;
            } else {
                // Trigger the update operation with the provided word
                WordLL.update(textField1.getText(), null, null);
                clearFields();
            }

        } else if(e.getSource() == button2) {
            // Navigate back to the admin home screen
            setVisible(false);
            new AdminHome(text).setVisible(true);
        }
    }
}

// Main class for UpdateWord
public class UpdateWord {
    public static void main(String[] args) {
        UpdateGUI updateGUI = new UpdateGUI();
        updateGUI.setVisible(true);
    }
}
