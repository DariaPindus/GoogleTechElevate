package test;

import com.daria.learn.week2.TrieWithTypo;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrieWithTypoTest {

    @Test
    public void testBasic() {
        String[] dictionary = new String[]{"tech","computer","technology","elevate","compute","elevator","company"};
        String[] queries = new String[]{"tevh", "compa","tivh","new","techn"};

        String[] expected = new String[]{"tech technology","company compute computer","<no matches>","<no matches>","technology"};
        testTrie(dictionary, queries, expected);

    }

    private void testTrie(String[] dictionary, String[] queries, String[] expected) {
        TrieWithTypo trie = new TrieWithTypo();
        for (String s: dictionary) {
            trie.put(s);
        }

        for (int i = 0; i < queries.length; i++) {
            String actual = suggestions(trie.getSuggestions(queries[i]));
            System.out.println("Input " + queries[i] + ", exp " + expected[i] + ", act " + actual);
            //assertEquals(expected[i], actual);
        }
    }

    @Test
    public void myTest() {
        String[] dict = new String[] {"temperature", "bt", "the", "album", "temper", "alala", "alter", "temper", "tempera",
                "temp", "temperat", "temperatu", "temperaat", "temperaturara", "temperarara", "temperatttt", "temperaturrr"};
        String[] queries = new String[] {"temp", "thje", "olol", "ilal", "alf"};

        String[] expected = new String[] {"temper temperature", "<no matches>", "<no matches>", "alala", "alala alter"};

        testTrie(dict, queries, expected);
    }

    @Test
    public void test2(){
        String[] dict = new String[]{"tevh", "computer", "technology", "elevate", "compute", "elevator", "technicality"};
        String[] queries = new String[] {"tevh" };

        String[] expected = new String[] {"tevh technology technicality"};

        testTrie(dict, queries, expected);
    }

    private String suggestions(Collection<String> suggestions) {
        return suggestions.isEmpty() ? "<no matches>" : String.join(" ", suggestions);
    }
}
