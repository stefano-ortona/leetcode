package com.ortona.stefano.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskExecutor {
  public int leastInterval(char[] tasks, int n) {
    final PriorityQueue<Integer> toComplete = fillQueue(tasks);
    int totTime = 0;
    while (!toComplete.isEmpty()) {
      final int next = getNextActivities(toComplete, n, 0);
      totTime += next;
      final int waitTime = (toComplete.isEmpty() || (next == (n + 1))) ? 0 : (n - next) + 1;
      totTime += waitTime;

    }
    return totTime;
  }

  private int getNextActivities(PriorityQueue<Integer> queue, int target, int curIndex) {
    if (queue.isEmpty()) {
      return 0;
    }
    final int nextNumb = queue.poll();
    int num = 1;
    if (curIndex < target) {
      num += getNextActivities(queue, target, curIndex + 1);
    }
    // put it back
    if (nextNumb > 1) {
      queue.add(nextNumb - 1);
    }
    return num;
  }

  private PriorityQueue<Integer> fillQueue(char[] tasks) {
    final int[] allCount = new int[26];
    for (int i = 0; i < tasks.length; i++) {
      allCount[tasks[i] - 'A']++;
    }
    final PriorityQueue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    for (int i = 0; i < allCount.length; i++) {
      if (allCount[i] > 0) {
        queue.add(allCount[i]);
      }
    }
    return queue;
  }

  public static void main(String[] args) {
    final TaskExecutor tE = new TaskExecutor();
    System.out.println(tE.leastInterval(new char[] { 'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G' }, 2));
  }

}
