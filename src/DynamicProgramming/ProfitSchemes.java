package DynamicProgramming;

/*
There are G people in a gang, and a list of various crimes they could commit.
The i-th crime generates a profit[i] and requires group[i] gang members to participate.
If a gang member participates in one crime, that member can't participate in another crime.
Let's call a profitable scheme any subset of these crimes that generates at least P profit,
and the total number of gang members participating in that subset of crimes is at most G.
How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.


Example 1:

Input: G = 5, P = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation:
To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
 */

public class ProfitSchemes {
    final int kMod = 1000000007;
    public int profitableSchemes(int P, int G, int[] group, int[] profit) {
        int[][][] dp = new int[group.length+1][P+1][G+1];

        dp[0][0][0]=1;
        for(int k=1; k<=group.length; k++) {
            int curr_p = profit[k-1];
            int curr_g = group[k-1];

            for(int i=0; i<=P; i++) {
                for(int j=0; j<=G; j++) {
                    dp[k][i][j] = dp[k-1][i][j]+(j<curr_g?0:dp[k-1][Math.max(0,i-curr_p)][j-curr_g])%kMod;
                }
            }
        }
        long ret =0;
        for(int sum:dp[group.length][P]) {
            ret+=sum;
        }
        return (int)ret;
    }

    public static void main(String[] args) {
        ProfitSchemes sol = new ProfitSchemes();

        System.out.println(sol.profitableSchemes(3,5,new int[]{2,2},new int[]{2,3}));
        System.out.println(sol.profitableSchemes(5,10,new int[]{2,3,5},new int[]{6,7,8}));
    }

}
