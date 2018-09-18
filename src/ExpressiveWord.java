import java.util.*;

/*
This class is to find out any extensive character in the word and return all extensive characters with start/end index
for example, heeeeellooooo --> e(1,5) o(8,12) any characters repeated at least 3 times
 */
public class ExpressiveWord {
    private HashSet<String> dict;
    public ExpressiveWord(String[] words) {
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

    public String matchWord(String word) {
        List<int[]> chars = find(word);
        List<String> candidate;
        StringBuilder sb = new StringBuilder();
        int i=0;
        for(int[] pair: chars) {
            int start = pair[1];
            int end = pair[2];
            sb.append(word.substring(i, start));
            for(int i=0; i<2;  i++) {

            }
        }
    }

    public static void main(String[] args) {
        ExpressiveWord sol = new ExpressiveWord();
        String[] words = {"hello", "hi", "cat", "world"};
        sol.matchWord(words);

        sol.find("heeeellooooo");
        sol.find("hiiiiiiiiiii");
        sol.find("");
        sol.find("hee");
        sol.find("heeellooootttggg");
    }
}
