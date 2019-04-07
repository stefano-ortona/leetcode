package com.ortona.stefano.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LowestCommonAncestorOfBinaryTreeTest {

  private TreeNode tree;
  private final LowestCommonAncestorOfBinaryTree ancestor = new LowestCommonAncestorOfBinaryTree();

  @Before
  public void bringUp() {
    tree = new TreeNode(3);
    tree.left = new TreeNode(5);
    tree.left.left = new TreeNode(6);
    tree.left.right = new TreeNode(2);
    tree.left.right.left = new TreeNode(7);
    tree.left.right.right = new TreeNode(4);

    tree.right = new TreeNode(1);
    tree.right.left = new TreeNode(0);
    tree.right.right = new TreeNode(8);
  }

  @Test
  public void testLowestCommonAncestor() {
    Assert.assertTrue(ancestor.lowestCommonAncestor(tree, new TreeNode(5), new TreeNode(1)).val == 3);
    Assert.assertTrue(ancestor.lowestCommonAncestor(tree, new TreeNode(5), new TreeNode(5)).val == 5);
    Assert.assertTrue(ancestor.lowestCommonAncestor(tree, new TreeNode(4), new TreeNode(8)).val == 3);
    Assert.assertTrue(ancestor.lowestCommonAncestor(tree, new TreeNode(5), new TreeNode(2)).val == 5);
  }

  @Test
  public void testLowestCommonAncestorWithFatherInformation() {
    Assert.assertTrue(
        ancestor.lowestCommonAncestorWithFatherInformation(tree, new TreeNode(5), new TreeNode(1)).val == 3);
    Assert.assertTrue(
        ancestor.lowestCommonAncestorWithFatherInformation(tree, new TreeNode(5), new TreeNode(5)).val == 5);
    Assert.assertTrue(
        ancestor.lowestCommonAncestorWithFatherInformation(tree, new TreeNode(4), new TreeNode(8)).val == 3);
    Assert.assertTrue(
        ancestor.lowestCommonAncestorWithFatherInformation(tree, new TreeNode(5), new TreeNode(2)).val == 5);
  }

}
