/*
  Given a sparse vectors, we can use two pointers or binary search if one vector is much smaller than the other.
 */
import java.util.*;

public class SparseVector {

    public int product (int[][] A, int[][] B) {
        int sum=0, l=0, r=B.length-1;
        for(int i=0; i<A.length; i++ ) {
            int idx = A[i][0];
            int match = binarysearch(B, l, r, idx);
            if(B[match][0]==idx) {
                sum+=A[i][1]*B[match][1];
            }
            l = match;
        }
        return sum;
    }

    public int binarysearch(int[][] v, int l, int r, int target) {
        while(l<r) {
            int mid = l+(r-l)/2;
            if(v[mid][0]<target)
                l=mid+1;
            else
                r=mid;
        }
        return l;
    }

    public static void main(String[] args) {
        SparseVector sol = new SparseVector();

        int[][] A = {{1,5}, {3, 4}, {4,1},{7, 18},{20,5}};
        int[][] B = {{1,3}, {2,5}, {5,9},{20,1}};


        System.out.println(sol.product(A, B));
    }

}
