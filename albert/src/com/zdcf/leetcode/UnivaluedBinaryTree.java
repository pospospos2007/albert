package com.zdcf.leetcode;
//965. Univalued Binary Tree
//A binary tree is univalued if every node in the tree has the same value.
//
//        Return true if and only if the given tree is univalued.
//
//
//
//        Example 1:
//
//
//        Input: [1,1,1,1,1,null,1]
//        Output: true
//        Example 2:
//
//
//        Input: [2,2,2,5,2]
//        Output: false
//
//
//        Note:
//
//        The number of nodes in the given tree will be in the range [1, 100].
//        Each node's value will be an integer in the range [0, 99].
public class UnivaluedBinaryTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public boolean isUnivalTree(TreeNode root) {
        if(root.left==null&&root.right==null){
            return true;
        }
        int consistencyValue = root.val;
        return bfs(root,consistencyValue);

    }
    private boolean bfs(TreeNode root,int consistencyValue){
        if(root==null){
            return true;
        }
        if(root.val!=consistencyValue){
            return false;
        }
        return bfs(root.left,consistencyValue)&&bfs(root.right,consistencyValue);

    }
}
