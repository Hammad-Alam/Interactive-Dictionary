import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

// Linked list class for managing words
class WordLL {
    static WordNode head;

    // Constructor for WordLL
    public WordLL() {
        this.head = null;
    }

    // Method to insert a new word node into the linked list
    public static boolean insert(String word, String meaning, String[] synonyms) {
        boolean wordExists = false;

        // Check if the word already exists in the dictionary
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(word)) {
                    wordExists = true;
                    break;
                } else {
                    for (int i = 0; i < 3; i++) {
                        br.readLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (wordExists) {
            return false; // Word already exists, no need to proceed further
        }

        WordNode newNode = new WordNode(word, meaning, synonyms);

        // Insert the new node at the end of the linked list
        if (head == null) {
            head = newNode;
        } else {
            WordNode currNode = head;
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = newNode;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("words.txt", true))) {
            // Write the new word information to the file
            bw.write("Word: " + word + "\n");
            bw.write("Meaning: " + meaning + "\n");
            bw.write("Synonyms: ");
            for (int i = 0; i < synonyms.length; i++) {
                bw.write(synonyms[i]);
                if (i < synonyms.length - 1) {
                    bw.write(", ");
                }
            }
            bw.write("\n");
            bw.write("--------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true; // Word successfully inserted
    }

    // Method to read words from a file and create a linked list
    public static WordLL readFromFile(String fileName) {
        WordLL wordList = new WordLL();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String word = line.substring(6);

                // Read meaning
                String meaning = br.readLine().substring(9);

                // Read synonyms
                String[] synonyms = br.readLine().substring(10).split(",\\s*");

                // Create a new WordNode and add it to the linked list
                WordNode newNode = new WordNode(word, meaning, synonyms);
                if (wordList.head == null) {
                    wordList.head = newNode;
                } else {
                    WordNode currNode = wordList.head;
                    while (currNode.next != null) {
                        currNode = currNode.next;
                    }
                    currNode.next = newNode;
                }

                // Read and discard the separator line
                br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordList;
    }

    // Method to search for a word in the linked list
    public static WordNode search(String searchWord) {
        WordNode currNode = head;

        while (currNode != null) {
            if (currNode.word.equalsIgnoreCase(searchWord)) {
                // Word found, return the node
                return currNode;
            }

            currNode = currNode.next;
        }

        // Word not found in the linked list
        return null;
    }

    // Method to view all words in the linked list
    public static String view() {
        StringBuilder words = new StringBuilder();

        WordNode currNode = head;
        while (currNode != null) {
            words.append("Word: " + currNode.word + " Meaning: " + currNode.meaning + " Synonyms: ");
            for (int i = 0; i < currNode.synonyms.length; i++) {
                words.append(currNode.synonyms[i]);
                if (i < currNode.synonyms.length - 1) {
                    words.append(", ");
                }
            }
            words.append("\n");
            currNode = currNode.next;
        }
        return String.valueOf(words);
    }

    // Method to sort the words in the linked list alphabetically
    public static String sort() {
        WordNode currNode = head, index = null;
        StringBuilder sortedWords = new StringBuilder();

        if (head == null) {
            return null;
        } else {
            while (currNode != null) {
                index = currNode.next;
                while (index != null) {
                    if (index.word.compareTo(currNode.word) < 0) {
                        String tempWord = index.word;
                        index.word = currNode.word;
                        currNode.word = tempWord;
                    }
                    index = index.next;
                }
                // Append the sorted word to the StringBuilder
                sortedWords.append(currNode.word).append("\n");
                currNode = currNode.next;
            }
        }

        // Return the string representation of sorted words
        return sortedWords.toString();
    }

    // Method to save the linked list to a file
    public static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("words.txt"))) {
            WordNode current = head;

            while (current != null) {
                // Format the data and write to the file
                bw.write("Word: " + current.word + "\n");
                bw.write("Meaning: " + current.meaning + "\n");

                // Convert the array to a string representation before writing
                String synonymsString = String.join(", ", current.synonyms);
                bw.write("Synonyms: " + synonymsString + "\n");

                bw.write("--------------------\n");
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update the meaning and synonyms of a word
    public static boolean update(String word, String meaning, String[] synonyms) {
        boolean wordExists = false;

        // Check if the word exists in the dictionary
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(word)) {
                    wordExists = true;
                    break;
                } else {
                    for (int i = 0; i < 3; i++) {
                        br.readLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (wordExists) {
            WordNode currNode = head;

            while (currNode != null) {
                // Trim both the entered word and the word in the node before comparison
                if (currNode.word.trim().equalsIgnoreCase(word.trim())) {
                    String updatedMeaning = JOptionPane.showInputDialog("Enter updated meaning:");
                    String synonymsInput = JOptionPane.showInputDialog("Enter updated synonyms:");
                    String[] updatedSynonyms = synonymsInput.split(",\\s");

                    currNode.meaning = updatedMeaning;
                    currNode.synonyms = updatedSynonyms;

                    // Save the updated information to the file
                    saveToFile();

                    JOptionPane.showMessageDialog(null, "Word updated Successfully!");
                    return true; // Word successfully updated
                }
                currNode = currNode.next;
            }
        }
        // Word not found after checking all node
        JOptionPane.showMessageDialog(null, "Word not found in the dictionary.");
        return false;
    }
}
