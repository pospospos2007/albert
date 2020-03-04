package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.List;

//113. Path Sum II
//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//
//        Note: A leaf is a node with no children.
//
//        Example:
//
//        Given the below binary tree and sum = 22,
//
//        5
//        / \
//        4   8
//        /   / \
//        11  13  4
//        /  \    / \
//        7    2  5   1
//        Return:
//
//        [
//        [5,4,11,2],
//        [5,8,4,5]
//        ]`
public class PathSumII {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<>();
        helper(result,temp,sum,root);
        return result;
    }
    private void helper(List<List<Integer>> result, List<Integer>  temp,int sum,TreeNode root){
        if(root==null){
            return ;
        }
        sum -=root.val;
        temp.add(root.val);
        if(root.left==null&&root.right==null){
            if(sum==0){
                result.add(new ArrayList<>(temp));
            }
        }
        if(root.left!=null&&root.right==null){
            helper(result,temp,sum,root.left);
        }
        if(root.left==null&&root.right!=null){
            helper(result,temp,sum,root.right);
        }
        if(root.left!=null&&root.right!=null){
            helper(result,temp,sum,root.right);
            helper(result,temp,sum,root.left);
        }
        temp.remove(temp.size()-1);

    }


}
