package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {

  public List<List<String>> partition(String s) {
    final List<List<String>> allSolutions = new ArrayList<>();
    partitionRecursive(0, s, new LinkedList<>(), allSolutions);
    return allSolutions;
  }

  public void partitionRecursive(int index, String s, List<String> curPartition, List<List<String>> allSolutions) {
    if ((index >= s.length()) && !curPartition.isEmpty()) {
      final List<String> oneSolution = new ArrayList<>();
      oneSolution.addAll(curPartition);
      allSolutions.add(oneSolution);
    } else {
      final StringBuilder curString = new StringBuilder();
      for (int i = index; i < s.length(); i++) {
        curString.append(s.charAt(i));
        if (isPalindrome(curString)) {
          curPartition.add(curString.toString());
          partitionRecursive(i + 1, s, curPartition, allSolutions);
          curPartition.remove(curPartition.size() - 1);
        }
      }
    }
  }

  private boolean isPalindrome(StringBuilder s) {
    for (int i = 0; i < (s.length() / 2); i++) {
      if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    final PalindromePartitioning pP = new PalindromePartitioning();
    System.out.println(pP.partition("aab"));
  }

}
