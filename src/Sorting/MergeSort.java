package Sorting;

public class MergeSort {
	public void mergeSort(int[] a, int[] aux) {
		for (int i = 0; i < a.length; i++) {
			aux[i] = a[i];
		}

		int low = 0;
		int high = a.length - 1;
		int mid = (low + high) / 2;
		for (int k = low; k < high; k++) {
			if (low > mid) {
				aux[k] = aux[high--];
			} else if (high < mid) {
				aux[k] = aux[low++];
			} else if (aux[low] <= aux[high]) {
				a[k] = aux[low++];
			} else {
				a[k] = aux[high--];
			}
		}
	}
}
