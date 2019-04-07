package com.ortona.stefano.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class NumberOfDigitOneTest {

  private final NumberOfDigitOne number = new NumberOfDigitOne();

  @Test
  public void testcountDigitOne() {
    Assert.assertEquals(number.countDigitOne(99), number.bruteForce(99));
  }

}
