package com.ortona.stefano.leetcode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class WordFilter {
  private final TreeNode prefixTree = new TreeNode();
  private final TreeNode suffixTree = new TreeNode();

  public WordFilter(String[] words) {
    for (int i = 0; i < words.length; i++) {
      addNodeToTree(i, words[i], 0, prefixTree, true);
      addNodeToTree(i, words[i], words[i].length() - 1, suffixTree, false);
      prefixTree.words.add(i);
      suffixTree.words.add(i);
    }
  }

  public int f(String prefix, String suffix) {
    // navigate prefix tree
    final TreeNode currentPrefix = navigate(prefix, prefixTree, 0, false);
    final TreeNode currentSuffix = navigate(suffix, suffixTree, suffix.length() - 1, true);
    if ((currentPrefix == null) || (currentSuffix == null)) {
      return -1;
    }
    // check if there is a word with both prefix and suffix
    final Iterator<Integer> prefixIterator = currentPrefix.words.iterator();
    final Iterator<Integer> suffixIterators = currentSuffix.words.iterator();
    return getIntersection(prefixIterator, suffixIterators, prefixIterator.next(), suffixIterators.next());
  }

  private int getIntersection(Iterator<Integer> firstIterator, Iterator<Integer> secondIterator, int firstN,
      int secondN) {
    if (firstN == secondN) {
      return firstN;
    }
    if (firstN > secondN) {
      if (!firstIterator.hasNext()) {
        return -1;
      }
      return getIntersection(firstIterator, secondIterator, firstIterator.next(), secondN);
    }
    // second number greater
    if (!secondIterator.hasNext()) {
      return -1;
    }
    return getIntersection(firstIterator, secondIterator, firstN, secondIterator.next());
  }

  private TreeNode navigate(String s, TreeNode input, int curIndex, boolean backwards) {
    if ((curIndex >= s.length()) || (curIndex < 0)) {
      return input;
    }
    final TreeNode next = input.getChild(s.charAt(curIndex));
    if (next == null) {
      return null;
    }
    return navigate(s, next, backwards ? curIndex - 1 : curIndex + 1, backwards);
  }

  private void addNodeToTree(int wordIndex, String s, int i, TreeNode current, boolean prefix) {
    if ((i >= s.length()) || (i < 0)) {
      return;
    }
    final char curC = s.charAt(i);
    TreeNode nextOpt = current.getChild(curC);
    if (nextOpt == null) {
      final TreeNode next = new TreeNode();
      next.character = curC;
      current.addChild(next);
      nextOpt = next;
    }
    nextOpt.words.add(wordIndex);
    i = prefix ? i + 1 : i - 1;
    addNodeToTree(wordIndex, s, i, nextOpt, prefix);
  }

  private class TreeNode {
    char character;
    TreeSet<Integer> words = new TreeSet<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    private final TreeNode[] children = new TreeNode[26];

    public TreeNode getChild(char c) {
      return children[c - 'a'];
    }

    public void addChild(TreeNode child) {
      children[child.character - 'a'] = child;
    }

    @Override
    public String toString() {
      return this.character + "(" + this.children + ")";
    }
  }

  public static void main(String[] args) {
    final String[] words = new String[] { "abbbababbb", "baaabbabbb", "abababbaaa", "abbbbbbbba", "bbbaabbbaa",
        "ababbaabaa", "baaaaabbbb", "babbabbabb", "ababaababb", "bbabbababa" };
    final WordFilter wF = new WordFilter(words);
    System.out.println(wF.f("ab", "baaa"));
  }

}
