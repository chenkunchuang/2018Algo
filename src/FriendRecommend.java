import javax.print.DocFlavor;
import java.util.*;

/*
This class is to provide recommended friends if A and B are not friend but
they have at least have 3 common friends
 */
public class FriendRecommend {
    private HashMap<Character, HashSet<Character>> map ;
    private HashMap<Character, Integer> SecondIndegree ;
    private int[] edgeTo;

    public List<Character> suggestedFriends(char[][] friends, char user) {
        map = new HashMap<>();
        SecondIndegree =  new HashMap<>();


        for(char[] friend: friends) {
            if(!map.containsKey(friend[0]))
                map.put(friend[0], new HashSet<Character>());
            if(!map.containsKey(friend[1]))
                map.put(friend[1], new HashSet<Character>());

            map.get(friend[0]).add(friend[1]);
            map.get(friend[1]).add(friend[0]);
//            if(!SecondIndegree.containsKey(friend[0]))
//                SecondIndegree.put(friend[0], 0);
//            if(!SecondIndegree.containsKey(friend[1]))
//                SecondIndegree.put(friend[1], 0);

        }
//        System.out.println(map);
        List<Character> ans = new ArrayList<>();
        Queue<Character> q = new LinkedList<>();
        HashSet<Character> visited = new HashSet<>();
        edgeTo = new int[256];
        q.offer(user);
        visited.add(user);
        int maxIndegree = 0;
        int level =0;
        while(!q.isEmpty()) {
            int size = q.size();
            //第幾層的朋友
            level++;
            for(int i=0; i<size; i++) {
                char c = q.poll();
                //開始找neighbor
                for(char nei:map.get(c)) {
                    if(level==2) {// second level 不可以用visited因為這樣比如
                        // b:afg, c:afg, 這樣當f跟g在b已經visited, 則c的afg就不會走到了,
                        //可以想做如果剛好走到第二層,則不需要去判斷有沒有走過
                        if(nei==user)
                            continue;
                        SecondIndegree.put(nei, SecondIndegree.getOrDefault(nei, 0)+1);
                        if(SecondIndegree.get(nei)>maxIndegree)
                            maxIndegree = SecondIndegree.get(nei);
                    }
                    else {
                        if(!visited.contains(nei)) {
                            q.offer(nei);
                            visited.add(nei);
                            edgeTo[nei] = c;
                        }
                    }

                }

            }
        }
        for(Map.Entry<Character, Integer> entry: SecondIndegree.entrySet()) {
            if(entry.getValue()==maxIndegree && entry.getKey()!=user) {
                ans.add(entry.getKey());
            }
        }

        System.out.println("user:"+user +" recommended friends:"+ans + " indegree:"+maxIndegree);

//        for(int recommend: ans) {
//            Queue<Integer> path = new LinkedList<>();
//            for (int x = recommend; x !=user; x=edgeTo[x]) {
//                path.offer(x);
//            }
//            path.offer((int)user);
//            System.out.print("graph path:");
//            while(!path.isEmpty()) {
//                System.out.print(" "+path.poll()+"->");
//            }
//        }
        return ans;


    }

    public static void main(String[] args) {
        FriendRecommend sol = new FriendRecommend();
        List<Character> friends = sol.suggestedFriends(new char[][]{{'a','b'}, {'a','c'}, {'a','d'},{'a','e'},{'f','z'},
                {'f','b'},{'f','c'},{'f','d'},{'g','b'},{'g','c'},{'g','x'}}, 'a');
//
////        List<Character> friends =
                sol.suggestedFriends(new char[][]{{'a','b'}, {'a','c'}, {'a','d'},{'a','e'},{'f','z'},
                {'f','b'},{'f','c'},{'f','d'},{'g','b'},{'g','c'},{'g','x'}}, 'b');
        sol.suggestedFriends(new char[][]{{'a','b'}, {'a','c'}, {'a','d'},{'a','e'},{'f','z'},
                {'f','b'},{'f','c'},{'f','d'},{'g','b'},{'g','c'},{'g','x'}}, 'x');
        sol.suggestedFriends(new char[][]{{'a','b'}, {'a','c'}, {'a','d'},{'a','e'},{'f','z'},
                {'f','b'},{'f','c'},{'f','d'},{'g','b'},{'g','c'},{'g','x'}}, 'z');
        sol.suggestedFriends(new char[][]{{'a','b'}, {'a','c'}, {'a','d'},{'a','e'},{'f','z'},
                {'f','b'},{'f','c'},{'f','d'},{'g','b'},{'g','c'},{'g','x'}}, 'f');

    }
}
