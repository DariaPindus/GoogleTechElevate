package com.daria.learn;


import java.util.Scanner;

/*
Google use case: warm up and fun.

-----

For the given integer N, print snake matrix such as (for N = 3):

1 2 3
6 5 4
7 8 9

Input
-----

A single integer N, 1 <= N <= 50.

Output
------

A snake matrix NxN:
- N lines with N space-separated integers each.
- Each number from 1 to N^2 inclusively should be present exactly once.
- Odd lines should be increasing, even lines should be decreasing, as above.

Example
-------

Input:

3

Output:

1 2 3
6 5 4
7 8 9
 */

public class snake {
    public static void main(String[] args) {
//        final Scanner sc = new Scanner(System.in);
//        final int n = sc.nextInt();
        long time = System.nanoTime();
        final int n = 50;
        validateN(n);
        // ... Replace code below.
        int[][] matr = snakeMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matr[i][j] + " ");
            }
            System.out.println("");
        }
        long timeEnd = System.nanoTime();
        System.out.println("Time = " + ((timeEnd - time) / 1000000.0));
    }

    private static void validateN(int n) {
        if (n < 1 || n > 50)
            throw new IllegalArgumentException("N should be A single integer, 1 <= N <= 50.");
    }

    private static int[][] snakeMatrix(int n) {
        int[][] snake = new int[n][n];

        for (int i = 0; i < n; i++) {
            int current = i % 2 == 0 ? i * n + 1 : i * n + n;
            int increment = i % 2 == 0 ? 1 : -1;

            for (int j = 0; j < n; j++) {
                snake[i][j] = current;
                current += increment;
            }
        }

        return snake;
    }
}
