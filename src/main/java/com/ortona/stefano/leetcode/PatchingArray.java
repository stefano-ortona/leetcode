package com.ortona.stefano.leetcode;

public class PatchingArray {
  public int minPatches(int[] nums, int n) {
    long totalSum = 0;
    int curIndex = 0;
    int moves = 0;
    while (totalSum < n) {
      if ((curIndex >= nums.length) || (nums[curIndex] > (totalSum + 1))) {
        totalSum += totalSum + 1;
        moves++;
      } else {
        totalSum += nums[curIndex];
        curIndex++;
      }
    }
    return moves;
  }

  public static void main(String[] args) {
    final PatchingArray patch = new PatchingArray();
    System.out.println(patch.minPatches(new int[] { 1, 5, 10 }, 20));
  }

}
