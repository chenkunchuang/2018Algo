package UnionFind;
/*
* This class is to implement improved union find algorithm.
*  quick-find: takes too long O(MN)
*  quick-union: find too expensive and tree can be tall
*  Algorithm  Worst-case
*  timeQuick-find  M
*  NQuick-union  M
*  NWeighted  QU  N  +  M  log
*  NPath  compression  N  +  M  log
*  NWeighted  +  path  (M  +  N)  lg*  N
*
*/
public class WeightedQuickUnionUF<E>{

    private int[] father; // father link
    private int[] sz; // size of component for roots(fathers)
    private int count; // number of components

    public WeightedQuickUnionUF(E[][] grid, E key) {
        int row = grid.length;
        int col = grid[0].length;
//        count = 0;
        father = new int[row*col];
        sz = new int[row*col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j].equals(key)) {
                    int idx = i*col + j;
                    father[idx] = idx;
                    sz[idx] = 1;
                    count++;
                }
            }
        }
    }
    //find the root of given node
    private int find(int i) {
        while(i!=father[i]) {
            father[i] = father[father[i]]; // this step is to break down tree path to all its root
            i = father[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return find(p)==find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if(i==j) return;

        //merge smaller root to larger one
        if(sz[i]<sz[j]) {
            father[i] = j;
            sz[j] +=sz[i];
        }
        else {
            father[j] = i;
            sz[i]+=sz[j];
        }
        count--;
    }

    public int count() {
        return count;
    }


}
