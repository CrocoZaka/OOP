package ru.nsu.ekovalenko4.prime;

public class SequentialCheck {

    public static boolean hasNonPrime(int[] array) {
        for (int value : array) {
            if (!IsPrime.isPrime(value)) {
                return true;
            }
        }
        return false;
    }
}
