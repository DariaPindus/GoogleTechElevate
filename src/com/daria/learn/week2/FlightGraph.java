package com.daria.learn.week2;

import java.util.*;

public class FlightGraph {
    public static class Flight {
        final String departs;
        final String arrives;
        final int price;

        public Flight(String departs, String arrives, int price) {
            this.departs = departs;
            this.arrives = arrives;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.join(" ", new String[]{this.departs, this.arrives, String.valueOf(this.price)});
        }
    }

    public static class Route implements Comparable<Route>{
        public final LinkedList<String> airports;
        public int price;

        public Route(LinkedList<String> airports, int price) {
            this.airports = airports;
            this.price = price;
        }

        public Route withAirport(String airport, int flightPrice) {
            LinkedList<String> clonedAirports = new LinkedList<>();
            clonedAirports.addAll(airports);
            clonedAirports.add(airport);
            return new Route(clonedAirports, price + flightPrice);
        }

        @Override
        public int compareTo(Route o) {
            return price == o.price ?
                    (airports.size() == o.airports.size() ? compareByStops(airports, o.airports) : airports.size() - o.airports.size())
            : price - o.price;
        }

        private int compareByStops(LinkedList<String> airportsA, LinkedList<String> airportsB) {
            return String.join(" ", airportsA).compareTo(String.join(" ", airportsB));
        }

        @Override
        public String toString() {
            return String.join(" ", airports + " " + price);
        }
    }

    class Node {
        final String code;
        final Map<String, Edge> connections;

        Node(String code, Map<String, Edge> connections) {
            this.code = code;
            this.connections = connections;
        }
    }

    class Edge {
        final int price;
        final String node;

        Edge(int price, String node) {
            this.price = price;
            this.node = node;
        }
    }

    private static final String DEFAULT_FLIGHT_PARENT = "DEFAULT";

    private final Map<String, Node> nodes;
    private final int limit;
    private Set<Route> resultRoutes;

    public FlightGraph(Collection<Flight> flights, int limit) {
        this.limit = limit;
        nodes = new HashMap<>();
        for (Flight flight : flights) {
            if (!nodes.containsKey(flight.departs)) {
                nodes.put(flight.departs, new Node(flight.departs, new HashMap<>()));
            }

            if (!nodes.containsKey(flight.arrives)) {
                nodes.put(flight.arrives, new Node(flight.arrives, new HashMap<>()));
            }
            nodes.get(flight.departs).connections.put(flight.arrives, new Edge(flight.price, flight.arrives));
        }
    }

    public List<Route> findConnections(String from, String to) {
        if (!nodes.containsKey(from) || !nodes.containsKey(to))
            return Collections.emptyList();

        resultRoutes = new TreeSet<>();
        Queue<Route> priceQueue = new PriorityQueue<>();
        Map<String, String> destinationParent = new HashMap<>();
        Set<String> visitedAirports = new HashSet<>();

        priceQueue.add(new Route(initialList(from), 0));
        destinationParent.put(from, DEFAULT_FLIGHT_PARENT);

        while (!priceQueue.isEmpty() && resultRoutes.size() < limit) {
            Route cheapest = priceQueue.poll();
            String currentCheapestAirport = cheapest.airports.getLast();
            visitedAirports.add(currentCheapestAirport);
            //TODO: check cycles
            for (Map.Entry<String, Edge> connection : nodes.get(currentCheapestAirport).connections.entrySet()) {
                if (visitedAirports.contains(connection.getKey()))
                    continue;
                destinationParent.put(connection.getKey(), currentCheapestAirport);
                Route newRoute = cheapest.withAirport(connection.getKey(), connection.getValue().price);
                if (connection.getKey().equals(to)) {
                    resultRoutes.add(newRoute);
                    continue;
                }
                priceQueue.add(newRoute);
            }
        }

        return new LinkedList<>(resultRoutes);
    }

    private LinkedList<String> initialList(String from) {
        LinkedList<String> list = new LinkedList<>();
        list.add(from);
        return list;
    }


}
