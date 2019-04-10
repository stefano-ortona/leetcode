package com.ortona.stefano.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfertNDays {
  public int[] prisonAfterNDays(int[] cells, int N) {
    final Map<String, Integer> status2iteration = new HashMap<>();
    status2iteration.put(getStatusString(cells), 0);
    int i = 1;
    int startOfCycle = -1;
    int cycleLength = -1;
    for (i = 1; i <= N; i++) {
      changeStatus(cells);
      final String curStatus = getStatusString(cells);
      if (status2iteration.containsKey(curStatus)) {
        startOfCycle = status2iteration.get(curStatus);
        cycleLength = i - startOfCycle;
        break;
      } else {
        status2iteration.put(curStatus, i);
      }
    }
    if (i >= N) {
      return cells;
    }
    final int statusToGet = ((N - startOfCycle) % cycleLength) + startOfCycle;
    String finalStatus = null;
    for (final String oneKey : status2iteration.keySet()) {
      if (status2iteration.get(oneKey) == statusToGet) {
        finalStatus = oneKey;
        break;
      }
    }
    return convertStatusToArray(finalStatus);
  }

  private int[] convertStatusToArray(String status) {
    final int[] out = new int[status.length()];
    for (int i = 0; i < status.length(); i++) {
      out[i] = status.charAt(i) == '1' ? 1 : 0;
    }
    return out;
  }

  private void changeStatus(int[] cells) {
    int prevValue = cells[0];
    for (int i = 1; i < (cells.length - 1); i++) {
      final int temp = cells[i];
      cells[i] = prevValue == cells[i + 1] ? 1 : 0;
      prevValue = temp;
    }
    cells[0] = 0;
    cells[cells.length - 1] = 0;
  }

  private String getStatusString(int[] cells) {
    final StringBuilder sBuilder = new StringBuilder();
    for (int i = 0; i < cells.length; i++) {
      sBuilder.append(cells[i]);
    }
    return sBuilder.toString();
  }

  public static void main(String[] args) {
    final PrisonCellsAfertNDays prison = new PrisonCellsAfertNDays();
    System.out.println(Arrays.toString(prison.prisonAfterNDays(new int[] { 1, 0, 0, 1, 0, 0, 1, 0 }, 1000000000)));

  }

}
