package year_2021.month_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Lc_802 {
  int n;
  int[] out;
  List<List<Integer>> path;
  public List<Integer> eventualSafeNodes(int[][] graph) {
    n = graph.length;
    out = new int[n];
    path = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      path.add(new ArrayList<>());
    }
    for (int i = 0; i < n; i++) {
      for (int to : graph[i]) {
        out[i]++;
        path.get(to).add(i);
      }
    }
    return topSort();
  }

  private List<Integer> topSort() {
    List<Integer> ans = new LinkedList<>();
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (out[i] == 0) {
        q.add(i);
      }
    }
    while (!q.isEmpty()) {
      int cur = q.poll();
      ans.add(cur);
      for (int from : path.get(cur)) {
        --out[from];
        if (out[from] == 0) {
          q.add(from);
        }
      }
    }
    ans.sort((a, b) -> a - b);
    return ans;
  }
}