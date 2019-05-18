package com.ortona.stefano.leetcode;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class PredictTheWinnerTest {
  private final PredictTheWinner sol = new PredictTheWinner();

  @Test
  public void testOneElement() {
    Assert.assertTrue(sol.predictTheWinner(new int[] { 5 }));
  }

  @Test
  public void testTwoElements() {
    Assert.assertTrue(sol.predictTheWinner(new int[] { 5, 7 }));
  }

  @Test
  public void testThreeElements() {
    Assert.assertFalse(sol.predictTheWinner(new int[] { 10, 20, 5 }));
    Assert.assertTrue(sol.predictTheWinner(new int[] { 1, 5, 4 }));
  }

  @Test
  public void testFourElements() {
    Assert.assertTrue(sol.predictTheWinner(new int[] { 1, 233, 233, 7 }));
    Assert.assertTrue(sol.predictTheWinner(new int[] { 1, 5, 233, 7 }));
  }

  @Test
  public void testTwentyElements() {
    final int[] elem = new int[20];
    final Random r = new Random();
    for (int i = 0; i < 20; i++) {
      elem[i] = r.nextInt(10000000);
    }
    Assert.assertTrue((10000000 * 20) < Integer.MAX_VALUE);
    sol.predictTheWinner(elem);
  }

}
