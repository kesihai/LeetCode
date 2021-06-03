package year_2021.month_6;

class Lc_798 {
  int[] vis;
  int n;
  public int bestRotation(int[] nums) {
    n = nums.length;
    vis = new int[n + 1];
    for (int i = 0; i < n; i++) {
      int pre = i - nums[i];
      if (pre >= 0) {
        vis[0]++;
        vis[pre + 1]--;
        vis[i + 1]++;
      } else {
        pre = (pre + n);
        vis[i + 1]++;
        vis[pre + 1]--;
      }
    }
    int ans = vis[0];
    int index = 0;
    for (int i = 1; i < n; i++) {
      vis[i] += vis[i - 1];
      if (vis[i] > ans) {
        ans = vis[i];
        index = i;
      }
    }
    return index;
  }
}