package ru.nsu.ekovalenko4.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {

    @Test
    void sort() {
        int[] array = new int[]{1, 2, 3};
        var result = Sort.sort(array);
        assertArrayEquals(new int[]{1, 2, 3},result);
    }
}