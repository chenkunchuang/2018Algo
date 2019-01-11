/*
Input:
rows = 2, cols = 8, sentence = ["hello", "world"]

Output:
1

Explanation:
hello---
world---

The character '-' signifies an empty space on the screen.
 */


import java.util.*;
public class SentenceScreenFit {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int l = sentence.length;
        int[] dp = new int[l];
        int wl = sentence[0].length();
        int next = 0;
        for(int i = 0; i < l; ++i){
            while(cols >= wl){
                cols -= wl + 1;
                next++;
                wl = sentence[next % l].length();
            }
            dp[i] = next;
            cols += sentence[i].length() + 1;
        }

        int count = 0;
        next = 0;
        for(int i = 0; i < rows; ++i){
            count += dp[next] / l;
            next = dp[next] % l;
        }
        return count;
    }

    public static void main(String[] args) {
        SentenceScreenFit sol = new SentenceScreenFit();
        String[] sentence= {"I", "had", "apple", "pie"};
        String[] sentence1={"a", "b", "e"};
        System.out.println(sol.wordsTyping(sentence1, 3, 6));
    }
}
