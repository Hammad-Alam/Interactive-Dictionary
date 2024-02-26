import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// GUI class for viewing words
class ViewGUI extends JFrame implements ActionListener {
    String text;

    JPanel panel1, panel2;
    JLabel label1;
    JButton button1, button2;
    JTextArea textArea; // Make it a class field

    // Constructor for ViewGUI
    public ViewGUI(String name) {
        text = name;

        this.setSize(800, 700);
        this.setTitle("View Word");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        label1 = new JLabel();
        label1.setText("View Word");
        label1.setFont(font);

        // Create a JTextArea
        textArea = new JTextArea();
        textArea.setLineWrap(true);  // Enable line wrapping
        textArea.setWrapStyleWord(true);  // Wrap at word boundaries

        // Set the font size to 16 (adjust the size as needed)
        Font textAreaFont = new Font(" ", Font.ROMAN_BASELINE, 14);  // You can choose a different font
        textArea.setFont(textAreaFont);

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Customize the appearance of the scrollbar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(0, 0)); // Set width and height to 0

        button1 = new JButton("View");
        button1.addActionListener(this);

        button2 = new JButton("Back");
        button2.addActionListener(this);

        panel2 = new JPanel();
        panel2.setBackground(Color.lightGray);
        panel2.setPreferredSize(new Dimension(100, 50));

        panel1 = new JPanel(new BorderLayout()); // Use BorderLayout
        panel1.setBackground(Color.lightGray);

        // Add components to the panel using BorderLayout
        panel1.add(label1, BorderLayout.NORTH);
        panel1.add(scrollPane, BorderLayout.CENTER);

        // Create a subpanel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // Adjust the layout and gap
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add the button subpanel to the main panel
        panel1.add(buttonPanel, BorderLayout.SOUTH);

        // Add panels to the frame
        this.add(panel2, BorderLayout.NORTH);
        this.add(panel1, BorderLayout.CENTER);
    }

    // Default constructor
    public ViewGUI() {

    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            // Trigger the view operation and display the words in the JTextArea
            String viewWords = WordLL.view();
            textArea.setText(viewWords);
        } else if (e.getSource() == button2) {
            // Navigate back to the respective home screen based on the user
            if ("Fadi".equals(text) || "Junaid".equals(text) || "Imran".equals(text)) {
                setVisible(false);
                new DictionaryUserHome(text).setVisible(true);
            } else if ("Sami".equals(text)) {
                setVisible(false);
                new AdminHome(text).setVisible(true);
            }
        }
    }
}

// Main class for ViewWord
public class ViewWord {
    public static void main(String[] args) {
        ViewGUI viewGUI = new ViewGUI();
        viewGUI.setVisible(true);
    }
}
