package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {

  public List<String> wordBreak(String s, List<String> wordDict) {
    List<String> allRes = new ArrayList<>();
    boolean []arra = new boolean[s.length()];
    Arrays.fill(arra, true);
    Set<String> setDict = new HashSet<>();
    setDict.addAll(wordDict);
    canBreak(s, 0, setDict, "", allRes, arra);
    return allRes;
  }

  private boolean canBreak(String s, int index,Set<String> dict, String curWord, List<String> result, boolean[] canSolve){
    if(index>=s.length()){
      result.add(curWord);
      return true;
    }
    if(canSolve[index]){
      boolean canSolveCurIndex = false;
      for(int i=index;i<s.length();i++){
        String curPrefix = s.substring(index,i+1);
        if(dict.contains(curPrefix)){
          canSolveCurIndex |= canBreak(s, i+1, dict, curWord.isEmpty()? curPrefix : curWord+" "+curPrefix, result, canSolve);
        }
      }
      canSolve[index]=canSolveCurIndex;
    }
    return canSolve[index];
  }

}
