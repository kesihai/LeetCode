package year_2021.month_6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Lc_1383 {
  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    List<Item> list = new ArrayList<>(speed.length);
    for (int i = 0; i < speed.length; i++) {
      list.add(new Item(speed[i], efficiency[i]));
    }
    list.sort((a, b) -> {
      if (a.efficiency!= b.efficiency) {
        return a.efficiency- b.efficiency;
      }
      return a.speed - b.speed;
    });
    int sum = 0;
    long ans = 0;
    PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
    for (int i = list.size() - 1; i >= 0; i--) {
      q.add(list.get(i).speed);
      sum += list.get(i).speed;
      if (q.size() > k) {
        sum -= q.poll();
      }
      ans = Math.max(ans, list.get(i).efficiency* 1L * sum);
    }
    return (int) (ans % (100000007));
  }

  public static class Item {
    int speed;
    int efficiency;

    Item(int speed, int efficiency) {
      this.speed = speed;
      this.efficiency = efficiency;
    }
  }
}