package heap_design;

import java.util.ArrayList;

/**
 * MinHeap implementation using array list coremen
 *
 * Created by nachiketlondhe on 2/27/17.
 */
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

        // bubble down part
        // get the smallest of either left of right
        // swap value at this index with index captured above
        // continue until this is not true and break at that point
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
}
