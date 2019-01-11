/*
 非死不可面經
  給定一個node的array, 每個node有左右孩子指像其他node, 判斷這個是否可以組成二叉樹
 */
import java.util.*;
public class ValidBinaryTreeNode {
    static class Node {
        Node left, right;
        int id;
        public Node(int _id){id = _id;}
    }

    public boolean isValid(int n, Node[] list) {
        int[] indegree = new int[n];
//        Arrays.fill(indegree, -1);
        Queue<Node> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        HashMap<Integer, Node> map = new HashMap<>();

        for(int i=0; i<list.length; i++) {
            map.put(i, list[i]);
            if(list[i].left!=null) {
//                if (indegree[list[i].left.id] == -1)
//                    indegree[list[i].left.id] = 0;

                indegree[list[i].left.id]++;
//                if (!map.containsKey(list[i].left.id))
//                map.put(list[i].left.id, list[i].left);
            }
            if(list[i].right != null ) {
//                if (indegree[list[i].right.id] == -1)
//                    indegree[list[i].right.id] = 0;
                indegree[list[i].right.id]++;
//                if (!map.containsKey(list[i].right.id))
//                map.put(list[i].right.id, list[i].right);
            }
        }

        for(int i=0; i<n; i++) {
            if(indegree[i]>1)
                return false;
            if(indegree[i]==0)
                q.offer(map.get(i));
        }

        if(q.size()>1 || q.size()==0)
            return false;

        while(!q.isEmpty()) {
            int sz = q.size();
            for(int i=0; i<sz; i++) {
                Node node = q.poll();
//                if(set.contains(node.left.id)||set.contains(node.right.id))
//                    return false;
//                set.add(node.id);
                if(node.left!=null) {
                    if(set.contains(node.left.id))
                        return false;
                    q.offer(node.left);
                    set.add(node.left.id);
                }
                if(node.right!=null) {
                    if(set.contains(node.right.id))
                        return false;
                    q.offer(node.right);
                    set.add(node.right.id);
                }
            }
        }
        return set.size()==list.length-1;
    }

    public static void main(String[] args) {
        ValidBinaryTreeNode sol = new ValidBinaryTreeNode();
        int n=5;//0, 1, 2, ...4 node
        Node[] list = new Node[n];
        for(int i=0; i<n; i++) {
            list[i] = new Node(i);
        }
        list[0].left = list[1];
        list[0].right = list[2];
        list[1].left = list[3];
        list[2].right = list[4];
        list[4].right = list[0];

        System.out.println(sol.isValid(5, list));

    }
}
