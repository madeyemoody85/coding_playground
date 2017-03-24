package heap_design;

import java.util.ArrayList;

/**
 * MinHeap implementation using array list coremen
 *
 * Created by nachiketlondhe on 2/27/17.
 */
/**
public class MinHeap {

    ArrayList<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public static void main(String args[]) {

        MinHeap myHeap = new MinHeap();

        myHeap.insert(6);
        myHeap.insert(2);
        myHeap.insert(7);
        myHeap.insert(4);
        myHeap.insert(5);
        myHeap.insert(3);
        myHeap.insert(1);

        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());

        System.out.println("Heap Insertion complete...");
    }

    // insert element in the heap
    public void insert(int value) {
        heap.add(value);

        int loc = heap.size() - 1;

        // bubble up maintaining the min heap property
        while ( loc > 0 && heap.get(loc).compareTo(heap.get(parentIndex(loc))) < 0 ) {
            int temp = heap.get(parentIndex(loc));
            heap.set(parentIndex(loc), heap.get(loc));
            heap.set(loc, temp);

            loc = parentIndex(loc);
        }
    }

    public int getMin() {
        if (heap.size() <= 0) {
            return Integer.MIN_VALUE;
        } else {
            return heap.get(0);
        }
    }

    public int removeMin() {
        int min = heap.get(0);

        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        minHeapify();

        return min;
    }

    // At this point you have extracted the minimum element from the heap
    // And replaced its position with the last element in the heap
    // bubble down the element fixing the heap property
    public void minHeapify() {
        int index = 0;

        while (leftChildIndex(index) < heap.size() ) {
            int smallerIndex = leftChildIndex(index);

            if (rightChildIndex(index) < heap.size() && heap.get(rightChildIndex(index)) < heap.get(smallerIndex)) {
                smallerIndex = rightChildIndex(index);
            }

            if (heap.get(index) > heap.get(smallerIndex)) {
                int temp = heap.get(index);
                heap.set(index, heap.get(smallerIndex));
                heap.set(smallerIndex, temp);
            } else {
                break;
            }

            index = smallerIndex;
        }
    }

    public int leftChildIndex(int i) {
        return 2 * i;
    }

    public int rightChildIndex(int i) {
        return  2 * i + 1;
    }

    public int parentIndex(int i) {
        return i / 2;
    }
}**/

/*
0 1 2 3 4 5 6
 */
public class MinHeap {
    ArrayList<Integer> heap;

    public static void main(String args[]) {
        MinHeap myHeap = new MinHeap();

        myHeap.insert(4);
        myHeap.insert(3);
        myHeap.insert(2);
        myHeap.insert(6);
        myHeap.insert(7);
        myHeap.insert(5);
        myHeap.insert(1);

        System.out.println("Done insertion");

        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
        System.out.println(myHeap.removeMin());
    }

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public int getParentIndex(int i) {
        return i / 2;
    }

    public int getLeftChildIndex(int i) {
        return 2 * i;
    }

    public int getRightChildIndex(int i) {
        return 2 * i + 1;
    }

    // insert element into heap
    public void insert(int i) {
        heap.add(i);

        // enter the element at the end of the array
        int loc = heap.size() - 1;

        // Move the element up as long as the location is greater than 0
        // And as long as parent index element is smaller than this element
        while (loc > 0 && heap.get(loc).compareTo(heap.get(getParentIndex(loc))) < 0) {
            int temp = heap.get(getParentIndex(loc));
            heap.set(getParentIndex(loc), heap.get(loc));
            heap.set(loc, temp);
            loc = getParentIndex(loc);
        }
    }

    // remove the minimum element from the heap
    // in this case the element will be at the top
    public int removeMin() {
        // Get the top element of heap
        int returnValue = heap.get(0);

        // Move the last element of the heap to top
        heap.set(0, heap.get(heap.size() - 1));

        // Remove the last element
        heap.remove(heap.size()-1);

        // Perform bubbleDown
        bubbleDown();

        return returnValue;
    }

    // In bubble down get the element at the top index
    // swap it with its minimum child it could be either left or right;
    public void bubbleDown() {
        int startIndex = 0;

        while(getLeftChildIndex(startIndex) < heap.size()) {
            int smallestChildIndex = getLeftChildIndex(startIndex);

            if (getRightChildIndex(startIndex) < heap.size() &&
                    heap.get(getRightChildIndex(startIndex)).compareTo(heap.get(smallestChildIndex)) < 0) {
                smallestChildIndex = getRightChildIndex(startIndex);
            }

            if (heap.get(smallestChildIndex).compareTo(heap.get(startIndex)) < 0) {
                int temp = heap.get(smallestChildIndex);
                heap.set(smallestChildIndex, heap.get(startIndex));
                heap.set(startIndex, temp);
            } else {
                break;
            }

            startIndex = smallestChildIndex;
        }
    }
}

