import java.util.*;

public class WordLadderPath {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>();
        for(String s: wordList)
            dict.add(s);

        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, List<String>> children = new HashMap<>();

        List<List<String>> ans = new ArrayList<>();
        if(!dict.contains(endWord))
            return ans;

        bfs(beginWord, endWord, dict, distance, children);
        //先在這邊加上beginWord,再產生path的時候,就直接處理neighbor就可以了
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, distance, children, path, ans);
        System.out.print(ans);
        return ans;
    }

    public void bfs(String beginWord, String endWord, HashSet<String> dict, HashMap<String, Integer> distance, HashMap<String, List<String>> children) {
        Queue<String> q = new LinkedList<>();
        //需要把開始的字加進去,這樣方便處理
        dict.add(beginWord);
        //先把children的key產生,這樣在queue loop內比較好處理
        for(String s: dict)
            children.put(s, new ArrayList<>());

        q.offer(beginWord);
        distance.put(beginWord, 0);
        // children.put(beginWord, new ArrayList<>());

        // boolean found = false;

        while(!q.isEmpty()) {
            int size = q.size();
            boolean found = false;
            for(int i=0; i<size; i++) {
                String parent = q.poll();
                int currDistance = distance.get(parent);

                List<String> neighbors = getNeighbors(parent, dict);

                for(String neighbor: neighbors) {
                    children.get(parent).add(neighbor);
                    //利用distance來看這個node是不是已經visited
                    if(!distance.containsKey(neighbor)) {
                        distance.put(neighbor, currDistance+1);

                        if(endWord.equals(neighbor)) {
                            found=true;
                        }
                        else {
                            q.offer(neighbor);
                        }
                    }
                }
            }
            if(found)
                break;
        }
        return;
    }

    public void dfs(String beginWord, String endWord, HashMap<String, Integer> distance, HashMap<String, List<String>> children, List<String> path, List<List<String>> ans) {

        if(beginWord.equals(endWord)) {
            ans.add(new ArrayList(path));
             return;
        }
        for(String nei: children.get(beginWord)) {
            if(distance.get(beginWord)+1==distance.get(nei)) {
                 path.add(nei);
                dfs(nei, endWord, distance, children, path, ans);
                 path.remove(path.size()-1);
            }
        }

        return;
    }
    //這邊可能會存有多個重複的neighbor,就交給distance來判斷這個neighbor是否走過
    public List<String> getNeighbors(String parent, HashSet<String> dict) {
        //這邊用hashset,就不會存重複的
        // HashSet<String> nei = new HashSet<>();
        List<String> nei = new ArrayList<>();
        char[] arr = parent.toCharArray();
        for(int i=0; i<arr.length; i++) {
            char old_ch = arr[i];
            for(char c='a'; c<='z'; c++) {

                if(old_ch==c)
                    continue;
                arr[i] = c;
                if(dict.contains(String.valueOf(arr)))
                    nei.add(String.valueOf(arr));
            }
            arr[i]=old_ch;
        }
        // return new ArrayList(nei);
        return nei;
    }

    public static void main(String[] args) {
        WordLadderPath obj = new WordLadderPath();
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        obj.findLadders("hit", "cog", Arrays.asList(words));
    }
}
