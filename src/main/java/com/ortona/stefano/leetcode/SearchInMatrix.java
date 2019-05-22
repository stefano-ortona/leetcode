package com.ortona.stefano.leetcode;

/**
 * Search for a number in a matrix where each row and each column are sorted
 * 
 * @author Stefano Ortona <stefano.ortona@gmail.com>
 *
 */
public class SearchInMatrix {
  
  public boolean exists(int [][]matrix, int target){
    return searchRecursive(matrix, target, 0, matrix[0].length-1, 0, matrix.length-1);
    
  }
  
  private boolean searchRecursive(int [][]matrix, int target, int startRow, int endRow,
      int startColumn, int endColumn){
    if(startRow>endRow || startColumn>endColumn){
      return false;
    }
    //search in the row
    int rowIndex = binarySearch(matrix[startRow], startColumn, endRow,target);
    if(matrix[startRow][rowIndex]==target){
      return true;
    }
    //search in column
    int colIndex = binarySearch(buildColumnArray(matrix, startColumn), startRow, endColumn,target);
    if(matrix[colIndex][startColumn]==target){
      return true;
    }
    //search in sub-matrix
    return searchRecursive(matrix, target, startRow+1, rowIndex, startColumn+1, colIndex);
  }
  
  /**
   * Return index of number if you find id, otherwise index of the biggest number that is smaller than
   * target
   * 
   * @param nums
   * @param start
   * @param end
   * @return
   */
  private int binarySearch(int []nums, int start, int end,int target){
    if(start==end){
      if(nums[start]==target || nums[start]<target){
        return start;
      } else {
        return start==0 ? 0 : start-1;
      }
    }
    int middle = start+(end-start)/2;
    if(nums[middle]==target){
      return middle;
    }
    if(nums[middle]>target){
      return binarySearch(nums, start, middle-1, target);
    }
    return binarySearch(nums, middle+1, end, target);
    
  }
  
  private int[] buildColumnArray(int[][] matrix, int column){
    int []colRes = new int[matrix.length];
    for(int i=0;i<matrix.length;i++){
      colRes[i]=matrix[i][column];
    }
    return colRes;
  }
  
  public static void main(String[] args){
    int [][]matrix = new int[][]{{15,20,40,85},{20,35,80,95},{20,55,95,105},{40,80,100,120}};
    SearchInMatrix sM = new SearchInMatrix();
    System.out.println(sM.exists(matrix, 100));
  }
  
  

}
