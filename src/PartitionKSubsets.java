import java.util.*;

/*
Given an array of integers nums and a positive integer k,
find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
 */

public class PartitionKSubsets {


    boolean[] visited;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        visited = new boolean[nums.length];
        int sum=0;
        for(int num: nums)
            sum+=num;
        //無法整除
        if(sum%k!=0)
            return false;

        return dfs(nums, 0, k, sum/k, 0);
    }
    //這邊重點是要找出k各group,所以k是比較重要的中止條件,也不需要真正的數組去存組合
    public boolean dfs(int[] nums, int idx, int k, int target, int sum) {
        if(sum>target)
            return false;
        //表示找到一組了,再繼續找下一組
        if(sum==target)
            return dfs(nums, 0, k-1, target, 0);

        if(k==1)
            return true;

        for(int i=0; i<nums.length; i++) {
            if(!visited[i]) {
                // subset.add(nums[j]);
                visited[i]=true;
                if(dfs(nums, i+1, k, target, sum+nums[i]))
                    return true;
                visited[i] = false;
                // subset.remove(subset.size()-1);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        CanPartitionSubset sol = new CanPartitionSubset();
        int[] a ={129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22};
        long start = System.nanoTime();
        sol.canPartitionKSubsets(a, 3);
        long end = System.nanoTime();
        long elapse = end-start;
        System.out.println("elapsed time:"+elapse);

//        System.out.println(sol.canPartitionKSubsets(a, 3));
    }
}
