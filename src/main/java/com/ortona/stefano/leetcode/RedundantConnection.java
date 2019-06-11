package com.ortona.stefano.leetcode;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

public class RedundantConnection {
  public int[] findRedundantDirectedConnection(int[][] edges) {
    final Map<Integer, Integer> node2parent = new HashMap<>();
    int[] firstDoubleParent = null;
    int[] secondDoubleParent = null;

    for (final int[] oneEdge : edges) {
      if (node2parent.containsKey(oneEdge[1])) {
        firstDoubleParent = new int[] { node2parent.get(oneEdge[1]), oneEdge[1] };
        secondDoubleParent = oneEdge;
        break;
      } else {
        node2parent.put(oneEdge[1], oneEdge[0]);
      }
    }
    node2parent.clear();
    for (final int[] oneEdge : edges) {
      final int p1 = findRoot(oneEdge[0], node2parent);
      final int p2 = findRoot(oneEdge[1], node2parent);
      if (p1 == p2) {
        return firstDoubleParent == null ? oneEdge : firstDoubleParent;
      } else {
        node2parent.put(p1, p2);
      }
    }
    return secondDoubleParent;
  }

  private int findRoot(int el, Map<Integer, Integer> node2root) {
    node2root.putIfAbsent(el, el);
    while (node2root.get(el) != el) {
      el = node2root.get(el);
    }
    return el;
  }

  public static void main(String[] args) {
    final int[][] edges = new int[][] { { 2, 1 }, { 3, 1 }, { 4, 2 }, { 1, 4 } };
    System.out.println(ArrayUtils.toString(new RedundantConnection().findRedundantDirectedConnection(edges)));
  }

}
