import java.util.*;

public class ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        if(grid.length==0|| grid[0].length==0)
            return -1;
        Queue<int[]> q = new LinkedList<>();
        int m=grid.length, n=grid[0].length;
        int[][] sum = new int[m][n];
        int idx=0;
        for(int[] a:sum) {
            a = Arrays.copyOf(grid[idx++], n);
        }
        int[][] moves ={{-1,0},{1,0},{0,-1},{0,1}};
        int res = Integer.MAX_VALUE;
        int val = 0;//這是用來判斷每個樓可以走的位置
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==1) {
                    res = Integer.MAX_VALUE;
                    int idx1=0;
                    int[][] dist = new int[m][n];
                    for(int[] a: dist)
                        a = Arrays.copyOf(grid[idx1++],n);
                    q.offer(new int[]{i, j});
                    while(!q.isEmpty()) {
                        // System.out.println("cnt:"+cnt);
                        int[] dis = q.poll();
                        int row = dis[0];
                        int col = dis[1];
                        // int steps = dis[2]+1;
                        for(int[] move: moves) {
                            int new_row = row+move[0];
                            int new_col = col+move[1];
                            if(new_row>=0&&new_row<m&&new_col>=0&&new_col<n) {
                                if(grid[new_row][new_col]==val){ //&&dist[new_row][new_col]>steps) {

                                    grid[new_row][new_col]--;
                                    dist[new_row][new_col]=dist[row][col]+1;
                                    q.offer(new int[]{new_row, new_col});
                                    sum[new_row][new_col]+=dist[new_row][new_col];
                                    // System.out.println("new_row:"+new_row+" new_col:"+new_col+" steps:"+sum[new_row][new_col]+" val:"+val);
                                    res = Math.min(res, sum[new_row][new_col]);
                                }
                            }
                        }
                    }
                    val--;
                }

            }
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ShortestDistanceFromAllBuildings sol = new ShortestDistanceFromAllBuildings();
        System.out.println(sol.shortestDistance(grid));
    }
}
