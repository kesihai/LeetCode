package year_2021.month_5;

import java.util.Arrays;

class Lc_1866 {
  public static final long MOD = (long)1e9 + 7;
  int n;
  long[][] dp;
  public int rearrangeSticks(int n, int k) {
    dp = new long[n + 1][k + 1];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return (int)dfs(n, k);
  }

  private long dfs(int n, int k) {
    if (n == 0) {
      return 0;
    }
    if (k > n) {
      return 0;
    }
    if (n == k) {
      return 1;
    }
    if (k == 0) {
      return 0;
    }
    if (dp[n][k] != -1) {
      return dp[n][k];
    }
    long tmp = dfs(n - 1, k - 1) % MOD;
    tmp = (tmp + (n - 1) * dfs(n - 1, k)) % MOD;
    return dp[n][k] = tmp;
  }
}