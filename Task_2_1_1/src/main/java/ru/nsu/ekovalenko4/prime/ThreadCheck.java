package ru.nsu.ekovalenko4.prime;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadCheck {

    public static boolean hasNonPrime(int[] array, int threadCount) throws InterruptedException {

        AtomicBoolean found = new AtomicBoolean(false);
        Thread[] threads = new Thread[threadCount];

        int chunkSize = array.length / threadCount;

        for (int t = 0; t < threadCount; t++) {
            final int start = t * chunkSize;
            final int end = (t == threadCount - 1) ? array.length : start + chunkSize;

            threads[t] = new Thread(() -> {
                for (int i = start; i < end && !found.get(); i++) {
                    if (!IsPrime.isPrime(array[i])) {
                        found.set(true);
                        break;
                    }
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return found.get();
    }
}

