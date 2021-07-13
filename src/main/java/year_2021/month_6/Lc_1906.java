package year_2021.month_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
   三种解题思想:
      ① 莫队.
      ② 前缀和.
      ③ 二分查找.
 */

/*
   二分查找解法
 */
class Lc_1906 {
  private static final int N = 101;
  private int[] vis = new int[N];
  private List<Integer> a[] = new List[N];
  public int[] minDifference(int[] nums, int[][] queries) {
    for (int i = 0; i < N; i++) {
      a[i] = new ArrayList<>();
    }
    for (int i = 0; i < nums.length; i++) {
      a[nums[i]].add(i);
    }
    int[] ans = new int[queries.length];
    int cnt = 0;
    for (int[] q : queries) {
      int le = q[0];
      int ri = q[1];
      ans[cnt++] = getAns(le, ri);
    }
    return ans;
  }

  private int getAns(int le, int ri) {
    for (int i = 1; i < N; i++) {
      vis[i] = find(le, ri, i);
    }
    int ans = Integer.MAX_VALUE;
    int pre = 0;
    for (int i = 1; i < N; i++) {
      if (vis[i] == 0) {
        continue;
      }
      if (pre != 0) {
        ans = Math.min(ans, i - pre);
      }
      pre = i;
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private int find(int le, int ri, int value) {
    List<Integer> list = a[value];
    int L = 0, R = list.size() - 1;
    while (L <= R) {
      int mid = (L + R) / 2;
      int pos = list.get(mid);
      if (pos >= le && pos <= ri) {
        return 1;
      } else if (pos < le) {
        L = mid + 1;
      } else {
        R = mid - 1;
      }
    }
    return 0;
  }

  public static class Item {
    int le;
    int ri;
    int index;

    Item(int le, int ri, int index) {
      this.le = le;
      this.ri = ri;
      this.index = index;
    }
  }
}

/*
// ② 前缀和. 解法
import java.util.Arrays;
class Solution {
  private int[] vis = new int[N];
  private static final int N = 101;
  public int[] minDifference(int[] nums, int[][] queries) {
    int[] cnt = new int[N];
    int[][] dp = new int[nums.length][N];
    for (int i = 0; i < nums.length; i++) {
      int v = nums[i];
      cnt[v]++;
      for (int j = 1; j < N; j++) {
        dp[i][j] = cnt[j];
      }
    }

    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int le = queries[i][0];
      int ri = queries[i][1];
      ans[i] = getAns(le, ri, dp);
    }
    return ans;
  }

  private int getAns(int le, int ri, int[][] dp) {
    for (int i = 1; i <= 100; i++) {
      int len = dp[ri][i] - (le > 0 ? dp[le - 1][i] : 0);
      vis[i] = len;
    }
    int ans = -1;
    int pre = -1;
    for (int i = 1; i < N; i++) {
      if (vis[i] == 0) {
        continue;
      }
      if (pre != -1) {
        ans = (ans == -1 ? i - pre : Math.min(ans, i - pre));
      }
      pre = i;
    }
    return ans;
  }

  public static class Item {
    int le;
    int ri;
    int index;

    Item(int le, int ri, int index) {
      this.le = le;
      this.ri = ri;
      this.index = index;
    }
  }
}

// ① 莫队. 解法
import java.util.Arrays;
class Solution {
  private int[] vis;
  private int[] nums;
  public int[] minDifference(int[] nums, int[][] queries) {
    this.nums = nums;
    Item[] items = new Item[queries.length];
    for (int i = 0; i < queries.length; i++) {
      items[i] = new Item(queries[i][0], queries[i][1], i);
    }
    int len = (int)Math.sqrt(nums.length);
    Arrays.sort(items, (a, b) -> {
      if (a.le / len != b.le / len) {
        return a.le / len - b.le / len;
      }
      return a.ri - b.ri;
    });
    vis = new int[105];
    int[] ans = new int[queries.length];
    int le = -1;
    int ri = -1;
    for (Item item : items) {
      while (le < item.le) del(le++);
      while (le > item.le) add(--le);
      while (ri < item.ri) add(++ri);
      while (ri > item.ri) del(ri--);
      ans[item.index] = getAns();
    }
    return ans;
  }

  private int getAns() {
    int pre = -1;
    int ans = Integer.MAX_VALUE;
    for (int i = 1; i <= 100; i++) {
      if (vis[i] == 0) {
        continue;
      }
      if (pre != -1) {
        ans = Math.min(ans, i - pre);
      }
      pre = i;
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private void del(int pos) {
    if (pos == -1) return;
    pos = nums[pos];
    vis[pos]--;
  }

  private void add(int pos) {
    if (pos == -1) return;
    pos = nums[pos];
    vis[pos]++;
  }

  public static class Item {
    int le;
    int ri;
    int index;

    Item(int le, int ri, int index) {
      this.le = le;
      this.ri = ri;
      this.index = index;
    }
  }

}
 */