# RMQ 算法

RMQ 能解决的问题, 树状数组和线段树都可以解决.

## 解决的问题
```
静态数组，任意区间的最大最小值

当求最小值时
dp[i][j] = min{a[i] ~ a[i + (1 << j) - 1]}
那么dp[i][j] = min(dp[i][j - 1], dp[i + (1 << (j - 1)][j - 1]);

当求[L, R] 的区间最小值时, 令len = 最大的整数 && (1 << len) <= (R - L)
那么min{a[L]~a[R]} = min(dp[le][len], dp[ri - (1 << len) + 1][len]) 

```

## 模板
```
//练习题：https://vjudge.net/contest/454679

const int N = 50005;
struct Node
{
  int mi;
  int ma;
} f[N][20];

int n, m;
int a[N];
int len;

int getLen(int n)
{
  for (int i = 0; i < 32; i++)
  {
    if ((1 << i) > n)
    {
      return i - 1;
    }
  }
  return -1;
}

void prepare()
{
  len = getLen(n);
  for (int i = 0; i <= len; i++)
  {
    int tmpLen = 1 << i;
    for (int le = 1; le + tmpLen - 1 <= n; le++)
    {
      if (i == 0)
      {
        f[le][i].mi = a[le];
        f[le][i].ma = a[le];
        continue;
      }
      f[le][i].mi = min(f[le][i - 1].mi, f[le + (1 << (i - 1))][i - 1].mi);
      f[le][i].ma = max(f[le][i - 1].ma, f[le + (1 << (i - 1))][i - 1].ma);
    }
  }
}

int queryMi(int le, int ri) {
  int tmpLen = getLen(ri - le + 1);
  return min(f[le][tmpLen].mi, f[ri - (1 << tmpLen) + 1][tmpLen].mi);
}

int queryMa(int le, int ri) {
  int tmpLen = getLen(ri - le + 1);
  return max(f[le][tmpLen].ma, f[ri - (1 << tmpLen) + 1][tmpLen].ma);
}

int main()
{
  while (~scanf("%d%d", &n, &m))
  {
    for (int i = 1; i <= n; i++)
    {
      scanf("%d", &a[i]);
    }
    prepare();
    int le, ri;
    while (m--) {
      scanf("%d%d", &le, &ri);
      printf("%d\n", queryMa(le, ri) - queryMi(le, ri));
    }
  }
  return 0;
}
```