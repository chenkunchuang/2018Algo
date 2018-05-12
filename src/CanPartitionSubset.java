import java.util.Arrays;

/*
* Given an array of integers nums and a positive integer k,
* find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 */
public class CanPartitionSubset {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;

        for(int num: nums)
            sum+=num;

        if(sum%k>0)
            return false;
        int target = sum/k;

        Arrays.sort(nums);

        return dfs(new int[k], nums, nums.length-1, target );


    }

    /*
    * This backtracking function is used to determine if each group can have a subset if target
    * group: k group,
    * idx: index to track nums
    */

    public boolean dfs(int[] group, int[] nums, int idx, int target) {
        if(idx<0)
            return true;

        int val = nums[idx];

        //dfs
        for(int i=0; i<group.length; i++) {
            if(group[i]+val<=target) {

                group[i]+=val;
                if(dfs(group, nums, idx-1, target)) return true;
                group[i]-=val;

                if(group[i]==0)
                    break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanPartitionSubset obj = new CanPartitionSubset();

        int[] input = {4, 3, 2, 3, 5, 2, 1};
        int k=4;

        obj.canPartitionKSubsets(input, k);


    }
}
