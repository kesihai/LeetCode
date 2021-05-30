package year_2021.month_5;

class Lc_995 {
  public int minKBitFlips(int[] nums, int k) {
    int[] isFliped = new int[nums.length];
    int ans = 0;
    int cnt = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i >= k) {
        // 去掉滑出窗口的元素
        cnt -= isFliped[i - k];
      }
      int tmp = cnt % 2;
      if ((tmp ^ nums[i]) == 1) {
        continue;
      }
      if (i + k - 1 >= nums.length) {
//        System.out.println("index:" + i + " cnt: " + cnt);
        return -1;
      }
      cnt++;
      ans++;
      isFliped[i] = 1;
    }
    return ans;
  }
}