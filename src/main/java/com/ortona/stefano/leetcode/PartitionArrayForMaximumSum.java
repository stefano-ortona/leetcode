package com.ortona.stefano.leetcode;

public class PartitionArrayForMaximumSum {

  public int maxSumAfterPartitioning(int[] A, int K) {
    final int[] dp = new int[A.length + 1];
    dp[0] = 0;
    for (int i = 1; i <= A.length; i++) {
      int curMax = -1;
      for (int j = 1; j <= K; j++) {
        if ((i - j) < 0) {
          break;
        }
        curMax = Math.max(A[i - j], curMax);
        dp[i] = Math.max(dp[i], dp[i - j] + (curMax * j));
      }
    }
    return dp[A.length];
  }

}
