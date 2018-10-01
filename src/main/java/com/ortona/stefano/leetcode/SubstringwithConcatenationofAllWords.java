package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodstudentgoodword",
  words = ["word","student"]
Output: []

 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 * This solution is based on (almost) a brute force one. First discover all potentials start-end of the substring
 * (must start with one of the starting chars, must end with one of the ending chars). Then, for each start-end, check
 * the substring covers all input words. It can be heavily speed up
 *
 */
public class SubstringwithConcatenationofAllWords {

  public List<Integer> findSubstring(String s, String[] words) {
    final List<Integer> allStarts = new ArrayList<>();
    if (words.length == 0) {
      return allStarts;
    }
    final Map<Character, List<String>> initial2char = buildInitial2wordsMap(words);
    final Set<Character> initialChars = new HashSet<Character>();
    final Set<Character> endingChars = new HashSet<Character>();
    int totLength = 0;
    for (final String word : words) {
      totLength += word.length();
      initialChars.add(word.charAt(0));
      endingChars.add(word.charAt(word.length() - 1));
    }
    final List<StartEnd> allStartEnds = findStartEnd(initialChars, endingChars, totLength, s);
    for (final StartEnd oneStartEnd : allStartEnds) {
      if (containAllWords(s.substring(oneStartEnd.start, oneStartEnd.end), initial2char)) {
        allStarts.add(oneStartEnd.start);
      }
    }
    return allStarts;
  }

  private class StartEnd {
    int start;
    int end;

    public StartEnd(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  private List<StartEnd> findStartEnd(Set<Character> startChar, Set<Character> endChar, int totLength, String s) {
    final List<StartEnd> allStartEnds = new ArrayList<StartEnd>();
    for (final Character c : startChar) {
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == c) {
          // check right
          if (((s.length() - i) >= totLength) && endChar.contains(s.charAt((i + totLength) - 1))) {
            allStartEnds.add(new StartEnd(i, i + totLength));
          }
        }
      }
    }
    return allStartEnds;
  }

  private Map<Character, List<String>> buildInitial2wordsMap(String[] words) {
    final Map<Character, List<String>> initial2word = new HashMap<>();
    for (final String word : words) {
      final List<String> curWords = initial2word.getOrDefault(word.charAt(0), new ArrayList<>());
      curWords.add(word);
      initial2word.put(word.charAt(0), curWords);
    }
    return initial2word;
  }

  private boolean containAllWords(String s, Map<Character, List<String>> initial2words) {
    final Map<Character, List<String>> copy = new HashMap<>();
    initial2words.forEach((k, v) -> copy.put(k, new ArrayList<>(v)));
    while (true) {
      if (copy.isEmpty()) {
        return true;
      }
      // get current words
      final List<String> curWords = copy.get(s.charAt(0));
      if ((curWords == null) || curWords.isEmpty()) {
        return false;
      }
      final String nextTarget = s.substring(0, curWords.get(0).length());
      final Optional<String> word = curWords.stream().filter(w -> nextTarget.equals(w)).findFirst();
      if (word.isPresent()) {
        s = s.substring(word.get().length());
        curWords.remove(word.get());
        if (curWords.isEmpty()) {
          copy.remove(word.get().charAt(0));
        }
      } else {
        return false;
      }
    }
  }

}
