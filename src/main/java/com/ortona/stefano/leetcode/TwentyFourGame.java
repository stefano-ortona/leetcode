package com.ortona.stefano.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TwentyFourGame {

  public boolean judgePoint24(int[] nums) {
    List<Double> input = new LinkedList<>();
    for(int oneN:nums){
      input.add(oneN+0.);
    }
    return computeOutput(input, 24,new HashMap<>());
  }

  private boolean computeOutput(List<Double> numbers, double targetValue, Map<String,Boolean> mem){
    if(numbers.size()==0){
      return compareNumbers(0, targetValue);
    }
    if(numbers.size()==1){
      return compareNumbers(numbers.get(0),targetValue);
    }
    String memoeryKey = buildKey(numbers, targetValue);
    Boolean res = mem.get(memoeryKey);
    if(res!=null){
      return res;
    }
    //without parenthesis
    boolean found = false;
    for(int i=0;i<numbers.size();i++){
      double curNumber = numbers.remove(i);
      found = allPossibleWays(found, numbers, curNumber, targetValue,mem);
      //backtrack
      numbers.add(i,curNumber);
    }
    //with parenthesis
    if(numbers.size()>2){
      for(int i=0;i<numbers.size();i++){
        double curNumber = numbers.remove(i);
        for(int j=0;j<numbers.size();j++){
          double secNumber = numbers.remove(j);
          found = allPossibleWays(found, numbers, curNumber*secNumber, targetValue,mem) 
              ||  allPossibleWays(found, numbers, curNumber-secNumber, targetValue,mem) 
              ||  allPossibleWays(found, numbers, curNumber+secNumber, targetValue,mem);
          if(secNumber!=0){
            found = allPossibleWays(found, numbers, curNumber/secNumber, targetValue,mem);
          }
          //backtrack
          numbers.add(j, secNumber);
        }
        //backtrack
        numbers.add(i, curNumber);
      }
    }
    mem.put(memoeryKey, found);
    return found;
  }
  
  private String buildKey(List<Double> numbers,double target){
    StringBuilder keyBuilder = new StringBuilder();
    keyBuilder.append(numbers.get(0));
    for(int i=1;i<numbers.size();i++){
      keyBuilder.append("-").append(numbers.get(i));
    }
    keyBuilder.append("T").append(target);
    return keyBuilder.toString();
  }
  
  private boolean allPossibleWays(boolean start, List<Double> numbers, double curValue, double targetValue, Map<String,Boolean> mem){
    if(curValue==0 && targetValue==0){
      return true;
    }
    boolean outcome = start || computeOutput(numbers, targetValue-curValue,mem) ||
        computeOutput(numbers, curValue-targetValue,mem);
    if(curValue!=0 && targetValue!=0){
      outcome = outcome || computeOutput(numbers, targetValue/curValue,mem);
    }
    if(curValue!=0 && targetValue!=0){
      outcome = outcome || computeOutput(numbers, curValue/targetValue,mem);
    }
    return outcome;
  }
  
  private boolean compareNumbers(double first, double second){
    return Math.abs(first-second) < 0.00001;
  }
  
  public static void main(String[] args){
    TwentyFourGame tG = new TwentyFourGame();
    System.out.println(tG.judgePoint24(new int[]{4,1,8,7}));
  }



}
