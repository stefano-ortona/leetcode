package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.List;

public class DecodeStringAtIndex {
  public String decodeAtIndex(String S, int K) {
    final List<StringEncodedContainer> decodedCounting = new ArrayList<>();
    int curStringLen = 0;
    StringBuilder curString = new StringBuilder();
    for (int i = 0; i < S.length(); i++) {
      if ((S.charAt(i) >= '0') && (S.charAt(i) <= '9')) {
        // it is a number
        final int curRep = Integer.parseInt(S.charAt(i) + "");
        curStringLen += (curRep - 1) * curStringLen;
        decodedCounting.add(new StringEncodedContainer(curString.toString(), curRep));
        if (curStringLen >= K) {
          return findCharacter(decodedCounting, curStringLen, K);
        }
        curString = new StringBuilder();
      } else {
        curString.append(S.charAt(i));
        curStringLen++;
        if (curStringLen == K) {
          return S.charAt(i) + "";
        }
      }
    }
    // K is greater than the decoded string length, return empty
    return "";
  }

  private String findCharacter(List<StringEncodedContainer> counting, int len, int K) {

    return "";
  }

  private class StringEncodedContainer {
    private final String string;
    private final int repetition;

    public StringEncodedContainer(String s, int repetition) {
      this.string = s;
      this.repetition = repetition;
    }
  }

}
