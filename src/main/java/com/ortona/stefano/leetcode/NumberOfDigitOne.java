package com.ortona.stefano.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Brute force - generate all numbers from 1 and n, and check number of digits 1 in each number
 *
 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 */
public class NumberOfDigitOne {

  public int countDigitOne(int n) {
    int nextDigitNumber = 0;
    int before = 0;
    int curPow = 0;
    final String numberString = n + "";
    int totCount = 0;
    for (int i = numberString.length() - 1; i >= 0; i--) {
      before = countDigintNthDigit(Integer.parseInt(numberString.charAt(i) + ""), before, curPow, nextDigitNumber,
          new AtomicInteger(0));
      totCount += before;
      curPow++;
      nextDigitNumber = Integer.parseInt(numberString.substring(i, numberString.length()));
    }
    return totCount;
  }

  private int countDigintNthDigit(int digit, int before, int curPow, int nextDigitNumber,
      AtomicInteger missingToNextDigit) {
    int curSum = 0;
    if (digit > 1) {
      curSum = (int) Math.pow(10, curPow) + before;
    } else {
      curSum = nextDigitNumber + 1 + before;
    }
    // TODO MISSING NUMBER OF 1s TO REACH NEXT DIGIT - CAN BE COMPUTED AS ABOVE
    // example: like this, when 245 and I'm at last digit 2, I am counting from 100to199,
    // and then summing from 200to245. I am missing from 45to100
    return curSum + ((digit - 1) * before) + missingToNextDigit.get();
  }

  public int bruteForce(int n) {
    int totDigits = 0;
    for (int i = 1; i <= n; i++) {
      totDigits += countOneDigitInString(i + "");
    }
    return totDigits;
  }

  public int countOneDigitInString(String number) {
    int counter = 0;
    for (int i = 0; i < number.length(); i++) {
      if (number.charAt(i) == '1') {
        counter++;
      }
    }
    return counter;
  }
}
