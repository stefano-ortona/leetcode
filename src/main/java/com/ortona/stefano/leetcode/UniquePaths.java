package com.ortona.stefano.leetcode;

public class UniquePaths {
  public int uniquePaths(int m, int n) {
    if ((m == 1) || (n == 1)) {
      return 1;
    }
    return uniqueWays(Math.min(m, n) - 1, (n + m) - 2, new int[Math.min(m, n)][((n + m) - 2) + 1]);
  }

  private int uniqueWays(int count, int positionLeft, int[][] memory) {
    if (count == positionLeft) {
      return 1;
    }
    if (count == 1) {
      return positionLeft;
    }
    if (memory[count][positionLeft] != 0) {
      return memory[count][positionLeft];
    }
    int totCount = 0;
    for (int i = positionLeft - 1; i >= (count - 1); i--) {
      totCount += uniqueWays(count - 1, i, memory);
    }
    memory[count][positionLeft] = totCount;
    return totCount;
  }

  public static void main(String[] args) {
    final UniquePaths uP = new UniquePaths();
    System.out.println(uP.uniquePaths(1, 2));
  }

}
