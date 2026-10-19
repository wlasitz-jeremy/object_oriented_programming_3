package sortUtils;

public class MergeSort {

	public static void mergeSort(Integer[] nums, int left, int right) {
		if (left >= right) {
			return;
		}
		
		int middle = left + (right - left) / 2;
		
		mergeSort(nums, left, middle);
		mergeSort(nums, middle + 1, right);
		
		merge(nums, left, middle, right);
	}
	
	private static void merge(Integer[] nums, int left, int middle, int right) {

		int leftSize = middle - left + 1;
		int rightSize = right - middle;
		
		int[] leftArray = new int[leftSize];
		int[] rightArray = new int[rightSize];
		
		for (int i = 0; i < leftSize; i++) {
			leftArray[i] = nums[left + i];
		}
		
		for (int j = 0; j < rightSize; j++) {
			rightArray[j] = nums[middle + 1 + j];
		}
		
		int i = 0;
		int j = 0;
		int k = left;
		
		while (i < leftSize && j < rightSize) {
			if (leftArray[i] >= rightArray[j]) {
				nums[k] = leftArray[i];
				i++;
			} else {
				nums[k] = rightArray[j];
				j++;
			}
			k++;
		}
		
        while (i < leftSize) {
            nums[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            nums[k] = rightArray[j];
            j++;
            k++;
        }
	}
}
