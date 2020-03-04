package com.zdcf.leetcode;
import java.util.HashSet;
//663. Equal Tree Partition
//Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.
//        Example 1:
//        Input:
//        5
//        / \
//        10 10
//        /  \
//        2   3
//
//        Output: True
//        Explanation:
//        5
//        /
//        10
//
//        Sum: 15
//
//        10
//        /  \
//        2    3
//
//        Sum: 15
//        Example 2:
//        Input:
//        1
//        / \
//        2  10
//        /  \
//        2   20
//
//        Output: False
//        Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
//        Note:
//        The range of tree node value is in the range of [-100000, 100000].
//        1 <= n <= 10000
//
//        Analysis:
//        Get all sums, check whether the collections of sums contains totalSum/2. Do not include total sum to set of sums, if the total sum is 0, it will qualify.
public class EqualTreePartition {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private int helper(TreeNode node, HashSet<Integer> h){
        if(node==null){
            return 0;
        }
        int sum = node.val +helper(node.left,h)+helper(node.right,h);
        h.add(sum);
        return sum;

    }
    public boolean checkEqualTree(TreeNode root) {

        if(root ==null){
            return false;
        }
        HashSet<Integer> h = new HashSet<>();
        int sum = root.val+helper(root.left,h)+helper(root.right,h);
        return sum%2==0&&h.contains(sum/2);

    }
}
