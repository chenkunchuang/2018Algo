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
        private TrieNode[] child;
        private List<Integer> indexes; // possible indexes to the word with same prefix
        public TrieNode() {
            child = new TrieNode[R];
            indexes = new ArrayList<>();
//            prefix = new ArrayList<>();
//            prefix = new StringBuilder();
        }
    }
    //Since we might need to use prefix to find the words. In this case, we don't want to search to the end
    // of the string. Instead, each node will contains possible index from dictionary. In this way,
    // as long as the prefix is found, we just return possible words, i.e., indeces.
    public TrieST(String[] words) {
        root = new TrieNode();

        //build trie. Idea is to pass key(word) and break into chars and each node with char info.
        // the end of key and add a index to dictionary. This is to save space for too much strings
        for(int i=0; i<words.length; i++) {
            TrieNode curr = root;
            for(int j=0; j<words[i].length(); j++) {
                int idx = words[i].charAt(j)-'a';
                if(curr.child[idx]==null)
                    curr.child[idx] = new TrieNode();
//                curr.prefix.add(word);
//                curr.prefix.append(word.charAt(j));
                curr.indexes.add(i);
                curr = curr.child[idx];

            }
        }
    }

    public List<Integer> findPrefix(String str) {
        List<Integer> ans = new ArrayList<>();
        TrieNode curr = root;

        for(char c: str.toCharArray()) {
            if(curr.child[c-'a']==null)
                return ans;

            curr = curr.child[c-'a'];
        }
        ans.addAll(curr.indexes);
        return ans;
    }

}
