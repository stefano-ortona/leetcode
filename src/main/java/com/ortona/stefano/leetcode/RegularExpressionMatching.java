package com.ortona.stefano.leetcode;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 */
public class RegularExpressionMatching {

  public boolean isMatch(String s, String p) {
    return isMatch(s, p, 0, 0);
  }

  private boolean isMatch(String s, String p, int curSIndex, int curPIndex) {
    final boolean isSFinished = s.length() == curSIndex;
    final boolean isPFiinished = p.length() == curPIndex;
    // check next character is star, needs to advance
    if ((curPIndex < (p.length() - 1)) && (p.charAt(curPIndex + 1) == '*')) {
      return isMatch(s, p, curSIndex, curPIndex + 1);
    }
    if (isPFiinished && isSFinished) {
      return true;
    }
    // if pattern is finished, then it is a non match
    if (curPIndex == p.length()) {
      return false;
    }
    // none of the two is finished, keep checking
    // normal character or .
    if (p.charAt(curPIndex) != '*') {
      if (!matchChar(s, curSIndex, p.charAt(curPIndex))) {
        return false;
      }
      return isMatch(s, p, curSIndex + 1, curPIndex + 1);
    }
    // special char *
    boolean isMatch = false;
    // do not match char and discard *
    isMatch = isMatch || isMatch(s, p, curSIndex, curPIndex + 1);
    // match char and discard *
    isMatch = isMatch
        || (matchChar(s, curSIndex, p.charAt(curPIndex - 1)) && isMatch(s, p, curSIndex + 1, curPIndex + 1));
    // match char and keep *
    isMatch = isMatch || (matchChar(s, curSIndex, p.charAt(curPIndex - 1)) && isMatch(s, p, curSIndex + 1, curPIndex));
    return isMatch;
  }

  private boolean matchChar(final String s, final int index, final char c) {
    return (index < s.length()) && ((c == '.') || (s.charAt(index) == c));
  }

}
