package com.daria.learn;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class single {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        FlightsGraph graph = new FlightsGraph();

        final int n = sc.nextInt();
        for (int i = 0; i < n; ++i) {
            final String fr = sc.next();
            final String to = sc.next();
            graph.addConnection(fr, to);
        }
        List<String[]> toTest = new LinkedList<>();
        final int m = sc.nextInt();
        for (int i = 0; i < m; ++i) {
            final String fr = sc.next();
            final String to = sc.next();
            toTest.add(new String[]{fr, to});
            //System.out.println();
        }

        for (String[] test : toTest) {
            Set<String> res = graph.singleFlights(test[0], test[1]);
            System.out.println(String.join(" ", res));
        }
    }
}

/*
Google use case: Google Flights.

-----

There are airports with direct flights between them. Find all flights from an origin to a destination airports with exactly one stopover between them and print all found stopovers sorted lexicographically from smallest to largest.

Input
-----
The fist line contains N, the number of direct flights. 0 < N < 1000000. Then the subsequent N lines contain two words per line, both ASCII-only and case sensitive. The first word is the origin airport and the second word is the destination airport of the flight. Please note that flights are one-way, maybe there is no flight in the opposite direction. Flights can be listed in any order, but without duplicates.

The next line contains M, the number of trips. 0 < M < 1000000. Then the subsequent M lines contain two words per line, both ASCII-only and case sensitive. The first word is the origin airport and the second word is the final destination airport of the trip. In addition to these airports, the trip contains a single stopover airport in-between. Trip origin and destination can be the same.

Each airport name is at most 4 characters long. it is ASCII only, it may not contain whitespace, and it is case sensitive.

Output
------
There is one output line for each trip. The line contains a space-separated list of possible stopover airports, in ASCII code lexicographical order.

Example
-------
Input:

8
AMS ZRH
SFO NYC
NYC ZRH
SFO ZRH
SFO AMS
NYC AMS
NYC MUC
MUC ZRH
6
SFO ZRH
ZRH SFO
AMS ZRH
x ZRH
SFO y
NYC ZRH

Output:

AMS NYC




AMS MUC

 */