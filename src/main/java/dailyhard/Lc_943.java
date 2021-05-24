package dailyhard;

import java.util.Arrays;

/*
旅行商问题: 把所有的字符串访问一遍至少访问一遍, 能获取的字符串的最短长度.
 */
class Lc_943 {
  int[][] common;
  int[][] dp;
  int n;
  String[] words;
  int[][] path;
  public String shortestSuperstring(String[] words) {
    init(words);
    dfs(0, 0);
    return getValue();
  }

  private void init(String[] words) {
    this.words = words;
    n = words.length;
    common = new int[n][n];
    dp = new int[1 << n][n];
    path = new int[1 << n][n];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        common[i][j] = getCommon(i, j);
      }
    }
  }

  private int getCommon(int x, int y) {
    if (x == y) {
      return words[x].length();
    }

    String pre = words[x];
    String suf = words[y];
    for (int i = 0; i < pre.length(); i++) {
      if (suf.startsWith(pre.substring(i))) {
        return pre.length() - i;
      }
    }
    return 0;
  }

  private int dfs(int cur, int last) {
    if (last != -1 && dp[cur][last] != -1) {
      return dp[cur][last];
    }
    int len = 1 << n;
    if (cur == len - 1) {
      return 0;
    }
    for (int i = 0; i < n; i++) {
      if ((cur & (1 << i)) != 0) continue;
      int tmp = dfs(cur | (1 << i), i) + getLen( cur == 0 ? -1 : last, i);
      if (dp[cur][last] == -1 || dp[cur][last] > tmp) {
        dp[cur][last] = tmp;
        path[cur][last] = i;
      }
    }
    return dp[cur][last];
  }

  private String getValue() {
    int cur = 0;
    int last = 0;
    StringBuffer sb = new StringBuffer();
    int len = (1 << n) - 1;
    while (cur != len) {
      int next = path[cur][last];
      if (cur == 0) {
        sb.append(words[next]);
      } else {
        sb.append(words[next].substring(common[last][next]));
      }
      cur |= (1 << next);
      last = next;
    }
    return sb.toString();
  }

  private int getLen(int x, int y) {
    if (x == -1) {
      return words[y].length();
    }
    return words[y].length() - common[x][y];
  }
}