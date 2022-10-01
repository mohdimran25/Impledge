import java.util.*;
import java.io.*;

public class LongestCompounded {
    public static void main(String[] args) throws FileNotFoundException {


        long startTime = System.currentTimeMillis();

        // to read input from a file
        File file = new File("src/Input_01.txt");
        //Trie Creation
        Trie trie = new Trie();
        LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
        //created to calculate the total amount of compound words
        HashSet<String> compoundWords = new HashSet<>();

        //Scan the file
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(file);

        String word;
        List<Integer> sufindices;

        while(sc.hasNext()) {
            word = sc.next();
            sufindices = trie.getSuffixesStartIndices(word);

            for(int i: sufindices){
                if(i>=word.length()) {
                    break;
                }
                queue.add(new Pair<String>(word,word.substring(i)));

            }
            trie.insert(word);

        }

        Pair<String> p;        // a pair of word and its remaining suffix
        int maxLength = 0;      // longest compound word length
        String longest = "";
        String sec_longest = "";

        while(!queue.isEmpty()){
            p = queue.removeFirst();
            word = p.second();
            sufindices = trie.getSuffixesStartIndices(word);

            if(sufindices.isEmpty())
                continue;

            for(int i: sufindices) {
                if(i>word.length())
                    break;
                if(i == word.length()){
                    if(p.first().length() > maxLength){
                        sec_longest = longest;
                        maxLength = p.first().length();
                        longest = p.first();
                    }
                    compoundWords.add(p.first());
                } else {
                    queue.add(new Pair<>(p.first(),word.substring(i)));
                }
            }
        }


        long endTime = System.currentTimeMillis();


        System.out.println("Longest Compound Word: "+ longest);
        System.out.println("Second Longest Compound Word: "+ sec_longest);
        System.out.println("Total Number of Compound Words: "+ compoundWords.size());
        System.out.println("Total time taken to process the file: "+ (endTime - startTime) + " milliseconds");

    }
}
