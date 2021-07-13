package year_2021.month_6;

import java.util.ArrayList;
import java.util.List;

class Lc_792 {
  List<Integer> g[];
  public int numMatchingSubseq(String s, String[] words) {
    g = new List[26];
    for (int i = 0; i < g.length; i++) {
      g[i] = new ArrayList<>();
    }
    for (int i = 0; i < s.length(); i++) {
      int index = s.charAt(i) - 'a';
      g[index].add(i);
    }
    int ans = 0;
    for (String str : words) {
      if (check(str))  {
        ans++;
      }
    }
    return ans;
  }

  private boolean check(String str) {
    int le = 0;
    for (int i = 0; i < str.length(); i++) {
      int index = str.charAt(i) - 'a';
      int pos = find(index, le);
      if (pos == -1) {
        return false;
      }
      le = pos + 1;
    }
    return true;
  }

  private int find(int ch, int start) {
    List<Integer> list = g[ch];
    int le = 0, ri = list.size() - 1;
    int ans = -1;
    while (le <= ri) {
      int mid = (le + ri) / 2;
      if (list.get(mid) >= start) {
        ans = list.get(mid);
        ri = mid - 1;
      } else {
        le = mid + 1;
      }
    }
    return ans;
  }
}

/*

// 第二种解题方法
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Solution {
  List<Item> a[];
  public int numMatchingSubseq(String s, String[] words) {
    a = new List[26];
    for (int i = 0; i < a.length; i++) {
      a[i] = new LinkedList<>();
    }
    for (String str : words) {
      int index = str.charAt(0) - 'a';
      a[index].add(new Item(str, 0));
    }
    int ans = 0;
    int len = s.length();
    for (int i = 0; i < len; i++) {
      int index = s.charAt(i) -'a';
      for (Item item : a[index]) {
        item.pos++;
      }
      Iterator<Item> it = a[index].iterator();
      while (it.hasNext()) {
        Item item = it.next();
        if (item.pos == item.str.length()) {
          ans++;
          it.remove();
        } else {
          int nextIndex = item.str.charAt(item.pos) - 'a';
          if (nextIndex == index) {
            continue;
          }
          a[nextIndex].add(item);
          it.remove();
        }
      }
    }
    return ans;
  }

  static class Item {
    String str;
    int pos;

    Item(String str, int pos) {
      this.str = str;
      this.pos = pos;
    }
  }
}
 */