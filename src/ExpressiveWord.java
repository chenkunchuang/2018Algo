import java.util.*;

/*
This class is to find out any extensive character in the word and return all extensive characters with start/end index
for example, heeeeellooooo --> e(1,5) o(8,12) any characters repeated at least 3 times
 */
public class ExpressiveWord {
    private HashSet<String> dict;
    public ExpressiveWord(String[] words) {
        dict = new HashSet<>();
        for(String word: words)
            dict.add(word);
    }
    public List<int[]> find(String word) {
        int i=0;
        int len = word.length();
        List<int[]> ans = new ArrayList<>();
        if(len<=2)
            return ans;

        while(i<len-1){
            if(word.charAt(i)==word.charAt(i+1)) {
                int count =1;
                int start = i;
                while(i<len-1&&word.charAt(i)==word.charAt(i+1)) {
                    count++;
                    i++;
                }
                if(count>=3)
                    ans.add(new int[]{(int)word.charAt(i),start, i});

            }
            i++;
        }
        for(int[] pair: ans)
            System.out.println((char)pair[0]+" start:"+pair[1]+" end:"+pair[2]);

        return ans;
    }

    public List<String> matchWord(String word) {
        List<int[]> chars = find(word);
        List<String> candidates = new ArrayList<>();
        List<String> ans = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
        //只有贅字的時候才需要dfs
//        if(chars.size()>0)
        dfs(chars, 0, candidates, 0, word, "");
        System.out.println("possible candidates:"+candidates);

        for(String s: candidates) {
            if(dict.contains(s))
                ans.add(s);
        }
        return ans;
    }

    public void dfs(List<int[]> indices, int pairIdx, List<String> candidates, int idx, String word, String new_word) {
        if(idx==word.length()) {
            candidates.add(new_word);
            return;
        }
        if(pairIdx<indices.size()) {
            int[] pair = indices.get(pairIdx);
            while(idx<word.length()&&idx<pair[1]) {
                new_word+=word.charAt(idx++);
            }
            dfs(indices, pairIdx+1, candidates, pair[2]+1, word, new_word+(char)pair[0]);
            dfs(indices, pairIdx+1, candidates, pair[2]+1, word, new_word+(char)pair[0]+(char)pair[0]);
        }
        else {
            while(idx<word.length()) {
                new_word+=word.charAt(idx++);
            }
            dfs(indices, pairIdx, candidates, idx, word, new_word);
        }
    }

    public static void main(String[] args) {
//        String[] words = {"hello", "hi", "cat", "world","hee","hellotg"};
//        ExpressiveWord sol = new ExpressiveWord(words);
        //in command line: java ExpressiveWord hello hi cat world hee  hellotg

        ExpressiveWord sol = new ExpressiveWord(args);
        String[] extensiveWords = {"heeeellooooo","hiiiiiiiiiii","hee","heeellooootttggg","cccaaatttt"};
        for(String s: extensiveWords)
            System.out.println("extensive word:"+s+" and word in dictionary:"+sol.matchWord(s));

//        sol.find("heeeellooooo");
//        sol.find("hiiiiiiiiiii");
//        sol.find("");
//        sol.find("hee");
//        sol.find("heeellooootttggg");
    }
}
