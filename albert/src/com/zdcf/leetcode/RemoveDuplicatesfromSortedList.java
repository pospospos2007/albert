package com.zdcf.leetcode;
//83. Remove Duplicates from Sorted List
//Given a sorted linked list, delete all duplicates such that each element appear only once.
//
//Example 1:
//
//Input: 1->1->2
//Output: 1->2
//Example 2:
//
//Input: 1->1->2->3->3
//Output: 1->2->3
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class RemoveDuplicatesfromSortedList {
	

	public ListNode deleteDuplicates(ListNode head) {
        
        if(head==null){
        	return head;
        }
        ListNode temp = head;
        while(temp!=null&&temp.next!=null){
            if(temp.val==temp.next.val){
            	temp.next = temp.next.next;
            }else{
            	temp = temp.next;
            }
            
        }
        return head;
    }
	public static void main(String[] args) {
		RemoveDuplicatesfromSortedList r = new RemoveDuplicatesfromSortedList();
		ListNode l1= new ListNode(1);
		ListNode l2= new ListNode(1);
		ListNode l3= new ListNode(2);
		ListNode l4= new ListNode(3);
		ListNode l5= new ListNode(3);
		l1.next = l2;
		l2.next = l3;
//		l3.next = l4;
//		l4.next = l5;
		ListNode temp =r.deleteDuplicates(l1);
		while(temp!=null){
			System.out.println(temp.val);
			temp=temp.next;
		}
		
	}
}
