package com.zdcf.leetcode;
//98. Validate Binary Search Tree
//Given a binary tree, determine if it is a valid binary search tree (BST).
//
//        Assume a BST is defined as follows:
//
//        The left subtree of a node contains only nodes with keys less than the node's key.
//        The right subtree of a node contains only nodes with keys greater than the node's key.
//        Both the left and right subtrees must also be binary search trees.
//
//
//        Example 1:
//
//        2
//        / \
//        1   3
//
//        Input: [2,1,3]
//        Output: true
//        Example 2:
//
//        5
//        / \
//        1   4
//        / \
//        3   6
//
//        Input: [5,1,4,null,null,3,6]
//        Output: false
//        Explanation: The root node's value is 5 but its right child's value is 4.
public class ValidateBinarySearchTree {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public boolean isValidBST(TreeNode root) {
        return helper(root,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    }
    private boolean helper(TreeNode root,Double min,Double max){
        if(root==null){
            return true;
        }
        if(root.val<=min||root.val>=max){
            return false;
        }
        return helper(root.left,min,(double)root.val)
                &&helper(root.right,(double)root.val,max);
    }
}
