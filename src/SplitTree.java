/*
This class is to delete a given node and then split the tree if possible.
 */

import java.util.*;

public class SplitTree {

    static class TreeNode {
        public   TreeNode left, right;
        public int val;
        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private List<TreeNode> list;
    private TreeNode splitHead;
    private TreeNode root;

    public SplitTree(String[] nodes) {
        Queue<TreeNode> q = new LinkedList<>();
        list = new ArrayList<>();
        root = new TreeNode(Integer.valueOf(nodes[0]));
        q.offer(root);
        int idx=1;
        while(!q.isEmpty()&&idx<nodes.length) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                TreeNode node = q.poll();
                node.left = nodes[idx].equals("null") ? null : new TreeNode(Integer.valueOf(nodes[idx]));
                idx++;
                node.right = nodes[idx].equals("null")? null: new TreeNode(Integer.valueOf(nodes[idx]));
                idx++;
                if(node.left!=null)
                    q.offer(node.left);
                if(node.right!=null)
                    q.offer(node.right);
            }
        }
        list.add(root);
    }

    private TreeNode[] split(TreeNode head, int key) {
//        splitHead = head;
//        isFound = false;
        if(head==null) return new TreeNode[]{null, null};
        TreeNode[] splitsHead = new TreeNode[2];
        if(head.val<key) {
            splitsHead[0] = head;
            TreeNode[] splitsRight = split(head.right, key);
            if(head.right == splitsRight[1]) {
             //只有當要刪除的node是head.right,才切開
                head.right = null;
            }
            splitsHead[1] = splitsRight[1];//要刪掉的
            return splitsHead;
        }
        else if(head.val>key) {
            splitsHead[0] = head;
            TreeNode[] splitsLeft = split(head.left, key);
            if(head.left==splitsLeft[1]) {
                //只有當要刪除的node是head.left,才切開
                head.left=null;
            }
            splitsHead[1] = splitsLeft[1];
            return splitsHead;
        }
        else {
            splitsHead[1] = head;
            return splitsHead;
        }
    }

    public void printTrees() {
        for(TreeNode root1:list) {
            if(root1!=null) {
                Queue<TreeNode> q = new LinkedList<>();
                q.offer(root1);
                List<String> preorder = new ArrayList<>();
                while(!q.isEmpty()) {
                    int size = q.size();
                    for(int i=0; i<size; i++) {
                        TreeNode node = q.poll();
                        if(node==null) {
                            preorder.add("null");
                        }
                        else {
                            preorder.add(String.valueOf(node.val));

                            if (node.left == null && node.right == null)
                                continue;
                            q.offer(node.left);
                            q.offer(node.right);
                        }
                    }
                }
                System.out.println(preorder);
            }
        }
    }

//    public void split(int key) {
//        this.split(root, key);
//    }
    public boolean deleteNode(int key) {
//        list.clear();
        boolean isFound = false;
        for(int i=0; i<list.size(); i++ ) {
            TreeNode[] trees = split(list.get(i), key);
            if (trees[1] != null) {
                list.remove(i);
                if(trees[0]!=null)
                  list.add(i, trees[0]);
                deleteNode(trees[1], key);
                printTrees();
                isFound = true;
            }
        }
        return isFound;
    }
    // this API is delete the node with given key.
    // true: found the matching key and delete it. false: no key found
    private TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)
            return null;
        TreeNode node = root;
        if(key>node.val) {
           node.left = deleteNode(node.right, key);
           list.add(splitHead);
        }
        else if(key<node.val) {
            node.left = deleteNode(node.left, key);
            list.add(splitHead);
        }
        else {
            if(node.left!=null)
                list.add(node.left);
            if(node.right!=null)
                list.add(node.right);
            node = null;
        }
        return node;
    }

    public static void main(String[] args) {
        String[] BST = {"6", "3", "8", "2", "4", "7", "9","1","null","null","null","null","null","null","10"};
        SplitTree sol = new SplitTree(BST);
        sol.deleteNode(6);
        System.out.println();
        sol.deleteNode(5);
        System.out.println();
        sol.deleteNode(3);
        System.out.println();
        sol.deleteNode(7);
        System.out.println();
        sol.deleteNode(4);

        HashSet<int[] > set = new HashSet<>();
        set.add(new int[] {0,1});
        set.add(new int[] {1,0});
        set.add(new int[] {2,0});
        int [] a = {2,0};
        if(set.contains(a)) {
            System.out.println("contains");
        }
    }
}
