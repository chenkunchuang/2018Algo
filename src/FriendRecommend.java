import javax.print.DocFlavor;
import java.util.*;

/*
This class is to provide recommended friends if A and B are not friend but
they have at least have 3 common friends
 */
public class FriendRecommend {
    private HashMap<Character, HashSet<Character>> map = new HashMap<>();

    public List<Character> suggestedFriends(char[][] friends, char user) {
        for(char[] friend: friends) {
            if(!map.containsKey(friend[0]))
                map.put(friend[0], new HashSet<Character>());
            if(!map.containsKey(friend[1]))
                map.put(friend[1], new HashSet<Character>());

            map.get(friend[0]).add(friend[1]);
            map.get(friend[1]).add(friend[0]);
        }
//        System.out.println(map);
        List<Character> ans = new ArrayList<>();
        //bfs找suggested friend
        int count=0;// at least 3 common friends
        HashMap<Character, Integer> friendMap = new HashMap<>();
        HashSet<Character> set = map.get(user);
//        System.out.println(set);
        for(Character nei: set) {
            System.out.print("nei:"+nei);
            HashSet<Character> neighbors = map.get(nei);
            for(Character neighbor: neighbors) {
                System.out.print(" neighbor:"+neighbor);
                if (neighbor != user && !map.get(user).contains(neighbor)) {
                    friendMap.put(neighbor, friendMap.getOrDefault(neighbor, 0) + 1);
                }
            }
            System.out.println();
        }

//        System.out.println(friendMap);
        for(Map.Entry<Character, Integer> entry: friendMap.entrySet()) {
            if(entry.getValue()>=3)
                ans.add(entry.getKey());
        }
        System.out.println("user:"+user +" recommended friends:"+ans);
        return ans;

    }

    public static void main(String[] args) {
        FriendRecommend sol = new FriendRecommend();
        List<Character> friends = sol.suggestedFriends(new char[][]{{'a','b'}, {'a','c'}, {'a','d'},{'a','e'},{'f','z'},
                {'f','b'},{'f','c'},{'f','d'},{'g','b'},{'g','c'},{'g','x'}}, 'a');

    }
}
