package com.zdcf.leetcode;
//2. Add Two Numbers
//You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//Example
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.
public class AddTwoNumbers {
	 public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	  }
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode resultListNode = new ListNode(0);
	        ListNode tempNode = resultListNode;
	        int carry = 0;
	        int  val_1 = 0;
	        int val_2 = 0;
	        int temp = 0;
	        while(l1!=null||l2!=null){
	            val_1 = 0;
	            val_2 = 0;
	            if(null!=l1){
	                val_1 = l1.val;
	                l1 = l1.next;
	            }
	            if(null!=l2){
	                val_2 = l2.val;
	                l2 = l2.next;
	            }
	            temp = val_1+val_2+carry;
	            carry = temp/10;
	            ListNode next = new ListNode(temp%10);
	            tempNode.next  = next;
	            tempNode = tempNode.next;
	        }
	        if(carry==1){
	             ListNode next = new ListNode(carry);
	             tempNode.next =  next;
	        }
	        return resultListNode.next;
	    }
}
