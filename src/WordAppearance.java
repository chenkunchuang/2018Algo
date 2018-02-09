import java.util.*;

public class WordAppearance {

    /*
    *  This method calculate the appearance of words using google: map and reduce
     */
    private void gMapRedeuce(String[] input, HashMap<String, Integer> output) {

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


    public static void main(String[] args) {

        String[] input = {"Deer Bear River", "Car Car River", "Deer Car Bear"};

        WordAppearance solution = new WordAppearance();

        solution.gMapRedeuce(input, new HashMap<>());

    }
}
