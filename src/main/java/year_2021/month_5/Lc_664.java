package year_2021.month_5;

import java.util.Arrays;

class Lc_664 {
  public int strangePrinter(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], 0);
    }
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        dp[i][j] = j - i + 1;
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i + 1; j < n; j++) {
        for (int k = i; k < j; k++) {
          if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1] + 1);
          }
          dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] - (s.charAt(k) == s.charAt(j) ? 1 : 0));
        }
      }
    }
    return dp[0][n - 1];
  }
}