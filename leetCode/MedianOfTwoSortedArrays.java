package leetCode;

/**
 * Created by nachiketlondhe on 1/12/17.
 */
public class MedianOfTwoSortedArrays {

    public static void main(String args[]) {
        int arr1[] = new int[] {1,2,3,4,5,6,7,8,9};
        int arr2[] = new int[] {11,12,13,14,15,16,17,18};

        MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
        double median = solution.findMedianSortedArrays(arr1, arr2);
        System.out.println("Median of the given arrays is : " + median);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0.0;
    }
}
