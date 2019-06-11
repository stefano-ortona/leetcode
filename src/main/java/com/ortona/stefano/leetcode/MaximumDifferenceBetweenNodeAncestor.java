package com.ortona.stefano.leetcode;

public class MaximumDifferenceBetweenNodeAncestor {
  int maxDiff;

  public int maxAncestorDiff(TreeNode root) {
    maxDiff = 0;
    findDifference(root, root.val, root.val);
    return maxDiff;
  }

  private void findDifference(TreeNode node, int curMax, int curMin) {
    if (node != null) {
      final int curDiff = Math.max(Math.abs(node.val - curMax), Math.abs(node.val - curMin));
      maxDiff = Math.max(curDiff, maxDiff);
      findDifference(node.left, Math.max(curMax, node.val), Math.min(curMin, node.val));
      findDifference(node.right, Math.max(curMax, node.val), Math.min(curMin, node.val));
    }
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

}
