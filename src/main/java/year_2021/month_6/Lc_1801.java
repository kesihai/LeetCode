package year_2021.month_6;

import java.util.PriorityQueue;

class Lc_1801 {
  private static final int MOD = (int)(1e9 + 7);
  public int getNumberOfBacklogOrders(int[][] orders) {
    PriorityQueue<Item> buy = new PriorityQueue<>((a, b) -> {
      return b.money - a.money;
    });
    PriorityQueue<Item> sell = new PriorityQueue<>((a, b) -> {
      return a.money - b.money;
    });

    // buy: sell 的价格 低于或等于 当前
    // sell: buy >= 当前

    for (int [] order : orders) {
      int money = order[0];
      int count = order[1];
      int type = order[2];
      if (type == 0) {
        // buy:
        while (count > 0 && !sell.isEmpty() && sell.peek().money <= money) {
          Item item = sell.poll();
          int mi = Math.min(item.count, count);
          count -= mi;
          item.count -= mi;
          if (item.count > 0) {
            sell.add(item);
          }
        }
        if (count > 0) {
          buy.add(new Item(money, count, type));
        }
      } else {
        while (count > 0 && !buy.isEmpty() && buy.peek().money >= money) {
          Item item = buy.poll();
          int mi = Math.min(item.count, count);
          count -= mi;
          item.count -= mi;
          if (item.count > 0) {
            buy.add(item);
          }
        }
        if (count > 0) {
          sell.add(new Item(money, count, type));
        }
      }
    }
    long ans = 0;
    while (!sell.isEmpty()) {
      ans = (ans + sell.poll().count) % MOD;
    }
    while (!buy.isEmpty()) {
      ans = (ans + buy.poll().count) % MOD;
    }
    return (int)ans;
  }

  private static class Item {
    int money;
    int count;
    int type;

    Item(int money, int count, int type) {
      this.money = money;
      this.count = count;
      this.type = type;
    }
  }
}
/*

[[16,24,0],[16,2,1],[23,28,1],[21,6,0],[17,8,0],[25,7,0]]



buy: [16, 22] [21, 6], [17,8]

sell: [23, 21]
 */