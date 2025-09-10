package ru.nsu.ekovalenko4.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {

    @Test
    void nul() {
        int[] array = null;
        Sort.heapsort(array);
        assertArrayEquals(null, array);
    }

    @Test
    void empty() {
        int[] array = {};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void single() {
        int[] array = {1};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1}, array);
    }

    @Test
    void array() {
        int[] array = {2, 7, 4, 5, 1, 3, 8, 6};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }

    @Test
    void sorted() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }

    @Test
    void duplicates() {
        int[] array = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}, array);
    }

    @Test
    void negative() {
        int[] array = new int[]{-1, -5, -7, -2, -8, -3, -4, -6};
        Sort.heapsort(array);
        assertArrayEquals(new int[]{-8, -7, -6, -5, -4, -3, -2, -1}, array);
    }

    @Test
    void big_numbers() {
        int n = 10000000;
        int[] array = new int[n];
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            expected[i] = i + 1;
            array[i] = n - i;
        }
        Sort.heapsort(array);
        assertArrayEquals(expected, array);
    }

}