package com.ortona.stefano.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RemoveBoxes {
  public int removeBoxes(int[] boxes) {
    return solveInterval(boxes, 0, boxes.length - 1);
  }

  private int solveInterval(int[] boxes, int start, int end) {
    if (start == end) {
      boxes[start] = -1;
      return 1;
    }
    // find highest number
    final int[] maxNumbAndOcc = getMaxNumberWithLeastBlockingIntervals(boxes, start, end);
    final int maxNumb = maxNumbAndOcc[0];
    final int maxOcc = maxNumbAndOcc[1];
    if (maxNumb == -1) {
      return 0;
    }
    // solve problems for all intervals happening in between the best number
    int bestStart = start;
    for (int i = start; i <= end; i++) {
      if (boxes[i] == maxNumb) {
        bestStart = i;
        break;
      }
    }
    int bestEnd = end;
    for (int i = end; i >= start; i--) {
      if (boxes[i] == maxNumb) {
        bestEnd = i;
        break;
      }
    }
    int previous = -1;
    int total = 0;
    for (int i = bestStart; i <= bestEnd; i++) {
      // consider only numbers!=-1
      if (boxes[i] != -1) {
        if (boxes[i] != maxNumb) {
          if (previous == -1) {
            previous = i;
          }
        } else {
          if (previous != -1) {
            total += solveInterval(boxes, previous, i - 1);
            previous = -1;
          }
        }
      }
    }
    replaceMaxNumber(boxes, start, end, maxNumb, -1);
    // need to add last interval
    return total + solveInterval(boxes, start, end) + (maxOcc * maxOcc);
  }

  private void replaceMaxNumber(int boxes[], int start, int end, int maxNumb, int replacement) {
    for (int i = start; i <= end; i++) {
      if (boxes[i] == maxNumb) {
        boxes[i] = replacement;
      }
    }
  }

  private int[] getMaxNumberWithLeastBlockingIntervals(int[] boxes, int start, int end) {
    final Map<Integer, Integer> numb2occ = new HashMap<Integer, Integer>();
    final Map<Integer, Integer> numb2start = new HashMap<Integer, Integer>();

    int maxOcc = -1;
    int maxNumb = -1;
    for (int i = start; i <= end; i++) {
      // do not consider -1 values
      if (boxes[i] != -1) {
        if (!numb2start.containsKey(boxes[i])) {
          numb2start.put(boxes[i], i);
        }
        final int newValue = numb2occ.getOrDefault(boxes[i], 0) + 1;
        if (newValue > maxOcc) {
          maxOcc = newValue;
          maxNumb = boxes[i];
        } else if (newValue == maxOcc) {
          final int prevStart = numb2start.get(maxNumb);
          final int curStart = numb2start.get(boxes[i]);
          if (curStart > prevStart) {
            maxNumb = boxes[i];
          }
        }
        numb2occ.put(boxes[i], newValue);
      }
    }
    return new int[] { maxNumb, maxOcc };
  }

  public static void main(String[] args) {
    final int[] allBoxes = new int[] { 2, 1, 1, 2, 1, 3, 3, 2, 3, 2, 3 };
    System.out.println(new RemoveBoxes().removeBoxes(allBoxes));
  }

}
