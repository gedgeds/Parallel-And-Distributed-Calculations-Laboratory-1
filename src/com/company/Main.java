/*
 * Main.java
 * @author Gediminas Krasauskas
 * The program shows how to define and use synchronization object
 *
 *  lock (int indexFrom, in indexTo)
 *  unlock (int indexFrom, in indexTo).
 *  The method lock () locks the array elements from indexFrom to indexTo inclusive.
 *  If at least one of the elements already "locked in" another thread, the call to lock () is blocked.
 *  Unlock - frees the locked elements (with the same indices).
 *  Program implements lock object and provides meaningful use of it (sorting an array values)
 */

package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Program started ===");
        TestThread.execute();
        System.out.println("=== Program completed ===");
    }
}

// Resursų eiliškumas