package ru.nsu.ekovalenko4.prime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ParallelStreamCheckTest {

    @Test
    void testAllPrimes() {
        int[] data = {2, 3, 5, 7, 11};
        assertFalse(ParallelStreamCheck.hasNonPrime(data));
    }

    @Test
    void testNonPrime() {
        int[] data = {2, 3, 6, 7};
        assertTrue(ParallelStreamCheck.hasNonPrime(data));
    }

}