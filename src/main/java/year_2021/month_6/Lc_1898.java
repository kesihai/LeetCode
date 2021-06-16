package year_2021.month_6;

class Lc_1898 {
  public int maximumRemovals(String s, String p, int[] removable) {
    int ans = -1;
    int le = 0, ri = removable.length - 1;
    while (le <= ri) {
      int mid = (le + ri) / 2;
      if (check(s, p, removable, mid)) {
        ans = mid;
        le = mid + 1;
      } else {
        ri = mid - 1;
      }
    }
    return ans + 1;
  }

  private boolean check(String s, String p, int[] remove, int pos) {
    int[] less = new int[26];
    int[] more = new int[26];
    int[] vis = new int[s.length()];
    for (int i = 0; i <= pos; i++) {
      vis[remove[i]] = 1;
    }
    int len1 = s.length();
    int len2 = p.length();
    int pos1 = 0;
    int pos2 = 0;
    while (pos2 < p.length()) {
      int ch = p.charAt(pos2++) - 'a';
      less[ch]++;
      while (pos1 < len1 && more[ch] < less[ch]) {
        if (vis[pos1] == 1) {
          pos1++;
          continue;
        }
        int t = s.charAt(pos1++) - 'a';
        if (t == ch) {
          more[t]++;
        }
      }
      if (less[ch] > more[ch]) {
        return false;
      }
    }
    return true;
  }
}