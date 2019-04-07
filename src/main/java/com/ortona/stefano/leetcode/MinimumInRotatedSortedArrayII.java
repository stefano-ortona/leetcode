package com.ortona.stefano.leetcode;

/**
 * Here, because of duplicates, we cannot use same approach of binary search used in MinimumRotatedSortedArray
 *
 * We based on the following information: if an array is ordered (first element less than last element), then its minimum is the first
 * element. Otherwise we look for the Min(left part, right part)
 *
 * In the normal case, we are still doing O(logn). In the worst case (all equal elements), it is O(n)
 *
 *
 * @author stefano
 *
 */
public class MinimumInRotatedSortedArrayII {

  public int findMin(int[] nums) {
    return findMinInArray(nums, 0, nums.length);
  }

  private int findMinInArray(int[] nums, int start, int end) {
    if ((end - start) == 1) {
      return nums[start];
    }
    if (nums[start] < nums[end - 1]) {
      // ordered array
      return nums[start];
    }
    return Math.min(findMinInArray(nums, start, start + ((end - start) / 2)),
        findMinInArray(nums, start + ((end - start) / 2), end));
  }

}
