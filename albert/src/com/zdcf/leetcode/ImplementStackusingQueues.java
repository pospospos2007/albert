package com.zdcf.leetcode;

import java.util.LinkedList;
import java.util.Queue;

//225. Implement Stack using Queues
//Implement the following operations of a stack using queues.
//
//push(x) -- Push element x onto stack.
//pop() -- Removes the element on top of the stack.
//top() -- Get the top element.
//empty() -- Return whether the stack is empty.
//Example:
//
//MyStack stack = new MyStack();
//
//stack.push(1);
//stack.push(2);  
//stack.top();   // returns 2
//stack.pop();   // returns 2
//stack.empty(); // returns false
public class ImplementStackusingQueues {
	private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    
    // Push element x onto stack.
    public void push(int x) {
        q1.offer(x);
    }
 
    // Removes the element on top of the stack.
    public void pop() {
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        q1.poll();
        
        Queue tmp = q1;
        q1 = q2;
        q2 = tmp;
    }
 
    // Get the top element.
    public int top() {
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }
        int top = q1.peek();
        q2.offer(q1.poll());
        
        Queue tmp = q1;
        q1 = q2;
        q2 = tmp;
        
        return top;
    }
 
    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty();
    }
    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
}
