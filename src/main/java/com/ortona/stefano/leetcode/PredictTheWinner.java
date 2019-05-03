package com.ortona.stefano.leetcode;

public class PredictTheWinner {

  public boolean predictTheWinner(int[] nums) {
    int remainingScore = 0;
    for (int i = 0; i < nums.length; i++) {
      remainingScore += nums[i];
    }
    return canWin(nums, 0, nums.length - 1, 0, 0, remainingScore);
  }

  private boolean canWin(int[] scores, int i, int j, int firstScore, int secondScore, int remainingScore) {
    if (firstScore >= (secondScore + remainingScore)) {
      return true;
    }
    if ((secondScore > (firstScore + remainingScore)) || (i > j)) {
      return false;
    }
    if (i == j) {
      // only one move
      return (firstScore + scores[i]) >= secondScore;
    }
    // make a move, it has to win no matter what the other players play
    return (canWin(scores, i + 1, j - 1, firstScore + scores[i], secondScore + scores[j],
        remainingScore - scores[i] - scores[j])
        && canWin(scores, i + 2, j, firstScore + scores[i], secondScore + scores[i + 1],
            remainingScore - scores[i] - scores[i + 1]))
        || (canWin(scores, i + 1, j - 1, firstScore + scores[j], secondScore + scores[i],
            remainingScore - scores[i] - scores[j])
            && canWin(scores, i, j - 2, firstScore + scores[j], secondScore + scores[j - 1],
                remainingScore - scores[j] - scores[j - 1]));
  }

}
