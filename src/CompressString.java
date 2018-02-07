import java.util.*;


public class CompressString {

    public void compress1(StringBuilder s) {

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
    public void compress2 (String s) {

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
        System.out.println(s);
    }

    public static void main(String[] args) {

        CompressString solution = new CompressString();
        //System.out.println("Compress String main");
        solution.compress1(new StringBuilder("abbcccd"));
        solution.compress2("aavvvccccdd");

    }
}
