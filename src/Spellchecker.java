
import java.util.*;
import java.io.*;
import java.net.*;

public class Spellchecker
{
    public static void main(String[] args) throws IOException
    {
        URL url = new URL("http://andrew.cmu.edu/course/15-121/dictionary.txt");
        Scanner sc = new Scanner( url.openStream() );

        HashSet<String> dict =  new HashSet();

        while( sc.hasNext() ) dict.add(sc.next());
        sc.close();
        String path = System.getProperty("user.dir")+"/src/";
        sc = new Scanner( new File(path + "Spellchecker.java") ) ;
        while( sc.hasNextLine() )
        {
            String[] tokens = sc.nextLine().split("\\W");
            for(String token : tokens)
                if (token.length() > 1 && !dict.contains(token.toLowerCase()))
                    System.out.println(token);
        }
    }
}