package com.zdcf.test;

import java.util.ArrayDeque;
import java.util.Deque;


    import java.util.ArrayDeque;
import java.util.Deque;

    public class DequeTest {

        /**
         * @param args
         */
        public static void main(String[] args) {
            Deque<Integer> mDeque = new ArrayDeque<Integer>();

            for(int i=0;i<5;i++){
                mDeque.offer(i);
            }

            System.out.println(mDeque.peek());

            System.out.println("***********集合方式遍历**********");

            //集合方式遍历，元素不会被移除
            for (Integer x : mDeque) {
                System.out.println(x);
            }

            System.out.println("**********遍历队列*************");

            //队列方式遍历，元素逐个被移除
            while (mDeque.peek() != null) {
                System.out.println(mDeque.poll());
            }

            System.out.println("***********进栈操作************");

            mDeque.push(10);
            mDeque.push(15);
            mDeque.push(24);
            print(mDeque);

            System.out.println("*********出栈操作*************");

            System.out.println(mDeque.pop());
        }

        public static void print(Deque<Integer> queue){
            //集合方式遍历，元素不会被移除
            for (Integer x : queue) {
                System.out.println(x);
            }
        }

}
