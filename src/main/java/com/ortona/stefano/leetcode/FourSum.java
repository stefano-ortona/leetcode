package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    final List<List<Integer>> allQuadruples = new ArrayList<>();
    Arrays.sort(nums);
    final Map<Integer, List<Sum>> sum2components = new HashMap<>();
    for (int i = 0; i < (nums.length - 1); i++) {
      for (int j = 0; j < nums.length; j++) {
        final int curSum = nums[i] + nums[j];
        List<Sum> allSums = sum2components.get(curSum);
        if (allSums == null) {
          allSums = new ArrayList<>();
          sum2components.put(curSum, allSums);
        }
        allSums.add(new Sum(nums[i], nums[j], i, j));
      }
    }
    int i = 0;
    while (i < (nums.length - 1)) {
      int j = i + 1;
      while (j < nums.length) {
        final int curSum = nums[i] + nums[j];
        final List<Sum> allSums = sum2components.get(target - curSum);
        if (allSums != null) {
          final Set<Integer> seenAsThird = new HashSet<>();
          for (final Sum oneSum : allSums) {
            if ((oneSum.posA > j) && (oneSum.posB > oneSum.posA) && !seenAsThird.contains(oneSum.a)) {
              allQuadruples.add(Arrays.asList(nums[i], nums[j], oneSum.a, oneSum.b));
              seenAsThird.add(oneSum.a);
            }
          }
        }
        // iterate till next j
        final int curJ = nums[j];
        while ((j < nums.length) && (nums[j] == curJ)) {
          j++;
        }
      }
      // iterate till next i
      final int curI = nums[i];
      while ((i < (nums.length - 1)) && (nums[i] == curI)) {
        i++;
      }
    }
    return allQuadruples;

  }

  private class Sum {
    int a;
    int b;
    int posA;
    int posB;

    public Sum(int a, int b, int posA, int posB) {
      this.a = a;
      this.b = b;
      this.posA = posA;
      this.posB = posB;
    }

    @Override
    public String toString() {
      return a + "," + b + "(" + posA + "," + posB + ")";
    }
  }

  public static void main(String[] args) {
    final FourSum sol = new FourSum();
    System.out.println(sol.fourSum(new int[] { -3, -2, -1, 0, 0, 1, 2, 3 }, 0));
  }
}
