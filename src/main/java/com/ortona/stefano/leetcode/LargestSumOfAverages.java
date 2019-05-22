package com.ortona.stefano.leetcode;

public class LargestSumOfAverages {
  public double largestSumOfAverages(int[] A, int K) {
    int partAvailable=K-1;
    double sum=0;
    while(partAvailable>0){
      int maxIndex=getMaximum(A,partAvailable);
      sum+=A[maxIndex];
      partAvailable-=getPartitionIntroduced(A,maxIndex);
      A[maxIndex]=0;
    }
    //sum other partitions
    double curSum=0;
    int curEl=0;
    for(int i=0;i<A.length;i++){
      if(A[i]==0){
        if(curEl>0){
          sum+=(curSum/curEl);
        }
        curEl=0;
        curSum=0;
      } else {
        curEl++;
        curSum+=A[i];
      }
    }
    if(curEl>0){
      sum+=(curSum/curEl);
    }
    return sum;
  }

  private int getMaximum(int []A, int partitionAvailable){
    int max=-1;
    int maxIndex=-1;
    for(int i=0;i<A.length;i++){
      if(A[i]!=0 && A[i]>max && getPartitionIntroduced(A,i)<=partitionAvailable){
        max=A[i];
        maxIndex=i;
      }
    }
    return maxIndex;  
  }

  private int getPartitionIntroduced(int []A, int index){
    int part=0;
    if(index>0 && A[index-1]!=0){
      part++;
    }
    if(index<A.length-1 && A[index+1]!=0){
      part++;
    }
    return part;
  }
  
  public static void main(String []args){
    LargestSumOfAverages lS = new LargestSumOfAverages();
    System.out.println(lS.largestSumOfAverages(new int[]{5870,2722,6249,2196,8717}, 4));
  }

}
