/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays
whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
 */
import java.util.*;
public class SubArrayEqualsK {

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum=0, count=0;
        map.put(0,1);
        for(int i=0; i<nums.length; i++) {
            sum+=nums[i];
            if(map.containsKey(sum-k)) {
                count+=map.get(sum-k);
            }
            //這邊用getOrDefault, 因為可以想成[0,i]如果之前[0,i-1]已經有多個subarray sum=k
            // 那當[0,i] 必然是要把之前的加進去
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubArrayEqualsK sol = new SubArrayEqualsK();
        int[] a ={1,1,1,-1,2,-2};
        sol.subarraySum(a, 2);
    }
}
