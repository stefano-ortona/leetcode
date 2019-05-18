package com.ortona.stefano.leetcode;

import java.util.Arrays;

public class ArrayOfDoubledPairs {
  public boolean canReorderDoubled(int[] A) {
    Arrays.sort(A);
    return canReorderRecursive(A, 0, new boolean[A.length]);
  }
  
  public boolean canReorderRecursive(int[] A,int curIndex,boolean []used) {
    if(curIndex==A.length){
      return true;
    }
    if(used[curIndex]){
      return canReorderRecursive(A, curIndex+1, used);
    }
    used[curIndex]=true;
    int targetIndex = A[curIndex]>=0 ? contains(used, A, A[curIndex]*2,curIndex+1) : A[curIndex]%2==0 ? contains(used, A, A[curIndex]/2,curIndex+1) : -1;
    if(targetIndex>0){
      used[targetIndex]=true;
      return canReorderRecursive(A, curIndex+1, used);
    }
    return false;
  }
  
  private int contains(boolean []used,int []A, int target,int curIndex){
    int targetIndex = Arrays.binarySearch(A, curIndex, A.length, target);
    if(targetIndex<0 || !used[targetIndex]){
      return targetIndex;
    }
    //search in the sorrounding
    for(int i=targetIndex+1;i<A.length;i++){
      if(A[i]!=target){
        break;
      }
      if(!used[i]){
        return i;
      }
    }
    for(int i=targetIndex-1;i>=curIndex;i--){
      if(A[i]!=target){
        break;
      }
      if(!used[i]){
        return i;
      }
    }
    return -1;
  }
  
  public static void main(String []args){
    ArrayOfDoubledPairs aDP = new ArrayOfDoubledPairs();
    System.out.println(aDP.canReorderDoubled(new int []{-2,-4,2,4,8,16}));
  }
  
  

}
