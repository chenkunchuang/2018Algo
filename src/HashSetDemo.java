import java.util.*;

public class HashSetDemo {
    private HashSet<String> hs;

    public HashSetDemo() {
        hs = new HashSet<>();
    }

    public HashSet<String> getHS() {
        return hs;
    }

    public void populate(String[] words) {

        for(String word:words)
            hs.add(word);
    }

    public static void main(String[] args) {

        String[] colors = {"white", "pink", "red", "green", "red", "orange","Aa","BB"};
        HashSetDemo solution = new HashSetDemo();
        solution.populate(colors);

        System.out.println(solution.getHS());

        System.out.println("Does it contain green " + solution.getHS().contains("green"));

        //iterator all colors
        Iterator it = solution.getHS().iterator();

        while(it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        System.out.println();

        for(String s:solution.getHS())
            System.out.print(s + " ");



    }
}
