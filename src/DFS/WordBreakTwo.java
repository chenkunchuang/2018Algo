package DFS;
import java.util.*;
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
 */

public class WordBreakTwo {

    private HashMap<String, List<String>> mem = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        for(String word: wordDict)
            dict.add(word);

        List<String> ret = DFS(s, dict);

        System.out.println(ret);
        return ret;
    }

    // private void appendWord(String s, List<String> ans) {
    // In List, Inserts the specified element at the specified position in this list. Shifts the element
    // currently at that position (if any) and any subsequent elements to the right (adds one to their indices)
    private List<String> appendWord(String s, List<String> ans) {

        List<String> result = new ArrayList<>();
       for(String str: ans) {
            result.add(s+" "+str);
        }
        return result;
    }
    //bottom-up
    private List<String> DFS(String s, HashSet<String> dict) {

        if(mem.containsKey(s))
            return mem.get(s);

        List<String> ans = new ArrayList<>();
        // add to ans from the bottom and work all the way up
        if(dict.contains(s))
            ans.add(s);

        for(int i=0; i<s.length(); i++) {
            String new_word = s.substring(0, i+1);
            if(dict.contains(new_word)) {
//                ans.add(new_word);
                String r_word = s.substring(i+1, s.length());
//                List<String> rlist = DFS(r_word, dict);
//                ans.remove()
                ans.addAll(appendWord(new_word, DFS(r_word, dict)));
//                ans.remove(ans.size()-1);
            }
        }
        mem.put(s, ans);
        return ans;
    }

    public static void main(String[] args) {
        WordBreakTwo sol = new WordBreakTwo();
        String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] wordsArray1 = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};

        sol.wordBreak(s1, Arrays.asList(wordsArray1));
        String s2 = "catsanddog";
        String[] wordsArray2 = {"cat", "cats", "and", "sand", "dog"};
        sol.wordBreak(s2, Arrays.asList(wordsArray2));
        String s3 = "pineapplepenapple";
        String[] wordsArray3 = {"apple", "pen", "applepen", "pine", "pineapple"};
        sol.wordBreak(s3, Arrays.asList(wordsArray3));
    }
}
