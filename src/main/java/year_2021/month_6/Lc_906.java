package year_2021.month_6;

import java.util.ArrayList;
import java.util.List;

class Lc_906 {
  public int superpalindromesInRange(String left, String right) {
    List<Long> list = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
      list.add(i * 1L);
    }

    for (int i = 1; i < 100000; i++) {
      String L = String.valueOf(i);
      String R = new StringBuffer(L).reverse().toString();
      list.add(Long.valueOf(L + R));
      for (int j = 0; j < 10; j++) {
        list.add(Long.valueOf(L + j + R));
      }
    }

    Long L = Long.valueOf(left);
    Long R = Long.valueOf(right);
    int ans = 0;
    for (Long item : list) {
      item = item * item;
      if (item < L || item > R) {
        continue;
      }
      ans += check(String.valueOf(item)) ? 1 : 0;
    }
    return ans;
  }

  private boolean check(String s) {
    int le = 0, ri = s.length() - 1;
    while (le < ri) {
      if (s.charAt(le++) != s.charAt(ri--)) {
        return false;
      }
    }
    return true;
  }
}