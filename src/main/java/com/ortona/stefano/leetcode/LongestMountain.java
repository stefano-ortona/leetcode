package com.ortona.stefano.leetcode;

public class LongestMountain {
  public int longestMountain(int[] A) {
    int mountainStartIndex = -1;
    boolean descending = false;
    int maxMountain = 0;
    for (int i = 0; i < (A.length - 1); i++) {
      if (A[i] < A[i + 1]) {
        if (mountainStartIndex == -1) {
          mountainStartIndex = i;
        } else if (descending) {
          // end of the mountain
          maxMountain = Math.max(maxMountain, (i - mountainStartIndex) + 1);
          mountainStartIndex = i;
        }
        descending = false;
      } else if (A[i] > A[i + 1]) {
        descending = true;
      } else {
        // i is equal to next element
        if (descending && (mountainStartIndex != -1)) {
          maxMountain = Math.max(maxMountain, (i - mountainStartIndex) + 1);
        }
        mountainStartIndex = -1;
        descending = false;
      }
    }
    // last mountain
    if (descending && (mountainStartIndex != -1)) {
      maxMountain = Math.max(maxMountain, A.length - mountainStartIndex);
    }
    return maxMountain;
  }

  public static void main(String[] args) {
    final LongestMountain m = new LongestMountain();
    System.out.println(m.longestMountain(new int[] { 1, 1, 0, 0, 1, 0 }));
  }

}
