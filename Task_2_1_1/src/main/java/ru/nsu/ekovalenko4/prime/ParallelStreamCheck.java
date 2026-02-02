package ru.nsu.ekovalenko4.prime;

import java.util.Arrays;

public class ParallelStreamCheck {

    public static boolean hasNonPrime(int[] array) {
        return Arrays.stream(array).parallel().anyMatch(value -> !IsPrime.isPrime(value));
    }
}
