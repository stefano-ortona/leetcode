package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SubstringwithConcatenationofAllWordsTest {

  private static SubstringwithConcatenationofAllWords SUBSTRING;

  @BeforeClass
  public static void bringUp() {
    SUBSTRING = new SubstringwithConcatenationofAllWords();
  }

  @Test
  public void isMatchTestOne() {
    final String s = "barfoothefoobarman";
    final String[] words = new String[] { "foo", "bar" };
    final List<Integer> output = new ArrayList<>();
    output.add(0);
    output.add(9);
    final List<Integer> actualOutput = SUBSTRING.findSubstring(s, words);
    Collections.sort(actualOutput);
    Assert.assertEquals(actualOutput, output);
  }

  @Test
  public void isMatchTestTwo() {
    final String s = "wordgoodstudentgoodword";
    final String[] words = new String[] { "word", "student" };
    final List<Integer> output = new ArrayList<>();
    Assert.assertEquals(SUBSTRING.findSubstring(s, words), output);
  }

  @Test
  public void findSubstringTestThree() {
    final String s = "barfoofoobarthefoobarman";
    final String[] words = new String[] { "bar", "foo", "the" };
    final List<Integer> output = new ArrayList<>();
    output.add(6);
    output.add(9);
    output.add(12);
    final List<Integer> actualOutput = SUBSTRING.findSubstring(s, words);
    Collections.sort(actualOutput);
    Assert.assertEquals(actualOutput, output);
  }

}
