package heap_design;

/**
 * Created by nachiketlondhe on 2/4/17.
 */
public class Heap {

    // Declare the heap of size 10
    public static Integer[] heap = new Integer[10];

    public static int numberOfElements = 0;

    public static void main(String args[]) {

        Heap solution = new Heap();

        solution.insert(6);
        solution.insert(2);
        solution.insert(7);
        solution.insert(4);
        solution.insert(5);
        solution.insert(3);
        solution.insert(1);

        System.out.println(solution.removeMin());
        System.out.println(solution.removeMin());
        System.out.println(solution.removeMin());
        System.out.println(solution.removeMin());
        System.out.println(solution.removeMin());
        System.out.println(solution.removeMin());
        System.out.println(solution.removeMin());

        System.out.println("Heap Insertion complete...");
    }

    // insert an elemnt in the heap
    public void insert(int value) {
        if (numberOfElements >= heap.length) {
            System.out.println("Array out of bounds...");
            return;
        }
        numberOfElements++;
        heap[numberOfElements] = value;
        bubbleUp();
    }

    // bubble up and fix the heap
    public void bubbleUp() {
        int index = numberOfElements;

        while(hasParent(index) && (parent(index) > heap[index])) {
            swap(parentIndex(index), index);
            index = parentIndex(index);
        }
    }

    // Returns the minimum element but doesn't remove it
    public int peek() {
        if (numberOfElements == 0) {
            throw new IllegalStateException("No more elements left in heap");
        }
        return heap[1];
    }

    // delete the element from heap
    public int removeMin() {

        int result = peek();

        heap[1] = heap[numberOfElements];
        heap[numberOfElements] = Integer.MAX_VALUE;
        numberOfElements--;

        bubbleDown();

        return result;
    }

    // once the element has been removed fix the heap property
    public void bubbleDown() {
        int index = 1;

        // Proceed as long as I have left child
        while(hasLeftChild(index)) {
            int smallerChildIndex = leftChildIndex(index);

            // If my right child is smaller that my left child proceed with that index instead
            if (hasRightChild(index) &&
                    heap[rightChildIndex(index)] < heap[smallerChildIndex]) {
                smallerChildIndex = rightChildIndex(index);
            }

            // Now if the value at smaller child index is smaller than that of the current index (parent)
            // swap them
            if (heap[index] > heap[smallerChildIndex]) {
                swap(index, smallerChildIndex);
            } else {
                break;
            }

            index = smallerChildIndex;
        }
    }

    // returns the left child of the given element
    public int leftChildIndex(int i) {
        return i * 2;
    }

    // returns whether given index has left child or not
    public boolean hasLeftChild(int i) {
        return leftChildIndex(i) < heap.length;
    }

    // returns the right child of the given element
    public int rightChildIndex(int i) {
        return i * 2 + 1;
    }

    // returns whether given index has right child or not
    public boolean hasRightChild(int i) {
        return  rightChildIndex(i) < heap.length;
    }

    // returns the parent index of the given element
    public int parentIndex(int i) {
        return i / 2;
    }

    // returns the parent of the given element
    public int parent(int i) {
        return heap[parentIndex(i)];
    }

    // returns whether the given element has parent or not
    public boolean hasParent(int index) {
        return index > 1;
    }

    // swap elements at given indices
    public void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
