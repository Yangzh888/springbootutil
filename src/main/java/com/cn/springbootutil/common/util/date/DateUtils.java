package com.cn.springbootutil.common.util.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }


    /**
     * 获取当前日期时间（字符串格式）yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getMomentDateTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        return df.format(new Date());
    }
    public static String getMomentDay(String formatte) {
        SimpleDateFormat df = new SimpleDateFormat(formatte);
        return df.format(new Date());
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 获取当前时间的时间戳
     * @param prefix
     * @return
     */
    public static String getTimestamp(String prefix) {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        //new Date()为获取当前系统时间，也可使用当前时间戳
        String date = df.format(new Date());

        return prefix+date;
    }

    /**
     * 获取当前时间加N天的时间(加用正数表示，减用负数表示)
     */
    public static Date getTimeAfterOperation(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,day);
        return calendar.getTime();
    }

    /**
     * 获取上n/下n 个月的日期格式
     * @param format 需要转成的格式
     * @param integer 负数代表往前算，正数往后算。-1则上个月
     * @return
     */
    public static  String getAnyMonth(String format,Integer integer){
        SimpleDateFormat sdf1=new SimpleDateFormat(format);
        Calendar calendar8 = Calendar.getInstance();
        calendar8.add(Calendar.MONTH, integer);
        String str = sdf1.format(calendar8.getTime());
        return str;
    }
    /**
     * 获取上个月第一天第一秒的时间
     * @return
     */
    public static  Date getLastMonthStartDate(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        calendar1.set(Calendar.HOUR_OF_DAY, 23); //将小时至23
        calendar1.set(Calendar.MINUTE, 59); //将分钟至59
        calendar1.set(Calendar.SECOND,59); //将秒至59
        calendar1.set(Calendar.MILLISECOND, 59); //将毫秒至59
        return calendar1.getTime();
    }
    /**
     * 获取上个月最后一天最后一秒的时间
     * @return
     */
    public static  Date getLastMonthEndDate() {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        calendar2.set(Calendar.HOUR_OF_DAY, 23); //将小时至23
        calendar2.set(Calendar.MINUTE, 59); //将分钟至59
        calendar2.set(Calendar.SECOND,59); //将秒至59
        calendar2.set(Calendar.MILLISECOND, 59); //将毫秒至59
        return calendar2.getTime();
    }
    /**
     * 获取上个季度开始的时间
     * @return
     */
    public static  Date getLastQuarterStar() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);
        return startCalendar.getTime();
    }
    /**
     * 获取上个季度结束的时间
     * @return
     */
    public static  Date getLastQuarterEnd() {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);
        return endCalendar.getTime();
    }
    private static void setMinTime(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    private static void setMaxTime(Calendar calendar){
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
    }

    public static void main(String[] args) {
        //获取前一个月第一天
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        calendar1.set(Calendar.HOUR_OF_DAY, 23); //将小时至23
        calendar1.set(Calendar.MINUTE, 59); //将分钟至59
        calendar1.set(Calendar.SECOND,59); //将秒至59
        calendar1.set(Calendar.MILLISECOND, 59); //将毫秒至59
        String firstDay = sdf.format(calendar1.getTime());
        //获取前一个月最后一天
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        calendar2.set(Calendar.HOUR_OF_DAY, 23); //将小时至23
        calendar2.set(Calendar.MINUTE, 59); //将分钟至59
        calendar2.set(Calendar.SECOND,59); //将秒至59
        calendar2.set(Calendar.MILLISECOND, 59); //将毫秒至59
        String lastDay = sdf.format(calendar2.getTime());
        System.out.println("第一天"+firstDay+"未转"+calendar1.getTime());
        calendar1.getTime();
        System.out.println("最后一天"+lastDay+"未转"+calendar2.getTime());
     /*   SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMM");
        Calendar calendar8 = Calendar.getInstance();
        calendar8.add(Calendar.MONTH, 0);
        String format = sdf1.format(calendar8.getTime());
        System.out.println("format:"+format);*/
        // List<String> list=new ArrayList();
        //coreRewardServiceImpl.CountScoreByQuarter();

     /*   list.add( DateUtils.getAnyMonth("yyyyMM",-1));
        list.add( DateUtils.getAnyMonth("yyyyMM",-2));
        list.add( DateUtils.getAnyMonth("yyyyMM",-3));
        for (String s:list) {
            System.out.println("月份集合"+s);

        }*/
    }
}
