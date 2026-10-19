package sortingAlgorithms;

public class MergeSort {

    // Main method that initiates the sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // 1. Find the midpoint
            int mid = left + (right - left) / 2;

            // 2. Recursively sort the first half
            mergeSort(arr, left, mid);

            // 3. Recursively sort the second half
            mergeSort(arr, mid + 1, right);

            // 4. Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Helper method to merge two sorted subarrays: arr[left...mid] and arr[mid+1...right]
    public static void merge(int[] arr, int left, int mid, int right) {
        // Determine sizes of the two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays for the left and right halves
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge the temp arrays back into arr[left...right]
        int i = 0, j = 0;      // Initial indices of first and second subarrays
        int k = left;          // Initial index of merged subarray

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any remain
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any remain
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Helper method to print the array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        int[] data = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Original Array:");
        printArray(data);

        // Call merge sort
        mergeSort(data, 0, data.length - 1);

        System.out.println("\nSorted Array:");
        printArray(data);
    }
}