package com.zdcf.test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class TestDate {
 
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begDate = sdf.parse("2017-6-2");
        Date endDate = sdf.parse("2017-6-10");
        if (begDate.after(endDate))
            throw new Exception("日期范围非法");
        // 总天数
        int days = (int) ((endDate.getTime() - begDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
        // 总周数，
        int weeks = days / 7;
        int rs = 0;
        // 整数周
        if (days % 7 == 0) {
            rs = days - 2 * weeks;
        }
        else {
            Calendar begCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            begCalendar.setTime(begDate);
            endCalendar.setTime(endDate);
            // 周日为1，周六为7
            int beg = begCalendar.get(Calendar.DAY_OF_WEEK);
            int end = endCalendar.get(Calendar.DAY_OF_WEEK);
            if (beg > end) {
                rs = days - 2 * (weeks + 1);
            } else if (beg < end) {
                if (end == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            } else {
                if (beg == 1 || beg == 7) {
                    rs = days - 2 * weeks - 1;
                } else {
                    rs = days - 2 * weeks;
                }
            }
        }
        System.out.println(sdf.format(begDate)+"到"+sdf.format(endDate)+"中间有"+rs+"个工作日");
    }
}