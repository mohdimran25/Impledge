import java.util.*;

public class Trie {
    private class TrieNode {
        @SuppressWarnings("unused")
        private char val;
        private HashMap<Character,TrieNode> children;
        private boolean isWord;

        public TrieNode(char val) {
            this.val = val;
            children = new HashMap<>();
            isWord = false;
        }

        public void addChild(char child){
            children.put(child, new TrieNode(child));
        }

        public TrieNode getChild(char child) {
            if(!children.keySet().contains(child)){
                return null;
            }
            return children.get(child);
        }

        public boolean containsChild(char child) {
            return children.keySet().contains(child);
        }

    }
    private TrieNode root;
    private TrieNode curr;

    public Trie() {
        root  = new TrieNode(' ');
        curr = root;
    }

    public void insert(String s){
        char letter;
        curr = root;
        for (int i = 0; i <s.length(); i++) {
            letter = s.charAt(i);

            if (!curr.containsChild(letter)) {
                curr.addChild(letter);
            }
            curr = curr.getChild(letter);
        }
        curr.isWord = true;

    }

    public List<Integer> getSuffixesStartIndices(String s) {
        List<Integer> indices = new LinkedList<>();
        char letter;
        curr = root;
        for (int i = 0; i < s.length(); i++) {
            letter = s.charAt(i);

            if(!curr.containsChild(letter))
                return indices;

            curr = curr.getChild(letter);
            if(curr.isWord)
                indices.add(i+1);
        }
        return indices;
    }
}
