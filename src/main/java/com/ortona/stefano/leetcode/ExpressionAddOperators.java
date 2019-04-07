package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
  public List<String> addOperators(String num, int target) {
    final List<String> allSolutions = new ArrayList<>();
    if (num.length() > 0) {
      nextValue(num, 0, new StringBuilder(), true, 0, 1, target, allSolutions, null);
    }
    return allSolutions;
  }

  public void nextValue(String num, long curSum, StringBuilder curString, boolean prevOpSum, int previousIndex,
      int index, int target, List<String> solutions, Long prevProduct) {
    long curNumber = -1;
    try {
      curNumber = Long.parseLong(num.substring(previousIndex, index));
    } catch (final Exception e) {
      // number is not valid, stop
      return;
    }
    final char curChar = num.charAt(index - 1);
    if (index >= num.length()) {
      if (addToSum(prevOpSum, num, index, previousIndex, prevProduct, curSum, curNumber) == target) {
        solutions.add(curString.toString() + curChar);
      }
    } else {
      final int builderLen = curString.length();
      // keep expanding the number only if first element is not zero
      if (num.charAt(previousIndex) != '0') {
        nextValue(num, curSum, curString.append(curChar), prevOpSum, previousIndex, index + 1, target, solutions,
            prevProduct);
        // re-set the string builder
        curString.setLength(builderLen);
      }
      // subtract
      nextValue(num, addToSum(prevOpSum, num, index, previousIndex, prevProduct, curSum, curNumber),
          curString.append(curChar).append("-"), false, index, index + 1, target, solutions, null);
      // re-set the string builder
      curString.setLength(builderLen);
      // add
      nextValue(num, addToSum(prevOpSum, num, index, previousIndex, prevProduct, curSum, curNumber),
          curString.append(curChar).append("+"), true, index, index + 1, target, solutions, null);
      // re-set the string builder
      curString.setLength(builderLen);
      // multiply
      nextValue(num, curSum, curString.append(curChar).append("*"), prevOpSum, index, index + 1, target, solutions,
          prevProduct == null ? curNumber : prevProduct * curNumber);
      // re-set the string builder
      curString.setLength(builderLen);
    }

  }

  private long addToSum(boolean prevOpSum, String num, int index, int prevIndex, Long prevProduct, long curSum,
      long curNum) {
    if (prevProduct != null) {
      curNum = (prevProduct * curNum);
    }
    if (prevOpSum) {
      return curSum + curNum;
    }
    return curSum - curNum;
  }

  public static void main(String[] args) {
    System.out.println(new ExpressionAddOperators().addOperators("105", 5));
  }

}
