package com.ortona.stefano.leetcode;

import java.util.List;

public class WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    final boolean[] canBeBreak = new boolean[s.length() + 1];
    canBeBreak[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (canBeBreak[j] && wordDict.contains(s.substring(j, i))) {
          canBeBreak[i] = true;
          break;
        }
      }
    }
    return canBeBreak[s.length()];
  }

}
