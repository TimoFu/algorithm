package Sorting;


class QuickSort {
	public int findKthLargest(int[] nums, int k) {
		return quickSort(nums, 0, nums.length - 1, k);
	}

	private int quickSort(int[] nums, int start, int end, int k){
		//System.out.println("nums=" + Arrays.toString(nums) + " start=" + start + " end=" + end + " k=" + k);
		if (start == end){
			return nums[start];
		}

		int left = start, right = end;

		int privot = nums[(start + end) / 2];

		while (left <= right){
			while (left <= end && nums[left] > privot){
				left ++;
			}

			while (right >= left && nums[right] < privot){
				right --;
			}

			if (left <= right){
				int temp = nums[left];
				nums[left] = nums[right];
				nums[right] = temp;
				left ++;
				right --;
			}
		}

		if (right >= start && right - start + 1 >= k){
			return quickSort(nums, start, right, k);
		}else if (left <= end && left - start < k){
			return quickSort(nums, left, end, k - (left - start));
		}
		return nums[right + 1];
	}
}
