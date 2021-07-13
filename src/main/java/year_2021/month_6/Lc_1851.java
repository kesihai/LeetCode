package year_2021.month_6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Lc_1851 {
  public int[] minInterval(int[][] intervals, int[] queries) {
    List<Integer> list = getUnique(intervals, queries);
//    System.out.println(list);
    Map<Integer, Integer> mp = new HashMap<>();
    for (int i = 0; i < list.size(); i++) {
      mp.put(list.get(i), i);
    }
    Tree tree = new Tree();
    tree.build(0, list.size() - 1);
    for (int i = 0; i < intervals.length; i++) {
     int le = mp.get(intervals[i][0]);
     int ri = mp.get(intervals[i][1]);
     tree.update(le, ri, intervals[i][1] - intervals[i][0] + 1);
    }

    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int tmp = tree.query(mp.get(queries[i]));
      if (tmp == Integer.MAX_VALUE) {
        tmp = -1;
      }
      ans[i] = tmp;
    }
    return ans;
  }

  private List<Integer> getUnique(int[][] a, int[] b) {
    Set<Integer> s = new HashSet<>();
    for (int i = 0; i < a.length; i++) {
      s.add(a[i][0]);
      s.add(a[i][1]);
    }
    for (int i = 0; i < b.length; i++) {
      s.add(b[i]);
    }
    return s.stream().sorted().collect(Collectors.toList());
  }

  static class Tree {
    int le;
    int ri;
    Tree[] child;
    int value = Integer.MAX_VALUE;

    void build(int le, int ri) {
      this.le = le;
      this.ri = ri;
      if (le == ri) {
        return;
      }
      int mid = (le + ri) / 2;
      child = new Tree[2];
      child[0] = new Tree();
      child[1] = new Tree();
      child[0].build(le, mid);
      child[1].build(mid + 1, ri);
    }

    void pushDone() {
      child[0].value = Math.min(this.child[0].value, value);
      child[1].value = Math.min(child[1].value, value);

    }
    void update(int le, int ri, int value) {
      if (this.value <= value) {
        return;
      }
      if (this.le == le && ri == this.ri) {
        this.value = Math.min(this.value, value);
        return;
      }
      pushDone();
      if (ri <= child[0].ri) {
        child[0].update(le, ri, value);
      } else if (le > child[0].ri) {
        child[1].update(le, ri, value);
      } else {
        child[0].update(le, child[0].ri, value);
        child[1].update(child[0].ri + 1, ri, value);
      }
    }

    int query(int pos) {
      if (le == ri) {
        return value;
      }
      pushDone();
      if (pos <= child[0].ri) {
        return child[0].query(pos);
      }
      return child[1].query(pos);
    }
  }
}

/*

// 优先队列的解法
class Solution {
  public int[] minInterval(int[][] intervals, int[] queries) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    int[][] q = new int[queries.length][2];
    for (int i = 0; i < queries.length; i++) {
      q[i][0] = queries[i];
      q[i][1] = i;
    }
    Arrays.sort(q, (a, b) -> a[0] - b[0]);
    PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int[] ans = new int[queries.length];
    int pos = 0;
    for (int i = 0; i < q.length; i++) {
      int start = q[i][0];
      while (pos < intervals.length && intervals[pos][0] <= start) {
        queue.add(new int[] {intervals[pos][1] - intervals[pos][0] + 1, intervals[pos][1]});
        pos++;
      }
      while (!queue.isEmpty() && queue.peek()[1] < start) {
        queue.poll();
      }
      ans[q[i][1]] = queue.isEmpty() ? -1 : queue.peek()[0];
    }
    return ans;
  }
}

 */