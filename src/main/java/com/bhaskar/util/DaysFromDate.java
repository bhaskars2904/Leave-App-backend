package com.bhaskar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DaysFromDate {
    public static int getDaysFromDate(Date start, Date end){
        if(start!=null && end!=null){
            long numLeaveDays = end.getTime()-start.getTime();
            numLeaveDays += 24*3600*1000;
            int leaveDays = (int)TimeUnit.DAYS.convert(numLeaveDays, TimeUnit.MILLISECONDS);
            return leaveDays;
        }
        return 0;
    }
    public static int getDaysFromString(String start, String end, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date sd = simpleDateFormat.parse(start);
        Date ed = simpleDateFormat.parse(end);
        long numLeaveDays = ed.getTime()-sd.getTime();
        numLeaveDays += 24*3600*1000;
        int leaveDays = (int)TimeUnit.DAYS.convert(numLeaveDays, TimeUnit.MILLISECONDS);
        return leaveDays;
    }
}
