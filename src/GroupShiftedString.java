import java.util.*;

/*
Given a list of strings which contains lowercase and upper alphabets,
group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */
public class GroupShiftedString {
    private String[] dict;
    public GroupShiftedString(String[] words) {
            dict = Arrays.copyOf(words, words.length);
    }

    public List<List<String>> grouping() {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> group = new HashMap<>();

        for(String s: dict) {
            StringBuilder sb = new StringBuilder();
//            int offset = s.charAt(0)-'a'>0?s.charAt(0)-'a'
            for(int i=1; i<s.length(); i++) {
                int offset = s.charAt(i)-s.charAt(0);
                if(offset<0&&offset>-26) {
                    offset+=26;
                }
                else if(offset<=-26){
                    offset+=58;
                }
                sb.append(offset+",");
            }
            System.out.println("s:"+sb.toString());
            if(!group.containsKey(sb.toString()))
                group.put(sb.toString(), new ArrayList<>());
            group.get(sb.toString()).add(s);
        }

        for(List<String> val: group.values())
            ans.add(val);
        return ans;
    }
    public static void main(String[] args) {
        String[] words ={"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z", "aBc", "bCd", "ABC", "XYZ", "xYz", "zab", "zAB"};
        GroupShiftedString sol = new GroupShiftedString(words);
        System.out.println(sol.grouping());
    }
}
