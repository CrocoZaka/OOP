package ru.nsu.ekovalenko4.prime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ThreadCheckTest {

    @Test
    void testAllPrimes() throws InterruptedException {
        int[] data = {2, 3, 5, 7, 11, 13};
        assertFalse(ThreadCheck.hasNonPrime(data, 2));
        assertFalse(ThreadCheck.hasNonPrime(data, 4));
    }

    @Test
    void testNonPrime() throws InterruptedException {
        int[] data = {3, 5, 9, 11};
        assertTrue(ThreadCheck.hasNonPrime(data, 2));
        assertTrue(ThreadCheck.hasNonPrime(data, 4));
    }

    @Test
    void testMoreThreadsThanElements() throws InterruptedException {
        int[] data = {2, 3, 4};
        assertTrue(ThreadCheck.hasNonPrime(data, 8));
    }

}