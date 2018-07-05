package Trie;
import java.util.*;
/*
Given a set of words (without duplicates), find all word squares you can build from them.
A sequence of words forms a valid word square if the kth row and column read the exact same string,
where 0 ≤ k < max(numRows, numColumns).
For example, the word sequence ["ball","area","lead","lady"] forms a word square
because each word reads the same both horizontally and vertically.


* Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares.
The order of output does not matter (just the order of words in each word square matters).
 */

// Idea is to find the prefix to form wordsquare and use Trie to find key(word) with this prefix
// this will reduce searching.
public class wordSquares {
    private TrieST mTrie;

    public List<List<String>> findWordSquares(String[] words) {

        mTrie = new TrieST(words);
        List<String> wordsquare = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();

        //backtracking to form the wordsquares
        for(int i=0; i<words.length; i++) {
            wordsquare.add(words[i]);
            search(words, mTrie, wordsquare, ans);
            wordsquare.remove(wordsquare.size()-1);
        }
        System.out.println(ans);
        return ans;
    }

    // search for wordsquare based on word indexes from Trie
    public void search(String[] words, TrieST root, List<String> wordsquare, List<List<String>> ans) {

        if(wordsquare.size()==words[0].length()) {
            ans.add(new ArrayList(wordsquare));
            return;
        }

        StringBuilder prefix = new StringBuilder();
        int idx = wordsquare.size();// idx to pick char in string

        for(String w: wordsquare)
            prefix.append(w.charAt(idx));

        List<Integer> wordsIdx = root.findPrefix(prefix.toString());

        for(Integer i:wordsIdx) {
            wordsquare.add(words[i]);
            search(words, root, wordsquare, ans);
            wordsquare.remove(wordsquare.size()-1);
        }

        return;
    }

    public static void main(String[] args) {
        String[][] words = {{"abat","baba","atan","atal"},{"area","lead","lady","ball","wall"},
                {"momy","oooo","yoyo"}};
        wordSquares sol = new wordSquares();
        for(int i=0; i<words.length; i++)
            sol.findWordSquares(words[i]);

    }

}
