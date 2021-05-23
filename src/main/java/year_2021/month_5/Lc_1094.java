package year_2021.month_5;

class Lc_1094 {
  public boolean carPooling(int[][] trips, int capacity) {
    int[] time = new int[1005];
    for (int i = 0; i < trips.length; i++) {
      time[trips[i][1]] += trips[i][0];
      time[trips[i][2]] -= trips[i][0];
    }
    if (time[0] > capacity) {
      return false;
    }
    for (int i = 1; i < time.length; i++) {
      time[i] += time[i - 1];
      if (time[i] > capacity) {
        return false;
      }
    }
    return true;
  }
}