package com.zdcf.leetcode;
//1026. Maximum Difference Between Node and Ancestor
//        Medium
//
//        45
//
//        3
//
//        Favorite
//
//        Share
//        Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
//
//        (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
//
//
//
//        Example 1:
//
//
//
//        Input: [8,3,10,1,6,null,14,null,null,4,7,13]
//        Output: 7
//        Explanation:
//        We have various ancestor-node differences, some of which are given below :
//        |8 - 3| = 5
//        |3 - 7| = 4
//        |8 - 1| = 7
//        |10 - 13| = 3
//        Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
//
//
//        Note:
//
//        The number of nodes in the tree is between 2 and 5000.
//        Each node will have value between 0 and 100000.
public class MaximumDifferenceBetweenNodeandAncestor {


    //思路用dfs

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public int maxAncestorDiff(TreeNode root) {
        return  dfs(root,root.val,root.val);


    }
    private int dfs(TreeNode node,int left,int right){
        if(node==null){
            return 0;
        }
        int result  =  Math.max(Math.abs(node.val-left),Math.abs(right-node.val));
        left = Math.min(left,node.val);
        right = Math.max(right,node.val);
        int l = dfs(node.left,left,right);
        int r = dfs(node.right,left,right);
        return Math.max(Math.max(result,l),r);

    }
}
