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