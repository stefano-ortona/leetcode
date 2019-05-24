package com.ortona.stefano.leetcode;

public class KthSmallestElementBST {

  private int counter;

  public int kthSmallest(TreeNode root, int k) {
    counter = 0;
    return visitInOrder(root, k);
  }

  private Integer visitInOrder(TreeNode current, int k) {
    if (current == null) {
      return null;
    }
    final Integer res = visitInOrder(current.left, k);
    if (res != null) {
      return res;
    }
    counter++;
    if (counter == k) {
      return current.val;
    }
    return visitInOrder(current.right, k);
  }

  private class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

}
