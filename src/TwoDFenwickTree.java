import java.util.Arrays;
import java.util.*;


public class TwoDFenwickTree {

    private int[][] tree;
    private int[][] aux;

    public TwoDFenwickTree(int[][] nums) {
        tree = new int[nums.length+1][nums.length+1];
        aux = new int[nums.length][nums.length];

        for(int i=0;i<nums.length;i++) {
            for(int j=0; j<nums.length; j++) {
                update(i, j, nums[i][j]);
            }
        }
//        for(int i=1; i<=nums.length;i++) {
////            List<Integer> list = Arrays.asList(tree[i]);
            System.out.println(Arrays.deepToString(tree));
//        }
    }

    public void update(int x, int y, int val) {
        int diff = val-aux[x][y];
        aux[x][y] = val;
        for(int i=x+1;i<=aux.length; i+=i&-i) {
            for(int j=y+1; j<=aux.length; j+=j&-j) {
                tree[i][j]+=diff;
            }
        }
        //!!!!!!!!!DOn't use these for loop because after first x iteration
        // y will not start from original y passed in
//        x++;
//        y++;
//        for(;x<=aux.length; x+=x&-x) {
//            for(; y<=aux.length; y+=y&-y) {
//                tree[x][y]+=diff;
//            }
//        }
    }

    public int getSum(int x, int y) {
        int sum=0;

        for(int i=x+1; i>0; i-=i&-i) {
            for(int j=y+1; j>0; j-=j&-j) {
                sum+=tree[i][j];
            }
        }

        //!!!!!!!!!DOn't use these for loop because after first x iteration
        // y will not start from original y passed in
//        for(;x>0; x-=x&-x) {
//            for(;y>0; y-=y&-y) {
//                sum+=tree[x][y];
//            }
//        }
        return sum;
    }
    // to get the rectangular range sum
    // get (0,0)to(x2,y2) -{C+A:[(0,0),(x1-1,y1)} -{A+B:[(0,0),(x1,y1-1)]}+{A[(x1-1,y1-1)]}
    //   A region is deducted twice
//            /\
//            |
//            |------(x1-1,y2)----x2,y2)
//            |          |       |
//            |     C    |   D   |
//            |          |       |
//            |-------------------
//            |       (x1,y1)    |
//            |    A     |    B  |
//            |__________|_______|__________
//            (0, 0)                   x-->

    public int getRangeSum(int x1, int y1, int x2, int y2) {
        return getSum(x2,y2)-getSum(x1-1, y2) - getSum(x2, y1-1)+getSum(x1-1, y1-1);

    }
    public static void main(String[] args) {
        int[][] nums ={{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        TwoDFenwickTree sol = new TwoDFenwickTree(nums);
//        System.out.println(sol.getSum(2,4));
        System.out.println("get rangeSum:"+ sol.getRangeSum(2,1,4,3));
        sol.update(3,2,2);
        System.out.println("get rangeSum:"+ sol.getRangeSum(2,1,4,3));
    }


}
