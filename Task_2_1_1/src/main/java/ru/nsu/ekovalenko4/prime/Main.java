package ru.nsu.ekovalenko4.prime;

/**
 * Main class for computation time demonstration purpose.
 */
public class Main {

    /**
     * Main method for computation time demonstration purpose.
     */
    public static void main(String[] args) throws Exception {

        int[] data = new int[50000];
        for (int i = 0; i < 50000; i++) {
            data[i] = 1000_000_007;
        }

        long start;
        long end;
        boolean ans;

        start = System.nanoTime();
        ans = SequentialCheck.hasNonPrime(data);
        end = System.nanoTime();
        System.out.println("Sequential: " + ans + ", " + (end - start) / 1e6 + " ms");

        for (int threads : new int[]{2, 4, 6, 8, 16, 32}) {
            start = System.nanoTime();
            ans = ThreadCheck.hasNonPrime(data, threads);
            end = System.nanoTime();
            System.out.println("Threads (" + threads + "): " + ans + ", " + (end - start) / 1e6
                    + " ms");
        }

        start = System.nanoTime();
        ans = ParallelStreamCheck.hasNonPrime(data);
        end = System.nanoTime();
        System.out.println("ParallelStream: " + ans + ", " + (end - start) / 1e6 + " ms");
    }

}
