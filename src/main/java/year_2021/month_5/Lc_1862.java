package year_2021.month_5;

import java.util.Arrays;

class Lc_1862 {
  public static final long MOD = (long)1e9+7;

  public int sumOfFlooredPairs(int[] nums) {
    int ma = 0;
    for (int i = 0; i < nums.length; i++) {
      ma = Math.max(ma, nums[i]);
    }
    int[] count = new int[ma * 2 + 1];
    for (int i = 0; i < nums.length; i++) {
      count[nums[i]]++;
    }
    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
    }
    int[] preCount = new int[ma + 1];
    Arrays.fill(preCount, -1);
    long ans = 0;
    for (int i = 0; i < nums.length; i++) {
      if (preCount[nums[i]] != -1) {
        ans = (ans + preCount[nums[i]]) % MOD;
        continue;
      }
      int le = nums[i], ri = nums[i] * 2;
      int cnt = 1;
      int tmp = 0;
      while (le <= ma) {
        tmp += cnt * (count[ri - 1] - count[le - 1]);
        tmp %= MOD;
        le += nums[i];
        ri += nums[i];
        cnt++;
      }
      preCount[nums[i]] = tmp;
      ans = (ans + tmp) % MOD;
    }
    return (int)ans;
  }
}