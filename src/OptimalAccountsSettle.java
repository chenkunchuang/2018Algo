import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Leetcode 465
 * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
 *
 * Return the minimum number of transactions required to settle the debt.
 *
 * Example 1:
 *
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 *
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class OptimalAccountsSettle {
    int min = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        // 先建立balance
        int[] balance = new int[12];

        for(int[] transaction: transactions) {
            int from = transaction[0];
            int to = transaction[1];
            int money = transaction[2];
            balance[from]-=money;
            balance[to]+=money;
        }
        //資產是負的，我們不care誰是負的，我只要最後大家都是０就可以了，
        //所以不需要去記錄哪個人是負的
        List<Integer> debt = new ArrayList<>();
        for(int money: balance)
            if(money!=0)
                debt.add(money);

        backtracking(debt, 0, 0);
        return min;
    }

    private void backtracking(List<Integer> debt, int i, int step) {
        while(i<debt.size()&&debt.get(i)==0) i++;
        if(i==debt.size()) {
            min = Math.min(min, step);
            return;
        }
        if(step>min) return;

        Set<Integer> seen = new HashSet<>();
        int di = debt.get(i);
        for(int j=i+1; j<debt.size(); j++) {
            int dj = debt.get(j);
            if(di*dj<0) {
                seen.add(dj);
                debt.set(j, di+dj);
                backtracking(debt, i+1, step+1);
                debt.set(j, dj);
                // min = Math.min(min, step+1);
            }
            if(di+dj==0)
                break;
        }
        return;
    }

    public static void main(String[] args) {
        int[][] transactions1 = new int[][]{{0,1,10}, {2,0,5}};
        int[][] transactions2 = new int[][]{{0,1,10}, {1,0,1}, {1,2,5}, {2,0,5}};
        int[][] transactions3 = new int[][]{{1,5,8},{8,9,8},{2,3,9},{4,3,1}};
        System.out.println(new OptimalAccountsSettle().minTransfers(transactions1));
//        System.out.println("\n");
        System.out.println(new OptimalAccountsSettle().minTransfers(transactions2));
        System.out.println(new OptimalAccountsSettle().minTransfers(transactions3));
    }
}
