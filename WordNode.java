// Node class representing a word in the linked list
class WordNode {
    String word;
    String meaning;
    String[] synonyms;
    WordNode next;

    // Constructor for WordNode
    public WordNode(String word, String meaning, String[] synonyms) {
        this.word = word;
        this.meaning = meaning;
        this.synonyms = synonyms;
        this.next = null;
    }
}