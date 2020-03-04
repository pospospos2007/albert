package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//56. Merge Intervals
//Given a collection of intervals, merge all overlapping intervals.
//
//Example 1:
//
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//Example 2:
//
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
public class MergeIntervals {
	
	 public class Interval {
		     int start;
		     int end;
		     Interval() { start = 0; end = 0; }
		     Interval(int s, int e) { start = s; end = e; }
		 }

	 public List<Interval> merge(List<Interval> intervals) {
	        
	        List<Interval> result  = new ArrayList<Interval>();
	        if(intervals==null||intervals.size()<=1){
	            return intervals;
	        }
	         Collections.sort(intervals, (Interval i1, Interval i2) -> (i1.start - i2.start ));
	        
	        Interval pre=intervals.get(0);
	        
	        for(int i=1;i<intervals.size();i++){
	            Interval cur = intervals.get(i);
	            if(pre.end>=cur.start){
	                //do merge
	                Interval interval = new Interval(pre.start,Math.max(pre.end,cur.end));
	                pre = interval;
	            }else if(pre.end<cur.start){
	                result.add(pre);
	                pre = cur;
	            }
	            if(i==intervals.size()-1){
	                result.add(pre);
	            }
	        }
	        return result;
	    }
	   
	 
}
