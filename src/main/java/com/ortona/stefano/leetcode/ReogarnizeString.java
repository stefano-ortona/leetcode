package com.ortona.stefano.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReogarnizeString {
  public String reorganizeString(String S) {
    final Map<Character, Integer> char2occ = new HashMap<Character, Integer>();
    final char c[] = S.toCharArray();
    for (int i = 0; i < c.length; i++) {
      final int occ = char2occ.getOrDefault(c[i], 0) + 1;
      char2occ.put(c[i], occ);
    }
    final PriorityQueue<CharOccurrence> stack = new PriorityQueue<>(new Comparator<CharOccurrence>() {
      @Override
      public int compare(CharOccurrence o1, CharOccurrence o2) {
        return o2.occ - o1.occ;
      }
    });
    char2occ.keySet().forEach(chara -> stack.add(new CharOccurrence(chara, char2occ.get(chara))));
    if ((stack.peek().occ > ((c.length / 2) + 1)) || (((c.length % 2) == 0) && (stack.peek().occ > (c.length / 2)))) {
      return "";
    }
    final StringBuilder newString = new StringBuilder();
    Character lastChar = '$';
    while (!stack.isEmpty()) {
      final CharOccurrence first = stack.poll();
      if (first.occ > 0) {
        if (first.c == lastChar) {
          if (stack.peek().occ == 1) {
            newString.append(stack.poll().c);
          } else {
            newString.append(stack.peek().c);
            stack.peek().occ--;
          }
        }
        newString.append(first.c);
        first.occ--;
        if (first.occ > 0) {
          stack.add(first);
        }
        lastChar = first.c;
      }
    }
    return newString.toString();
  }

  private class CharOccurrence {
    char c;
    int occ;

    public CharOccurrence(char c, int occurence) {
      this.c = c;
      this.occ = occurence;
    }

    @Override
    public String toString() {
      return this.c + "(" + this.occ + ")";
    }
  }

  public static void main(String[] args) {
    final ReogarnizeString rS = new ReogarnizeString();
    System.out.println(rS.reorganizeString("bbbbayobq"));
  }

}
