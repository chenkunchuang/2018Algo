import java.util.*;
/*
Remove the minimum number of invalid parentheses in order to make the input string valid.
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
 */
public class RemovedInvalidParantheses {
    public List<String> removeInvalidParentheses(String s) {
        int left_cnt=0, right_cnt=0;

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='(')
                left_cnt++;
            else if(s.charAt(i)==')'){
                left_cnt--;
                if(left_cnt<0) {
                    right_cnt++;
                    left_cnt=0;
                }
            }
        }
        List<String> list = new ArrayList<>();
        dfs(list, s, 0, "", left_cnt, right_cnt);
        return list;
    }

    public void dfs(List<String> list, String s, int idx, String path, int left_cnt, int right_cnt) {
        if(left_cnt<0 || right_cnt<0)
            return;
//        if(idx==s.length()) {
        if(left_cnt==0&&right_cnt==0&&isValid(s)) {
            list.add(s);
//            }
            return;
        }

         for(int i=idx; i<s.length(); i++) {
             if(i>idx&&s.charAt(i)==s.charAt(i-1))
                 continue;

             if(s.charAt(i)=='('&&left_cnt>0)
                 dfs(list, s.substring(0,i)+s.substring(i+1), i, path, left_cnt-1, right_cnt);
             if(s.charAt(i)==')'&&right_cnt>0)
                 dfs(list, s.substring(0,i)+s.substring(i+1), i, path, left_cnt, right_cnt-1);

         }
//        for(int i=idx; i<s.length(); i++) {
//            if(i>idx&&s.charAt(i)==s.charAt(i-1)&&(s.charAt(i)=='('||s.charAt(i)==')'))
//                continue;
//            if (left_cnt > 0 && s.charAt(i) == '(') {
//                dfs(list, s, i + 1, path, left_cnt - 1, right_cnt);
//            } else if (right_cnt > 0 && s.charAt(idx) == ')') {
//                dfs(list, s, i + 1, path, left_cnt, right_cnt - 1);
//            } else
//                dfs(list, s, i + 1, path + s.charAt(i), left_cnt, right_cnt);
//        }
        return;
    }

    public boolean isValid(String s) {
        int left=0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='(')
                left++;
            else if(s.charAt(i)==')') {
                left--;
                if(left<0)
                    return false;
            }

        }
        return left==0;
    }

    public static void main(String[] args){
        RemovedInvalidParantheses sol = new RemovedInvalidParantheses();
        String s1 = "(a)())()";
        System.out.println(sol.removeInvalidParentheses(s1));
    }
}
