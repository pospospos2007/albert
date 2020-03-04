package com.zdcf.leetcode;
//925. Long Pressed Name
//Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
//
//        You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
//
//
//
//        Example 1:
//
//        Input: name = "alex", typed = "aaleex"
//        Output: true
//        Explanation: 'a' and 'e' in 'alex' were long pressed.
//        Example 2:
//
//        Input: name = "saeed", typed = "ssaaedd"
//        Output: false
//        Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
//        Example 3:
//
//        Input: name = "leelee", typed = "lleeelee"
//        Output: true
//        Example 4:
//
//        Input: name = "laiden", typed = "laiden"
//        Output: true
//        Explanation: It's not necessary to long press any character.
//
//
//        Note:
//
//        name.length <= 1000
//        typed.length <= 1000
//        The characters of name and typed are lowercase letters.
public class LongPressedName {

    public static boolean isLongPressedName(String name, String typed) {
        int i=0,j=0;
        char pre = name.charAt(0);
        while(i<name.length()&&j<typed.length()){
            if(name.charAt(i)==typed.charAt(j)){

                pre = name.charAt(i);
                i++;
                j++;
            }else{
                if(pre==typed.charAt(j)){
                    j++;
                }else{
                    return false;
                }
            }

        }
        if(i<name.length()){
            return false;
        }
        while(j<typed.length()){
            if(pre==typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }


        return true;
    }

    public static void main(String[] args) {
        String name = "alex";
        String typed =  "aaleex";
        System.out.println(isLongPressedName(name,typed));
    }
}
