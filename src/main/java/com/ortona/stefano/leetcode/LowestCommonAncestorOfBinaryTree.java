package com.ortona.stefano.leetcode;

import java.util.Stack;

/**
 * We suppose that for a tree node we do not have access to its father information,
 * therefore the tree can be navigated only top-down
 *
 * We suppose the input node p and q are always present in the tree, and that two nodes
 * are uniquely identify by their values
 *
 * Stefano Ortona <stefano.ortona@gmail.com>
 *
 * Complexity with O(1) of memory=O(n^2)
 * We return the first node p where one of the target node's ancestor in p.left, and the other
 * target node's ancestor is p.right
 *
 * If we can use extra memory (lowestCommonAncestorWithFatherInformation), then we can solve it in O(n)
 * We first build the list of ancestors of p and q (traverse all the tree - O(n))
 * Then we compare the two lists backwards, and we return the first equal node (we can use a stack for this)
 *
 * If we had access to the father information for each node, we can build a solution in O(logn)
 * Same solution as above, without building the list of ancestors though
 *
 */
public class LowestCommonAncestorOfBinaryTree {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if ((root.val == p.val) || (root.val == q.val)) {
      return root;
    }
    final boolean pLeftDescendant = isDescendant(root.left, p);
    final boolean qLeftDescendant = isDescendant(root.left, q);
    if (pLeftDescendant && qLeftDescendant) {
      return lowestCommonAncestor(root.left, p, q);
    }
    if (!pLeftDescendant && !qLeftDescendant) {
      return lowestCommonAncestor(root.right, p, q);
    }
    return root;
  }

  public TreeNode lowestCommonAncestorWithFatherInformation(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val == q.val) {
      return p;
    }
    // first build father information and depth of 2 target nodes
    final Stack<TreeNode> pAncestors = findAncestors(root, p, new Stack<>());
    final Stack<TreeNode> qAncestors = findAncestors(root, q, new Stack<>());
    Stack<TreeNode> biggerStack = pAncestors;
    if (pAncestors.size() < qAncestors.size()) {
      biggerStack = qAncestors;
    }
    popFirstElements(biggerStack, Math.abs(pAncestors.size() - qAncestors.size()));
    while (!pAncestors.isEmpty() && !qAncestors.isEmpty()) {
      final TreeNode curNode = pAncestors.pop();
      if (curNode.val == qAncestors.pop().val) {
        return curNode;
      }
    }
    // should never get here, means did not find a common element
    return null;
  }

  private void popFirstElements(Stack<TreeNode> stack, int numElements) {
    for (int i = 0; i < numElements; i++) {
      stack.pop();
    }
  }

  private Stack<TreeNode> findAncestors(TreeNode current, TreeNode target, Stack<TreeNode> curAncestors) {
    if (current == null) {
      return null;
    }
    curAncestors.add(current);
    if (current.val == target.val) {
      return curAncestors;
    }
    Stack<TreeNode> nextAncestors = findAncestors(current.left, target, curAncestors);
    if (nextAncestors == null) {
      nextAncestors = findAncestors(current.right, target, curAncestors);
    }
    if (nextAncestors == null) {
      curAncestors.pop();
      return null;
    }
    return nextAncestors;
  }

  private boolean isDescendant(TreeNode father, TreeNode child) {
    if (father == null) {
      return false;
    }
    if (father.val == child.val) {
      return true;
    }
    return isDescendant(father.left, child) || isDescendant(father.right, child);
  }

}
