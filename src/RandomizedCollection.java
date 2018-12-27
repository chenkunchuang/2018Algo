/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements.
The probability of each element being returned is linearly related to the number of same value the collection contains.
 */
import java.util.*;
public class RandomizedCollection {
    HashMap<Integer, HashSet<Integer>> map;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)) {
            map.put(val, new HashSet<>());
            list.add(val);
            map.get(val).add(list.size()-1);
            return true;
        }
        else {
            list.add(val);
            map.get(val).add(list.size()-1);
            return false;
        }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        HashSet<Integer> idx_list = map.get(val);
        // int remove_idx = idx_list.get(idx_list.size()-1);
        int remove_idx = idx_list.iterator().next();
        System.out.println(idx_list);
         idx_list.remove(remove_idx);
        if(remove_idx!=list.size()-1) {
            int lastElement = list.get(list.size()-1);
            // HashSet<Integer> lastElement_idxList = map.get(lastElement);
            // int lastElement_idx = map.get(lastElement).iterator().next();
            list.set(remove_idx, lastElement);
            map.get(lastElement).remove(list.size()-1);
            map.get(lastElement).add(remove_idx);
        }
        //不可以放在這邊因為這邊, 因為如果lastElment剛好是val, 這樣會在line40加了remove_idx,
        //然後因為此時的idx_list已經有了remove_idx, 這樣加了等於沒加,然後line 43移除了,就會短少
//        idx_list.remove(remove_idx);
        if(idx_list.size()==0)
            map.remove(val);
        list.remove(list.size()-1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int idx = (int)(Math.random()*list.size());
        return list.get(idx);
    }

    public static void main(String[] args) {
        RandomizedCollection sol = new RandomizedCollection();
        sol.insert(4);
        sol.insert(3);
        sol.insert(4);
        sol.insert(2);
        sol.insert(4);
        sol.remove(4);
        sol.remove(3);
        sol.remove(4);
        sol.remove(4);
    }
}
