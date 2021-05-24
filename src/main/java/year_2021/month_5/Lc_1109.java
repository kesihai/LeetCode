package year_2021.month_5;

class Lc_1109 {
  public int[] corpFlightBookings(int[][] bookings, int n) {
    int[] ans = new int[n];
    for (int i = 0; i < bookings.length; i++) {
      int from = bookings[i][0] - 1;
      int to = bookings[i][1] - 1;
      ans[from] += bookings[i][2];
      if (to + 1 < ans.length) {
        ans[to + 1] -= bookings[i][2];
      }
    }
    for (int i = 1; i < ans.length; i++) {
      ans[i] += ans[i - 1];
    }
    return ans;
  }
}