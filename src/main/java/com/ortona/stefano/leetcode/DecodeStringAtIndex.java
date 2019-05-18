package com.ortona.stefano.leetcode;

public class DecodeStringAtIndex {
  public String decodeAtIndex(String S, int K) {
    int curStringLen = 0;
    for (int i = 0; i < S.length(); i++) {
      if ((S.charAt(i) >= '0') && (S.charAt(i) <= '9')) {
        // it is a number
        if ((curStringLen * (S.charAt(i) - '0')) >= K) {
          return decodeAtIndex(S.substring(0, i), ((K - 1) % curStringLen) + 1);
        } else {
          curStringLen = curStringLen * (S.charAt(i) - '0');
        }
      } else {
        curStringLen++;
        if (curStringLen == K) {
          return S.charAt(i) + "";
        }
      }
    }
    // K is greater than the decoded string length, return empty
    return "";
  }

  public static void main(String[] args) {
    final DecodeStringAtIndex d = new DecodeStringAtIndex();
    System.out.println(d.decodeAtIndex("leet2code3", 36));
  }

}
