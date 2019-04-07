package com.ortona.stefano.leetcode;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 * Current solution is O(n*n)
 *
 * It visits all indexes from 0 to n-1, where n is the length of the input string
 * For each start index i, it then computes the longest valid substring starting at i.
 * When computing the longest substring, the algorithm can stop as soon as the sequence
 * becomes invalid, which means the count of ')' - count of '(' seen so far becomes
 * negative. At that point all sequent substring starting at i will be invalid.
 *
 * Further optimization: the algorithm can stop when the current max len substring
 * is greater or equal than s.length() - i
 *
 * Avergae case is better than n square, and memory space is O(1)
 *
 * Optimise version: make a first pass on the string, and split the string into multiple
 * ones. The split is given by all those indexes where the sum of '(' minus ')' is negative.
 *
 * For each string split s, compute sum of all '(' and ')'. If the total sum is 0, then len(s)
 * is a valid substring, otherwise start from the beginning of the string and remove the first
 * starting string where the sum of '(' minus ')' is equal to m. The remaining length of the string
 * is a valid candidate. Return the longest valid candidate
 *  Run Time= O(n) (first pass is n, then for each split string we need to pass twice over the string, first
 *  to compute the totalSum, second to remove the first prefix)
 *
 */
public class LongestValidParentheses {
  public int longestValidParentheses(String s) {
    int maxLen = 0;
    for (int i = 0; i < (s.length() - 1); i++) {
      if (maxLen >= (s.length() - i)) {
        // cannot do better than this
        break;
      }
      int curSum = 0;
      // can only start with ')' - compute all
      for (int j = i; j < s.length(); j++) {
        if (s.charAt(j) == ')') {
          if (curSum == 1) {
            maxLen = Math.max((j - i) + 1, maxLen);
          }
          if (curSum == 0) {
            // invalid sequence - continue from j+1
            i = j;
            break;
          }
          curSum -= 1;
        } else {
          curSum += 1;
        }
      }
    }
    return maxLen;
  }
}
