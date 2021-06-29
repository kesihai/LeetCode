package year_2021.month_6;

/*
  2,3,5
*/
class Lc_264 {
  private static boolean ok = false;
  private static final int N = 1691;
  private static int[] dp = new int[N];
  public int nthUglyNumber(int n) {
    if (!ok) {
      init();
      // System.out.println(Arrays.toString(dp));
    }
    return dp[n - 1];
  }

  private void init() {
    ok = true;
    int[] value = new int[] {2, 3, 5};
    int[] pos = new int[] {0, 0, 0};
    dp[0] = 1;
    int cnt = 0;
    while (cnt + 1 < N) {
      int mi = Integer.MAX_VALUE;
      for (int i = 0; i < value.length; i++) {
        mi = Math.min(mi, dp[pos[i]] * value[i]);
      }

      for (int i = 0; i < value.length; i++) {
        if (dp[pos[i]] * value[i] == mi) {
          pos[i]++;
        }
      }
      dp[++cnt] = mi;
    }
  }
}