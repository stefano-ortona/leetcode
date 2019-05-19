package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SmallestStringStartingFromLeaf {

  private List<Integer> smallest=null;
  public String smallestFromLeaf(TreeNode root) {
    smallest=null;
    smallestRecursive(root,new ArrayList<>(),0);
    return fromNumbersToString(smallest);
  }

  private String fromNumbersToString(List<Integer> numbers){
    StringBuilder outputString = new StringBuilder();
    for(int oneNumber:numbers){
      outputString.append((char)('a'+oneNumber));
    }
    return outputString.toString();
  }

  private void smallestRecursive(TreeNode curNode, List<Integer> curPath,int level){
    //small optimization: if the current node value is bigger than cur smallest, not worth to continue
    if(smallest!=null && smallest.size()>level && smallest.get(level)<curNode.val){
      return;
    }
    if(curNode!=null){
      curPath.add(0,curNode.val);
      if(curNode.left==null && curNode.right==null){
        //leaf node
        updateSmallest(curPath);
      }
      smallestRecursive(curNode.left,curPath,level+1);
      smallestRecursive(curNode.right,curPath,level+1);
      //remove from the list
      curPath.remove(0);
    }
  }

  private void updateSmallest(List<Integer> current){
    boolean currentIsSmaller=false;
    if(smallest==null){
      currentIsSmaller=true;
    } else {
      for(int i=0;i<Math.min(smallest.size(),current.size());i++){
        if(current.get(i)<smallest.get(i)){
          currentIsSmaller=true;
          break;
        }
        if(smallest.get(i)<current.get(i)){
          return;
        }
      }
      //update only if length is smaller
      if(current.size()<smallest.size()){
        currentIsSmaller=true;
      }
    }
    if(currentIsSmaller){
      List<Integer> newSmallest = new ArrayList<>();
      newSmallest.addAll(current);
      smallest=newSmallest;
    }
  }

  public static void main(String []args){
    SmallestStringStartingFromLeaf sS = new SmallestStringStartingFromLeaf();
    TreeNode n = new TreeNode(0);
    n.left=new TreeNode(1);
    n.right=new TreeNode(2);
    n.left.left=new TreeNode(3);
    n.left.right=new TreeNode(4);
    n.right.left=new TreeNode(3);
    n.right.right=new TreeNode(4);
    System.out.println(sS.smallestFromLeaf(n));
  }

}
