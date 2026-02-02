package ru.nsu.ekovalenko4.prime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsPrimeTest {

    @Test
    void testNegative() {
        assertFalse(IsPrime.isPrime(-10));
    }

    @Test
    void testZeroAndOne() {
        assertFalse(IsPrime.isPrime(0));
        assertFalse(IsPrime.isPrime(1));
    }

    @Test
    void testPrimes() {
        assertTrue(IsPrime.isPrime(2));
        assertTrue(IsPrime.isPrime(3));
        assertTrue(IsPrime.isPrime(5));
    }

    @Test
    void testNonPrimes() {
        assertFalse(IsPrime.isPrime(4));
        assertFalse(IsPrime.isPrime(6));
        assertFalse(IsPrime.isPrime(9));
        assertFalse(IsPrime.isPrime(36));
    }

    @Test
    void testLargePrime() {
        assertTrue(IsPrime.isPrime(1_000_000_007));
    }

    @Test
    void testLargeNonPrime() {
        assertFalse(IsPrime.isPrime(1_000_000_005));
    }
}