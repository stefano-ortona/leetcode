package com.ortona.stefano.leetcode;

/**
 * In order to find the minimum, we need to find the pivot number (i.e, the number where the array was rotated)
 * A pivot number residing at position i has the property: nums[i]<nums[i-1] AND nums[i]<nums[i+1]
 *
 * We look for the Pivot using binary search, and we keep looking in the array that is not ordered (i.e., an array that is rotated)
 *
 * Since to check the Pivot case we need at least 3 numbers in the array, special treatment must be taken when the array contains one or
 * two elements.In such case, the minimum is just the single element or the minimum between the 3 elements
 *
 *
 * @author Stefano Ortona <stefano.ortona@meltwater.com>
 *
 */
public class MinimumInRotatedSortedArray {

  public int findMin(int[] nums) {
    if (isOrderedArray(nums, 0, nums.length)) {
      return nums[0];
    }
    return binarySearchPivot(nums, 0, nums.length);
  }

  public int binarySearchPivot(int[] nums, int start, int end) {
    if ((end - start) == 1) {
      return nums[start];
    }
    if ((end - start) == 2) {
      return Math.min(nums[start], nums[start + 1]);
    }
    // get the middle number
    final int curPivot = start + ((end - start) / 2);
    if ((nums[curPivot] < nums[curPivot - 1]) && (nums[curPivot] < nums[curPivot + 1])) {
      return nums[curPivot];
    }
    if (!isOrderedArray(nums, start, curPivot)) {
      return binarySearchPivot(nums, start, curPivot);
    }
    if (!isOrderedArray(nums, curPivot + 1, end)) {
      return binarySearchPivot(nums, curPivot + 1, end);
    }
    return Math.min(Math.min(nums[curPivot], nums[curPivot - 1]), nums[curPivot + 1]);
  }

  boolean isOrderedArray(int[] nums, int start, int end) {
    return ((end - start) == 1) || (nums[end - 1] > nums[start]);
  }

  boolean isOrderedArrayWithRepetition(int[] nums, int start, int end) {
    return ((end - start) == 1) || (nums[end - 1] > nums[start]);
  }

}
