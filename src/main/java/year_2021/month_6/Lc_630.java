package year_2021.month_6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Lc_630 {
  public int scheduleCourse(int[][] courses) {
    List<Item> list = new ArrayList<>(courses.length);
    for (int i = 0; i < courses.length; i++) {
      list.add(new Item(courses[i][0], courses[i][1]));
    }
    list.sort((a, b) -> a.end - b.end);
    int time = 1;
    PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
    int n = list.size();
    for (Item item : list) {
      int duration = item.duration;
      int end = item.end;
      if (time + duration - 1 <= end) {
        q.add(duration);
        time = time + duration;
      } else if (!q.isEmpty() && q.peek() > duration) {
        time -= q.poll();
        time = time + duration;
        q.add(duration);
      }
    }
    return q.size();
  }

  public static class Item {
    Item(int start, int end) {
      this.duration = start;
      this.end = end;
    }
    int duration;
    int end;
  }
}
/*

[[100,200],[200,1300],[1000,1250],[2000,3200]]
[[1,2]]
[[3,2],[4,3]]
[[1,2],[2,3]]
[[7,16],[2,3],[3,12],[3,14],[10,19],[10,16],[6,8],[6,11],[3,13],[6,16]]
[[7,17],[3,12],[10,20],[9,10],[5,20],[10,19],[4,18]]
 */