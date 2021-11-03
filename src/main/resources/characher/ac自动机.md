# AC 自动机

## 思想
```text
todo
```

## 基础代码
求n个串在文本串text出现的次数.

## 加强版
```
① 求出现次数最多的次数.
② 求出现次数最多的模式串.

转化为求每个串出现的次数, 我们将count改为index, 因为求次数, 因此不能将count 置为-1, 而是不断的匹配下去.

但是这是时间复杂度很高. (模式串长度 * 文本串长度)

```

## 加强版2

```text
优化上面加强版的时间复杂度问题:
显然，时间浪费在了重复的路径上面, 那么只需要有拓扑排序的思想, 肯定是可以优化这个问题的.
```

## 练习
https://vjudge.net/contest/464019

```java

// 基础本代码
/*
题意: 输入n个串和文本串 text, 求n个串有多少个在文本串出现过.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static int n;
    public static void main(String[] args) {
        n = in.nextInt();
        in.nextLine();
        Node node = new Node();
        // 建立字典树
        for (int i = 0; i < n; i++) {
            String str = in.nextLine();
            node.add(str);
        }
        // 建立ac 自动机
        node.buildGraph();
        String text = in.nextLine();

        // 匹配
        System.out.println(node.query(text));
    }

    private static class Node {
        Node[] child;
        int count;
        Node fail;

        Node() {
            child = new Node[26];
            count = 0;
            fail = null;
        }

        public void add(String str) {
            Node tmp = this;
            for (int i = 0; i < str.length(); i++) {
                int v = str.charAt(i) - 'a';
                if (tmp.child[v] == null) {
                    tmp.child[v] = new Node();
                }
                tmp = tmp.child[v];
            }
            tmp.count++;
        }

        public void buildGraph() {
            Queue<Node> q = new LinkedList<>();
            Node node = this;
            // 先把第一层的节点的fail指针都指向根节点.
            for (int i = 0; i < node.child.length; i++) {
                if (node.child[i] != null) {
                    node.child[i].fail = node;
                    q.add(node.child[i]);
                }
            }

            while (!q.isEmpty()) {
                Node cur = q.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.child[i] == null) {
                        continue;
                    }
                    Node fail = cur.fail;
                    while (fail != this && fail.child[i] == null) {
                        fail = fail.fail;
                    }
                    if (fail.child[i] != null) {
                        cur.child[i].fail = fail.child[i];
                    } else {
                        cur.child[i].fail = fail;
                    }
                    q.add(cur.child[i]);
                }
            }
        }

        public int query(String str) {
            Node node = this;
            int ans = 0;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                Node tmp = node.child[index];
                while (tmp != null && tmp != this && tmp.count != -1) {
                    ans += tmp.count;
                    tmp.count = -1;
                    tmp = tmp.fail;
                }
                if (node.child[index] == null) {
                    node = node.fail;
                    // 因为找到的节点时最长公共后缀的点，还没有匹配i这个字符
                    if (node != this) {
                        i--;
                    }
                } else {
                    node = node.child[index];
                }
            }
            return ans;
        }
    }
}
```

```java
// 简单加强,求每个字符串出现的次数
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static int n;
    static String[] a;
    static int[] vis;
    public static void main(String[] args) {
        while (true) {
            n = in.nextInt();
            if (n == 0) {
                break;
            }
            in.nextLine();
            a = new String[n];
            Node node = new Node();
            for (int i = 0; i < n; i++) {
                a[i] = in.nextLine();
                node.add(i, a[i]);
            }
            node.buildGraph();
            String text = in.nextLine();
            node.query(text);
        }
    }

    private static class Node {
        Node[] child;
        int flag;
        Node fail;

        public Node() {
            child = new Node[26];
            flag = -1;
            fail = null;
        }

        public void add(int pos, String str) {
            Node tmp = this;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (tmp.child[index] == null) {
                    tmp.child[index] = new Node();
                }
                tmp = tmp.child[index];
            }
            tmp.flag = pos;
        }

        public void buildGraph() {
            Queue<Node> q = new LinkedList<>();
            Node node = this;
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null) {
                    node.child[i].fail = this;
                    q.add(node.child[i]);
                }
            }
            while (!q.isEmpty()) {
                node = q.poll();
                for (int i = 0; i < 26; i++) {
                    if (node.child[i] == null) {
                        continue;
                    }
                    Node fail = node.fail;
                    while (fail != this && fail.child[i] == null) {
                        fail = fail.fail;
                    }
                    if (fail.child[i] == null) {
                        node.child[i].fail = fail;
                    } else {
                        node.child[i].fail = fail.child[i];
                    }
                    q.add(node.child[i]);
                }
            }
        }

        public void query(String str) {
            vis = new int[n];
            Node node = this;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                Node tmp = node.child[index];
                while (tmp != null && tmp != this) {
                    if (tmp.flag != -1) {
                        vis[tmp.flag]++;
                    }
                    tmp = tmp.fail;
                }
                if (node.child[index] == null) {
                    if (node == this) {
                        continue;
                    }
                    node = node.fail;
                    i--;
                } else {
                    node = node.child[index];
                }
            }
            int ma = 0;
            for (int i = 0; i < vis.length; i++) {
                if (vis[i] > vis[ma]) {
                    ma = i;
                }
            }
            System.out.println(vis[ma]);
            for (int i = 0; i < vis.length; i++) {
                if (vis[i] == vis[ma]) {
                    System.out.println(a[i]);
                }
            }
        }
    }
}
```

