import java.util.*;

public class ShortestSubArraySumAtLeastK {

    public int shortestSubarray(int[] A, int K) {
        //這邊要用long, 因為 10^5*50000=5 billion. 整數最大 2B
        long[] prefix = new long[A.length+1];
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=1; i<prefix.length; i++)
            prefix[i] = prefix[i-1]+A[i-1];
        int min = Integer.MAX_VALUE;
        for(int i=0; i<prefix.length; i++) {
            while(!deque.isEmpty()&&prefix[i]<prefix[deque.peekLast()])
                deque.pollLast();
            //表示sum[0,i-1] 的和 比 sum[0, deque.peekFirst()-1]+k還要大,所以表示[dequeu.peekFirst(), i-1]的subarray
            //和大於等於K
            while(!deque.isEmpty()&&prefix[i]>=prefix[deque.peekFirst()]+K)
                min=Math.min(min, i-deque.pollFirst());
            deque.offerLast(i);
        }
        return min==Integer.MAX_VALUE?-1:min;
    }

    public static void main(String[] args) {
        ShortestSubArraySumAtLeastK sol = new ShortestSubArraySumAtLeastK();
        int[] a = {84,-37,32,40,95};

        System.out.println(sol.shortestSubarray(a, 167));
    }
}
