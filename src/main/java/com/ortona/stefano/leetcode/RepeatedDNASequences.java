package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequences {
  public List<String> findRepeatedDnaSequences(String s) {
    final StringBuilder sB = new StringBuilder();
    final char[] c = s.toCharArray();
    for (int i = 0; i < 9; i++) {
      sB.append(c[i]);
    }
    final Map<String, Integer> s2occurence = new HashMap<>();
    final List<String> sol = new ArrayList<>();
    for (int i = 9; i < c.length; i++) {
      sB.append(c[i]);
      final String cur = sB.toString();
      final int curOc = s2occurence.getOrDefault(cur, 0) + 1;
      if (curOc == 2) {
        sol.add(cur);
      }
      s2occurence.put(cur, curOc);
      sB.deleteCharAt(0);
    }
    return sol;
  }

}
