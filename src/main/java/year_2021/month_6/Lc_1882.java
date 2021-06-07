package year_2021.month_6;

import java.util.PriorityQueue;

/*
  有一群处理任务的机器， 有一批待处理的任务.
 */
class Lc_1882 {
  public int[] assignTasks(int[] servers, int[] tasks) {
    int[] ans = new int[tasks.length];
    PriorityQueue<Item> free = new PriorityQueue<>((a, b) -> {
      if (a.weight != b.weight) {
        return a.weight - b.weight;
      }
      return a.id - b.id;
    });
    PriorityQueue<Item> used = new PriorityQueue<> ((a, b) -> {
      if (a.freeTime != b.freeTime) {
        return a.freeTime - b.freeTime;
      }
      if (a.weight != b.weight) {
        return a.weight - b.weight;
      }
      return a.id - b.id;
    });
    for (int i = 0; i < servers.length; i++) {
      free.add(new Item(i, servers[i], 0));
    }

    for (int i = 0; i < tasks.length; i++) {
      int time = i;
      while (!used.isEmpty() && used.peek().freeTime <= time) {
        free.add(used.poll());
      }
      if (!free.isEmpty()) {
        Item item = free.poll();
        ans[i] = item.id;
        item.freeTime = i + tasks[i];
        used.add(item);
      } else {
        Item item = used.poll();
        ans[i] = item.id;
        item.freeTime = item.freeTime + tasks[i];
        used.add(item);
      }
    }
    return ans;
  }

  private static class Item {
    Item(int id, int weight, int freeTime) {
      this.id = id;
      this.weight = weight;
      this.freeTime = freeTime;
    }
    int freeTime;
    int id;
    int weight;
  }
}