/*
* Given an unsorted array of integers, find the number of longest increasing subsequence.
* Example 1:
* Input: [1,3,5,4,7]
* Output: 2
* Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 */

public class FindNumOfLIS {

    class Node {
        int range_left, range_right;
        Node left, right;
        Value val;
        public Node(int start, int end) {
            range_left = start;
            range_right = end;
            left = null;
            right = null;
            val = new Value(0, 1);
        }
        public int getRangeMid() {
            return range_left + (range_right - range_left) / 2;
        }
        public Node getLeft() {
            if (left == null) left = new Node(range_left, getRangeMid());
            return left;
        }
        public Node getRight() {
            if (right == null) right = new Node(getRangeMid() + 1, range_right);
            return right;
        }
    }

    class Value {
        int length;
        int count;

        public Value(int len, int ct) {
            length = len;
            count = ct;
        }

    }


    public Value merge(Value v1, Value v2) {
        if (v1.length == v2.length) {
            if (v1.length == 0) return new Value(0, 1);
            return new Value(v1.length, v1.count + v2.count);
        }
        return v1.length > v2.length ? v1 : v2;
    }

    public void insert(Node node, int key, Value val) {
        if (node.range_left == node.range_right) {
            node.val = merge(val, node.val);
            return;
        } else if (key <= node.getRangeMid()) {
            insert(node.getLeft(), key, val);
        } else {
            insert(node.getRight(), key, val);
        }
        node.val = merge(node.getLeft().val, node.getRight().val);
    }

    public Value query(Node node, int key) {
        if (node.range_right <= key) return node.val;
        else if (node.range_left > key) return new Value(0, 1);
        else return merge(query(node.getLeft(), key), query(node.getRight(), key));
    }

    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int min = nums[0], max = nums[0];
        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        Node root = new Node(min, max);
        for (int num: nums) {
            Value v = query(root, num-1);
            insert(root, num, new Value(v.length + 1, v.count));
        }
        return root.val.count;
    }


    public static void main(String[] args) {
        FindNumOfLIS sol = new FindNumOfLIS();

        int[] input1 = {1,3,5,4,7};
        int[] input2 = {2,2,2,2,2};
        System.out.println("number of LIS: " + sol.findNumberOfLIS(input1));
        System.out.println("number of LIS: " + sol.findNumberOfLIS(input2));

    }
}



