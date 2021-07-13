package year_2021.month_6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Lc_524 {
  public String findLongestWord(String s, List<String> dictionary) {
    List<String> ans = new ArrayList<>();
    List<Item> a[] = new List[26];
    for (int i = 0; i < a.length; i++) {
      a[i] = new LinkedList<>();
    }
    for (String str : dictionary) {
      int index = str.charAt(0) - 'a';
      a[index].add(new Item(str, 0));
    }

    int len = s.length();
    for (int i = 0; i < len; i++) {
      int index = s.charAt(i) - 'a';
      for (Item item : a[index]) {
        item.pos++;
      }
      Iterator<Item> it = a[index].iterator();
      while (it.hasNext()) {
        Item item = it.next();
        if (item.pos == item.str.length()) {
          ans.add(item.str);
          it.remove();
          continue;
        }
        int nextIndex = item.str.charAt(item.pos) -'a';
        if (nextIndex == index) {
          continue;
        }
        it.remove();
        a[nextIndex].add(item);
      }
    }
    ans.sort((x, y) -> {
      if (x.length() != y.length()) {
        return y.length() - x.length();
      }
      return x.compareTo(y);
    });
    if (ans.size() == 0) {
      return "";
    }
    return ans.get(0);

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