package com.ortona.stefano.leetcode;

import java.util.Arrays;
import java.util.Collection;

/**
 * There's a staircase with N steps, and you can climb 1 or 2 steps at a time. Given N, write a function that returns the number of unique ways you can climb the staircase.
 * The order of the steps matters.
 * @author stefano
 *
 */
public class ClimbStairs {

  public int climbWays(int n, Collection<Integer> differentSteps) {
    final int[] allWays = new int[n + 1];
    allWays[0] = 1;
    for (int i = 1; i <= n; i++) {
      for (final int oneWay : differentSteps) {
        if ((i - oneWay) >= 0) {
          allWays[i] += allWays[i - oneWay];
        }
      }
    }
    return allWays[n];
  }

  public static void main(String[] args) {
    System.out.println(new ClimbStairs().climbWays(4, Arrays.asList(1, 2)));
  }

}
