package com.ortona.stefano.leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinimumInRotatedSortedArrayTest {

  MinimumInRotatedSortedArray minRotated = new MinimumInRotatedSortedArray();

  @Test
  public void testFindMin() {
    final int[] input = new int[] { 4, 5, 6, 7, 0, 1, 2 };
    assertEquals(0, minRotated.findMin(input));
  }

  @Test
  public void testFindMinNonRotatedArray() {
    final int[] input = new int[] { 0, 1, 2, 3, 4 };
    assertEquals(0, minRotated.findMin(input));
  }

  @Test
  public void testFindMinArrayTwoElements() {
    final int[] input = new int[] { 1, 0 };
    assertEquals(0, minRotated.findMin(input));
  }

  @Test
  public void testFindMinArrayOneElement() {
    final int[] input = new int[] { 0 };
    assertEquals(0, minRotated.findMin(input));
  }

  @Test
  public void testFindMin2() {
    final int[] input = new int[] { 3, 4, 5, 6, 7, 1, 2 };
    assertEquals(1, minRotated.findMin(input));
  }

  @Test
  public void testFindMin3() {
    final int[] input = new int[] { 2, 3, 1 };
    assertEquals(1, minRotated.findMin(input));
  }

}
