package com.ortona.stefano.leetcode;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    final int[] dp = new int[nums.length];
    int right = 0;
    for (final int one : nums) {
      final int index = binarySearch(dp, 0, right, one);
      dp[index] = one;
      if (index == right) {
        right++;
      }
    }
    System.out.println(Arrays.toString(dp));
    return right;
  }

  private int binarySearch(int[] nums, int start, int end, int target) {
    if (start == end) {
      return start;
    }
    if (target < nums[start]) {
      return start;
    }
    if (target > nums[end - 1]) {
      return end;
    }
    final int middleIndex = start + ((end - start) / 2);
    if (nums[middleIndex] == target) {
      return middleIndex;
    }
    if (nums[middleIndex] > target) {
      return binarySearch(nums, start, middleIndex, target);
    }
    return binarySearch(nums, middleIndex + 1, end, target);
  }

  public static void main(String[] args) {
    final int input[] = new int[] { 10, 9, 2, 5, 3, 7, 101, 18 };
    System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(input));

  }

}
