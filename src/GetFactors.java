/*
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

 */

import java.util.*;
public class GetFactors {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> factors = new ArrayList<>();
        if(n<=1)
            return ans;

        dfs(ans, factors, n, 2);
        return ans;
    }

    public void dfs(List<List<Integer>> ans, List<Integer> factors, int n, int start) {
        for(int i=start; i*i<=n; i++) {
            if(n%i==0) {
                factors.add(i);
                factors.add(n/i);
                ans.add(new ArrayList(factors));
                factors.remove(factors.size()-1);
                dfs(ans, factors, n/i, i);
                factors.remove(factors.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        GetFactors sol = new GetFactors();
        System.out.println("num:"+12+" factors:"+sol.getFactors(12));
    }
}
