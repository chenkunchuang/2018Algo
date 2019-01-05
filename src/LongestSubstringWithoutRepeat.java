public class LongestSubstringWithoutRepeat {

    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[26];
        int max=Integer.MIN_VALUE;
        for(int i=0, j=0; i<s.length(); i++) {
            map[s.charAt(i)-'a']++;
            if(map[s.charAt(i)-'a']==1) {
                max=Math.max(max, i-j+1);
            }
            else {
                //開始有repeat的字符
                while(s.charAt(i)>1&&j<s.length()) {
                    map[s.charAt(j++)-'a']--;
                }

            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeat sol = new LongestSubstringWithoutRepeat();
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));
    }
}
