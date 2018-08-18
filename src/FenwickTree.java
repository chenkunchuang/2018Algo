package com.company;
import java.util.*;
/*
This is implementation of fenwick tree or binary index tree.
this tree structure is good for prefix sum(range sum)

 */

public class FenwickTree {
    private int[] btree;
    private int[] m_nums;

    //update fenwich tree or binary index tree
    // idx是btree的index = nums的idx+1
    private void updateBtree(int idx, int val) {
        int diff = val-m_nums[idx];
        int org_idx = idx;
        idx+=1;
        while(idx<btree.length) {
            btree[idx] += diff;
            idx = getNext(idx);
        }
        m_nums[org_idx] = val;
    }
    // the range from 0 to end
    private int getRangeBtree(int end) {
        end+=1;
        int sum =0;
        while(end>0) {
            // System.out.println("end:"+end+" btree:"+btree[end]);
            sum+=btree[end];
            end = getParent(end);
        }
        return sum;
    }

    /* 1) 2's complement to get -idx
      2) AND idx to get most right bit
      3) add to index to get next node
    */
    private int getNext(int idx) {
        return idx+(idx&-idx);
    }
     /* 1) 2's complement to get -idx
      2) AND idx to get most right bit
      3) substract that from index
    */

    private int getParent(int idx) {
        return idx-(idx&-idx);
    }

    private void buildBtree(int[] nums) {
        btree = new int[nums.length+1];
        m_nums = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            updateBtree(i, nums[i]);
        }
        //存原本的矩陣,可以用來比較後來有沒有更新,要放在建立btree之後
        m_nums = Arrays.copyOf(nums, nums.length);
    }

    public static void main(String[] args) {
        int[] nums ={1,3,5,6,9,10};

        FenwickTree sol = new FenwickTree();
        sol.buildBtree(nums);

        assert 1==sol.getRangeBtree(2);

    }
}
