/*
 * TestThread.java
 * @author Gediminas Krasauskas
 * Class tests thread
 */

package com.company;

class TestThread extends Thread {

    private static SortableArray sortableArray;
    private int from_Index;
    private int to_Index;

    private TestThread(SortableArray sortableArray, int from_Index, int to_Index) {
        TestThread.sortableArray = sortableArray;
        this.from_Index = from_Index;
        this.to_Index = to_Index;
    }

    public void run() {
        System.out.println("Thread " + this + " started");

        for (int i = 0; i < 3; i++)
        {
            System.out.println("Thread " + this + ": before calling LOCK");
            System.out.println("Thread " + this + ": array BEFORE calling LOCK: ");
            printArrayElements();
            sortableArray.lock(from_Index, to_Index);
            System.out.println("Thread " + this + ": array AFTER calling LOCK:");
            printArrayElements();
            System.out.println("Thread " + this + ": called LOCK successfully");

            sortableArray.sortArray(from_Index, to_Index);

            System.out.println("Thread " + this + ": before calling UNLOCK");
            System.out.println("Thread " + this + ": array BEFORE calling UNLOCK: ");
            printArrayElements();
            sortableArray.unlock(from_Index, to_Index);
            System.out.println("Thread " + this + ": array AFTER calling UNLOCK:");
            printArrayElements();
            System.out.println("Thread " + this + ": called UNLOCK successfully");
        }
        System.out.println("Thread " + this + " completed");
    }

    static void execute() {

        SortableArray array = new SortableArray(10);

        try {
            Thread t1 = new TestThread(array, 0, 5);
            t1.start();

            Thread t2 = new TestThread(array, 2,7);
            t2.start();

            Thread t3 = new TestThread(array, 7,9);
            t3.start();

            t1.join();  t2.join();  t3.join();
        }

        catch (InterruptedException exc) {
            System.out.println("Error: " + exc);
        }
    }

    private void printArrayElements(){
        int[] sortableArrayElements = sortableArray.getSortableArrayElements();
        StringBuilder arrayElementsList = new StringBuilder();
        arrayElementsList.append(this);
        arrayElementsList.append(" ------> ");
        for(int i = 0; i < 10; i++){
            arrayElementsList.append(sortableArrayElements[i]);
            arrayElementsList.append(" ");
        }
        System.out.println(arrayElementsList);
    }
}
