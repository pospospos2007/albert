package com.zdcf.test;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {

    public static void main(String[] args) {
        Queue<Integer> qi = new PriorityQueue<Integer>();

        qi.add(5);
        qi.add(2);
        qi.add(1);
        qi.add(10);
        qi.add(3);

        while (!qi.isEmpty()) {
            System.out.print(qi.poll() + ",");
        }
        System.out.println();
        System.out.println("-----------------------------");
        // 自定义的比较器，可以让我们自由定义比较的顺序 Comparator<Integer> cmp;
        Comparator cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        };
        Queue<Integer> q2 = new PriorityQueue<Integer>(5, cmp);
        q2.add(2);
        q2.add(8);
        q2.add(9);
        q2.add(1);
        while (!q2.isEmpty()) {
            System.out.print(q2.poll() + ",");
        }

    }

}