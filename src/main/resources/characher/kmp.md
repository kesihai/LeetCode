# KMP 算法



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