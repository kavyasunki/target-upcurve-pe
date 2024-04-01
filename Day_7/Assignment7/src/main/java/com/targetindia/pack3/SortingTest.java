package com.targetindia.pack3;

import java.util.Arrays;

public class SortingTest {
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 9, 1, 7};
        int[] arr2 = {8, 3, 6, 4, 2};
        int[] arr3 = {10, 6, 3, 8, 1};

        Sorter sorter = new Sorter();

        // Using Bubble Sort
        System.out.println("Using Bubble Sort:");
        System.out.println("Original array: " + Arrays.toString(arr1));
        sorter.sort(new BubbleSort(), arr1);
        System.out.println("Sorted array: " + Arrays.toString(arr1));

        // Using Selection Sort
        System.out.println("\nUsing Selection Sort:");
        System.out.println("Original array: " + Arrays.toString(arr2));
        sorter.sort(new SelectionSort(), arr2);
        System.out.println("Sorted array: " + Arrays.toString(arr2));

        // Using Merge Sort
        System.out.println("\nUsing Merge Sort:");
        System.out.println("Original array: " + Arrays.toString(arr3));
        sorter.sort(new MergeSort(), arr3);
        System.out.println("Sorted array: " + Arrays.toString(arr3));
    }
}