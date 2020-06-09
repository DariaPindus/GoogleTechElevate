package com.daria.learn.week1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Merge N sorted arrays into 1 sorted array. Read the input and print the result using the provided starter code.

Input
-----
The first line of the input contains one integer N, the number of sorted arrays. The next line contains N integers, the lengths of the N arrays. The following N lines contain the sorted elements of the arrays, separated by whitespaces. All elements are integers.

Output
------
A sorted array containing the elements of all N arrays. The result should be output in one line, with elements separated by whitespaces.

Constraints:
------
1 <= N <= 23’000
For all 1 <= p <= N and all  0 <= k < lenp, where lenp is the length of the array Ap, it holds that:
0 < len1, …, lenn < 1'000
the elements Ap[k] are of type int and 0 <= Ap[k] <= 1’000’000
Ap[i] <= Ap[j] for 0 <= i < j < lenp

Example
-------
Input:

3
2 3 4
1 3
2 4 5
2 3 3 4

Output:
1 2 2 3 3 3 4 4 5
 */
public class MergeNSorted {


    public static ArrayList<Integer> merge(ArrayList<ArrayList<Integer>> arrays, int[] arrayLengths) {
        return mergeIteratingPointers(arrays, arrayLengths);
//        return mergePriorityQueue(arrays, arrayLengths);
    }

    private static ArrayList<Integer> mergePriorityQueue(ArrayList<ArrayList<Integer>> arrays, int[] arrayLengths) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (ArrayList<Integer> array : arrays) {
            queue.addAll(array);
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }

    private static ArrayList<Integer> mergeIteratingPointers(ArrayList<ArrayList<Integer>> arrays, int[] arrayLengths) {
        // TODO: Implement the functionality here.
        int resultLength = Arrays.stream(arrayLengths).sum();

        ArrayList<Integer> result = new ArrayList<>(resultLength);
        int[] pointers = new int[arrayLengths.length];

        for (int i = 0; i < resultLength; i++) {
            int minarg = minArg(fillIndexArray(arrays, pointers));
            result.add(arrays.get(minarg).get(pointers[minarg]));
            pointers[minarg] += 1;
        }

        return result;
    }

    private static ArrayList<Integer> fillIndexArray(ArrayList<ArrayList<Integer>> arrays, int[] pointers) {
        ArrayList<Integer> resultList = new ArrayList<>(arrays.size());
        for (int ind = 0; ind < arrays.size(); ind++ ) {
            int value = pointers[ind] < arrays.get(ind).size() ? arrays.get(ind).get(pointers[ind]) : -1;
            resultList.add(value);
        }
        return resultList;
    }

    private static int minArg(List<Integer> valuesAtPointers) {
        int minarg = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < valuesAtPointers.size(); i++) {
            if (valuesAtPointers.get(i) > -1 && valuesAtPointers.get(i) < min) {
                minarg = i;
                min = valuesAtPointers.get(i);
            }
        }
        return minarg;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int numArrays = Integer.parseInt(scanner.nextLine());
//        int arrayLengths[] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
//        for (int i = 0; i < numArrays; ++i) {
//            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//            ArrayList<Integer> arrayList = new ArrayList<Integer>();
//            for (int el : array) arrayList.add(Integer.valueOf(el));
//            arrays.add(arrayList);
//        }
//        scanner.close();

        String st = "8\n" +
                "8 9 2 10 4 5 5 4\n" +
                "1 2 2 3 5 6 10 11\n" +
                "2 5 6 10 12 13 15 20 22\n" +
                "1 4\n" +
                "4 10 13 15 16 20 22 29 51 142\n" +
                "1 4 20 120\n" +
                "50 55 100 111 150\n" +
                "1 100 1000 1000 10000\n" +
                "51 99 499 4949";
        String[] input = st.split("\n");
        int numArrays = Integer.parseInt(input[0]);
        int arrayLengths[] = Arrays.stream(input[1].split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
        for (int i = 2; i < numArrays + 2; ++i) {
            int[] array = Arrays.stream(input[i].split(" ")).mapToInt(Integer::parseInt).toArray();
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (int el : array) arrayList.add(Integer.valueOf(el));
            arrays.add(arrayList);
        }

        // TODO: Implement the merge() function.
        long start = System.nanoTime();
        ArrayList<Integer> merged = merge(arrays, arrayLengths);
        long end = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int s : merged) {
            sb.append(s);
            sb.append(" ");
        }
        System.out.println(sb.toString());
        System.out.println("Time " + (end - start));
    }

    /*
8
8 9 2 10 4 5 5 4
1 2 2 3 5 6 10 11
2 5 6 10 12 13 15 20 22
1 4
4 10 13 15 16 20 22 29 51 142
1 4 20 120
50 55 100 111 150
1 100 1000 1000 10000
51 99 499 4949
     */
}

