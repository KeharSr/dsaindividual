

// Q6)

import java.util.Arrays;

public class ParallelMergeSort {

    // Function to merge two sorted arrays
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];  // Create a temporary array to hold merged values
        int i = left, j = mid + 1, k = 0;  // Initialize pointers for the left, right, and temp arrays

        // Merge the sorted subarrays into the temp array
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];  // Take the smaller element from the left subarray
            } else {
                temp[k++] = arr[j++];  // Take the smaller element from the right subarray
            }
        }

        // Copy any remaining elements from both subarrays to the temp array
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted values from the temp array back to the original array
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // Parallel merge sort function
    private static void parallelMergeSort(int[] arr, int left, int right, int depth) {
        if (left < right) {
            if (depth <= 1) {
                Arrays.sort(arr, left, right + 1);  // Use Arrays.sort for small subarrays
            } else {
                int mid = left + (right - left) / 2;  // Calculate the middle index
                
                // Create two threads for sorting left and right subarrays
                Thread leftThread = new Thread(() -> parallelMergeSort(arr, left, mid, depth - 1));
                Thread rightThread = new Thread(() -> parallelMergeSort(arr, mid + 1, right, depth - 1));
                
                // Start the threads
                leftThread.start();
                rightThread.start();
                
                try {
                    // Wait for both threads to finish
                    leftThread.join();
                    rightThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // Merge the sorted subarrays
                merge(arr, left, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        int depth = 2; // Number of threads for parallelism
        parallelMergeSort(arr, 0, arr.length - 1, depth);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
