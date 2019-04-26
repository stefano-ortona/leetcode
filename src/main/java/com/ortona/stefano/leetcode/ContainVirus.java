package com.ortona.stefano.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContainVirus {

  public int containVirus(int[][] grid) {
    final List<InfectedRegion> allRegions = buildRegions(grid);
    Collections.sort(allRegions);
    final Set<Wall> usedWalls = new HashSet<>();
    while (!allRegions.isEmpty()) {
      final InfectedRegion curR = allRegions.remove(0);
      curR.getWallsToContain(usedWalls, grid);
      allRegions.forEach(r -> r.expandRegion(grid, usedWalls));
    }
    return usedWalls.size();
  }

  private List<InfectedRegion> buildRegions(int[][] grid) {
    final List<InfectedRegion> infected = new ArrayList<>();
    final boolean[][] visited = new boolean[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if ((grid[i][j] == 1) && !visited[i][j]) {
          final List<Point> toExpand = new ArrayList<>();
          toExpand.add(new Point(i, j));
          int curStart = 0;
          while (curStart < toExpand.size()) {
            final int end = toExpand.size();
            for (curStart = curStart + 0; curStart < end; curStart++) {
              final Point curPoint = toExpand.get(curStart);
              expandPoint(curPoint.x + 1, curPoint.y, grid, toExpand, visited);
              expandPoint(curPoint.x, curPoint.y + 1, grid, toExpand, visited);
              expandPoint(curPoint.x - 1, curPoint.y, grid, toExpand, visited);
              expandPoint(curPoint.x, curPoint.y - 1, grid, toExpand, visited);
            }
          }
          final InfectedRegion iR = new InfectedRegion();
          iR.toInfect = new HashSet<>();
          iR.toInfect.addAll(toExpand);
          iR.infected = new HashSet<>();
          iR.expandRegion(grid, new HashSet<>());
          infected.add(iR);
        }
      }
    }
    return infected;
  }

  private void expandPoint(int x, int y, int[][] grid, List<Point> allPoints, boolean[][] visited) {
    if ((x >= 0) && (y >= 0) && (x < grid.length) && (y < grid[0].length) && (grid[x][y] == 1)) {
      final Point newP = new Point(x, y);
      if (!allPoints.contains(newP)) {
        visited[x][y] = true;
        allPoints.add(newP);
      }
    }
  }

  private class InfectedRegion implements Comparable<InfectedRegion> {
    Set<Point> infected;
    Set<Point> toInfect;

    @Override
    public int compareTo(InfectedRegion o) {
      return o.toInfect.size() - this.toInfect.size();
    }

    public void getWallsToContain(Set<Wall> usedWalls, int[][] grid) {
      for (final Point oneP : infected) {
        buildWall(oneP, oneP.x - 1, oneP.y, usedWalls, grid);
        buildWall(oneP, oneP.x, oneP.y - 1, usedWalls, grid);
        buildWall(oneP, oneP.x + 1, oneP.y, usedWalls, grid);
        buildWall(oneP, oneP.x, oneP.y + 1, usedWalls, grid);
      }
    }

    private void buildWall(Point p, int x, int y, Set<Wall> usedWalls, int[][] grid) {
      if ((x >= 0) && (y >= 0) && (x < grid.length) && (y < grid[0].length) && (grid[x][y] == 0)) {
        final Point p1 = new Point(x, y);
        usedWalls.add(new Wall(p, p1));
      }
    }

    public void expandRegion(int[][] grid, Set<Wall> walls) {
      infected.addAll(toInfect);
      final Set<Point> newInfected = new HashSet<>();
      for (final Point p : toInfect) {
        grid[p.x][p.y] = 1;
      }
      for (final Point p : toInfect) {
        expandInfectedPoint(p, p.x - 1, p.y, newInfected, grid, walls);
        expandInfectedPoint(p, p.x, p.y - 1, newInfected, grid, walls);
        expandInfectedPoint(p, p.x + 1, p.y, newInfected, grid, walls);
        expandInfectedPoint(p, p.x, p.y + 1, newInfected, grid, walls);
      }
      toInfect = newInfected;
    }

    private void expandInfectedPoint(Point original, int x, int y, Set<Point> toInfect, int[][] grid, Set<Wall> walls) {
      final Point toAdd = new Point(x, y);
      if ((x >= 0) && (y >= 0) && (x < grid.length) && (y < grid[0].length) && (grid[x][y] == 0)
          && !walls.contains(new Wall(original, toAdd))) {
        toInfect.add(toAdd);
      }
    }

  }

  private class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + x;
      result = (prime * result) + y;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final Point other = (Point) obj;
      if (x != other.x) {
        return false;
      }
      if (y != other.y) {
        return false;
      }
      return true;
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + ")";
    }

  }

  private class Wall {
    Point one;
    Point two;

    public Wall(Point one, Point two) {
      this.one = one;
      this.two = two;
    }

    @Override
    public int hashCode() {
      return this.one.hashCode() + this.two.hashCode();
    }

    @Override
    public boolean equals(Object o1) {
      final Wall w = (Wall) o1;
      return (one.equals(w.one) && two.equals(w.two)) || (one.equals(w.two) && two.equals(w.one));
    }

    @Override
    public String toString() {
      return this.one.toString() + "/" + this.two.toString();
    }
  }

  public static void main(String[] args) {
    final ContainVirus cV = new ContainVirus();
    System.out.println(cV.containVirus(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }));
  }

}
