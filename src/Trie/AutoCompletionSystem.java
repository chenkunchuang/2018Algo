package Trie;
import java.util.*;
/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 */
public class AutoCompletionSystem {
    final int R=27;
    private TrieNode head;
    private TrieNode iterator;//這是用來存每個input後的trieNode節點
    private StringBuilder sb; //這是用來記錄user input char so far
    final int numOfPicks =3;
    private class TrieNode{
        TrieNode[] child;
        int time;
        public TrieNode() {
            child = new TrieNode[R];
            time = 0;
        }
    }
    private class Node {
        int freq;
        String sen;
        public Node(String s, int f){sen=s; freq=f;}
    }
    public void buildTrie(String[] sentences, int[] times) {
        for(int i=0; i<sentences.length; i++) {
            String s = sentences[i];
            int freq = times[i];
            TrieNode iter = head;
//            Node node = new Node(s, freq);
            for(char c: s.toCharArray()) {
                int idx=c==' '?26:c-'a';

                if(iter.child[idx]==null)
                    iter.child[idx] = new TrieNode();
                iter = iter.child[idx];

            }
            iter.time = freq;
        }
    }
    public void insert(String s, int time) {
       TrieNode iter = head;
       for(char c: s.toCharArray()) {
           int idx = c==' '?26:c-'a';
           if(iter.child[idx]==null)
               iter.child[idx] = new TrieNode();
           iter = iter.child[idx];
       }
       iter.time+= time;
    }

    public List<Node> lookup(String s) {
        List<Node> ret = new ArrayList<>();
        if(iterator==null)
            iterator = head;
        for(int i=0; i<s.length(); i++) {
            int idx = s.charAt(i)==' '?26:s.charAt(i)-'a';
            if(iterator.child[idx]==null)
                return new ArrayList<>();
            iterator = iterator.child[idx];
        }

        find(s, iterator, ret);
        return ret;
    }
    //邊便利邊建立sentences
    public void find(String s, TrieNode iter, List<Node> list) {
        if(iter.time>0) {
            list.add(new Node(s, iter.time));
        }
        for(int i=0; i<=26; i++) {
            if(i==26) {
                if(iter.child[i]!=null)
                    find(s+" ", iter.child[i], list);
            }
            else {
                if(iter.child[i]!=null)
                    find(s+(char)(i+'a'), iter.child[i], list);
            }
        }
        return;
    }


    public AutoCompletionSystem(String[] sentences, int[] times) {
        head = new TrieNode();
        iterator = null;
        sb = new StringBuilder();
        buildTrie(sentences, times);

    }
    public List<String> input(char c) {
        if(c=='#') {
            insert(sb.toString(), 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }

        sb.append(c);
        List<Node> list = lookup(sb.toString());
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.freq==o2.freq?o1.sen.compareTo(o2.sen):o2.freq-o1.freq;
            }
        });
        int sz = Math.min(list.size(),numOfPicks );
        List<String> ret = new ArrayList<>();
        for(int i=0; i<sz; i++) {
            ret.add(list.get(i).sen);
        }
        return ret;
    }
    public static void main(String[] args) {
        String[] senten= {"i love you","island","iroman","i love leetcode"};
        int[] freq = {5,3,2,2};
        AutoCompletionSystem sol = new AutoCompletionSystem(senten, freq);
        int i=1;
        while(i-->0) {
            System.out.println(sol.input('i'));
            System.out.println(sol.input(' '));
            System.out.println(sol.input('a'));
            System.out.println(sol.input('#'));
        }
    }
}

