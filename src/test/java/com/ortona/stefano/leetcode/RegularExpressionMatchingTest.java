package com.ortona.stefano.leetcode;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegularExpressionMatchingTest {

  private static RegularExpressionMatching MATCHING;

  @BeforeClass
  public static void bringUp() {
    MATCHING = new RegularExpressionMatching();
  }

  @Test
  public void isMatchTestOne() {
    final String input = "aa";
    final String pattern = "a";
    Assert.assertFalse(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestTwo() {
    final String input = "aa";
    final String pattern = "a*";
    Assert.assertTrue(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestThree() {
    final String input = "ab";
    final String pattern = ".*";
    Assert.assertTrue(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestFour() {
    final String input = "aab";
    final String pattern = "c*a*b";
    Assert.assertTrue(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestFive() {
    final String input = "mississippi";
    final String pattern = "mis*is*p*.";
    Assert.assertFalse(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestSix() {
    final String input = "a";
    final String pattern = "ab*";
    Assert.assertTrue(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestBothEmpty() {
    final String input = "";
    final String pattern = "";
    Assert.assertTrue(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestInputEmpty() {
    final String input = "";
    final String pattern = "aa";
    Assert.assertFalse(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestPatternEmpty() {
    final String input = "aa";
    final String pattern = "";
    Assert.assertFalse(MATCHING.isMatch(input, pattern));
  }

  @Test
  public void isMatchTestLongRuntime() {
    final String input = "baccbbcbcacacbbc";
    final String pattern = "c*.*b*c*ba*b*b*.a*";
    Assert.assertTrue(MATCHING.isMatch(input, pattern));
  }

}
