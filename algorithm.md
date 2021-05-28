# 自己对算法的一些总结

### 对算法的
粗浅的自己对算法的理解.

#### 莫队算法
```text
1. 解决问题: 求解给定区间值的离线算法, 例如 n个数的数组, m个询问, 每个询问求[L, R]之间出现unique的数的个数.
2. 解决思路:
    a. 先定义一个块大小：sqrt(n), 那么一共有n/sqrt(n) = sqrt(n) 个块.
    b. 将查询[L, R]按照下面规则排序:
        i. 先按照L所在的块(L/sqrt(n))排序.
        ii. 如果i中相同，那么按照R排序.
    c. 接下来, 处理每个块中的所有询问:
       le = -1, ri = -1;
       while(le < L) del(le++);
       while(le > L) add(--le);
       while(ri < R) add(++ri);
       while(ri > R) del(ri--);
3. 时间复杂度分析:
    a. 每个块:
         i. le导致的时间复杂度: 每次询问 le 会在一个块内变化 为sqrt(n), m个询问, 所以为sqrt(n)*m.
         ii. ri导致的时间复杂度: 每次询问 ri 递增,最多变化为 n, sqrt(n) 个块，所以为sqrt(n) * n.
    b. 不同block移动导致的时间复杂度:
        i. le block的大小: sqrt(n), 一共sqrt(n) 个块，所以为sqrt(n) * sqrt(n) = n.
        ri. ri 移动为n, 一共sqrt(n),所以为n * sqrt(n).

    所以总的时间复杂度为: sqrt(n) * m + n*sqrt(n) + n + n * sqrt(n).
```

#### 前缀和与差分
```text
前缀和: sum[i] 等于 sum(a[0], ...., a[i]);
性质: 
   ① a[i] = sum[i] - sum[i - 1];
   ②sum[a[le], a[le + 1], ..., a[ri]) = sum[ri] - sum[le - 1];
   
二维前缀和: sum[i][j] = (a[0][0], .....a[i][j]) 的矩形的和.
性质：     sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + a[i][j]


差分:
    例如有数组：a[n], 那么数组 b[i] = a[i] - a[i - 1]; (i >= 1) && b[0] = a[0]; 即为数组a的差分数组.

性质：① sum[b[0]~b[i]] = a[i];
     ② 对区间a[le]-a[ri] 的每个元素全部操作，例如每个元素加一，在差分上的体现就是b[le] += 1, b[ri + 1] -= 1; 这是极其重要的性质！！！ 把o(n) 复杂度降低为o(1);o

二维差分：
     b[i][j]:   sum[b[0][0].....b[i][j]] = a[i][j];
性质：①对于矩形(x1, y1)(x2, y2) 的批量操作,例如加1， 体现在差分数组就是:
       b[x1][y1] += 1;
       b[x1][y2 + 1] -= 1;
       b[x2 + 1][y1] -= 1;
       b[x2 + 1][y2 + 1] += 1;

具体前缀和与差分练习题： https://vjudge.net/contest/440752#overview
```