/*
 Determine if the integer of binary representation is palindrome.
 */
import java.util.*;

public class BinaryIsPalindrom {

    public boolean isPal(int num) {
        if(num%2==0)
            return false;
        int l=1, r=0x80000000;
        while(l<r) {
            if((num&l)!=(num&r))
                return false;
            l<<=1;
            r>>=1;
        }
        return true;
    }
    public static void main(String[] args) {
        BinaryIsPalindrom sol = new BinaryIsPalindrom();
        System.out.println(sol.isPal(9));
        System.out.println(sol.isPal(8));
        System.out.println(sol.isPal(23));
        System.out.println(sol.isPal(15));

    }
}
