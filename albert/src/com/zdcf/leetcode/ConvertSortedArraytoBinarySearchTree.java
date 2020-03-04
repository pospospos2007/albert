package com.zdcf.leetcode;
//108. Convert Sorted Array to Binary Search Tree
//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
//
//        For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
//        Example:
//
//        Given the sorted array: [-10,-3,0,5,9],
//
//        One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//        0
//        / \
//        -3   9
//        /   /
//        -10  5
public class ConvertSortedArraytoBinarySearchTree {


    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    //思路：使用二分法 自顶向下
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length<1){
            return null;
        }
        return helper(nums,0,nums.length-1);
    }
    private TreeNode helper(int[] nums,int left,int right){
        if(left>right){
            return null;
        }
        int mid =left+(right-left)/2;
        TreeNode node  = new TreeNode(nums[mid]);
        node.left = helper(nums,left,mid-1);
        node.right = helper(nums,mid+1,right);
        return node;
    }

}
