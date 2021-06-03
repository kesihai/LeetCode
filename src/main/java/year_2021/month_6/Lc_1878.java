package year_2021.month_6;

import java.util.HashSet;
import java.util.Set;

class Lc_1878 {
  int[][] le;
  int[][] ri;
  int[][] grid;
  int n, m;
  final static int[][] dir = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
  int[] px = new int[4];
  int[] py = new int[4];

  public int[] getBiggestThree(int[][] grid) {
    n = grid.length;
    m = grid[0].length;
    this.grid = grid;
    initLe();
    initRi();

    int ans = 0;
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        for (int len = 0; len <= n; len++) {
          if (len == 0) {
            set.add(grid[i][j]);
            continue;
          }
          boolean ok = true;
          for (int k = 0; k < 4; k++) {
            px[k] = i + dir[k][0] * len;
            py[k] = j + dir[k][1] * len;
            if (!check(px[k], py[k])) {
              ok = false;
              break;
            }
          }
          if (!ok) {
            continue;
          }
          int tmp = calculateLe(px[0], py[0], px[1], py[1]) + calculateLe(px[3], py[3], px[2], py[2])
              + calculateRi(px[1], py[1], px[2], py[2]) + calculateRi(px[0], py[0], px[3], py[3]);
          for (int k = 0; k < 4; k++) {
            tmp -= grid[px[k]][py[k]];
          }
          set.add(tmp);
        }
      }
    }
    return set.stream().sorted((a, b) -> b - a).limit(3).mapToInt(x -> x).toArray();
  }

  private int calculateLe(int x, int y, int xx, int yy) {
    x--;
    y++;
    if (!check(x, y)) {
      return le[xx][yy];
    }
    return le[xx][yy] - le[x][y];
  }

  private int calculateRi(int x, int y, int xx, int yy) {
    x--;
    y--;
    if (!check(x, y)) {
      return ri[xx][yy];
    }
    return ri[xx][yy] - ri[x][y];
  }

  private boolean check(int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < m;
  }

  private void initLe() {
    le = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        le[i][j] = grid[i][j];
      }
    }
    for (int j = 0; j < m; j++) {
      handleLe(0, j);
    }

    for (int i = 1; i < n; i++) {
      handleLe(i, m - 1);
    }
  }

  private void handleLe(int x, int y) {
    while (true) {
      int xx = x + 1;
      int yy = y - 1;
      if (!check(xx, yy)) {
        return;
      }
      le[xx][yy] += le[x][y];
      x = xx;
      y = yy;
    }
  }

  private void initRi() {
    ri = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        ri[i][j] = grid[i][j];
      }
    }

    for (int i = 0; i < n; i++) {
      handleRi(i, 0);
    }
    for (int j = 1; j < m; j++) {
      handleRi(0, j);
    }
  }

  private void handleRi(int x, int y) {
    while (true) {
      int xx = x + 1;
      int yy = y + 1;
      if (!check(xx, yy)) {
        return;
      }
      ri[xx][yy] += ri[x][y];
      x = xx;
      y = yy;
    }
  }
}