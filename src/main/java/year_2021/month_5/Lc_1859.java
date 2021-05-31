package year_2021.month_5;

import java.util.Arrays;

class Lc_1859 {
  int[] vis;
  final static int MOD = (int)1e9+7;
  public int maxSumRangeQuery(int[] nums, int[][] requests) {
    int n = nums.length;
    vis = new int[n];
    for (int[] item : requests) {
      int from = item[0];
      int to = item[1] + 1;
      vis[from]++;
      if (to < n) {
        vis[to]--;
      }
    }
    for (int i = 1; i < n; i++) {
      vis[i] += vis[i - 1];
    }
    Arrays.sort(nums);
    Arrays.sort(vis);
    long ans = 0L;
    for (int i = 0; i < n; i++) {
      ans = (ans + 1L * vis[i] * nums[i]) % MOD;
    }
    return (int)ans;
  }
}