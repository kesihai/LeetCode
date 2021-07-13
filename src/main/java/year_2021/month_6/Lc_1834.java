package year_2021.month_6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Lc_1834 {
  public int[] getOrder(int[][] tasks) {
    List<Item> list = new ArrayList<>(tasks.length);
    for (int i = 0; i < tasks.length; i++) {
      list.add(new Item(tasks[i][0], tasks[i][1], i));
    }
    list.sort((a, b) -> a.start - b.start);
    int[] ans = new int[tasks.length];

    PriorityQueue<Item> q = new PriorityQueue<>((a, b) -> {
      if (a.cost != b.cost) {
        return a.cost - b.cost;
      }
      return a.index - b.index;
    });
    int cnt = 0;
    int pos = 0;
    int time = -1;

    while (pos < list.size() || !q.isEmpty()) {
      if (pos < list.size() && q.isEmpty()) {
        time = Math.max(time, list.get(pos).start);
      }
      while (pos < list.size() && time >= list.get(pos).start) {
        q.add(list.get(pos++));
      }
      Item item = q.poll();
      ans[cnt++] = item.index;
      time = time + item.cost;
    }
    return ans;
  }

  public static class Item {
    int start;
    int cost;
    int index;
    Item(int start, int cost, int index) {
      this.start = start;
      this.cost = cost;
      this.index = index;
    }
  }
}