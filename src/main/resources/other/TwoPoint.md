# TWO POINT

## 简单应用 
+ 合并两个有序的数组.
+ 求两个有序数组中, 第二个数组的每一个位置的数，比第一个数组中多少个数绝对大.
+ 求两个有序数组中 (i, j) 满足a[i] == b[j] 的对数.

## 基础应用
+ 查询**正整数**数组a[1~n]中，最长的子数组 && 这个整数组的和 <= k.
+ 查询**正整数**数组a[1~n]中，子数组个数, 其中子数组和需要 <= k. (确定右端点, 然后最长的子数组满足 sum{a[L, R]} <=k, 那么以R结尾的子数组个数是 R - L + 1)
+ 查询**正整数**数组a[1~n]中, 最短的子数组 && 这个数组的和 >= k.
+ 查询**正整数**数组a[1~n]中,子数组个数,其中子数组和 >= k.  (确定左端点, 然后最长的子数组，满足 sum{a[L, R]} >= k, 那么以L为左短点的数组个数 (n - ri + 1))
+ 查询**正整数**数组a[1~n]中，子数组个数, 其中子数组需要满足unique数字 <= k.
+ 查询**正整数**数组a[1~n]中，子数组个数, 其中子数组 要求最大最小值差 <= k. 