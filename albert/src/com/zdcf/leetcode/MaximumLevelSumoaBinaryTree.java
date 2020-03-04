package com.zdcf.leetcode;

import java.util.LinkedList;
import java.util.Queue;

//5052.  Maximum Level Sum of a Binary Tree
//Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
//
//        Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
//
//
//
//        Example 1:
//
//
//
//        Input: [1,7,0,7,-8,null,null]
//        Output: 2
//        Explanation:
//        Level 1 sum = 1.
//        Level 2 sum = 7 + 0 = 7.
//        Level 3 sum = 7 + -8 = -1.
//        So we return the level with the maximum sum which is level 2.
//
//
//        Note:
//
//        The number of nodes in the given tree is between 1 and 10^4.
//        -10^5 <= node.val <= 10^5
public class MaximumLevelSumoaBinaryTree {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public int maxLevelSum(TreeNode root) {
        int result = 1;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int max = 0;
        int level = 1;
        while(!q.isEmpty()){
            int sum = 0;
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode curNode = q.poll();
                sum +=curNode.val;
                if(curNode.left!=null){
                    q.add(curNode.left);
                }
                if(curNode.right!=null){
                    q.add(curNode.right);
                }
            }
            if(sum>max){
                max = sum;
                result = level;
            }

            level++;

        }
        return result;

    }

}
