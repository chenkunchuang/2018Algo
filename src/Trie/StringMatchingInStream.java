package Trie;
import java.util.*;
/*
This class is to find any matching string from data stream.
The key is you will have a stream and in order to speed up the searching,
build up a trie with reverse of string order so when looking up, you look from the back
so it eliminate O(n^2) due to different prefix. for example, abab--> abab, bab, ab, b
 */

public class StringMatchingInStream {
    private TrieNode root;
    //reverse string Trie
    public class TrieNode {
        private TrieNode[] child;
        private boolean isWord;
        private String target;
        public TrieNode() {
            child = new TrieNode[26];
            isWord = false;
            target="";
        }

    }

    public void build(String[] words) {
        root = new TrieNode();

        for(String word:words) {
            TrieNode iter = root;
            for(int i=word.length()-1; i>=0; i--) {
                if(iter.child[word.charAt(i)-'a']==null) {
                    iter.child[word.charAt(i)-'a'] = new TrieNode();
                }
                iter = iter.child[word.charAt(i)-'a'];
            }
            iter.isWord = true;
            iter.target = word;

        }
    }
    public List<String> search(String word) {
        TrieNode iter = root;
        List<String> ans = new ArrayList<>();
        for(int i=word.length()-1; i>=0; i--) {

            if (iter.child[word.charAt(i) - 'a'] != null && iter.child[word.charAt(i) - 'a'].isWord == true)
                ans.add(iter.child[word.charAt(i) - 'a'].target);

            iter = iter.child[word.charAt(i) - 'a'];
        }
        return ans;
    }

    public static void main(String[] args) {
        StringMatchingInStream sol = new StringMatchingInStream();
        String[] words ={"cat", "bcat", "bbcat", "abc", "aa", "a"};
        sol.build(words);
        String input1 = "bbcat";
        String input2 = "aaa";
        String input3 = "";

        System.out.println("input stream:"+input1+" matches dictionary words:"+sol.search(input1));
        System.out.println("input stream:"+input2+" matches dictionary words:"+sol.search(input2));
        System.out.println("input stream:"+input3+" matches dictionary words:"+sol.search(input3));
    }
}
