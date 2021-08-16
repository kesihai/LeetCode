# Manacher

## 解决问题
① 查找一个字符串最长的回文串.
② 得出每一个字符为中心的回文串长度.

## 面临问题
问题: 求一个字符串中，最长的回文子串长度? 其中字符串长度 <= 10^5

```
思路1：枚举每一个子串，然后判断是否为回文串
复杂度：o(n^3)， 显然不行
```

```text
思路2：以每一个i为终点，向两周扩散
复杂度：o(n^2), 还是不行

但是跟思路1相比，已经是一个量级的提升, 还是很神奇的.
```

```text
思路3： manacher

假如有字符串: ***a*e**b**d*c， 其中*表示的字符先不考虑, abdc代表的只是位置，不代表真正的字符内容.
① 假如以b这个位置为中心的回文串是[a,c], 当然这个可以通过以b为中心扩展出来.
② 因为我们已经求出来了b为中心的回文串，那么当我们求以d为中心的回文串时:
   a. 我们可以找到d关于b对称的e, 那么d和e是关于b完全对称的, 因此 len[d] = Math.min(len[e], c - d + 1) // 其中len[i] 代表以i为中心的回文串一半长度.
③ 通过维护一个max (i + len[i]), 并且不断的重复②，我们就可以以o(n) 的时间复杂度算出每个节点为中心的回文串长度.

④ but 前面的算法只是考虑到奇数长度的回文串，因此, 我们可以将原字符串加入一些字符总是变成前面的样子，
如有字符串: abccba 变为: $*a*b*c*c*b*a*#
如有字符串: abcba  变为: $*a*b*c*b*a*#
```

代码模板(todo)
```cpp
// hdu 3068
#include <iostream>
#include <cstdio>
#include <cstring>
#include <algorithm>
#include <queue>
#include <stack>
#include <map>
using namespace std;
const int N = 110005;
const long long MOD = 1000000007L;

char a[N];
char b[N * 2];
int n;
int len;
int dp[N * 2];

int solve() {
  int id = 0;
  dp[id] = 0;
  int ans = 0;
  for (int i = 1; i < len; i++) {
    if (id + dp[id] > i) { // 等价于 id + dp[id] - 1 >= i
      dp[i] = min(dp[id - (i - id)], id + dp[id] - i); // min(对称点长度, 不能超过 i 到 id + dp[id]);
    } else {
      dp[i] = 1;
    }
    while (b[i - dp[i]] == b[i + dp[i]]) { // 不需要考虑数组越界问题
      dp[i]++;
    }
    if (i + dp[i] > id + dp[id]) {
      id = i;
    }
    ans = max(ans, dp[i]);
  }
  return ans - 1;
}

void prepare() {
  len = 0;
  b[len++] = '$';
  b[len++] = '*';
  for (int i = 0; i < n; i++) {
    b[len++] = a[i];
    b[len++] = '*';
  }
  b[len++] = '#';
  b[len] = '\0';
}

int main() {
  while (~scanf("%s", a)) {
    n = strlen(a);
    if (n == 0) {
      printf("0\n");
      continue;
    }
    prepare();
    printf("%d\n", solve());
  }
  return 0;
}

```



