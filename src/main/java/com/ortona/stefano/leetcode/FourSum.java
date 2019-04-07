package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FourSum {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> allQuadruples=new ArrayList<>();
    Arrays.sort(nums);
    Map<Integer,List<Sum>> sum2components = new HashMap<>();
    for(int i=0;i<nums.length-1;i++){
      for(int j=0;j<nums.length;j++){
        int curSum = nums[i]+nums[j];
        List<Sum> allSums = sum2components.get(curSum);
        if(allSums==null){
          allSums = new ArrayList<>();
          sum2components.put(curSum,allSums);
        }
        allSums.add(new Sum(nums[i],nums[j],i,j));
      }
    }
    int i=0;
    while(i<nums.length-1){
      int j=i+1;
      while(j<nums.length){
        int curSum = nums[i]+nums[j];
        List<Sum> allSums=sum2components.get(target-curSum);
        if(allSums!=null){
          for(Sum oneSum:allSums){
            if(oneSum.posA>j && oneSum.posB>oneSum.posA){
              allQuadruples.add(Arrays.asList(nums[i],nums[j],oneSum.a,oneSum.b));
            }
          }
        }
        //iterate till next j
        int curJ = nums[j];
        while(j<nums.length && nums[j]==curJ){
          j++;
        }
      }
      //iterate till next i
      int curI = nums[i];
      while(i<nums.length-1 && nums[i]==curI){
        i++;
      }
    }
    return allQuadruples;

  }

  private class Sum{
    int a;
    int b;
    int posA;
    int posB;
    public Sum(int a,int b,int posA,int posB){
      this.a=a;
      this.b=b;
      this.posA=posA;
      this.posB=posB;
    }
    public String toString(){
      return a+","+b+"("+posA+","+posB+")";
    }
  }

  public static void main(String []args){
    FourSum sol = new FourSum();
    System.out.println(sol.fourSum(new int[]{1,0,-1,0,-2,2}, 0));
  }
}
