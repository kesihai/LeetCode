package year_2021.month_5;
import java.util.Stack;

class Lc_1856 {
  public int maxSumMinProduct(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int[] le = new int[nums.length];
    int[] ri = new int[nums.length];
    long[] sum = new long[nums.length];
    sum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sum[i] = sum[i - 1] + nums[i];
    }
    initLe(le, nums);
    initRi(ri, nums);
    long ans = 0;
    for (int i = 0; i < nums.length; i++) {
      long tmp = sum[ri[i] - 1] -  (le[i] >= 0 ? sum[le[i]] : 0);
      ans = Math.max(ans, 1L * nums[i] * tmp);
    }
    ans = ans % 1000000007L;
    return (int) ans;
  }

  private void initLe(int[] le, int[] nums) {
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
      while (!s.isEmpty() && nums[i] <= nums[s.peek()]) {
        s.pop();
      }
      le[i] = s.isEmpty() ? -1 : s.peek();
      s.add(i);
    }
  }

  private void initRi(int[] ri, int[] nums) {
    Stack<Integer> s = new Stack<>();
    for (int i = nums.length - 1; i >= 0; i--) {
      while (!s.isEmpty() && nums[i] <= nums[s.peek()]) {
        s.pop();
      }
      ri[i] = s.isEmpty() ? nums.length : s.peek();
      s.add(i);
    }
  }
}
