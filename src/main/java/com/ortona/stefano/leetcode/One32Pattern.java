package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.List;

public class One32Pattern {
  public boolean find132pattern(int[] nums) {
    List<Range> ranges = new ArrayList<>();
    Integer curMin=null;
    Integer curMax=null;
    for(int i=0;i<nums.length;i++){
      if(curMin==null || nums[i]<curMin){
        if(curMax==null){
          curMin = nums[i];
        } else {
          Range r = new Range(curMin,curMax);
          addRange(ranges, r);
          curMin=nums[i];
          curMax=null;
        }
      } else if(curMax==null||nums[i]>curMax){
        curMax = nums[i];
      }
      //check number is contained in one of the ranges
      if((curMax!=null && nums[i]>curMin && nums[i]<curMax) || containedInRange(ranges,nums[i])){
        return true;
      }
    }
    return false;
  }

  private boolean containedInRange(List<Range> ranges, int num){
    int smallerRangeIndex = findSmallerRangeIndex(0, ranges.size(), ranges, num);
    return smallerRangeIndex!=-1 && ranges.get(smallerRangeIndex).max>num && ranges.get(smallerRangeIndex).min<num;
  }

  private void addRange(List<Range> ranges, Range newRange){
    if(ranges.isEmpty()){
      ranges.add(newRange);
      return;
    }
    //binary search to insert range
    int indexRange = findSmallerRangeIndex(0, ranges.size(), ranges, newRange.min);
    if(indexRange==-1){
      indexRange=0;
      //special case
      Range firstRange = ranges.get(0);
      int tempMin = firstRange.min;
      int tempMax = firstRange.max;
      firstRange.min=newRange.min;
      firstRange.max=newRange.max;
      newRange.min=tempMin;
      newRange.max=tempMax;
    }
    Range smallerRange = ranges.get(indexRange);
    //disjoint
    if(smallerRange.max<newRange.min){
      ranges.add(indexRange+1,newRange);
    } else {
      smallerRange.min=Math.min(smallerRange.min, newRange.min);
      smallerRange.max=Math.max(smallerRange.max, newRange.max);
    }
  }

  //find range where the start of the range is <= given number
  private int findSmallerRangeIndex(int start, int end, List<Range> ranges, int number){
    if(ranges.isEmpty()){
      return -1;
    }
    int middle = start+((end-start)/2);
    Range midRange = ranges.get(middle);
    if(midRange.min==number || (midRange.min<number && (middle==ranges.size()-1 || ranges.get(middle+1).min>number))){
      return middle;
    }
    if(midRange.min>number && (middle==0 || ranges.get(middle-1).min<number)){
      return middle-1;
    }
    if(midRange.min<number){
      return findSmallerRangeIndex(middle+1, end, ranges, number);
    }
    return findSmallerRangeIndex(start, middle, ranges, number);
  }

  private class Range{
    int min;
    int max;
    public Range(int min, int max){
      this.min=min;
      this.max=max;
    }
  }

  public static void main(String []args){
    int []nums=new int[]{1,0,2,-4,-3,1};
    One32Pattern p = new One32Pattern();
    p.find132pattern(nums);
  }

}
