package com.ortona.stefano.leetcode;

public class JumpGameII {

  public int jump(int[] nums) {
    int curMaxReached = 0;
    int nextMaxReached = 0;
    int currSteps = 0;
    for (int i = 0; i < (nums.length - 1); i++) {
      nextMaxReached = Math.max(nextMaxReached, nums[i] + i);
      if (nextMaxReached >= (nums.length - 1)) {
        return currSteps;
      }
      if (i == curMaxReached) {
        curMaxReached = nextMaxReached;
        currSteps++;
      }
    }
    return currSteps;
  }

  public static void main(String[] args) {
    System.out.println(new JumpGameII().jump(new int[] { 2, 3, 1, 1, 4 }));
  }

}
