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
1. 前缀和: sum[i] 等于 sum(a[0], ...., a[i]);
性质: 
   ① a[i] = sum[i] - sum[i - 1];
   ②sum[a[le], a[le + 1], ..., a[ri]) = sum[ri] - sum[le - 1];
   
2. 差分:
   给定数组:a[0], a[1], ..., a[n - 1],
   数组: b[0~n-1], 其中b[0] = a[0], 当 i > 0 && i < n时, b[i] = b[i] - b[i - 1]


```