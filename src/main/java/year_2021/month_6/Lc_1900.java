package year_2021.month_6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Lc_1900 {
  final int N = 29;
  int[][][][] dp = new int[N][N][N][2];
  boolean[][][] vis = new boolean[N][N][N];
  public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
    return dfs(n, firstPlayer, secondPlayer);
  }

  int[] dfs(int n, int f, int s) {
    if (f + s == n + 1) {
      return new int[] {1, 1};
    }
    if (vis[n][f][s]) {
      return dp[n][f][s];
    }
    vis[n][f][s] = true;
    List<int[]> tmp = generage(n, f, s);
    int mi = Integer.MAX_VALUE;
    int ma = Integer.MIN_VALUE;
    for (int[] item : tmp) {
      int[] t = dfs(item[0], item[1], item[2]);
      mi = Math.min(mi, t[0] + 1);
      ma = Math.max(ma, t[1] + 1);
    }
    return dp[n][f][s] = new int[] {mi, ma};
  }

  private List<int[]> generage(int n, int f, int s) {
    List<Integer> list = new ArrayList<>();
    Queue<Item> q = new LinkedList<>();

    q.add(new Item(0, 1, n, f, s));
    while (!q.isEmpty()) {
      Item item = q.poll();
      if (item.le > item.ri) {
        list.add(item.state);
        continue;
      }
      if (item.le == item.ri) {
        list.add(item.state | (1 << item.le));
        continue;
      }
      if (item.le != f && item.le != s) {
        int tmp = item.state | (1 << (n + 1 - item.le));
        q.add(new Item(tmp, item.le + 1, item.ri - 1, f, s));
      }

      if (item.ri != f && item.ri != s) {
        int tmp = item.state | (1 << (n + 1 - item.ri));
        q.add(new Item(tmp, item.le + 1, item.ri - 1, f, s));
      }
    }
    List<int[]> ans = new ArrayList<>();

    for (int value : list) {
      int newF = 0;
      int newS = 0;
      int op = 0;
      for (int i = 1; i <= n; i++) {
        if ((value & (1 << i)) != 0) {
          op++;
        }
        if (i == f) {
          newF = op;
        }
        if (i == s) {
          newS = op;
        }
      }
      ans.add(new int[]{(n + 1) / 2, newF, newS});
    }
    return ans;
  }

  public static class Item {
    int state;
    int le;
    int ri;
    int f;
    int s;

    Item (int state, int le, int ri, int f, int s) {
      this.state = state;
      this.le = le;
      this.ri = ri;
      this.f = f;
      this.s = s;
    }
  }
}