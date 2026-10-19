package sortingAlgorithms;

public class MergeSort2 {

    public static void mergeSort(int[] arr, int left, int right) {
        // Base case: a range with 0 or 1 elements is already sorted
        if (left >= right) {
            return;
        }

        // Divide: find the midpoint
        int mid = left + (right - left) / 2;

        // Conquer: sort each half
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // Combine: merge the sorted halves
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Make temporary copies of the two sorted subarrays
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = arr[left + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        // Merge the temporary arrays back into arr[left..right]
        int i = 0;      // index in leftArray
        int j = 0;      // index in rightArray
        int k = left;   // index in arr

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements
        while (i < leftSize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] data = {38, 27, 43, 3, 9, 82, 10};

        mergeSort(data, 0, data.length - 1);

        for (int value : data) {
            System.out.print(value + " ");
        }
    }
}