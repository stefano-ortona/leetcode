package com.ortona.stefano.leetcode;

public class NumberofEnclaves {
  private boolean touchBorder;
  private int curCount;

  public int numEnclaves(int[][] A) {
    int totEnclaves = 0;
    final boolean[][] visited = new boolean[A.length][A[0].length];
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A[0].length; j++) {
        curCount = 0;
        touchBorder = false;
        exploreEnclave(i, j, A, visited);
        if (!touchBorder) {
          totEnclaves += curCount;
        }
      }
    }
    return totEnclaves;
  }

  private void exploreEnclave(int i, int j, int[][] A, boolean[][] visited) {
    if ((i >= A.length) || (i < 0) || (j >= A[0].length) || (j < 0)) {
      touchBorder = true;
      return;
    }
    if ((A[i][j] == 1) && !visited[i][j]) {
      curCount++;
      visited[i][j] = true;
      exploreEnclave(i + 1, j, A, visited);
      exploreEnclave(i, j + 1, A, visited);
      exploreEnclave(i - 1, j, A, visited);
      exploreEnclave(i, j - 1, A, visited);
    }
  }

  public static void main(String[] args) {
    final NumberofEnclaves nE = new NumberofEnclaves();
    System.out.println(nE.numEnclaves(new int[][] { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }));
  }

}
