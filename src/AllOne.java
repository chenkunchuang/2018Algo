/*
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */

import java.util.*;
public class AllOne {
    class Node {
        Set<String> set;//存相同value的key
        int value;//每個key的值
        Node prev, next;
        public Node(int val) {
            value = val;
            set = new HashSet<>();
        }
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    //如果產生新比較大的值,插到後面
    public void insertBack(Node node, Node newNode) {
        // Node newNode = new Node(node.value+1);
        newNode.next = node.next;
        newNode.prev = node;
        node.next.prev = newNode;
        node.next = newNode;
    }
    //如果產生新比較小的值,插到前面
    public void insertFront(Node node, Node newNode) {
        // Node newNode = new Node(node.value-1);
        newNode.prev=node.prev;
        newNode.next = node;
        node.prev.next = newNode;
        node.prev = newNode;
    }

    HashMap<String, Integer> map;
    HashMap<Integer, Node> bucket_map;
    Node head, tail;
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        bucket_map = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key)) {
            map.put(key, 1);
            if(!bucket_map.containsKey(1)) {
                Node newNode = new Node(1);
                bucket_map.put(1, newNode);
                insertBack(head, newNode);
            }
            bucket_map.get(1).set.add(key);
        }
        else {
            int val = map.get(key);
            int newVal = val+1;
            map.put(key, newVal);
            Node curr = bucket_map.get(val);
            //當bucket沒有相對應的次數,產生新的node,加到double linked list
            //並且加到bucket_map
            if(!bucket_map.containsKey(newVal)) {
                Node newNode = new Node(newVal);
                bucket_map.put(newVal, newNode);
                insertBack(curr, newNode);
            }
            bucket_map.get(newVal).set.add(key);
            bucket_map.get(val).set.remove(key);
            if(bucket_map.get(val).set.size()==0) {
                removeNode(curr);
                bucket_map.remove(val);
            }
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)) {
            int val = map.get(key);
            int newVal = val - 1;
            if(newVal==0) {
                Node curr = bucket_map.get(val);
                map.remove(key);
                bucket_map.get(val).set.remove(key);
                if(bucket_map.get(val).set.size()==0) {
                    bucket_map.remove(val);
                    removeNode(curr);
                }
            }
            else{
                Node curr = bucket_map.get(val);
                map.put(key, newVal);
                if (!bucket_map.containsKey(newVal)) {
                    Node newNode = new Node(newVal);
                    bucket_map.put(newVal, newNode);
                    insertFront(curr, newNode);
                }
                bucket_map.get(newVal).set.add(key);
                bucket_map.get(val).set.remove(key);
                if (bucket_map.get(val).set.size() == 0) {
                    removeNode(curr);
                    bucket_map.remove(val);
                }
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.prev==head?"":tail.prev.set.iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next==tail?"":head.next.set.iterator().next();
    }

    public static void main(String[] args) {
        AllOne sol = new AllOne();
//        sol.inc("hello");
//        sol.inc("hello");
//        sol.inc("world");
//        sol.inc("world");
//        sol.inc("hello");
//        System.out.println("max key:"+sol.getMaxKey());
//        System.out.println("min key:"+sol.getMinKey());
//        sol.inc("world");
//        sol.inc("world");
//        sol.inc("leet");
//        System.out.println("max key:"+sol.getMaxKey());
//        System.out.println("min key:"+sol.getMinKey());
//        sol.inc("leet");
//        sol.inc("leet");
//        System.out.println("min key:"+sol.getMinKey());
//        System.out.println("min key:"+sol.getMinKey());
        sol.inc("a");
        sol.inc("b");
        sol.inc("c");
        sol.inc("d");
        sol.inc("a");
        sol.inc("b");
        sol.inc("c");
        sol.inc("d");
        sol.inc("d");
        sol.inc("c");
        sol.inc("d");
        sol.inc("d");
        sol.inc("a");
        System.out.println("min key:"+sol.getMinKey());


    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */