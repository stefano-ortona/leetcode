package com.ortona.stefano.leetcode;

import java.util.Arrays;

public class MovingStonesUntilConsecutive2 {

  public int[] numMovesStonesII(int[] stones) {
    Arrays.sort(stones);
    int []solution=new int[2];
    int totGaps = 0;
    for(int i=0;i<stones.length-1;i++){
      totGaps+=stones[i+1]-stones[i]-1;
    }
    solution[0]=computeMin(stones);
    solution[1]=Math.max(totGaps-(stones[1]-stones[0]-1), totGaps-(stones[stones.length-1]-stones[stones.length-2]-1));
    return solution;
  }

  private int computeMin(int []stones){
    int startInterval=0;
    int min=stones.length+1;
    int gaps=0;
    int i=0;
    boolean iIncreased=false;
    while(i<stones.length){
      if(iIncreased){
        gaps+=stones[i]-stones[i-1]-1;
      } else {
        if(startInterval>0){
          gaps-=(stones[startInterval]-stones[startInterval-1]-1);
        }
      }
      int remaining=stones.length-(i-startInterval)-1;
      if(gaps==0){
        if(remaining==1){
          if((startInterval==1 && stones[1]-stones[0]==1) || (i==stones.length-2 && stones[stones.length-1]-stones[stones.length-2]==1)){
            min=Math.min(min, 1);
          } else {
            min=Math.min(min, 2);
          }
        } else {
          min=Math.min(remaining,min);
        }
        i++;
        iIncreased=true;
      } else {
        if(gaps<=remaining){
          min=Math.min(min, Math.max(gaps, remaining-1));
          i++;
          iIncreased=true;
        } else {
          //need to remove some gaps
          startInterval++;
          iIncreased=false;
        }
      }
    }
    return min;
  }

  public static void main(String []args){
    MovingStonesUntilConsecutive2 mS = new MovingStonesUntilConsecutive2();
    System.out.println(Arrays.toString(mS.numMovesStonesII(new int []{4,7,9})));
  }

}
