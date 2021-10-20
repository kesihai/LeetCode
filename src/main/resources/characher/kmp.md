# KMP 算法

## 解决的问题
```text
① 求在 a字符串中, b字符串出现的次数.
② 求a字符串前缀出现的次数.
③ 求字符串的最大循环节, 如果没有循环节，那么需要添加几个字符? (循环次数至少为2)
```

## kmp的一些性质 (最大前缀、后缀的一些性质)

```text
① 一直一个字符串a 长度问n, 目前求出来fail数组, 令len=fail[n - 1]
    a. 当 len * 2 == n 时, 说明当前串是前后两个相同的串, 例如abcabc.
    b. 当 len * 2 < n 时，说明当前串类似于 abca, 那么此时如果想将串补全为循环串, 那么需要补充 n - len * 2 = 2;
    c. 当 len * 2 > n 时, 说明当前串类似于  aaaaaa 或者  abcabcab, 那么能说明：令 x = n - len, 那么a串显然是长度为x的循环串（只是n不一定能整除x而导致剩余字符长度不够x).
```

## 模板
```text
#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
#include <stack>
#include <queue>
using namespace std;

const int N = (int)(1e6 + 5);
const int M = (int)(1e4 + 5);
int fail[M];
int a[N];
int b[M];
int n, m;

void input() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) {
    scanf("%d", &a[i]);
  }
  for (int i = 0; i < m; i++) {
    scanf("%d", &b[i]);
  }
}

void initFail() {
  fail[0] = 0; // 第一个字符的最大前缀 后缀都为空.
  for (int i = 1; i < m; i++) {
    int k = fail[i - 1]; // i 这个的最大前缀是多少，首先 i-1 的最大前缀如果是3, 那么b[i] 就应该与b[3] 比较
    while (b[k] != b[i] && k != 0) {
      k = fail[k - 1]; // 如果 b[i] 与b[3] 不相等, 那么应该看b[2] 的最大前缀为多少
    }
    if (b[k] == b[i]) {
      fail[i] = k + 1;
    } else{
      fail[i] = 0;
    }
  }
}

void debugFail() {
  for (int i = 0; i < m; i++) {
    printf("%d ", fail[i]);
  }
  printf("\n");
}

int solve() {
  initFail();
  //debugFail();
  int i = 0, j = 0;
  while (i < n && j < m) {
    if (a[i] == b[j]) {
      i++;
      j++;
      if (j == m) {
        return i - m + 1;
      }
      continue;
    }
    if (j == 0) {
      i++;
      continue;
    }
    j = fail[j - 1];
  }
  return -1;
}

int main() {
  int t;
  scanf("%d", &t);
  while(t--) {
    input();
    printf("%d\n", solve());
  }
  return 0;
}
```


## 扩展kmp算法

```text
解决问题:
假如有两个字符串A,B, 求对于每一个i (0 <= i < lenB), B.subString(i) 和 A 的最长公共前缀.


思想：
① 第一步求 z 函数, 其中z[i] 代表 A[i ~ Len) 与 A[0 ~lenA) 的最长公共前缀.
② 第二步借助z函数,求出来 b[i~lenB) 与 A 的最长公共前缀, 其中 i >= 0 && i < lenB.
```

```text
#include <bits/stdc++.h>
using namespace std;

const int N = 50005;
char a[N];
char b[N];
char c[N * 2];
int z[N];
int extend[N * 2];
int lenA;
int lenB;

/* 自己跟自己匹配, z[i] 代表 a[i~lenA] 与 a[0~lenA] 的最长公共前缀 */
void generateZ() {
  lenA = strlen(a);
  int le = 0;
  z[le] = 0;
  for (int i = 1; i < lenA; i++) {
    int ri = le + z[le];
    int p = 0;
    if (ri > i) {
      p = min(ri - i, z[i - le]);
    }
    while (i + p < lenA && a[i + p] == a[p]) {
      p++;
    }
    z[i] = p;
    if (i + z[i] > le + z[le]) {
      le = i;
    }
  }
  z[0] = lenA;
}

/* 借助 z数组, 算出每一个 b[i~lenB] 与 a[0~lenA]的最长公共前缀. */
void generateExtend() {
  lenB = strlen(b);
  int le = -1;
  for (int i = 0; i < lenB; i++) {
    int ri = le == -1 ? -1 : le + extend[le];
    int p = 0;
    if (ri > i) {
      p = min(ri - i, z[i - le]);
    }
    while (p < lenA && i + p < lenB && a[p] == b[i + p]) {
      p++;
    }
    extend[i] = p;
    if (le == -1 || i + extend[i] > le + extend[le]) {
      le = i;
    }
  }
}

void solve() {
  generateZ();
  generateExtend();
  int ans = 0;
  for (int i = 0; i < lenB; i++) {
    if (i + extend[i] == lenB) {
      ans = extend[i];
      break;
    }
  }
  if (ans == 0) {
    printf("0\n");
  } else {
    a[ans] = '\0';
    printf("%s %d\n", a, ans);
  }
}

int main() {
  while (~scanf("%s", a)) {
    scanf("%s", b);
    solve();
  }
  return 0;
}

/*

clinton
homer
riemann
marjorie

*/
```