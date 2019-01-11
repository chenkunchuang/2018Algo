/*
This class is add two kbase number string
用一個table把相對數字轉成字串, 另一個table是把字符轉成數字
轉換主要注意兩件事情,
1. 先把字符轉成相對應的數字, 相加後, 先modulo base, 然後在進位
2. 把數字轉成相對應的字符
 */
import java.util.*;

public class AddTwoNumberInKBase {
    String[] values ={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public String add(String num1, String num2, int base) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(char c='A'; c<='F'; c++) {
            map.put(c, c-'A'+10);
        }
        for(char c='0'; c<='9'; c++) {
            map.put(c, c-'0');
        }

        int i=num1.length()-1, j=num2.length()-1;
        int carry=0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j>=0) {
            int sum=carry+(i>=0?map.get(num1.charAt(i)):0)+(j>=0?map.get(num2.charAt(j)):0);
            sb.insert(0, values[sum%base]);

            carry=sum/base;
            i--; j--;
        }
        if(carry!=0)
            sb.insert(0, "1");
        return sb.toString();
    }

    public String deduct(String num1, String num2) {
        int sign1=1, sign2=1;
        boolean neg=false;
        if(num1.charAt(0)=='-') {
            sign1 = -1;
            num1=num1.substring(1);
        }
        if(num2.charAt(0)=='-') {
            sign2 = -1;
            num2 = num2.substring(1);
        }
        if(sign1<0&&sign2<0)
            return "-"+add(num1, num2, 10);
        if(isSmaller(num1, num2)) {
            String temp = num1;
            num1=num2;
            num2 = temp;
            int tmp=sign1;
            sign1=sign2;
            sign2=tmp;
        }
        int i=num1.length()-1, j=num2.length()-1;
//            return deduct(num2, num1);

        int carry=0;
        String ans = "";
        while(i>=0||j>=0) {
            int a=0, b=0;
//            if(num1.charAt(i)!='-')
            a = i>=0?num1.charAt(i)-'0':0;
//            if(num2.charAt(j)!='-')
            b = j>=0?num2.charAt(j)-'0':0;

            int sum = a-b-carry;
            if(sum<0) {
                carry=1;
                sum+=10;
            }
            else {
                carry=0;
            }
            if(i!=0 || j!=0)
                ans = String.valueOf(sum) + ans;
            i--;j--;
        }
        if(ans.charAt(0)=='0')
            ans = ans.substring(1);
        if(sign1<0)
            return "-"+ans;
        else
            return ans;

    }
    public boolean isSmaller(String num1, String num2) {
        if(num1.length()<num2.length())
            return true;
        else if(num1.length()==num2.length()){
            for(int i=0; i<num1.length(); i++)
                if(num1.charAt(i)<num2.charAt(i))
                    return false;
                else break;
        }

        return false;
    }
    public static void main(String[] args) {
        AddTwoNumberInKBase sol = new AddTwoNumberInKBase();
        System.out.println("2 base:"+sol.add("1010", "111", 2));
        System.out.println("16 base:"+sol.add("ABCF", "10BA", 16));
        System.out.println("8 base:" + sol.add("4567", "7211", 8));
//        System.out.println(Integer.valueOf("A", 16));
        System.out.println(sol.deduct("-100", "40"));
        System.out.println(sol.deduct("-234","-1151"));
    }
}
