import java.util.*;

/*
This class is to calculate Euler path (a path visiting every edge exactly once
 */

public class CrackSafe {
    private HashSet<String> set;
    private StringBuilder ans;
    private Queue<String> post;
    private boolean[] marked;

    public String gencodes(int n, int k) {
        set = new HashSet<>();
        ans = new StringBuilder();
        post = new LinkedList<>();
//        marked = new boolean[Math.pow(n, k)];

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n-1; i++)
            sb.append("0");
        String start = sb.toString();
//        ans.append(start);
        dfs(start, k);
        ans.append(start);
        post.offer(start);
        String codes="";
        while(post.isEmpty()!=true)
            codes+=post.poll();
//        return ans.toString();
        return codes;
    }


    public void dfs(String code, int k) {
        for(int i=0; i<k; i++) {
            String temp = code+i;

            if(set.contains(temp)!=true) {
                set.add(temp);
//                ans.append(i);
                dfs(temp.substring(1), k);
//                ans.deleteCharAt(ans.length()-1);
                ans.append(i);
                post.offer(String.valueOf(i));
            }
        }
        return;
    }
    public static void main(String[] args) {
        CrackSafe sol = new CrackSafe();
        System.out.println("code:"+sol.gencodes(2,10));
    }
}
