import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// GUI class for searching a word
class SearchGUI extends JFrame implements ActionListener {
    String text;

    JPanel panel1, panel2;
    static JLabel label1, label2, label3, label4, label5, label6;
    static JTextField textField1;
    JButton button1, button2;
    JComboBox<String> searchResults; // Add a JComboBox for showing search results

    // Constructor for SearchGUI
    public SearchGUI(String name) {
        text = name;

        this.setSize(800, 700);
        this.setTitle("Search Word");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        // Initialize components
        textField1 = new JTextField();
        textField1.setBounds(100, 100, 250, 30);

        label1 = new JLabel();
        label1.setBounds(330, 10, 200, 20);
        label1.setText("Search Word");
        label1.setFont(font);

        label2 = new JLabel();
        label2.setBounds(100, 70, 160, 30);
        label2.setText("Word");
        label2.setFont(font);

        label3 = new JLabel();
        label3.setBounds(100, 180, 160, 30);
        label3.setText("Meaning:");
        label3.setFont(font);

        label4 = new JLabel();
        label4.setBounds(200, 180, 600, 30);
        label4.setText("⏳");
        label4.setFont(font);

        label5 = new JLabel();
        label5.setBounds(100, 250, 160, 30);
        label5.setText("Synonyms:");
        label5.setFont(font);

        label6 = new JLabel();
        label6.setBounds(200, 250, 500, 30);
        label6.setText("⏳");
        label6.setFont(font);

        button1 = new JButton("Search");
        button1.setBounds(100, 300, 100, 30);
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
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);
        panel1.add(label6);
        panel1.add(button1);
        panel1.add(button2);

        // Initialize searchResults JComboBox
        searchResults = new JComboBox<>();
        searchResults.setBounds(100, 130, 250, 30);
        panel1.add(searchResults);

        // Add a DocumentListener to the textField for live searching
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                liveSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                liveSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                liveSearch();
            }
        });

        // Add KeyListener to textField1
        textField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    searchResults.requestFocusInWindow();
                    searchResults.showPopup();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
            }
        });

        // Add KeyListener to searchResults JComboBox
        searchResults.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String selectedWord = (String) searchResults.getSelectedItem();
                    if (selectedWord != null) {
                        textField1.setText(selectedWord);
                        searchOperation(textField1.getText());
                        searchHistory();
                        searchResults.setPopupVisible(false); // Hide the popup after selection
                    }
                }
            }
        });
    }

    // Default constructor
    public SearchGUI() {

    }

    // Live search operation based on the current text in the textField
    public void liveSearch() {
        String searchText = textField1.getText().toLowerCase();
        WordNode current = WordLL.head;

        // List to store matching words
        List<String> matchingWords = new ArrayList<>();

        while (current != null) {
            // Check if the current word contains the search text
            if (current.word.toLowerCase().contains(searchText)) {
                matchingWords.add(current.word);
            }
            current = current.next;
        }

        // Update the searchResults JComboBox with matching words
        searchResults.removeAllItems();
        for (String word : matchingWords) {
            searchResults.addItem(word);
        }

        // Show the searchResults JComboBox
        searchResults.setPopupVisible(true);
    }

    // Perform search operation
    public static void searchOperation(String searchWord) {
        textField1.setText(searchWord);
        WordNode resultNode = WordLL.search(searchWord);

        if (resultNode != null) {
            // Word found
            label4.setText(resultNode.meaning);
            StringBuilder synonymsText = new StringBuilder();

            for (int i = 0; i < resultNode.synonyms.length; i++) {
                synonymsText.append(resultNode.synonyms[i]);

                if (i < resultNode.synonyms.length - 1) {
                    synonymsText.append(", ");
                }
            }

            label6.setText(synonymsText.toString());
        } else {
            // Word not found
            label4.setText("Not found");
            label6.setText("Not found");
        }
    }

    public void searchHistory() {
        if ("Fadi".equals(text) && !label4.getText().equals("Not found")) {
            try (BufferedWriter br = new BufferedWriter(new FileWriter("Fadi.txt", true))) {
                br.write(textField1.getText());
                br.write("\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if ("Junaid".equals(text) && !label4.getText().equals("Not found")) {
            try (BufferedWriter br = new BufferedWriter(new FileWriter("Junaid.txt", true))) {
                br.write(textField1.getText());
                br.write("\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if ("Imran".equals(text) && !label4.getText().equals("Not found")) {
            try (BufferedWriter br = new BufferedWriter(new FileWriter("Imran.txt", true))) {
                br.write(textField1.getText());
                br.write("\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            if (textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(SearchGUI.this, "Please fill the required field.");
                return;
            }
            searchOperation(textField1.getText());
            searchHistory();
            }
        else if (e.getSource() == button2) {
            // Navigate back to the respective home screen
            if ("Fadi".equals(text) || "Junaid".equals(text) || "Imran".equals(text)) {
                setVisible(false);
                new DictionaryUserHome(text).setVisible(true);
            }
        }
    }
}

// Main class for SearchWord
public class SearchWord {
    public static void main(String[] args) {
        SearchGUI searchGUI = new SearchGUI();
        searchGUI.setVisible(true);
    }
}
