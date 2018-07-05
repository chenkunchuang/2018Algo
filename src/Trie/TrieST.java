package Trie;
import java.util.*;
/*
* This class is Trie symbol table.
*  The Trie structure is best for prefix searching with short prefix key
 */


public class TrieST {
    private static final int R = 26;
    private TrieNode root; // root of Trie
    private class TrieNode {
        private List<String> prefix; // this is the prefix string in the node
        private TrieNode[] child;
        public TrieNode() {
            child = new TrieNode[R];
            prefix = new ArrayList<>();

        }
    }

    public TrieST(String[] words) {
        root = new TrieNode();

        for(String word: words) {
            TrieNode curr = root;
            for(int j=0; j<word.length(); j++) {
                int idx = word.charAt(j)-'a';
                if(curr.child[idx]==null)
                    curr.child[idx] = new TrieNode();
                curr.prefix.add(word);
                curr = curr.child[idx];

            }
        }
    }

    public List<String> findPrefix(String str) {
        List<String> ans = new ArrayList<>();
        TrieNode curr = root;

        for(char c: str.toCharArray()) {
            if(curr.child[c-'a']==null)
                return ans;

            curr = curr.child[c-'a'];
        }
        ans.addAll(curr.prefix);
        return ans;
    }

}
