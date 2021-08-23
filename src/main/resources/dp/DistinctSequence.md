# Distinct sub sequence

## 字符串唯一子序列个数统计

```text
题目：给定一个字符串, 求字符串的唯一子串个数是多少? 字符串长度n <= 10^5.
    例如: aac 的唯一子串个数为: a c  ac aac  (相同的ac 只能算一次)
https://leetcode.com/problems/distinct-subsequences-ii/submissions/


方法①:
    令dp[i] 代表 [1~i] 的唯一子串的个数, 并不需要以i结尾, 那么 dp[i] = dp[i - 1] * 2 ??? 不选这个i和选这个i，一共两种情况.
    但是选了这个i会导致一个重复的问题，那么重复了多少？ 重复的个数为 dp[last[s[i]] -1]; // last[s[i]] 代表i这个位置的字符最近出现的位置.
    所以dp[i] = dp[i - 1] * 2 - dp[last[i] - 1];
    最后答案为 dp[n] - 1 // 排除空串的情况.

方法②：
    令dp[i] 代表一定以i结尾的唯一子串个数, 如果dp[i] = sum{dp[last[s[i]] ~ i-1]}
    最后答案为: sum{dp[1~n]}
```

```java
// 方法①代码
import java.util.Arrays;

class Solution {
    final int MOD = (int)(1e9+7);
    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] pre = new int[26];
        int[] dp = new int[n + 1];
        Arrays.fill(pre, -1);
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            int index = s.charAt(i - 1) - 'a';
            dp[i] = dp[i - 1] * 2 % MOD;
            if (pre[index] != -1) {
                dp[i] = (dp[i] - dp[pre[index] - 1] + MOD) % MOD;
            }
            pre[index] = i;
        }
        return (dp[s.length()] - 1 + MOD) % MOD; // 排除空串的情况
    }
}

// 方法②代码
class Solution {
    final int MOD = (int)(1e9+7);
    int[] a;
    int[] dp;
    int[] pre;
    int n;
    public int distinctSubseqII(String s) {
        n = s.length() + 3;
        a = new int[n];
        dp = new int[n];
        pre = new int[26];
        int ans = 0;
        add(1, 1);
        for (int i = 0; i < s.length(); i++) {
            int pos = i + 2;
            int index = s.charAt(i) - 'a';
            int le = pre[index];
            dp[pos] = query(le, pos - 1);
            add(pos, dp[pos]);
            ans = (ans + dp[pos]) % MOD;
            pre[index] = pos;
        }
        return ans;
    }

    private void add(int pos, int v) {
        while (pos < n) {
            a[pos] = (a[pos] + v) % MOD;
            pos += lowbit(pos);
        }
    }

    private int query(int le, int ri) {
        return (query(ri) - query(le - 1) + MOD) % MOD;
    }

    private int query(int pos) {
        int ans = 0;
        while (pos > 0) {
            ans = (ans + a[pos]) % MOD;
            pos -= lowbit(pos);
        }
        return ans;
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}
```

