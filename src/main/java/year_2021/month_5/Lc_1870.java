package year_2021.month_5;

class Lc_1870 {
  public int minSpeedOnTime(int[] dist, double hour) {
    int le = 1, ri = (int)1e7 + 5;
    int ans = -1;
    while (le <= ri) {
      int mid = (le + ri) / 2;
      if (check(dist, mid, hour)) {
        ans = mid;
        ri = mid - 1;
      } else {
        le = mid + 1;
      }
    }
    return ans;
  }

  private boolean check(int[] dist, int rate, double hour) {
    double cost = 0;
    for (int i = 0; i < dist.length; i++) {
      if (i != dist.length - 1) {
        cost += (dist[i] - 1) / rate + 1;
      } else {
        cost += dist[i] * 1.0 / rate;
      }
    }
    return cost <= hour;
  }
}