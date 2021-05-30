package dailyhard;

import java.util.LinkedList;
import java.util.Queue;

class Lc_980 {
  int sx, sy;
  int ex, ey;
  int target = 0;

  private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public int uniquePathsIII(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    prepare(grid);
    Queue<Item> q = new LinkedList<>();
    q.add(new Item(sx, sy, 1 << (sx * m + sy)));
    int ans = 0;
    while (!q.isEmpty()) {
      Item cur = q.poll();
      if (grid[cur.x][cur.y] == 2) {
        if (cur.value == target) {
          ans++;
        }
        continue;
      }
      for (int i = 0; i < dirs.length; i++){
        int xx = cur.x + dirs[i][0];
        int yy = cur.y + dirs[i][1];
        if (xx < 0 || xx >= n || yy < 0 || yy >= m) {
          continue;
        }
        if (grid[xx][yy] == -1) {
          continue;
        }
        int len = xx * m + yy;
        if ((cur.value & (1 << len)) != 0) {
          continue;
        }
        Item item = new Item(xx, yy, cur.value | (1 << len));
        q.add(item);
      }
    }
    return ans;
  }

  public void prepare(int[][] grid) {
    target = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] != -1) {
          target |= (1 << (i * grid[i].length + j));
        }
        if (grid[i][j] == 1) {
          sx = i;
          sy = j;
        }
        if (grid[i][j] == 2) {
          ex = i;
          ey = j;
        }
      }
    }
  }

  public static class Item {
    int x;
    int y;
    int value;

    Item(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
}