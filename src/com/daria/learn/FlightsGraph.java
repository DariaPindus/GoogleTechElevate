package com.daria.learn;

import java.util.*;

public class FlightsGraph {

    private final Map<String, List<String>> adjacencyMap;
    private Set<String> visited;

    public FlightsGraph () {
        adjacencyMap = new HashMap<>();
    }

    public void addConnection(String from, String to) {
        validateConnection(from, to);

        if (!adjacencyMap.containsKey(from))
            adjacencyMap.put(from, new LinkedList<>());
        if (!adjacencyMap.containsKey(to))
            adjacencyMap.put(to, new LinkedList<>());
        adjacencyMap.get(from).add(to);
    }

    private void validateConnection(String from, String to) {
        if (from == null || from.length() > 4)
            throw new IllegalArgumentException("Illegal argument " + from);

        if (to == null || to.length() > 4)
            throw new IllegalArgumentException("Illegal argument " + to);
    }

    public Set<String> singleFlights(String from, String to) {
        if (!adjacencyMap.containsKey(from) ||
                !adjacencyMap.containsKey(to) )
            return Collections.emptySet();

        visited = new HashSet<>();

        Set<String> airports = new TreeSet<>();
        findFlights(from, to, 0, airports);
        return airports;
    }

    private void findFlights(String from, String to, int currentCounter, Set<String> currentSet) {
        if (currentCounter > 1 || visited.contains(from))
            return;

        List<String> adjacent = adjacencyMap.get(from);

        for (String airport : adjacent) {
            if (airport.equals(to) && currentCounter == 1)
                currentSet.add(from);
            else
                findFlights(airport, to, currentCounter + 1, currentSet);
        }
        visited.add(from);
    }
}

