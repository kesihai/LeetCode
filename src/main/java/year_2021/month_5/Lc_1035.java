package year_2021.month_5;

class Lc_1035 {
  int[][] dp;
  public int maxUncrossedLines(int[] nums1, int[] nums2) {
    dp = new int[nums1.length + 1][nums2.length + 1];
    int n = nums1.length;
    int m = nums2.length;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        if (nums1[i - 1] == nums2[j - 1]) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
        }
      }
    }
    return dp[n][m];
  }
}