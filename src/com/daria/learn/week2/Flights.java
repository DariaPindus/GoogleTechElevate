package com.daria.learn.week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flights {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FlightGraph.Flight> flights = new ArrayList<>();

        int numberOfFlights = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numberOfFlights; i++) {
            String[] line = sc.nextLine().trim().split(" ");
            String source = line[0];
            String dest = line[1];
            int price = Integer.parseInt(line[2].trim());
            flights.add(new FlightGraph.Flight(source, dest, price));
        }

        int limit = Integer.parseInt(sc.nextLine());

        String[] sourceDestinationPair = sc.nextLine().trim().split(" ");
        String source = sourceDestinationPair[0];
        String destination = sourceDestinationPair[1];

        List<FlightGraph.Route> connections = findConnections(flights, source, destination, limit);

        if (connections.isEmpty())
            System.out.println("<no solution>");
        else
            for (FlightGraph.Route connection : connections) {
                System.out.println(String.join(" ", connection.airports) + " " + connection.price);
            }
    }

    private static List<FlightGraph.Route> findConnections(
            List<FlightGraph.Flight> flights,
            String start,
            String end,
            int limit
    ) {
        FlightGraph flightGraph = new FlightGraph(flights, limit);
        List<FlightGraph.Route> results = flightGraph.findConnections(start, end);
        return results;
    }
}
