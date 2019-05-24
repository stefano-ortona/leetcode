package com.ortona.stefano.leetcode;

public class LargestSumOfAverages {

  public double largestSumOfAverages(int[] A, int K) {
    final double[][] dp = new double[K + 1][A.length];
    final double[] sum = new double[A.length];
    sum[0] = A[0];
    dp[1][0] = A[0];
    for (int i = 1; i < A.length; i++) {
      sum[i] = sum[i - 1] + A[i];
      dp[1][i] = sum[i] / (i + 1);
    }
    for (int k = 2; k <= K; k++) {
      dp[k][0] = sum[0];
      for (int i = 0; i < A.length; i++) {
        for (int j = 0; j < i; j++) {
          final double curSum = dp[k - 1][j] + ((sum[i] - sum[j]) / (i - j));
          dp[k][i] = Math.max(dp[k][i], curSum);
        }
      }
    }
    return dp[K][A.length - 1];
  }

  public static void main(String[] args) {
    final LargestSumOfAverages lS = new LargestSumOfAverages();
    System.out
        .println(lS.largestSumOfAverages(new int[] { 2561, 9087, 398, 8137, 7838, 7669, 8731, 2460, 1166, 619 }, 3));
  }

}
