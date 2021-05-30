package year_2021.month_5;

/*
   解法①：
       a. 线段树区间更新 & 单点查询

   解法②:
       a. 差分思想, 每发现s[i]=='0' && 可以到达, vis[i + minJump] +=1, vis[i + maxJump + 1] -= 1;

 */
class Lc_1871 {
  public boolean canReach(String s, int minJump, int maxJump) {
    int n = s.length();
    int[] vis = new int[s.length()];
    vis[0] +=1;
    if (vis.length > 1) {
      vis[1] -= 1;
    }
    for (int i = 0; i < n; i++) {
      if (i > 0) {
        vis[i] += vis[i - 1];
      }
      if (vis[i] > 0 && s.charAt(i) == '0') {
        int le = i + minJump;
        int ri = i + maxJump + 1;
        if (le < n) {
          vis[le] ++;
        }
        if (ri < n) {
          vis[ri]--;
        }
      }
    }
    return vis[n - 1] > 0 && s.charAt(n - 1) == '0';
  }
}