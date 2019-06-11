package com.ortona.stefano.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
  public String largestNumber(int[] nums) {
    final String[] numberStrings = initialize(nums);
    Arrays.sort(numberStrings, new CompareNumber());
    boolean allZeros = true;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        allZeros = false;
        break;
      }
    }
    if (allZeros) {
      return "0";
    }
    final StringBuilder finalString = new StringBuilder();
    for (int i = 0; i < numberStrings.length; i++) {
      finalString.append(numberStrings[i]);
    }
    return finalString.toString();
  }

  private String[] initialize(int[] nums) {
    final String[] out = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      out[i] = nums[i] + "";
    }
    return out;
  }

  private class CompareNumber implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      final String first = o1 + o2;
      final String second = o2 + o1;
      for (int i = 0; i < first.length(); i++) {
        final int diff = (second.charAt(i) - '0') - (first.charAt(i) - '0');
        if (diff != 0) {
          return diff;
        }
      }
      return 0;
    }
  }

  public static void main(String[] args) {
    final int[] nums = new int[] { 2, 2281 };
    System.out.println(new LargestNumber().largestNumber(nums));
  }

}
