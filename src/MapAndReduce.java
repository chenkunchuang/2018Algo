import java.util.*;

public class MapAndReduce {

    /*
    *  This method calculate the appearance of words using google: map and reduce
     */
    private void WordAppearance(String[] input, HashMap<String, Integer> output) {

         for(String s:input) {

             String[] words = s.split(" ");
             for(String word:words) {

                 output.put(word, output.getOrDefault(word, 0)+1);

             }

         }
         for(Map.Entry<String, Integer> p : output.entrySet()) {
             System.out.println("word: " + p.getKey() + " appearance: " + p.getValue());
         }
    }
    // This method is to do user recommendation
    private void InvertedIndex(String[] input, HashMap<String, List<Integer>> output) {
        int userID =0;
        for(String s:input) {
            String[] items = s.split(" ");

            for(String item: items) {
                if(!output.containsKey(item))
                    output.put(item, new ArrayList<>());

                output.get(item).add(userID);
            }
            userID++;
        }
        for(Map.Entry<String, List<Integer>> p : output.entrySet()) {
            System.out.println("Item: " + p.getKey() + " bought by users:  " + p.getValue().toString());
        }
    }


    public static void main(String[] args) {

        String[] input = {"Deer Bear River", "Car Car River", "Deer Car Bear"};
        String[] input1 = {"echo headphone mouse", "mouse car phone", "echo mouse shoes"};
        MapAndReduce solution = new MapAndReduce();

        solution.WordAppearance(input, new HashMap<>());
        solution.InvertedIndex(input1, new HashMap<>());

    }
}
