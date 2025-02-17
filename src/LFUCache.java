import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class LFUCache {
  class Node {
    int key, val, freq;
    Node next, prev;
    Node(int key, int val) {
      this.key = key;
      this.val = val;
      this.freq = 1;
      this.next = null;
      this.prev = null;
    }
  }
  class DoublyLinkedList {
    Node head, tail;
    int size;
    DoublyLinkedList() {
      head = new Node(0, 0);
      tail = new Node(0, 0);
      size = 0;
      head.next = tail;
      tail.prev = head;
      head.prev = null;
      tail.next = null;
    }

    void insertHead(Node node) {
      node.next = head.next;
      head.next.prev = node;
      head.next = node;
      node.prev = head;
      size++;
    }

    Node remove(Node node) {
      Node removed = node;
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.prev = null;
      node.next = null;
      size--;
      return removed;
    }
    Node removeTail() {
      if(size==0) return null;
      Node removed = tail.prev;
      tail.prev.prev.next = tail;
      tail.prev = tail.prev.prev;
      removed.prev=removed.next = null;
      size--;
      return removed;
    }
  }
  int maxCapacity;
  int minFreq = 0;
  HashMap<Integer, Node> map;
  HashMap<Integer, DoublyLinkedList> freqMap;
  // DoublyLinkedList dll;//這個不需要,因為每個freMap會有自己的doubly linked list,只要處理當freq ties, 要移除LRU

  public LFUCache(int capacity) {
    maxCapacity = capacity;
    // int minFreq = Integer.MAX_VALUE;
    map = new HashMap<>();
    freqMap = new HashMap<>();
    // System.out.printf("LFUCache minFreq:%d\n", minFreq);
  }

  // get的logic,先看有沒有,沒有傳-1, 有的話需要
  // 1. get node from map. 2. freqMap拿出這個node,在移除這個node
  // 3. 移除old freq entry 如果node拿出來後沒有其他nodes
  // 4. 更新freq, 看freqMap有沒有這個freq, 沒有的話,產生新的,有的話,insertHead
  // 5. 更新minFreq,這是用來移除LFU
  public int get(int key) {
    // System.out.printf("get at beginning: %d,minFreq:%d\n", key, minFreq);
    if(!map.containsKey(key)) {
      return -1;
    }
    Node node = map.get(key);
    int oldFreq = node.freq;
    DoublyLinkedList oldDll = freqMap.get(node.freq);
    Node removed = oldDll.remove(node);
    if (minFreq==oldFreq && oldDll.size == 0){
      freqMap.remove(node.freq);
      minFreq++;
    }
    node.freq++;
    if (!freqMap.containsKey(node.freq)) {
      DoublyLinkedList dll = new DoublyLinkedList();
      freqMap.put(node.freq, dll);
    }
    DoublyLinkedList newDll = freqMap.get(node.freq);
    newDll.insertHead(removed);
    // System.out.printf("get key:%d, node.freq:%d minFreq:%d\n", key, node.freq, minFreq);
    map.put(key, node);
    return node.val;
  }

  // 1. check if the key exists. a. exists -> update value, update freq
  public void put(int key, int value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.val = value;
      int oldFreq = node.freq;
      DoublyLinkedList oldDll = freqMap.get(node.freq);
      Node removed = oldDll.remove(node);
      if(oldFreq==minFreq && oldDll.size==0) {
        freqMap.remove(node.freq);
        // only if the removed node is from minFreq and no more entry in minFreq
        minFreq++;
      }
      node.freq++;
      if(!freqMap.containsKey(node.freq)) {
        DoublyLinkedList dll = new DoublyLinkedList();
        freqMap.put(node.freq, dll);
      }
      DoublyLinkedList newDll = freqMap.get(node.freq);
      newDll.insertHead(removed);
//      map.put(key, node);
      //   System.out.printf("put containsKey: %d, node.freq:%d minFreq:%d\n", key, node.freq, minFreq);
    } else {
      if (map.size()==maxCapacity) {
        DoublyLinkedList dll = freqMap.get(minFreq);
        Node removed = dll.removeTail();
        // clear map, key -> node
        map.remove(removed.key);
        if (dll.size==0) freqMap.remove(minFreq);
      }
      // create a new node for map and freqMap
      Node newNode = new Node(key, value);
      map.put(key, newNode);
      if(!freqMap.containsKey(newNode.freq)) {
        DoublyLinkedList dll= new DoublyLinkedList();
        freqMap.put(newNode.freq, dll);
      }
      DoublyLinkedList newDll = freqMap.get(newNode.freq);
      newDll.insertHead(newNode);
      //   System.out.printf("put new node key:%d, node.freq:%d minFreq:%d\n", key, newNode.freq, minFreq);
      minFreq = 1;
    }
  }
  public static void main(String[] args) {
    LFUCache lfu = new LFUCache(3);
    lfu.put(2, 2);
    lfu.put(1, 1);
    assertEquals(2, lfu.get(2), "Expected 2");
    assertEquals(1, lfu.get(1), "Expected 1");
    assertEquals(2, lfu.get(2), "Expected 2");
    lfu.put(3, 3);
    lfu.put(4, 4);

    assertEquals(-1, lfu.get(3), "Expected -1");
    assertEquals(2, lfu.get(2), "Expected 2");
    assertEquals(1, lfu.get(1), "Expected -1 (evicted)");
    assertEquals(4, lfu.get(4), "Expected 4");
  }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
