package year_2021.month_5;

import java.util.Arrays;

class Solution {
  public void rotate(int[][] matrix) {
    if (matrix.length == 0) {
      return;
    }
    int n = matrix.length;
    int m = matrix[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        // 交换 [i, j] 和 [j][i]
        swap(matrix, i, j, j, i);
      }
    }

    for (int i = 0; i < n; i++) {
      for (int le = 0, ri = m - 1; le < ri; le ++, ri--) {
        swap(matrix, i, le, i, ri);
      }
    }
  }

  public void swap(int[][] a, int x, int y, int xx, int yy) {
    int tmp = a[x][y];
    a[x][y] = a[xx][yy];
    a[xx][yy] = tmp;
  }
}