package Trie;
import java.util.*;

public class WordSearchTwo {

    private static class TrieST {
        private final int R = 26;
        private TrieNode root;

        class TrieNode {
            private TrieNode[] child = new TrieNode[R];
            private int val = -1; // idx to words array
        }

        public TrieST(String[] words) {
            root = new TrieNode();

            for(int i=0; i<words.length; i++) {
                TrieNode curr = root;
                for(int j=0; j<words[i].length(); j++) {
                    int idx = words[i].charAt(j) - 'a';
                    if(curr.child[idx]==null)
                        curr.child[idx] = new TrieNode();
                    curr = curr.child[idx];
                }
                curr.val = i;
            }
        }
        // 這個search是從頭找,所以在ＤＦＳ叫這個API,等於每次prefix都要重找,會超時
        public int search(String s) {
            TrieNode curr = root;
            for(char c: s.toCharArray()) {
                if(curr.child[c-'a']==null)
                    return -1;
                curr = curr.child[c-'a'];
            }
            int ret = curr.val;
            //把index 重設成-1,就不會有重複的
            curr.val = -1;
            return ret;
        }

        public boolean IsPrefix(char c) {
            return root.child[c-'a']!=null;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        if(words==null || words.length==0)
            return ans;

        boolean[][] visited = new boolean[board.length][board[0].length];
        TrieST mTrie = new TrieST(words);
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++)
                // if(mTrie.IsPrefix(board[i][j])==true && visited[i][j]==false) {
                // visited[i][j]=true;
                DFS(mTrie.root.child[board[i][j]-'a'], board, String.valueOf(board[i][j]), i, j, ans, visited);
            // visited[i][j]=false;
            // }
        }
        System.out.println(ans);
        return ans;
    }
    //idx: 2-D index轉換, //要用current node,而不是從root開始,要不然會一直從頭找
    //NOTE: java nested class的type, 可以直接宣告, Base.nestType, like TrieST.TrieNode
    public void DFS(TrieST.TrieNode curr, char[][] board, String word, int i, int j, List<String> ans, boolean[][] visited) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length)
            return;
        //early return if char is not found
        if(curr==null)
            return;
        else if(curr.val!=-1)
        {
            ans.add(word);
            //重設定成-1,就不會有重複的
            curr.val = -1;
        }

        if(i-1>=0 && visited[i-1][j]!=true) {
            // DFS(mTrie, board, word.append(board[i-1][j]), i-1, j, ans);
            // word.deleteCharAt(word.length()-1);
            visited[i-1][j] = true;
            DFS(curr.child[board[i-1][j]-'a'], board, word+board[i-1][j], i-1, j, ans, visited);
            visited[i-1][j] = false;
        }
        if(i+1<board.length && visited[i+1][j]!=true) {
            // DFS(mTrie, board, word.append(board[i+1][j]), i+1, j, ans);
            // word.deleteCharAt(word.length()-1);
            visited[i+1][j] = true;
            DFS(curr.child[board[i+1][j]-'a'], board, word+board[i+1][j], i+1, j, ans, visited);
            visited[i+1][j] = false;
        }
        if(j-1>=0&&visited[i][j-1]!=true) {
            // DFS(mTrie, board, word.append(board[i][j-1]), i, j-1, ans);
            // word.deleteCharAt(word.length()-1);
            visited[i][j-1] = true;
            DFS(curr.child[board[i][j-1]-'a'], board, word+board[i][j-1], i, j-1, ans, visited);
            visited[i][j-1] = false;
        }
        if(j+1<board[0].length&&visited[i][j+1]!=true) {
            // DFS(mTrie, board, word.append(board[i][j+1]), i, j+1, ans);
            // word.deleteCharAt(word.length()-1);
            visited[i][j+1] = true;
            DFS(curr.child[board[i][j+1]-'a'], board, word+board[i][j+1], i, j+1, ans,visited);
            visited[i][j+1] = false;
        }
        return;
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        WordSearchTwo sol = new WordSearchTwo();
        sol.findWords(board, words);

    }

}
