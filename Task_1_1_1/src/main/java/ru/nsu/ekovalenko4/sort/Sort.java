package ru.nsu.ekovalenko4.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Heapsort method.
 */
public class Sort {

    static void heapsort(int[] arr) {
        if (arr != null) {
            int n = arr.length;
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }
            for (int i = n - 1; i >= 0; i--) {
                swap(arr, 0, i);
                heapify(arr, i, 0);
            }
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] sizes = {10, 50, 100, 500, 1000, 2500, 5000, 7500, 10000, 25000, 50000, 75000, 100000, 250000, 500000, 750000, 1000000};

        for (int size : sizes) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt();
            }
            int[] copy = array.clone();

            long start = System.nanoTime();
            heapsort(array);
            double time = (System.nanoTime() - start) / 1_000_000.0; // milliseconds

            Arrays.sort(copy);
            if(Arrays.equals(array, copy)) {
                System.out.println("Array length: " + size + " - Sorting time: " + time + " ms");
            }
            else {
                System.out.println("Incorrect heapsort result");
            }
        }
    }

}
