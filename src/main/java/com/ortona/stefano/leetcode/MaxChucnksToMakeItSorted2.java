package com.ortona.stefano.leetcode;

public class MaxChucnksToMakeItSorted2 {
  public int maxChunksToSorted(int[] arr) {
    int []minReverse=new int[arr.length];
    //each element i, represents the min value from end of array to i included
    minReverse[arr.length-1]=arr[arr.length-1];
    for(int i=arr.length-2;i>0;i--){
      minReverse[i]=Math.min(minReverse[i+1],arr[i]);
    }
    int curMax=arr[0];
    int curPartitions=1;
    for(int i=0;i<arr.length-1;i++){
      curMax=Math.max(curMax,arr[i]);
      if(curMax<=minReverse[i+1]){
        curPartitions++;
      }
    }
    return curPartitions;
  }
  
  public static void main(String []args){
    MaxChucnksToMakeItSorted2 mC = new MaxChucnksToMakeItSorted2();
    System.out.println(mC.maxChunksToSorted(new int[]{1,1,1,0,1,0,0,0,1,0}));
  }

}
