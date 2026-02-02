package ru.nsu.ekovalenko4.prime;

import java.util.Arrays;

/**
 * Provides a parallel implementation for detecting a non-prime number
 * using Java parallel streams.
 */
public class ParallelStreamCheck {

    /**
     * Determines whether the given array contains at least one non-prime number.
     */
    public static boolean hasNonPrime(int[] array) {
        return Arrays.stream(array).parallel().anyMatch(value -> !IsPrime.isPrime(value));
    }
}
