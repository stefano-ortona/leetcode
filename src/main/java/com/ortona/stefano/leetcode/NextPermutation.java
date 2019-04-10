package com.ortona.stefano.leetcode;

import java.util.Arrays;

public class NextPermutation {
  public void nextPermutation(int[] nums) {
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        final int minIndex = findMinimumBiggerIndex(i, nums);
        final int temp = nums[minIndex];
        nums[minIndex] = nums[i];
        nums[i] = temp;
        moveNumber(minIndex, nums, i);
        reverseArray(nums, i + 1, nums.length - 1);
        return;
      }
    }
    // all numbers are in ascending orders, need to reverse the array
    reverseArray(nums, 0, nums.length - 1);
  }

  private void moveNumber(int index, int nums[], int end) {
    if ((index > end) && (nums[index] > nums[index - 1])) {
      while ((index > end) && (nums[index] > nums[index - 1])) {
        final int tmp = nums[index];
        nums[index] = nums[index - 1];
        nums[index - 1] = tmp;
        index--;
      }
    } else {
      if ((index < (nums.length - 1)) && (nums[index] < nums[index + 1])) {
        while ((index < (nums.length - 1)) && (nums[index] < nums[index + 1])) {
          final int tmp = nums[index];
          nums[index] = nums[index + 1];
          nums[index + 1] = tmp;
          index++;
        }
      }
    }
  }

  private int findMinimumBiggerIndex(int index, int nums[]) {
    final int curNum = nums[index];
    int minNumber = Integer.MAX_VALUE;
    int minIndex = -1;
    for (int i = index + 1; i < nums.length; i++) {
      if ((nums[i] > curNum) && (nums[i] < minNumber)) {
        minNumber = nums[i];
        minIndex = i;
      }
    }
    return minIndex;
  }

  private void reverseArray(int[] nums, int start, int end) {
    int low = start;
    int high = end;
    while (low < high) {
      final int temp = nums[low];
      nums[low] = nums[high];
      nums[high] = temp;
      low++;
      high--;
    }
  }

  public static void main(String[] args) {
    final NextPermutation nextPermut = new NextPermutation();
    final int[] number = new int[] { 2, 2, 4, 0, 1, 2, 4, 4, 0 };
    nextPermut.nextPermutation(number);
    System.out.println(Arrays.toString(number));
  }

}
