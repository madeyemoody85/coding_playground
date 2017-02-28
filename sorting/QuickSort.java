package sorting;

/**
 * Created by nachiketlondhe on 2/25/17.
 */
public class QuickSort {

    public static void main(String args[]) {
        int[] nums = new int[] {2,4,5,6,1,2,7,8,9,6,33,24,56,0};
        sort(0, nums.length-1, nums);
        System.out.println("Sort this things ");
    }

    public static void sort(int low, int high, int[] numbers) {
        int i = low;
        int j = high;
        // Get the pivor
        int pivot = numbers[low + (high-low)/2];

        while(i <= j) {
            // Find all the smaller numbers than pivot
            while (numbers[i] < pivot) i++;
            // Find all the greater numbers than pivot
            while (numbers[j] > pivot) j++;
            // swap the mismatched positions
            if (i <= j) swap(i, j, numbers);
        }

        if (low < j) sort(low, j, numbers);
        if (i < high) sort(i, high, numbers);
    }

    private static void swap(int i, int j, int[] numbers) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
