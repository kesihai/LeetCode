package year_2021.month_5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Lc_1462 {
  int[] in;
  List<Set<Integer>> pre;
  List<List<Integer>> path;
  int numCourses;
  public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
    in = new int[numCourses];
    pre = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      pre.add(new HashSet<>());
    }
    path = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      path.add(new ArrayList<>());
    }

    for (int[] item: prerequisites) {
      int from = item[1];
      int to = item[0];
      in[to]++;
      path.get(from).add(to);
    }
    this.numCourses = numCourses;
    topQueue();
    List<Boolean> ans = new LinkedList<>();
    for (int i = 0; i < queries.length; i++) {
      int from = queries[i][1];
      int to = queries[i][0];
      ans.add(pre.get(to).contains(from));
    }
    return ans;
  }

  private void topQueue() {
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (in[i] == 0) {
        q.add(i);
      }
    }
    while (!q.isEmpty()) {
      int cur = q.poll();
      for (int to : path.get(cur)) {
        pre.get(to).addAll(pre.get(cur));
        pre.get(to).add(cur);
        --in[to];
        if (in[to] == 0) {
          q.add(to);
        }
      }
    }
  }
}