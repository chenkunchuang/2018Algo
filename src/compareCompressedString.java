import java.util.*;

public class compareCompressedString {
    public boolean compare(String s1, String s2) {
        if(s1.length()==0 || s2.length()==0)
            return false;

        int i = 0, j = 0;

        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                if (Character.isDigit(s2.charAt(j)) != true) {
                    return false;
                }

                int idx = j; //紀錄數字的開頭

                while (j < s2.length() && Character.isDigit(s2.charAt(j))) {
                    j++;
                }

                int cnt = Integer.valueOf(s2.substring(idx, j)) - 1;//第一個已經比較過了
                while (cnt > 0 && i < s1.length()) {
                    if (s1.charAt(i) != s2.charAt(idx - 1)) {
                        return false;
                    }
                    i++;
                    cnt--;
                }

            }

        }
        return i == s1.length()&&j==s2.length() ? true : false;
    }
    public static void main(String[] args) {
        compareCompressedString sol = new compareCompressedString();
        System.out.println("s1:"+"abbbcccc"+ " s2:"+"abbbcccc" + " is "+(sol.compare("abbbcccc","ab3c4")?" same":
        " not same"));
    }
}
