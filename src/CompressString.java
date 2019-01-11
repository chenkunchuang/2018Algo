/*
Leetcode 443 in compress3

compress4 用來比較兩個字串 aaabbbc = a3b3c
 */
import java.util.*;


public class CompressString {

    private void compress1(StringBuilder s) {

        int i=0;

        while(i<s.length()-1) {

            if(s.charAt(i)==s.charAt(i+1))
            {
                s.setCharAt(i, '@');
                s.setCharAt(i+1, '#');
                i+=2;
            }
            else {
                i++;
            }
        }

        i=0;
        int j=0;
        //remove '#'
        while(j<s.length()) {
            if(s.charAt(j)=='#')
            {
                j++;
            }
            else {
                s.setCharAt(i++, s.charAt(j++));
            }
        }
        // Since java string doesn't have null-terminated conceopt. So get substring for compressed string
        String ret = s.substring(0, i).toString();

        System.out.println("compressed string: " + ret);
    }

    // use stack to do
    private void compress2 (String s) {

        Stack<Character> stack = new Stack<>();
        String ret = "";
        for(char c:s.toCharArray()) {
            if(!stack.isEmpty() && c==stack.peek()) {
                stack.pop();
                stack.push('@');
            }
            else {
                stack.push(c);
            }
        }

        while(stack.isEmpty()==false) {
            ret = String.valueOf(stack.pop()) + ret;
        }
        System.out.println(ret);
    }

    /*
        compress3 is used to compress same letter to number of appearance.
        aaabbbc--> a3b3c leetcode; 443
     */

    public int compress3(char[] chars) {
        int curr=0, n=chars.length;

        for(int i=0, j=0; j<n; j=i) {
            while(i<n&&chars[i]==chars[j]) i++;
            int cnt = i-j;
            chars[curr++] = chars[j];
            if(cnt==1)
                continue;
            for(char c: String.valueOf(cnt).toCharArray())
                chars[curr++] = c;

        }
        System.out.println("new len " + curr);
        return curr;
    }

    public boolean compress4(String s1, String s2) {
        int i=0, j=0;

        while(i<s1.length()&&j<s2.length()) {
            if(s1.charAt(i)!=s2.charAt(j)) {
                if(!Character.isDigit(s2.charAt(j)))
                    return false;
                int idx = j;
                while(j<s2.length()&&Character.isDigit(s2.charAt(j))) j++;
                int count = Integer.valueOf(s2.substring(idx, j))-1;
                idx=i;
                while(i<s1.length()&&count>0) {
                    if(s1.charAt(idx)!=s1.charAt(i))
                        return false;
                    count--; i++;
                }
            }
            else {
                i++; j++;
            }
        }
        return i==s1.length()&&j==s2.length();
    }

    public static void main(String[] args) {

        CompressString solution = new CompressString();
        //System.out.println("Compress String main");
        solution.compress1(new StringBuilder("abbcccd"));
        solution.compress2("aavvvccccdd");
        solution.compress3("abccc".toCharArray());
        System.out.println(solution.compress4("aaaaaaaaaaaabbbcddd", "a12b3cd3"));
        System.out.println(solution.compress4("abbbcddd", "ab2cd3"));

    }
}
