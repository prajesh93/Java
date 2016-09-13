  /**
     *  Parallel implementation of SieveOfEratosthenes.
     *  Created for each multiplier one new thread (multiplierThread).
     *  The maximum number active multiplierThreads is indicated by a command line argument.
     *     
         * $Id: SieveOfEratosthenes.java, v1.1 10/19/2015
     *
         * @author      Ashwini Singh
         * @author      Prajesh Jhumkhawala
         */




class Add extends Thread {
    int v = 0;
    int[] test;
    static int MAX;
    static boolean[] numbers;

    public Add(int max, int[] value) {
        numbers = new boolean[max];
        this.MAX = max;
        test = value;

    }


    /**
     * This method prints the values with boolean answers, whether they are prime or not.
     */

   
    public void testForPrimeNumber() throws InterruptedException {
        for (int index = 0; index < test.length; index++) {
            if (test[index] < MAX) {

                System.out.println(test[index] + " = " + numbers[test[index]]);
            }
        }
    }
    /**
     * This method sets all the values in test to true.
     */


    public void determinePrimeNumbers() {
        for (int index = 1; index < MAX; index++) {
            numbers[index] = true;
        }

    }

}

/**
 * This class inherits the Add class and has run method in it.
 *
 */


class Add1 extends Add {
    int index;

    public Add1(int max, int[] value, int x) {
        super(max, value);
        this.index = x;
    }

    Add a = new Add(this.MAX, test);

    public void run() {
        try {
            if (index == 2) // Only for 2's prime number all test case values will be set to true.
                a.determinePrimeNumbers();
            int counter = 2; // this is the part for the parallel part
            while (index * counter < a.MAX) { // this is the part for the
                                                // parallel part
                a.numbers[index * counter] = false; // this is the part for the
                                                    // parallel part
                counter++; // this is the part for the parallel part
            } // this is the part for the parallel part
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
/**
 * This class contains main method, which executes the entire program.
 *
 */


public class SieveOfEratosthenes {

    public static void main(String args[]) throws InterruptedException {

        int[] test = { 2, 4, 6, 8, 3, 4, 7, 13, 17, 19, 20, 23, 69, 87, 120, 35, 46 };
        int n = test.length;
        int max = Integer.parseInt(args[0]);
        int no_of_threads = Integer.parseInt(args[1]);
        Add a = new Add(max + 1, test);

        Thread v1 = new Add1(max + 1, test, 2);
        Thread v2 = new Add1(max + 1, test, 3);
        Thread v3 = new Add1(max + 1, test, 5);
        Thread v4 = new Add1(max + 1, test, 7);

        if (no_of_threads < 1 || no_of_threads > Runtime.getRuntime().availableProcessors())
            System.err.println("Process Terminated : Invalid Input ");
        else {
            v1.start();
            v1.join();

            if (no_of_threads == 1) {  //If only one thread is running at a time.
                if (!v1.isAlive()) {  // wait for 1st thread to dead then start 2nd thread.
                    v2.start();
                    v2.join();
                }
                if (!v2.isAlive()) {  // wait for 2nd thread to dead then start 3rd thread.
                    v3.start();
                    v3.join();

                }
                if (!v3.isAlive()) { // wait for 3rd thread to dead then start 4th thread.
                    v4.start();
                    v4.join();

                }

            }

            if (no_of_threads == 2) { // If user wants 2 threads running at the same time.
                v2.start();
                v2.join();
                if (!v1.isAlive()) {  // check if 1st is dead then start 3rd thread.
                    v3.start();
                    v3.join();

                }
                if (!v2.isAlive()) { // check if 2nd is dead then start 4th thread.
                    v4.start();
                    v4.join();

                }

            }

            if (no_of_threads == 3) {
                v2.start();
                v2.join();
                v3.start();
                v3.join();
                if (!v1.isAlive() || !v2.isAlive() || !v3.isAlive()) {
                    v4.start();
                    v4.join();

                }
            }
            if (no_of_threads == 4) {
                v2.start();
                v2.join();
                v3.start();
                v3.join();
                v4.start();

            }
            a.testForPrimeNumber();

        }
    }

}