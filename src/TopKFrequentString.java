import java.util.*;

public class TopKFrequentString {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                return e1.getValue()==e2.getValue()?e2.getKey().compareTo(e1.getKey()):e1.getValue()-e2.getValue();
            }
        });

        for(String word:words) {
            map.put(word, map.getOrDefault(word,0)+1);
        }
         System.out.println(map);
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            pq.offer(entry);
            // System.out.println("pq size:"+pq.size()+" peek key:"+pq.peek().getKey());
            if(pq.size()>k)
                pq.poll();//彈出最小頻率的字串
        }
        List<String> ans  = new ArrayList<>();
        while(pq.size()!=0) {
             System.out.println("key:"+pq.peek().getKey()+" val:"+pq.peek().getValue());
            ans.add(pq.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        TopKFrequentString sol = new TopKFrequentString();
        String [] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is","egg"};

        System.out.println(sol.topKFrequent(words, 5));
        System.out.println("love".compareTo("i"));

    }
}
