package com.ortona.stefano.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ArrayOfDoubledPairsSecondSolution {
  public boolean canReorderDoubled(int[] A) {
    return reorderRecursive(0, A, new HashMap<>());
  }

  public boolean reorderRecursive(int curIndex, int []A, Map<Integer,Integer> occurence){
    if(curIndex>=A.length){
      return emptyMap(occurence);
    }
    Integer occDouble = occurence.get(A[curIndex]*2);
    if(A[curIndex]%2==0 && A[curIndex]!=0){
      boolean canReorder=false;
      //check there is double
      boolean halfAndDouble = true;
      if(occDouble!=null && occDouble>0){
        //try with double
        occurence.put(A[curIndex]*2, occDouble-1);
        canReorder=canReorder||reorderRecursive(curIndex+1, A, occurence);
        //backtrack
        occurence.put(A[curIndex]*2, occDouble+1);
      } else {
        halfAndDouble=false;
      }
      //check there is half
      Integer occHalf = occurence.get(A[curIndex]/2);
      if(occHalf!=null && occHalf>0){
        occurence.put(A[curIndex]/2, occHalf-1);
        canReorder=canReorder||reorderRecursive(curIndex+1, A, occurence);
        //backtrack
        occurence.put(A[curIndex]*2, occHalf+1);
      } else {
        halfAndDouble = false;
      }
      if(!halfAndDouble){
        //put the current number in the map
        Integer occ2 = occurence.getOrDefault(A[curIndex],0)+1;
        occurence.put(A[curIndex], occ2);
        canReorder=canReorder||reorderRecursive(curIndex+1, A, occurence);
        occurence.put(A[curIndex], occ2-1);
      }
      return canReorder;
    } else {
      boolean canReorder = false;
      if(occDouble!=null && occDouble>0){
        //remove it and keep going
        occurence.put(A[curIndex]*2, occDouble-1);
        canReorder = reorderRecursive(curIndex+1, A, occurence);
        //backtrack
        occurence.put(A[curIndex]*2, occDouble+1);
      } else {
        //put the current number in the map
        Integer occ2 = occurence.getOrDefault(A[curIndex],0)+1;
        occurence.put(A[curIndex], occ2);
        canReorder = canReorder || reorderRecursive(curIndex+1, A, occurence);
        occurence.put(A[curIndex], occ2-1);
      }
      return canReorder;
    }
  }

  private boolean emptyMap(Map<Integer,Integer> occurrence){
    for(int oneValue:occurrence.values()){
      if(oneValue>0){
        return false;
      }
    }
    return true;
  }
  
  public static void main(String [] args){
    ArrayOfDoubledPairsSecondSolution aA = new ArrayOfDoubledPairsSecondSolution();
    System.out.println(aA.canReorderDoubled(new int [] {-1,4,6,8,-4,6,-6,3,-2,3,-3,-8}));
  }


}
