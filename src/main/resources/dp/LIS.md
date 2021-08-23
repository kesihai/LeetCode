# LIS （最长递增子序列）

## 解决问题
```text
求一个数组的最长递增子序列.
时间复杂度: 0(n * log(n))
```

## 变形
```text
Leetcode 1713
已知数组a, b, a中的数字都是唯一的，b的数字有重复，求 a,b的最长公共子序列 (a.len <= 10^5)

思路：对a的每一个数字映射到一个数字, mp[a[i]] = i, 求b上的a的最长上升子序列.

import java.util.HashMap;
import java.util.Map;

class Solution {
    int[] dp;
    int len;
    Map<Integer, Integer> mp;
    public int minOperations(int[] target, int[] arr) {
        mp = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            mp.put(target[i], i);
        }
        dp = new int[target.length];
        len = 0;
        for (int v : arr) {
            if (!mp.containsKey(v)) {
                continue;
            }
            int vv = mp.get(v);
            int pos = getLen(vv);
            dp[pos] = vv;
            if (pos == len) {
                len++;
            }
        }
        return target.length - len; // 上面的题意答案应是 len
    }

    int getLen(int v) {
        if (len == 0) {
            return len;
        }
        int le = 0;
        int ri = len - 1;
        int ans = -1;
        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (dp[mid] < v) {
                ans = mid;
                le = mid + 1;
            } else {
                ri = mid - 1;
            }
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        int[] a = new int[] {6,4,8,1,3,2};
        int[] b = new int[] {4,7,6,2,3,8,6,1};
        Solution s = new Solution();
        System.out.println(s.minOperations(a, b));
    }
}
```
