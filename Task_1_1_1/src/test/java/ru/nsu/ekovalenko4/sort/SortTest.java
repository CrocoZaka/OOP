package ru.nsu.ekovalenko4.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {

    @Test
    void nul() {
        int[] array = null;
        var result = Sort.heapsort(array);
        assertArrayEquals(null, result);
    }

    @Test
    void empty() {
        int[] array = {};
        var result = Sort.heapsort(array);
        assertArrayEquals(new int[]{}, result);
    }

    @Test
    void single() {
        int[] array = {1};
        var result = Sort.heapsort(array);
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    void array() {
        int[] array = {2, 7, 4, 5, 1, 3, 8, 6};
        var result = Sort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, result);
    }

    @Test
    void sorted() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        var result = Sort.heapsort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, result);
    }

    @Test
    void duplicates() {
        int[] array = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3};
        var result = Sort.heapsort(array);
        assertArrayEquals(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}, result);
    }

    @Test
    void negative() {
        int[] array = new int[]{-1, -5, -7, -2, -8, -3, -4, -6};
        var result = Sort.heapsort(array);
        assertArrayEquals(new int[]{-8, -7, -6, -5, -4, -3, -2, -1}, result);
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