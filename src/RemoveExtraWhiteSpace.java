/*
 This class is to remove extra white space and only keep one space between each word
  Given a string, format this string to keep only one space between each word.
# For example:
# -represent space below.
# Input: -I---am----happy   Output: I-am-happy
# Input: I---------am--not-------happy. Output: I-am-not-happy
 */
public class RemoveExtraWhiteSpace {

    public String remove(String s) {
        if(s.length()==0)
            return s;

        StringBuilder sb = new StringBuilder();
        //remove leading and trailing white spaces
        int l=0, r=s.length()-1;
        while(l<s.length()-1&&s.charAt(l)==' ')
            l++;
        while(r>=0&&s.charAt(r)==' ')
            r--;
        String new_s = s.substring(l, r+1);

        l=0;
        while(l<new_s.length()) {
            if(new_s.charAt(l)!=' ') {
                sb.append(new_s.charAt(l));
                l++;
            }
            // the first white space
            else {
                sb.append(new_s.charAt(l++));
                while(l<new_s.length() && new_s.charAt(l)==' ')
                    l++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveExtraWhiteSpace sol = new RemoveExtraWhiteSpace();
        String s = "  I am      not          happy.     ";
        System.out.println("original string:"+s);
        System.out.println("after removing extra spaces:"+sol.remove(s));
    }

}
