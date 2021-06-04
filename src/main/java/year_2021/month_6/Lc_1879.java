package year_2021.month_6;

import java.util.Arrays;

class Lc_1879 {
  int len1;
  int[][] dp;
  public int minimumXORSum(int[] nums1, int[] nums2) {
    len1 = nums1.length;
    dp = new int[len1][1 << len1];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return dfs(nums1, nums2, 0, 0);
  }

  private int dfs(int[] nums1, int[] nums2, int curPos, int state) {
    if (curPos == len1) {
      return 0;
    }
    if (dp[curPos][state] != -1) {
      return dp[curPos][state];
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < len1; i++) {
      if ((state & (1 << i)) != 0) {
        continue;
      }
      int tmp = dfs(nums1, nums2, curPos + 1, state | (1 << i)) + (nums1[curPos] ^ nums2[i]);
      ans = Math.min(ans, tmp);
    }
    return dp[curPos][state] = ans;
  }
}