package com.daria.learn;


import java.util.Scanner;

/*
Google use case: merging log files written by the same binary (for example, Google Docs servers) running on multiple computers.

-----

Merge two sorted arrays into one sorted array. Read the input from STDIN and print result to STDOUT as described below.

Input
-----
The first line of the input contains 2 integers N and M - lengths of the arrays. 0 <= N, M <= 10000000.

The following N lines contain elements of the first array A, one integer per line.

The last M lines contain elements of the second array B. 0 <= A[i], B[i] <= 1000000000. A[i] <= A[j], B[i] <= B[j] for i < j.

Output
------

A sorted array constructed from elements of arrays A and B. Each element should be on a separate line.

Example
-------

Input:

2 3
1
3
2
4
5

Output:

1
2
3
4
5
 */

public class merge {
    public static void main(String[] args) {
/*        final Scanner sc = new Scanner(System.in);
        final int a = sc.nextInt();
        final int[] ta = new int[a];
        final int b = sc.nextInt();
        final int[] tb = new int[b];
        for (int ai = 0; ai < a; ++ai) {
            ta[ai] = sc.nextInt();
        }
        for (int bi = 0; bi < b; ++bi) {
            tb[bi] = sc.nextInt();
        }*/

        int[] ta = new int[]{2, 4, 10, 11};
        int[] tb = new int[] {1, 5, 7, 12};

        int[] merged = sort1(ta, tb);
        for (int i = 0; i < merged.length; i++) {
            System.out.print(merged[i] + " ");
        }        // ... Change code below.
    }

    private static int[] sort1(int[] arr1, int[] arr2) {
        int totalSize = arr1.length + arr2.length;
        int[] merged = new int[totalSize];
        int arr1Conter = 0, arr2Counter = 0;
        for (int i = 0; i < totalSize; i++) {
            if (arr1Conter == arr1.length)
                merged[i] = arr2[arr2Counter++];
            else if (arr2Counter == arr2.length)
                merged[i] = arr1[arr1Conter++];
            else
                merged[i] = arr1[arr1Conter] < arr2[arr2Counter]  ? arr1[arr1Conter++] : arr2[arr2Counter++];
        }
        return merged;
    }
}