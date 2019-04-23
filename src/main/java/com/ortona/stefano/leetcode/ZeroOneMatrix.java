package com.ortona.stefano.leetcode;

public class ZeroOneMatrix {
  public int[][] updateMatrix(int[][] matrix) {
    
    for(int i=0;i<matrix.length;i++){
      for(int j=0;j<matrix[0].length;j++){
        if(matrix[i][j]!=0){
          int disUp = i > 0 ? matrix[i-1][j] + 1 : Integer.MAX_VALUE;
          int disLeft = j > 0 ? matrix[i][j-1] + 1 : Integer.MAX_VALUE;
          matrix[i][j] = Math.min(disUp, disLeft);
        }
      }
    }
    
    for(int i=matrix.length-1;i>=0;i--){
      for(int j=matrix[0].length-1;j>=0;j--){
        if(matrix[i][j]!=0){
          int disRight = j < matrix[0].length-1 ? matrix[i][j+1] +1  : Integer.MAX_VALUE;
          int disDown = i < matrix.length-1 ? matrix[i+1][j] +1  : Integer.MAX_VALUE;
          matrix[i][j] = Math.min(disRight, Math.min(disDown,matrix[i][j]));
        }
      }
    }
    
    return matrix;
  }

}
