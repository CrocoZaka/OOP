package ru.nsu.ekovalenko4.prime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SequentialCheckTest {

    @Test
    void testPrimes() {
        int[] data = {2, 3, 5, 7, 11, 13};
        assertFalse(SequentialCheck.hasNonPrime(data));
    }

    @Test
    void testNonPrime() {
        int[] data = {3, 4, 5, 7, 8};
        assertTrue(SequentialCheck.hasNonPrime(data));
    }
}