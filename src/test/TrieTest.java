package test;

import com.daria.learn.Trie;

import java.util.ArrayList;
import java.util.List;

public class TrieTest {

    public static void main(String[] args) {
        //testTrieInsertion();
        testGoogleData();
    }

    public static void testTrieInsertion() {
        Trie trie = new Trie();
        trie.put("abc");
        trie.put("abgl");
        trie.put("cdf");
        trie.put("adfg");

//        boolean res = trie.search("ad");
//        boolean res2 = trie.search("abfc");

        List<String> res = trie.getSuggestions("ab");
        List<String> res2 = trie.getSuggestions("abfc");
    }

    public static void testGoogleData() {
        Trie trie = new Trie();
        trie.put("foo");
        trie.put("baz");
        trie.put("bar");

        List<String>[] results = new List[]{
                trie.getSuggestions("f"),
                trie.getSuggestions("b"),
                trie.getSuggestions("ba"),
                trie.getSuggestions("bar"),
                trie.getSuggestions("bard"),
                trie.getSuggestions("fo"),
        };

        for (List<String> suggestions : results) {
            System.out.println(String.join(" ", suggestions));
        }
     }
}
