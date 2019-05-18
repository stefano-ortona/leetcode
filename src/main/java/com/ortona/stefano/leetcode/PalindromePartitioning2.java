package com.ortona.stefano.leetcode;

public class PalindromePartitioning2 {

  public int minCut(String s) {
    final char[] c = s.toCharArray();
    final boolean[][] pal = new boolean[s.length()][s.length()];
    final int[] cut = new int[s.length()];

    for (int i = 0; i < s.length(); i++) {
      int min = i;
      for (int j = 0; j <= i; j++) {
        if ((c[j] == c[i]) && (((j + 1) > (i - 1)) || pal[j + 1][i - 1])) {
          pal[j][i] = true;
          min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
        }
      }
      cut[i] = min;
    }
    return cut[s.length() - 1];
  }

  public static void main(String[] args) {
    final PalindromePartitioning2 pP2 = new PalindromePartitioning2();
    System.out.println(pP2.minCut("cabababcbc"));
  }

}
