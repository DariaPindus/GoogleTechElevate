package com.daria.learn;

import java.util.*;

public class Trie {
    private class TrieNode {
        private final Map<Character, TrieNode> children;
        private final boolean isEndOfWord;

        public TrieNode(boolean isEndOfWord) {
            children = new TreeMap<>(); //TreeMap ?
            this.isEndOfWord = isEndOfWord;
        }

        //???
        public boolean isEndOfWord() {
            return children.isEmpty();
        }
    }

    private final TrieNode root;
    private int suggestionSize = 10;

    public Trie() {
        root = new TrieNode(false);
    }

    public void put(String word) {
        put(root, word);
    }

    private void put(TrieNode node, String word) {
        if (word.isEmpty()) {
            return;
        }

        char ch = word.charAt(0);
        if (!node.children.containsKey(ch)) {
            node.children.put(ch, new TrieNode(word.length() == 1));
        }
        put(node.children.get(ch), word.substring(1, word.length()));
    }

    public boolean search(String prefix) {
        return search(root, prefix);
    }

    private boolean search(TrieNode node, String prefix) {
        return findPrefixNode(node, prefix) != null;
    }

    public List<String> getSuggestions(String prefix) {
        List<String> suggestions = new LinkedList<>();
        TrieNode prefixNode = findPrefixNode(root, prefix);
        if (prefixNode == null)
            return Collections.emptyList();

        getSuggestions(prefixNode, prefix, suggestions);
        return suggestions;
    }


    private void getSuggestions(TrieNode node, String current, List<String> suggestions) {
        if (suggestions.size() == suggestionSize)
            return;

        if (node.isEndOfWord) {
            suggestions.add(current);
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            getSuggestions(entry.getValue(), current + entry.getKey(), suggestions);
        }
    }

    private TrieNode findPrefixNode(TrieNode node, String prefix) {
        if (prefix.isEmpty())
            return node;

        if (!node.children.containsKey(prefix.charAt(0)))
            return null;

        return findPrefixNode(node.children.get(prefix.charAt(0)), prefix.substring(1, prefix.length()));
    }

}
