package com.zdcf.leetcode;
//572. Subtree of Another Tree
//Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
//
//        Example 1:
//        Given tree s:
//
//        3
//        / \
//        4   5
//        / \
//        1   2
//        Given tree t:
//        4
//        / \
//        1   2
//        Return true, because t has the same structure and node values with a subtree of s.
//        Example 2:
//        Given tree s:
//
//        3
//        / \
//        4   5
//        / \
//        1   2
//        /
//        0
//        Given tree t:
//        4
//        / \
//        1   2
//        Return false.
public class SubtreeofAnotherTree {

      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        //s为空时就直接返回false
        if(s==null){
            return false;
        }
        if(isSame( s, t)){
            return true;
        }
        return isSubtree(s.left,t)||isSubtree(s.right,t);

    }
    private boolean isSame(TreeNode s,TreeNode t){
        if(s==null&&t==null){
            return true;
        }
        if(s==null||t==null){
            return false;
        }
        if(s.val!=t.val){
            return false;
        }
        return isSame(s.left,t.left)&&isSame(s.right,t.right);
    }
}
