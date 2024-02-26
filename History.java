import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.Utilities;

// GUI class for viewing search history
class HistoryGUI extends JFrame implements ActionListener {
    String text;

    JPanel panel1, panel2;
    JLabel label1;
    JButton button1, button2;
    JTextArea textArea; // Make it a class field

    // Constructor for HistoryGUI
    public HistoryGUI(String name) {
        text = name;

        this.setSize(800, 700);
        this.setTitle("History");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        label1 = new JLabel();
        label1.setText("View Words History");
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

        // Add mouse listener to detect double-clicks on words in the JTextArea
        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    setVisible(false);
                    new SearchGUI(text).setVisible(true);
                    int offset = textArea.viewToModel(e.getPoint());
                    try {
                        int start = Utilities.getWordStart(textArea, offset);
                        int end = Utilities.getWordEnd(textArea, offset);

                        String clickedWord = textArea.getText(start, end - start);
                        SearchGUI.searchOperation(clickedWord);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    // Default constructor
    public HistoryGUI() {

    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            StringBuilder content = new StringBuilder();
            if("Fadi".equals(text)) {
                try(BufferedReader br = new BufferedReader(new FileReader("Fadi.txt"))) {
                    String line;
                    while((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    if (content.toString().isEmpty()) {
                        JOptionPane.showMessageDialog(HistoryGUI.this, "You haven't searched for any words yet. Your search history is currently empty.");
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            } else if("Junaid".equals(text)) {
                try(BufferedReader br = new BufferedReader(new FileReader("Junaid.txt"))) {
                    String line;
                    while((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    if (content.toString().isEmpty()) {
                        JOptionPane.showMessageDialog(HistoryGUI.this, "You haven't searched for any words yet. Your search history is currently empty.");
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            } else if("Imran".equals(text)) {
                try(BufferedReader br = new BufferedReader(new FileReader("Imran.txt"))) {
                    String line;
                    while((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    if (content.toString().isEmpty()) {
                        JOptionPane.showMessageDialog(HistoryGUI.this, "You haven't searched for any words yet. Your search history is currently empty.");
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            textArea.setText(content.toString());
        } else if (e.getSource() == button2) {
            // Navigate back to the respective home screen
            if ("Fadi".equals(text) || "Junaid".equals(text) || "Imran".equals(text)) {
                setVisible(false);
                new DictionaryUserHome(text).setVisible(true);
            }
        }
    }
}

// Main class for History
public class History {
    public static void main(String[] args) {
        HistoryGUI historyGUI = new HistoryGUI();
        historyGUI.setVisible(true);
    }
}
