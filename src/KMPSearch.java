/*
This is implement KMP algorithm to search for pattern in the string
The main idea is to have a KMP table to store the pattern back up position, i.e., instead of going back
to the first char of pattern search, it goes back to previous position.
 */
public class KMPSearch {
    private int[] KMP_Table;
//
//    public KMPSearch() {
//    }

    // search the pattern in string
    public boolean search(String s, String pat) {
        int i, j, s_len = s.length(), pat_len = pat.length();
        KMP_Table = buildKMP(pat);
        //Don't increment index unless some conditions are met
        for(i=0, j=0; i<s_len&&j<pat_len; ) {
            if(s.charAt(i)==pat.charAt(j)) {
                i++;
                j++;
            }
            else {
                //when comparing and if it doesn't match,
                // back up pattern index until reaching the first char of pattern
                if(j>0) {
                    j = KMP_Table[j-1];
                }
                else {
                    i++;
                }
            }
        }
        return j==pat_len;


    }

    // build KMP table
    public int[] buildKMP(String pattern) {
        int[] kmp = new int[pattern.length()];

        int i, j;
        kmp[0] =0;
        for(i=1, j=0; i<pattern.length();) {
            if(pattern.charAt(i)==pattern.charAt(j)) {
                kmp[i] = j+1;
                i++;
                j++;
            }
            else {
                if(j>0) {
                    j=kmp[j-1];//just back up to previous position and compare with current i again.
                }
                else {
                    // if no other position to go back other than first char, it means a new start pattern compare again.
                    kmp[i]=0;
                    i++;
                }
            }
        }
        return kmp;
    }
    public static void main(String[]args) {
        KMPSearch obj = new KMPSearch();
        String s1="abaabbchhguy";
        String pattern = "aabc";

        System.out.println("string:"+s1+" has patter:"+ pattern +" : "+obj.search(s1, pattern));
    }
}
