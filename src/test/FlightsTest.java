package test;

import com.daria.learn.week2.FlightGraph;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightsTest {

    @Test
    public void testFlights() {
        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",500));
        flights.add(new FlightGraph.Flight("JFK","WAW",800));
        flights.add(new FlightGraph.Flight("JFK","BSL",700));
        flights.add(new FlightGraph.Flight("JFK","ZRH",850));
        flights.add(new FlightGraph.Flight("ZRH","BSL",300));
        flights.add(new FlightGraph.Flight("SFO","HEL",1000));
        flights.add(new FlightGraph.Flight("SFO","MUC",1100));
        flights.add(new FlightGraph.Flight("SFO","LHR",1100));
        flights.add(new FlightGraph.Flight("MUC","ZRH",500));
        flights.add(new FlightGraph.Flight("LHR","BSL",1));
        flights.add(new FlightGraph.Flight("LHR","ZRH",100));
        flights.add(new FlightGraph.Flight("BSL","ZRH",1));
        flights.add(new FlightGraph.Flight("SFO","ZRH",5000));

        String res = printResults(flights, "SFO", "ZRH", 10);
        assertEquals("SFO LHR BSL ZRH 1102\n" +
                "SFO LHR ZRH 1200\n" +
                "SFO JFK BSL ZRH 1201\n" +
                "SFO JFK ZRH 1350\n" +
                "SFO MUC ZRH 1600\n" +
                "SFO ZRH 5000\n", res);
    }

    @Test
    public void testNonExisting() {

        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",500));
        flights.add(new FlightGraph.Flight("JFK","WAW",800));
        flights.add(new FlightGraph.Flight("JFK","BSL",700));
        flights.add(new FlightGraph.Flight("JFK","ZRH",850));
        flights.add(new FlightGraph.Flight("ZRH","BSL",300));
        flights.add(new FlightGraph.Flight("SFO","HEL",1000));
        flights.add(new FlightGraph.Flight("SFO","MUC",1100));
        flights.add(new FlightGraph.Flight("SFO","LHR",1100));
        assertEquals("<no solution>", printResults(flights, "SFO", "ABC", 10));
    }


    @Test
    public void testNonExisting2() {
        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",500));
        flights.add(new FlightGraph.Flight("JFK","WAW",800));
        flights.add(new FlightGraph.Flight("WAW","SFO",700));
        flights.add(new FlightGraph.Flight("MUC","ZRH",500));
        assertEquals("<no solution>", printResults(flights, "SFO", "ZRH", 10));
    }


    @Test
    public void testFlightsLimit() {
        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",500));
        flights.add(new FlightGraph.Flight("JFK","WAW",800));
        flights.add(new FlightGraph.Flight("JFK","BSL",700));
        flights.add(new FlightGraph.Flight("JFK","ZRH",850));
        flights.add(new FlightGraph.Flight("ZRH","BSL",300));
        flights.add(new FlightGraph.Flight("SFO","HEL",1000));
        flights.add(new FlightGraph.Flight("SFO","MUC",1100));
        flights.add(new FlightGraph.Flight("SFO","LHR",1100));
        flights.add(new FlightGraph.Flight("MUC","ZRH",500));
        flights.add(new FlightGraph.Flight("LHR","BSL",1));
        flights.add(new FlightGraph.Flight("LHR","ZRH",100));
        flights.add(new FlightGraph.Flight("BSL","ZRH",1));
        flights.add(new FlightGraph.Flight("SFO","ZRH",5000));

        String res = printResults(flights, "SFO", "ZRH", 4);
        assertEquals(4, res.trim().split("\n").length);
    }

    @Test
    public void testOrdering() {

        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",10));
        flights.add(new FlightGraph.Flight("SFO","ZRH",20));
        flights.add(new FlightGraph.Flight("SFO","WAW",5));
        flights.add(new FlightGraph.Flight("JFK","BSL",10));
        flights.add(new FlightGraph.Flight("ZRH","LHR",25));
        flights.add(new FlightGraph.Flight("LHR","SFO",30));
        flights.add(new FlightGraph.Flight("WAW","BSL",15));

        String res = printResults(flights, "SFO", "BSL", 10);
        assertEquals("SFO JFK BSL 20\nSFO WAW BSL 20", res.trim());
    }

    @Test
    public void testOrdering2() {
        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",10));
        flights.add(new FlightGraph.Flight("SFO","ZRH",5));
        flights.add(new FlightGraph.Flight("SFO","WAW",5));
        //flights.add(new FlightGraph.Flight("JFK","BSL",10));
        flights.add(new FlightGraph.Flight("ZRH","LHR",5));
        flights.add(new FlightGraph.Flight("LHR","SFO",30));
        flights.add(new FlightGraph.Flight("WAW","BSL",15));
        flights.add(new FlightGraph.Flight("LHR","BSL",10));

        String res = printResults(flights, "SFO", "BSL", 10);
        assertEquals("SFO WAW BSL 20\nSFO ZRH LHR BSL 20", res.trim());
    }

    @Test
    public void testOrdering3() {
        List<FlightGraph.Flight> flights = new LinkedList<>();
        flights.add(new FlightGraph.Flight("SFO","JFK",10));
        flights.add(new FlightGraph.Flight("SFO","ZRH",7));
        flights.add(new FlightGraph.Flight("JFK","BSL",5));
        flights.add(new FlightGraph.Flight("JFK","WAW",10));
        flights.add(new FlightGraph.Flight("BSL","WAW",5));
        //flights.add(new FlightGraph.Flight("JFK","BSL",10));
        flights.add(new FlightGraph.Flight("ZRH","WAW",13));
        flights.add(new FlightGraph.Flight("LHR","ZRH",10));
        flights.add(new FlightGraph.Flight("WAW","LHR",15));

        String res = printResults(flights, "SFO", "WAW", 10);
        assertEquals("SFO JFK WAW 20\nSFO ZRH WAW 20\nSFO JFK BSL WAW 20", res.trim());
    }


    private String printResults(List<FlightGraph.Flight> flights,
                                String start,
                                String end,
                                int limit) {
        List<FlightGraph.Route> connections = findConnections(flights, start, end, limit);

        if (connections.isEmpty())
            return "<no solution>";
        else {
            StringBuilder sb = new StringBuilder();
            for (FlightGraph.Route connection : connections) {
                sb.append(String.join(" ", connection.airports) + " " + connection.price + "\n");
            }
            return sb.toString();
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
