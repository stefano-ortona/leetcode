package com.ortona.stefano.leetcode;

public class PushDominoes {

  public String pushDominoes(String dominoes) {
    final char[] status = dominoes.toCharArray();
    int previousIndexState = -1;
    for (int i = 0; i < dominoes.length(); i++) {
      if (status[i] != '.') {
        changeStatus(previousIndexState, i, status);
        previousIndexState = i;
      }
    }
    // change last step
    changeStatus(previousIndexState, dominoes.length(), status);
    return new String(status);
  }

  private void changeStatus(int prevIndex, int nextIndex, char[] status) {
    final char prevChar = prevIndex >= 0 ? status[prevIndex] : 'L';
    final char nextChar = nextIndex < status.length ? status[nextIndex] : 'R';
    nextIndex = nextIndex < status.length ? nextIndex : status.length - 1;
    prevIndex = prevIndex >= 0 ? prevIndex : 0;
    if ((prevChar == 'L') && (nextChar == 'L')) {
      // change all characters to left
      for (int i = prevIndex; i <= nextIndex; i++) {
        status[i] = 'L';
      }
    }
    if ((prevChar == 'R') && (nextChar == 'R')) {
      for (int i = prevIndex; i <= nextIndex; i++) {
        status[i] = 'R';
      }
    }
    if ((prevChar == 'R') && (nextChar == 'L')) {
      final int len = ((nextIndex - prevIndex) - 1) / 2;
      for (int i = 0; i < len; i++) {
        status[prevIndex + 1 + i] = 'R';
        status[nextIndex - 1 - i] = 'L';
      }
    }
  }

  public static void main(String[] args) {
    final PushDominoes pD = new PushDominoes();
    System.out.println(pD.pushDominoes(".L.R...LR..L.."));
  }

}
