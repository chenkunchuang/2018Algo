import java.util.*;

/*
This class is to calculate Euler path (a path visiting every edge exactly once
 */

public class CrackSafe {
    private HashSet<String> set;
    private StringBuilder ans;

    public String gencodes(int n, int k) {
        set = new HashSet<>();
        ans = new StringBuilder();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n-1; i++)
            sb.append("0");
        String start = sb.toString();
        dfs(start, k);
        ans.append(start);
        return ans.toString();
    }

    public void dfs(String code, int k) {
        for(int i=0; i<k; i++) {
            String temp = code+i;

            if(set.contains(temp)!=true) {
                set.add(temp);
                dfs(temp.substring(1), k);
                ans.append(i);
            }
        }
        return;
    }
    public static void main(String[] args) {
        CrackSafe sol = new CrackSafe();
        System.out.println("code:"+sol.gencodes(2,2));
    }
}
