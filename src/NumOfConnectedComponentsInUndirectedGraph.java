/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
import java.util.*;
public class NumOfConnectedComponentsInUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++)
            graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int count=0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                dfs(graph, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(List<List<Integer>> graph, boolean[] visited, int v) {
        if(visited[v])
            return;
        visited[v] = true;
        for(Integer w: graph.get(v)) {
            if(!visited[w])//!!! 這邊不可以加這個判斷,因為
                dfs(graph, visited, w);
        }
        return;
    }

    public static void main(String[] args) {
        NumOfConnectedComponentsInUndirectedGraph sol = new NumOfConnectedComponentsInUndirectedGraph();
        int[][] edges1 ={{0,1},{1,2},{3,4}};
        System.out.println(sol.countComponents(5, edges1));
    }
}
