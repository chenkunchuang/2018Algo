import java.util.*;
/*
This class is to compute sum of   low<node.val<=hi
 */

public class BSTRangeSum {
    SplitTree.TreeNode root;

    public BSTRangeSum(String[] nodes) {
        Queue<SplitTree.TreeNode> q = new LinkedList<>();
        root = new SplitTree.TreeNode(Integer.valueOf(nodes[0]));
        q.offer(root);
        int idx=1;
        while(!q.isEmpty()&&idx<nodes.length) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                SplitTree.TreeNode node = q.poll();
                node.left = nodes[idx].equals("null") ? null : new SplitTree.TreeNode(Integer.valueOf(nodes[idx]));
                idx++;
                node.right = nodes[idx].equals("null")? null: new SplitTree.TreeNode(Integer.valueOf(nodes[idx]));
                idx++;
                if(node.left!=null)
                    q.offer(node.left);
                if(node.right!=null)
                    q.offer(node.right);
            }
        }
    }

    public int getRangeSum(int lo, int hi) {
        return getRangeSum(root, lo, hi);
    }

    private int getRangeSum(SplitTree.TreeNode parent, int lo, int hi) {
        if(parent==null)
            return 0;

        int leftSum=0, rightSum=0;

        if(parent.val>hi) {
            leftSum = getRangeSum(parent.left, lo, hi);
        }
        else if(parent.val<lo) {
            rightSum = getRangeSum(parent.right, lo, hi);
        }
        else {
            leftSum = getRangeSum(parent.left, lo, hi);
            rightSum = getRangeSum(parent.right, lo, hi);
        }

        return leftSum+rightSum+((parent.val>lo&&parent.val<=hi)?parent.val:0);

    }

    public static void main(String[] args) {
        String[] BST = {"6", "3", "8", "2", "4", "7", "9","1","null","null","null","null","null","null","10"};

        BSTRangeSum sol = new BSTRangeSum(BST);

        System.out.println(" 7->10 range sum:"+sol.getRangeSum(7, 10));
        System.out.println(" 2->9 range sum:"+sol.getRangeSum(2, 9));
        System.out.println(" 4->6 range sum:"+sol.getRangeSum(4, 6));
        System.out.println(" 1->3 range sum:"+sol.getRangeSum(1, 3));
        System.out.println(" 7->8 range sum:"+sol.getRangeSum(7, 8));

    }
}
