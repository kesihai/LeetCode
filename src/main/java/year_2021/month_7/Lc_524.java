package year_2021.month_7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Lc_524 {
  public String findLongestWord(String s, List<String> dictionary) {
    List<Item>[] a = new List[26];
    for (int i = 0; i < a.length; i++) {
      a[i] = new ArrayList<>();
    }
    for (String str : dictionary) {
      int index = str.charAt(0) - 'a';
      a[index].add(new Item(str, 0));
    }
    List<String> ans = new ArrayList<>();
    int len = s.length();
    for (int i = 0; i < len; i++) {
      int index = s.charAt(i) - 'a';
      Iterator<Item> it = a[index].iterator();
      while (it.hasNext()) {
        Item item = it.next();
        item.pos++;
        if (item.pos == item.str.length()) {
          ans.add(item.str);
          it.remove();
        } else {
          int nextIndex = item.str.charAt(item.pos) - 'a';
          if (nextIndex != index) {
            a[nextIndex].add(item);
            it.remove();
          }
        }
      }
    }
    ans.sort((x, y) -> {
      if (x.length() != y.length()) {
        return y.length() - x.length();
      }
      return x.compareTo(y);
    });
    return ans.size() > 0 ? ans.get(0) : "";
  }

  private static class Item {
    String str;
    int pos;

    Item(String str, int pos) {
      this.str = str;
      this.pos = pos;
    }
  }
}