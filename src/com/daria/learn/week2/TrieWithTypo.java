package com.daria.learn.week2;

import java.util.*;

public class TrieWithTypo {

    private class TrieNode {
        private final Map<Character, TrieNode> children;
        private final boolean isEndOfWord;

        public TrieNode(boolean isEndOfWord) {
            children = new TreeMap<>(); //TreeMap ?
            this.isEndOfWord = isEndOfWord;
        }

    }

    private class NodePrefixResult {
        private final TrieNode node;
        private final String actualPrefix;

        private NodePrefixResult(TrieNode node, LinkedList<Character> actualPrefix) {
            this.node = node;
            this.actualPrefix = stringFromStack(actualPrefix);
        }

        private String stringFromStack(LinkedList<Character> stack) {
            StringBuilder sb = new StringBuilder();
            Iterator<Character> iterator = stack.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next());
            }
            return sb.toString();
        }

    }

    private final TrieNode root;
    private int suggestionSize = 10;
    private boolean typoWasMet;

    public TrieWithTypo() {
        root = new TrieNode(false);
    }

    public void put(String word) {
        put(root, word);
    }

    private void put(TrieNode node, String word) {
        if (word.isEmpty()) {
            node.children.put(Character.MIN_VALUE, new TrieNode(true));
            return;
        }

        char ch = word.charAt(0);
        if (!node.children.containsKey(ch)) {
            node.children.put(ch, new TrieNode(false));
        }
        put(node.children.get(ch), word.substring(1, word.length()));
    }

    public Collection<String> getSuggestions(String prefix) {
        typoWasMet = false;

        LinkedList<Character> stack = new LinkedList<>();
        List<NodePrefixResult> matchingNodes = new LinkedList<>();
        findPrefixNodes(root, prefix, stack, matchingNodes);
        if (matchingNodes.isEmpty())
            return Collections.emptyList();

        Set<String> suggestions = new TreeSet<>(String::compareTo);
        getSuggestions(matchingNodes, suggestions);;
        return suggestions;
    }

    private void getSuggestion(TrieNode node, String current, Set<String> suggestions) {
        if (suggestions.size() == suggestionSize)
            return;

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            if (entry.getValue().isEndOfWord)
                suggestions.add(current);
            else
                getSuggestion(entry.getValue(), current + entry.getKey(), suggestions);
        }
    }

    private void getSuggestions(List<NodePrefixResult> matchingNodes, Set<String> suggestions) {
        for (NodePrefixResult nodeResult : matchingNodes) {
            getSuggestion(nodeResult.node, nodeResult.actualPrefix, suggestions);
        }
    }

    private void findPrefixNodes(TrieNode node, String prefix, LinkedList<Character> stack, List<NodePrefixResult> matchingNodes) {
        if (prefix.isEmpty()) {
            matchingNodes.add(new NodePrefixResult(node, stack));
            return;
        }

        for (Map.Entry<Character, TrieNode> child : node.children.entrySet()) {
            stack.addLast(child.getKey());
            if (child.getKey() == prefix.charAt(0)) {
                findPrefixNodes(child.getValue(), prefix.substring(1, prefix.length()), stack, matchingNodes);
            } else {
                if (typoWasMet) {
                    stack.removeLast(); //??
                    return;
                }
                else {
                    typoWasMet = true;
                    findPrefixNodes(child.getValue(), prefix.substring(1, prefix.length()), stack, matchingNodes);
                    typoWasMet = false;
                }
            }
            stack.removeLast();
        }
    }

}
