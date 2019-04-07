package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReverseiGame {

  enum colour {
    black, white;
  }

  class DiskPosition {
    public DiskPosition(colour c, int x, int y) {
      this.colour = c;
      this.x = x;
      this.y = y;
    }

    colour colour;
    int x;
    int y;
  }

  class Board {
    int length;
    List<DiskPosition> allPos;

    public Board(int length) {
      allPos = new ArrayList<>();
      this.length = length;
      final DiskPosition secondBlack = new DiskPosition(colour.black, length - 1, length - 1);
      final DiskPosition firstBlack = new DiskPosition(colour.black, length, length);
      final DiskPosition firstWhite = new DiskPosition(colour.white, length - 1, length);
      final DiskPosition secondWhite = new DiskPosition(colour.white, length, length - 1);
      allPos.add(firstBlack);
      allPos.add(secondBlack);
      allPos.add(firstWhite);
      allPos.add(secondWhite);
    }

    public void makeMove(DiskPosition pos, DiskPosition toEat) {

    }
  }

}
