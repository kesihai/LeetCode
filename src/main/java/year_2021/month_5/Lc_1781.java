package year_2021.month_5;

class Lc_1781 {
  public boolean canReach(String s, int minJump, int maxJump) {
    int n = s.length();
    if (s.charAt(n - 1) != '0') {
      return false;
    }
    int[] vis = new int[n];
    vis[0] = 0;
    for (int i = 0; i < n; i++) {
      if (i > 0) {
        vis[i] += vis[i - 1];
      }
      if (s.charAt(i) != '0') {
        continue;
      }
      if (vis[i] == 0 && i != 0) {
        continue;
      }
      int le = i + minJump;
      int ri = i + maxJump + 1;
      if (le < n) {
        vis[le] +=1;
      }
      if (ri < n) {
        vis[ri] -= 1;
      }
    }
    return vis[n - 1] > 0;
  }
}