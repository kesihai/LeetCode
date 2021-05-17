package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class LFUCache {
  Map<Integer, List<Integer>> countList;
  Map<Integer, Integer> count;
  Map<Integer, Integer> mp;
  int min = -1;
  int capacity;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    min = -1;
    mp = new HashMap<>();
    count = new HashMap<>();
    countList = new HashMap<>();
  }

  public int get(int key) {
    if (!mp.containsKey(key)) {
      return -1;
    }
    int value = mp.get(key);
    visit(key);
    return value;
  }

  public void put(int key, int value) {
    if (capacity == 0) {
      return;
    }
    if (mp.containsKey(key))  {
      mp.put(key, value);
      visit(key);
      return;
    }

    checkCapacity();
    mp.put(key, value);
    count.put(key, 0);
    if (!countList.containsKey(0)) {
      countList.put(0, new LinkedList<>());
    }
    countList.get(0).add(key);
    min = 0;
    visit(key);
  }

  private void visit(int key) {
    int c = count.get(key);
    count.put(key, c + 1);
    countList.get(c).remove(Integer.valueOf(key));
    if (!countList.containsKey(c + 1)) {
      countList.put(c + 1, new LinkedList<>());
    }
    countList.get(c + 1).add(key);
    if (countList.get(c).size() == 0 && min == c) {
      min++;
    }
  }

  private void checkCapacity() {
    if (mp.size() == capacity) {
      Integer value = countList.get(min).get(0);
      countList.get(min).remove(0);
      count.remove(value);
      mp.remove(value);
      if (countList.get(min).size() == 0) {
        min++;
      }
    }
  }
}