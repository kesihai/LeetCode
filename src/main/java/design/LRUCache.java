package design;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

  Map<Integer, Node> mp;
  int capacity;
  Node head;
  Node tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    mp = new HashMap<>();
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    this.head.next = tail;
    this.tail.pre = head;
  }

  public int get(int key) {
    if (!mp.containsKey(key)) {
      return -1;
    }
    Node node = mp.get(key);
    moveToFirst(node);
    return node.value;
  }

  public void put(int key, int value) {
    if (capacity == 0) {
      return;
    }
    Node node;
    if (!mp.containsKey(key)) {
      checkCapacity();
      node = new Node(key, value);
      node.next = tail;
      node.pre = tail.pre;
      tail.pre.next = node;
      tail.pre = node;
    } else {
      node = mp.get(key);
      node.value = value;
    }
    mp.put(key, node);
    moveToFirst(node);
  }

  private void moveToFirst(Node node) {
    node.next.pre = node.pre;
    node.pre.next = node.next;

    node.next = head.next;
    node.next.pre = node;
    node.pre = head;
    head.next = node;
  }

  private void checkCapacity() {
    if (mp.size() == capacity) {
      int val = tail.pre.key;
      mp.remove(val);

      tail.pre.pre.next = tail;
      tail.pre = tail.pre.pre;
    }
  }

  private static class Node {
    int value;
    int key;
    Node pre;
    Node next;

    Node(int key, int val) {
      this.key = key;
      this.value = val;
      pre = next = null;
    }
  }
}