/*Given an input string, reverse the string word by word.

Example:

Input: "the sky is blue",
Output: "blue is sky the".
*
 */

public class ReverseWordsInString {

    public String reverseWords(String s) {

        //先除去前後的空格
        int left =0;
        int right = s.length()-1;
        while(s.charAt(left)==' ') left++;
        while(s.charAt(right)==' ') right --;
        String str = s.substring(left, right+1);

        // 除去中間多的空格
        char[] chars = str.toCharArray();
        left = right =0;
        while(right<str.length()) {
            if(chars[right]!=' ')
                chars[left++] = chars[right++];
            else {
                chars[left++] = chars[right++]; //保留一個space
                while(chars[right]==' ') right++;
            }
        }

        // 反轉字串
        reverse(chars, 0, left-1);

        //反轉個別的字
        reverseWords(chars, left);
        return new String(chars, 0, left);
    }

    public void reverse(char[] chars, int l, int r) {

        while(l<r) {
            char c = chars[l];
            chars[l] = chars[r];
            chars[r] = c;
            l++;
            r--;
        }
        return;
    }
    public void reverseWords(char[] chars, int n) {
        int i = 0; //用來定位第一個字母的指針
        int j = 0; //用來走的指針

        while(i<n) {

            while(j<n && chars[j] !=' ') j++; //找字的結尾
            reverse(chars, i, j-1);
            j++;
            i = j;
        }
    }
    public static void main(String[] args) {
        ReverseWordsInString sol = new ReverseWordsInString();
        System.out.println(sol.reverseWords("  The sky is   blue"));
        System.out.println(sol.reverseWords("        The     sky      is   blue"));
    }
}
