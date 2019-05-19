package com.ortona.stefano.cracking_code_interview.chapter8;

import java.util.HashMap;
import java.util.Map;

public class Question14 {
  
  public int booleanEvaluation(String expression, boolean result){
    //initial run with empty memory
    return booleanEvaluationWithMemo(expression, result, new HashMap<>());
  }
  
  private int booleanEvaluationWithMemo(String expression, boolean result,Map<String,Integer> memo){
    if(expression.length()==0) return 0;
    if(expression.length()==1) return result==fromExpressionToBoolean(expression) ? 1 : 0;
    
    //check if it is in memo
    Integer memoCount = memo.get(expression+result);
    if(memoCount!=null){
      return memoCount;
    }
    
    int ways=0;
    //put a parenthesis around all the chars
    for(int i=1;i<expression.length();i+=2){
      char curOp = expression.charAt(i);
      
      String left = expression.substring(0, i);
      String right = expression.substring(i+1,expression.length());
      
      int leftTrue = booleanEvaluationWithMemo(left, true,memo);
      int rightTrue = booleanEvaluationWithMemo(right, true,memo);
      int leftFalse = booleanEvaluationWithMemo(left, false,memo);
      int rightFalse = booleanEvaluationWithMemo(right, false,memo);
      
      int total = (leftTrue+leftFalse) * (rightTrue+rightFalse);
      int totalTrue = 0;
      if(curOp=='&'){
        //both left and right must be true
        totalTrue=(leftTrue)*(rightTrue);
      }
      if(curOp=='|'){
        //left and true cannot be both false;
        totalTrue=(leftTrue)*(rightTrue) + (leftFalse)*(rightTrue) + (leftTrue)*(rightFalse);
      }
      if(curOp=='^'){
        //left and right must be different
        totalTrue=(leftTrue)*(rightFalse) + (rightTrue)*(leftFalse);
      }
      ways+=result ? totalTrue : total-totalTrue;
    }
    memo.put(expression+result, ways);
    return ways;
    
  }
  
  private boolean fromExpressionToBoolean(String expression){
    return expression.equals("1") ? true : false;
  }
  
  public static void main(String []args){
    Question14 q = new Question14();
    System.out.println(q.booleanEvaluation("0&0&0&1^1|0", true));
  }

}
