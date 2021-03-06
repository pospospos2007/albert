package com.zdcf.leetcode;

import java.util.Stack;

//20. Valid Parentheses
//Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//        An input string is valid if:
//
//        Open brackets must be closed by the same type of brackets.
//        Open brackets must be closed in the correct order.
//        Note that an empty string is also considered valid.
//
//        Example 1:
//
//        Input: "()"
//        Output: true
//        Example 2:
//
//        Input: "()[]{}"
//        Output: true
//        Example 3:
//
//        Input: "(]"
//        Output: false
//        Example 4:
//
//        Input: "([)]"
//        Output: false
//        Example 5:
//
//        Input: "{[]}"
//        Output: true
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack= new  Stack<Character>();
        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(!stack.isEmpty()){
                if(stack.peek()=='('&&c==')'){
                    stack.pop();
                    continue;
                }
                if(stack.peek()=='['&&c==']'){
                    stack.pop();
                    continue;
                }
                if(stack.peek()=='{'&&c=='}'){
                    stack.pop();
                    continue;
                }

            }
            stack.push(c);
        }
        return stack.isEmpty();
    }
}
