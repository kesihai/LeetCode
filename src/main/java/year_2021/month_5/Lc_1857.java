package year_2021.month_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Lc_1857{
  int[] ballColor;
  List<List<Integer>> path;
  int n;
  int[] in;
  boolean[] vis; // 判断是否有环的vis
  boolean[] visBall; // 判断是否全部被访问的vis
  boolean flag;
  int[][] dp;
  public int largestPathValue(String colors, int[][] edges) {
    if (colors.length() == 0) {
      return 0;
    }
    ballColor = new int[colors.length()];
    for (int i = 0; i < colors.length(); i++) {
      ballColor[i] = colors.charAt(i) - 'a';
    }
    path = new ArrayList<>();
    n = colors.length();
    for (int i = 0; i < n; i++) {
      path.add(new ArrayList<>());
    }

    in = new int[n];
    Arrays.fill(in, 0);
    for (int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      path.get(from).add(to);
      in[to]++;
    }

    vis = new boolean[n];
    Arrays.fill(vis, false);

    visBall = new boolean[n];
    Arrays.fill(visBall, false);
    dp = new int[n][26];
    int ans = 1;
    flag = false;
    for (int i = 0; i < n; i++) {
      if (in[i] == 0) {
        int[] tmp = dfs (i);
        if (flag) {
          return -1;
        }
        for (int j = 0; j < 26; j++) {
          ans = Math.max(ans, tmp[j]);
        }
      }
    }
    for (int i = 0; i < visBall.length; i++) {
      if (!visBall[i]) {
        return -1;
      }
    }
    return ans;
  }

  private int[] dfs(int cur) {
    if (vis[cur]) {
      flag = true;
      return new int[26];
    }
    if (visBall[cur]) {
      return dp[cur];
    }
    int[] tmp = new int[26];
    Arrays.fill(tmp, 0);
    if (flag) {
      return tmp;
    }
    vis[cur] = true;
    visBall[cur] = true;

    for (int to : path.get(cur)) {
      int[] t = dfs(to);
      for (int i = 0; i < 26; i++) {
        tmp[i] = Math.max(tmp[i], t[i]);
      }
    }
    tmp[ballColor[cur]]++;
    vis[cur] = false;
    return dp[cur] = tmp;
  }
}