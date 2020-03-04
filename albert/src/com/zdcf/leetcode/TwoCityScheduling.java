package com.zdcf.leetcode;

import java.util.Arrays;
import java.util.Comparator;

//1029. Two City Scheduling
//There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
//
//        Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
//
//
//
//        Example 1:
//
//        Input: [[10,20],[30,200],[400,50],[30,20]]
//        Output: 110
//        Explanation:
//        The first person goes to city A for a cost of 10.
//        The second person goes to city A for a cost of 30.
//        The third person goes to city B for a cost of 50.
//        The fourth person goes to city B for a cost of 20.
//
//        The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
//
//
//        Note:
//
//        1 <= costs.length <= 100
//        It is guaranteed that costs.length is even.
//        1 <= costs[i][0], costs[i][1] <= 1000
public class TwoCityScheduling {

    public int twoCitySchedCost(int[][] costs) {
//         假设所有的人都选择城市A， 这时候sum=sum{a[i][0]},
// 然后要选择一半的人改成B， 这个时候, 选择某一个人对sum的影响是d=a[i][1]-a[i][0],
// 那么， 我们要让结果最小， 就需要让这个d最小， 那按照这个d对数组排序，然后选择最小的一半就好
        Comparator com = new Comparator<int[]>(){
            public int compare(int[] a ,int b[]){
                int v1 = a[1] - a[0];
                int v2 = b[1] -b[0];
                return v1-v2;
            }
        };
        Arrays.sort(costs,com);
        int sum = 0;
        for(int i=0;i<costs.length;i++){
            sum+=costs[i][0];
        }
        for(int i=0;i<costs.length/2;i++){
            sum+=costs[i][1]-costs[i][0];
        }
        return sum;

    }
}
