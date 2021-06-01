package year_2021.month_6;

/*

假设: 目标为T, 我们我们将(a, b) 修改为 a + b = T, 那么需要的次数有如下关系:

修改次数0:  a + b = T.
修改次数1:  [a + 1, a + b - 1] [a + b + 1, b + limit],
修改次数2:  [2, a] || [b + limit + 1];


 */
class Lc_1674 {
  public int minMoves(int[] nums, int limit) {
    int n = nums.length;
    int[] pre = new int[limit * 2 + 2];
    for (int i = 0, j = n - 1; i < j; i++, j--) {
      int a = Math.min(nums[i], nums[j]);
      int b = Math.max(nums[i], nums[j]);
      pre[a + 1] ++;
      pre[a + b] --;
      pre[a + b + 1] ++;
      pre[b + limit + 1] --;

      pre[2] += 2;
      pre[a + 1] -= 2;
      pre[b + limit + 1] += 2;
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 2; i < pre.length; i++) {
      pre[i] += pre[i - 1];
      ans = Math.min(ans, pre[i]);
    }
    return ans;
  }

}