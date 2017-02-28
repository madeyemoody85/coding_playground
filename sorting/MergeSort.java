package sorting;

/**
 * Created by nachiketlondhe on 2/25/17.
 */
public class MergeSort {

    private static int[] nums = new int[] {2,4,5,6,1,2,7,8,9,6,33,24,56,0};
    private static int[] helper = new int[nums.length];

    public static void main(String args[]) {
        mergeSort(0, nums.length-1);
        System.out.println("Sort this things ");
    }

    public static void mergeSort(int low, int high) {
        if (low < high) {
            int middle = low + (high-low)/2;
            mergeSort(low, middle);
            mergeSort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    public static void merge(int low, int middle, int high) {
        // Copy this part of the original array into helper
        for (int i = low; i <= high; i++ ) {
            helper[i] = nums[i];
        }

        // Now merge
        // Copy the smallest element on either side into original array
        int i  = low; // part 1
        int j = middle + 1; // part 2

        // new indexing for original array
        int k = low;

        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                nums[k] = helper[i];
                i++;
            } else {
                nums[k] = helper[j];
                j++;
            }
            k++;
        }

        // Now copy remaining right side
        while (i <= middle) {
            nums[k] = helper[i];
            k++;
            i++;
        }
    }
}
