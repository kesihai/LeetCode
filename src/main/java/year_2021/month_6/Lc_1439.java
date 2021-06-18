package year_2021.month_6;

import java.util.Comparator;
import java.util.PriorityQueue;

class Lc_1439 {
  public int kthSmallest(int[][] mat, int k) {
    PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
    q.add(0);
    final int m = mat[0].length;
    for (int[] row : mat) {
      PriorityQueue tmp = new PriorityQueue(Comparator.reverseOrder());
      while (!q.isEmpty()) {
        int cur = q.poll();
        for (int i = 0; i < m; i++) {
          tmp.add(cur + row[i]);
          if (tmp.size() > k) {
            tmp.poll();
          }
        }
      }
      q  = tmp;
    }
    return q.poll();
  }
}