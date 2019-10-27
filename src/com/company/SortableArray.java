/*
 * SortableArray.java
 * @author Gediminas Krasauskas
 * The main functionality implementation
 */

package com.company;
import java.util.Arrays;
import java.util.Random;

class SortableArray {

    private int[] array;
    private boolean[] lockedElements;

    SortableArray(int arraySize) {
        array = initArray(arraySize);
        lockedElements = initLockedElements(arraySize);
    }

    synchronized void lock(int from_Index, int to_Index) {

        while(isLocked(from_Index, to_Index)) {
            try {
                System.out.println("Suspend: " + Thread.currentThread());
                wait();
            }
            catch(InterruptedException exc){
                System.out.println("Interrupted: " + exc);
                System.exit(4);
            }
        }

        lockElements(from_Index, to_Index);
        //notifyAll();
        //sortArray(array, from_Index, to_Index);
    }

    synchronized void unlock(int from_Index, int to_Index){

        while(!isLocked(from_Index, to_Index)){
            try {
                wait();
            }
            catch(InterruptedException exc)
            {
                System.out.println("Interrupted: " + exc);
                System.exit(4);
            }
        }

        unlockElements(from_Index, to_Index);
        notifyAll();
    }

    private boolean isLocked(int from_Index, int to_Index){
        for(int i = from_Index; i < to_Index; i++){
            if(lockedElements[i]){
                return true;
            }
        }
        return false;
    }

    private void lockElements(int from_Index, int to_Index){
        for(int i = from_Index; i < to_Index; i++){
            lockedElements[i] = true;
        }
    }

    private void unlockElements(int from_Index, int to_Index){
        for(int i = from_Index; i < to_Index; i++){
            lockedElements[i] = false;
        }
    }

    private int[] initArray(int arraySize){
        Random rand = new Random();
        int[] intArray = new int[arraySize];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = rand.nextInt(100) + 1;
        }
        return intArray;
    }

    private boolean[] initLockedElements(int arraySize){
        return new boolean[arraySize];
    }

    void sortArray(int from_Index, int to_Index){
        Arrays.sort(array, from_Index, to_Index);
    }

    int[] getSortableArrayElements(){
        return array;
    }
}
