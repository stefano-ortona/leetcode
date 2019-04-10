package com.ortona.stefano.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumNumberRefuelingStops {
  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    final PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return -(o1.compareTo(o2));
      }
    });
    int curPos = 0;
    int res = 0;
    while (true) {
      while ((curPos < stations.length) && (stations[curPos][0] <= startFuel)) {
        queue.offer(stations[curPos][1]);
        curPos++;
      }
      if (startFuel >= target) {
        return res;
      }
      if (queue.isEmpty()) {
        return -1;
      }
      startFuel += queue.poll();
      res++;
    }
  }

  public static void main(String[] args) {
    final MinimumNumberRefuelingStops stops = new MinimumNumberRefuelingStops();
    System.out.println(stops.minRefuelStops(1000, 299, new int[][] { { 13, 21 }, { 26, 115 }, { 100, 47 }, { 225, 99 },
        { 299, 141 }, { 444, 198 }, { 608, 190 }, { 636, 157 }, { 647, 255 }, { 841, 123 } }));
  }

}
