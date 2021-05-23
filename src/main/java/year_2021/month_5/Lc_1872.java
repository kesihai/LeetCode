package year_2021.month_5;
/*
假设: dp[i] 代表[0~i] 个石头已经被合并了, 现在还剩下 [i, i + 1, j, ..., n - 1] 个石头.
假如已经知道 dp[j] 代表已经面临[j, .., n] 时的diff, 那么dp[i] 跟dp[j] 的关系就是:

dp[i] = pre[j] - dp[j]， 对于说有的 j > i && j < n 中的最大值.
 */

class Lc_1872 {
  public int stoneGameVIII(int[] stones) {
    if (stones.length < 2) {
      return 0;
    }
    int n = stones.length;
    int[] pre = new int[stones.length];
    pre[0] = stones[0];
    for (int i = 1; i < pre.length; i++) {
      pre[i] = pre[i - 1] + stones[i];
    }
    int[] dp = new int[n];
    dp[n - 1] = 0;
    int ma = pre[n - 1];
    for (int i =  n - 2; i >= 0; i--) {
      dp[i] = ma;
      ma = Math.max(ma, pre[i] - dp[i]);
    }
    return dp[0];
  }
}
