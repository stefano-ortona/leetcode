package com.ortona.stefano.leetcode;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class LongestValidParenthesesTest {
  private static LongestValidParentheses LONGEST;

  @BeforeClass
  public static void bringUp() {
    LONGEST = new LongestValidParentheses();
  }

  @Test
  public void longestValidParenthesesTestOne() {
    final String input = "(()";
    Assert.assertEquals(LONGEST.longestValidParentheses(input), 2);
  }

  @Test
  public void longestValidParenthesesTestTwo() {
    final String input = ")()())";
    Assert.assertEquals(LONGEST.longestValidParentheses(input), 4);
  }

  @Test
  public void longestValidParenthesesTestThree() {
    final String input = "()())(((((()))))()()(";
    Assert.assertEquals(LONGEST.longestValidParentheses(input), 14);
  }

  @Test
  public void longestValidParenthesesTestNoValid() {
    final String input = "((((((";
    Assert.assertEquals(LONGEST.longestValidParentheses(input), 0);
  }

  @Test
  public void longestValidParenthesesTestOnlyValid() {
    final String input = "(((())))";
    Assert.assertEquals(LONGEST.longestValidParentheses(input), 8);
  }

}
