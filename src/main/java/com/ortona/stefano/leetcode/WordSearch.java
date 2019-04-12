package com.ortona.stefano.leetcode;

public class WordSearch {
  public boolean exist(char[][] board, String word) {
    final boolean[][] used = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (exists(board, i, j, word, 0, used)) {
          return true;
        }
      }
    }
    return false;
  }

  boolean exists(char[][] board, int curI, int curJ, String word, int curIndex, boolean[][] used) {
    if (curIndex >= word.length()) {
      return true;
    }
    if (outsideBoard(board, curI, curJ) || (board[curI][curJ] != word.charAt(curIndex)) || used[curI][curJ]) {
      return false;
    }
    // include the current character in the solution, and try to move on
    used[curI][curJ] = true;
    final boolean existNext = exists(board, curI - 1, curJ, word, curIndex + 1, used)
        || exists(board, curI, curJ - 1, word, curIndex + 1, used)
        || exists(board, curI + 1, curJ, word, curIndex + 1, used)
        || exists(board, curI, curJ + 1, word, curIndex + 1, used);
    // backtrack
    used[curI][curJ] = false;
    return existNext;
  }

  private boolean outsideBoard(char[][] board, int i, int j) {
    return (i < 0) || (j < 0) || (i >= board.length) || (j >= board[0].length);
  }

  public static void main(String[] args) {
    final WordSearch wS = new WordSearch();
    System.out.println(
        wS.exist(new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED"));
  }

}
