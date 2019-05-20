package com.ortona.stefano.cracking_code_interview.chapter8;

public class Question5 {
  
  public int search(String []input, String target){
    return binarySearch(input, 0, input.length-1, target);
  }
  
  private int binarySearch(String []input,int start,int end, String target){
    if(start>end){
      return -1;
    }
    int middle = start+((end-start)/2);
    middle=findNextIndex(middle, input, start, end);
    if(middle==-1 || input[middle].compareTo(target)>0){
      return binarySearch(input, start, middle-1,target);
    }
    if(input[middle].equals(target)){
      return middle;
    }
    return binarySearch(input, middle+1, end, target);
  }
  
  private int findNextIndex(int curIndex,String []input,int start, int end){
    for(int i=start;i<=end;i++){
      if(input[i].length()>0){
        return i;
      }
    }
    return -1;
  }
  
  public static void main(String []args){
    Question5 q5 = new Question5();
    System.out.println(q5.search(new String[]{"at","","","","ball","","","car","","","dad","",""}, "ball"));
  }

}
