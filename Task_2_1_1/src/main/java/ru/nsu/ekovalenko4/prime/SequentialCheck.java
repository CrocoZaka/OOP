package ru.nsu.ekovalenko4.prime;

/**
 * Provides a sequential (single-threaded) implementation
 * for detecting a non-prime number in an integer array.
 */
public class SequentialCheck {

    /**
     * Determines whether the given array contains at least one non-prime number.
     */
    public static boolean hasNonPrime(int[] array) {
        for (int value : array) {
            if (!IsPrime.isPrime(value)) {
                return true;
            }
        }
        return false;
    }
}