```java
// 基于拓扑排序的加强，求每个子串出现的次数. (会有重复的模式串)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static int n;
    static String[] a;
    static int[] ans;

    public static void main(String[] args) {
        n = in.nextInt();
        in.nextLine();
        a = new String[n];
        Node node = new Node();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLine();
            node.add(i, a[i]);
        }
        String text = in.nextLine();
        node.buildGraph();
        node.query(text);
        node.topSort();
    }

    private static class Node {
        Node[] child;
        List<Integer> flag;
        Node fail;
        int inCount;
        int count;

        public Node() {
            child = new Node[26];
            flag = new ArrayList<>();
            fail = null;
            inCount = 0;
            count = 0;
        }

        public void add(int pos, String str) {
            Node node = this;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';
                if (node.child[index] == null) {
                    node.child[index] = new Node();
                }
                node = node.child[index];
            }
            node.flag.add(pos);
        }

        public void buildGraph() {
            Queue<Node> q = new LinkedList<>();
            Node node = this;
            for (int i = 0; i < 26; i++) {
                if (node.child[i] != null) {
                    node.child[i].fail = node;
                    q.add(node.child[i]);
                }
            }
            while (!q.isEmpty()) {
                node = q.poll();
                for (int i = 0; i < 26; i++) {
                    if (node.child[i] == null) {
                        continue;
                    }

                    Node fail = node.fail;
                    while (fail != this && fail.child[i] == null) {
                        fail = fail.fail;
                    }
                    if (fail.child[i] == null) {
                        node.child[i].fail = fail; // this
                    } else {
                        node.child[i].fail = fail.child[i];
                        fail.child[i].inCount++;
                    }
                    q.add(node.child[i]);
                }
            }
        }

        public void query(String str) {
            // 前面已经把拓扑排序建好了.
            Node node = this;
            for (int i = 0; i < str.length(); i++) {
                int index = str.charAt(i) - 'a';

                Node tmp = node.child[index];
                if (tmp != null) {
                    tmp.count++;
                }

                if (node.child[index] == null) {
                    if (node == this) {
                        continue;
                    }
                    node = node.fail;
                    i--;
                } else {
                    node = node.child[index];
                }
            }
        }

        private void topSort() {
            Queue<Node> q = new LinkedList<>();
            Queue<Node> tmp = new LinkedList<>();
            tmp.add(this);
            ans = new int[n];
            // 添加入度为0的点
            while (!tmp.isEmpty()) {
                Node cur = tmp.poll();
                if (cur != this && cur.inCount == 0) {
                    q.add(cur);
                }
                for (int i = 0; i < 26; i++) {
                    if (cur.child[i] != null) {
                        tmp.add(cur.child[i]);
                    }
                }
            }

            while (!q.isEmpty()) {
                Node cur = q.poll();
                if (cur.flag.size() > 0) {
                    for (int index : cur.flag) {
                        ans[index] = cur.count;
                    }
                }
                Node fail = cur.fail;
                if (fail != this) {
                    fail.count += cur.count;
                    fail.inCount--;
                    if (fail.inCount == 0) {
                        q.add(fail);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                System.out.println(ans[i]);
            }
        }
    }
}

```