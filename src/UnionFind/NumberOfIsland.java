package UnionFind;
/*
* This class is demonstrate union-find algorithm to find how many islands.
* Given a 2d grid map of '1's (land) and '0's (water),
* count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
* You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
public class NumberOfIsland {

    WeightedQuickUnionUF<Character> uf;

    public int CountIslands(Character[][] grid) {

        uf = new WeightedQuickUnionUF<Character>(grid, '1');

        int row = grid.length;
        int col = grid[0].length;

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(grid[i][j].equals('1')) {
                    //up neighbor
                    if(i-1>=0&&grid[i-1][j].equals('1'))
                        uf.union(i*col+j, (i-1)*col+j);
                    //down neighbor
                    if(i+1<row&&grid[i+1][j]=='1')
                        uf.union(i*col+j, (i+1)*col+j);

                    //left neighbor
                    if(j-1>=0&&grid[i][j-1]=='1')
                        uf.union(i*col+j, i*col+j-1);

                    //right neighbor
                    if(j+1<col && grid[i][j+1]=='1')
                        uf.union(i*col+j, i*col+j+1);

                }
            }
        }
        return uf.count();
    }

    public static void main(String[] args) {

        NumberOfIsland sol = new NumberOfIsland();
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        Character[][] new_grid = new Character[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++)
            for(int j=0; j<grid[0].length; j++)
                new_grid[i][j] = new Character(grid[i][j]);

        System.out.println(sol.CountIslands(new_grid));
    }

}
