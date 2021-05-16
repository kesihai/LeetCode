package year_2021.month_5;

import java.util.*;

public class Lc_1591 {
  int[] le;
  int[] ri;
  int[] up;
  int[] down;
  int n, m;
  List<List<Integer>> path;
  int[] in;
  private static final int N = 61;
  public boolean isPrintable(int[][] targetGrid) {
    init(targetGrid);
    return topSort();
  }

  private boolean topSort() {
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      if (in[i] == 0) {
        queue.add(i);
      }
    }
    int ans = 0;
    while (!queue.isEmpty()) {
      int cur = queue.poll();
      ans++;
      for (int to : path.get(cur)) {
        in[to]--;
        if(in[to] == 0) {
          queue.add(to);
        }
      }
    }
    return ans == N;
  }

  private void init(int[][] targetGrid) {
    le = new int[N];
    ri = new int[N];
    down = new int[N];
    up = new int[N];
    n = targetGrid.length;
    m = targetGrid[0].length;
    Arrays.fill(le, -1);
    Arrays.fill(ri, -1);
    Arrays.fill(down, -1);
    Arrays.fill(up, -1);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int x = targetGrid[i][j];
        le[x] = le[x] == -1 ? i : Math.min(le[x], i);
        ri[x] = ri[x] == -1 ? i : Math.max(ri[x], i);
        up[x] = up[x] == -1 ? j : Math.min(up[x], j);
        down[x] = down[x] == -1 ? j : Math.max(down[x], j);
      }
    }

    in = new int[N];
    Arrays.fill(in, 0);
    path = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      path.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int color = targetGrid[i][j];
        for (int c = 0; c < N; c++) {
          if (le[c] == -1) {
            continue;
          }
          if (c == color) {
            continue;
          }
          if (le[c] <= i && ri[c] >= i && up[c] <= j && down[c] >= j) {
            in[color]++;
            path.get(c).add(color);
          }
        }
      }
    }
  }
}