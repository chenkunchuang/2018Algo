/*Given an input string, reverse the string word by word.

Example:

Input: "the sky is blue",
Output: "blue is sky the".
*
 */

public class ReverseWordsInString {

    public String reverseWords(String s) {

        if(s.length()==0)
            return s;

        char[] arr = s.toCharArray();
        int n = s.length();



        // 反轉字串
        reverse(arr, 0, n-1);

        //反轉個別的字
        reverseWords(arr, n);

        //clean white space
        return cleanSpaces(arr, n);

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
    // use two pointers to find the first letter and the end of the word
    // then swap and update the pointer.
    public void reverseWords(char[] chars, int n) {
        int i = 0; //用來定位第一個字母的指針
        int j = 0; //用來走的指針

        while(j<n) {
            while(i<n && chars[i] == ' ') i++;
            j = i;
            while(j<n && chars[j]!=' ') j++;
            reverse(chars, i, j-1);
            i = j;
        }

    }

    public String cleanSpaces(char[] chars, int n) {
        int i = 0; // write pointer
        int j = 0; // read pointer

        while(j<n) {
            // leading or in-between white spaces
            while(j<n && chars[j]==' ') j++;
            while(j<n && chars[j]!=' ') chars[i++] = chars[j++];
            // in-between or trailing white spaces, find the next letter
            while(j<n && chars[j]==' ') j++;
            // only add a white space only if it's not last
            if(j<n) chars[i++] = ' ';
        }

        return new String(chars).substring(0, i);

    }

    public static void main(String[] args) {
        ReverseWordsInString sol = new ReverseWordsInString();
        System.out.println(sol.reverseWords("  The sky is   blue"));
        System.out.println(sol.reverseWords("    d.     "));
        System.out.println(sol.reverseWords("        The     sky      is   blue."));

    }
}
