package dailyhard;

/*
   dp[i][j]: 代表用了 i 分钟做了前j到菜的收益.
 */

import java.util.Arrays;

class Lc_1402 {
  int[] a;
  int[][] dp;
  int n;

  public int maxSatisfaction(int[] satisfaction) {
    Arrays.sort(satisfaction);
    a = satisfaction;
    n = satisfaction.length;
    dp = new int[n + 1][n + 1];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], Integer.MIN_VALUE);
    }
    return dfs(0, 0);
  }

  private int dfs(int minute, int pos) {
    if (pos == n) {
      return 0;
    }
    if (minute == n) {
      return 0;
    }
    if (dp[minute][pos] != Integer.MIN_VALUE) {
      return dp[minute][pos];
    }
    // 做这道菜
    int tmp  = 0;
    tmp = Math.max(tmp, dfs(minute + 1, pos + 1) + (minute + 1) * a[pos]);

    // 不做这道菜
    tmp = Math.max(tmp, dfs(minute, pos + 1));
    return dp[minute][pos] = tmp;
  }
}
