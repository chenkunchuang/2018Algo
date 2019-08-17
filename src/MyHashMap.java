import java.util.*;
import java.util.concurrent.locks.*;

public class MyHashMap<K, V> {
    private Node[] arr;
    ReentrantLock lock;
    private final int cap =100;
    class Node<K,V> {
        Node next;
        private K key;
        private V val;

        public Node(K _key, V _val) {
            key = _key;
            val = _val;
            next = null;
        }
    }
        public MyHashMap() {
            arr = new Node[cap];
            lock = new ReentrantLock();
        }

        public int hash(K _key) {
            return _key.hashCode()%cap;
        }

        public void put(K _key, V _value) {
            int idx = hash(_key);
            lock.lock();
            if(arr[idx]==null) {
                arr[idx] = new Node(_key, _value);
            }
            else {
                Node prev = null;
                Node iter = arr[idx];

                while (iter != null && !iter.key.equals(_key)) {
                    prev = iter;
                    iter = iter.next;
                }
                if (iter == null)
                    prev.next = new Node(_key, _value);
                else
                    iter.val = _value;
            }
            lock.unlock();

            return;
        }

        public V get(K _key) {
            int idx = hash(_key);

            if(arr[idx]==null)
                return null;

            Node iter = arr[idx];
//            Node prev = null;
            while(iter!=null&&!iter.key.equals(_key)) {
//                prev=iter;
                iter=iter.next;
            }
            if(iter==null)
                return null;
            else
                return (V) iter.val;
//            return iter==null?null:iter.val;
        }

        public void remove(K _key) {
            int idx = hash(_key);
            if(arr[idx]==null)
                return;
            Node iter = arr[idx];
            Node prev = iter;
            while(iter!=null&&!iter.key.equals(_key)) {
                prev = iter;
                iter=iter.next;
            }
            if(iter==prev)
                arr[idx] = prev.next;
            else if(iter!=null)
                prev.next = prev.next.next;

            return;

        }
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<cap; i++) {
                if(arr[i]!=null) {
                    Node iter = arr[i];
                    while(iter!=null) {
                        sb.append("{"+(K)iter.key +","+(V)iter.val +"} ");
                        iter = iter.next;
                        if(iter!=null)
                            System.out.println("key collision: idx:"+i+ " key:"+iter.key);
                    }
                }
            }
//            return "";
            return sb.toString();
        }

        public static void main(String[] args) {
            MyHashMap<String, Integer> map_string = new MyHashMap<>();
            map_string.put("abc", 1);
            map_string.put("adc", 2);
            map_string.put("abc",100);

            MyHashMap<Integer, Integer> map_int = new MyHashMap<>();
            map_int.put(1,30);
            map_int.put(1,25);
            map_int.put(101,35);
            map_int.remove(101);
            System.out.println(map_string);

            for(int i=1; i<=200; i+=3)
                map_int.put(i, i+30);
            System.out.println(map_int);
        }



}
