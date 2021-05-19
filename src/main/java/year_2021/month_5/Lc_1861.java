package year_2021.month_5;

class Lc_1861 {
  int n, m;
  public char[][] rotateTheBox(char[][] box) {
    n = box.length;
    m = box[0].length;
    char[][] ans = new char[m][n];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = 0; j < m; j++) {
        ans[j][n - 1 - i] = box[i][j];
      }
    }
    for (int i = ans.length - 1; i >= 0; i--) {
      for (int j = 0; j < ans[i].length; j++) {
        if (ans[i][j] == '*' || ans[i][j] == '.') {
          continue;
        }
        int t = i;
        while (t + 1 < ans.length && ans[t + 1][j] == '.') {
          swap(ans, t, j, t + 1, j);
          t++;
        }
      }
    }
    return ans;
  }

  private void swap(char[][] a, int x, int y, int xx, int yy) {
    char tmp = a[x][y];
    a[x][y] = a[xx][yy];
    a[xx][yy] = tmp;
  }
}