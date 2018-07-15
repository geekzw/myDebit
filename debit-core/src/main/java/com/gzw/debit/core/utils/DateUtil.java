package com.gzw.debit.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * auth:gujian
 * time:2018/7/10
 * email:gujian@maihaoche.com
 * describe:
 */
public class DateUtil {

    static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YMDHMS_CN = "yyyy年MM月dd日 HH:mm:ss";
    public static final String DATE_FORMAT_YMD_CN = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_YMDH_CN = "yyyy年MM月dd日HH时";

    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static Date yyyyMMddParse(String date) throws ParseException {
        SimpleDateFormat dateParse = new SimpleDateFormat("yyyyMMdd");
        Date targetDate = dateParse.parse(date);
        return targetDate;
    }

    public static String yyMMddFormate(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String yyMMddDate = dateFormat.format(date);
        return yyMMddDate;

    }

    public static String yyyyMMddFormate(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String yyyyMMddDate = dateFormat.format(date);
        return yyyyMMddDate;

    }

    public static String yyyyMMddHHmmssParse(Date date) {
        try {
            SimpleDateFormat dateParse = new SimpleDateFormat("yyyyMMddHHmmss");
            return dateParse.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * add by mahe 2017/02/27
     *
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static Date dateISO8601Parse(String dateFormat) throws ParseException {
        String isoFormatDate = dateFormat;
        if (!dateFormat.contains("+")) {
            isoFormatDate = dateFormat.substring(0, 19) + "+00:00";
        }
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat dateParse = new SimpleDateFormat(DATE_FORMAT_ISO8601);
        return dateParse.parse(isoFormatDate);
    }

    public static LocalDateTime localDateTimeISO8601Parse(String dateFormat) throws ParseException {
        Date date = dateISO8601Parse(dateFormat);
        return DateUtil.ofLocalDateTime(date);
    }

    public static String dateISO8601Format(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        Date dateDateTime = ofDate(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_ISO8601);
        return dateFormat.format(dateDateTime);
    }

    public static LocalDate localDateParseByYMD(String date) {
        LocalDate localDate = null;
        if(StringUtils.isBlank(date)){
            return localDate;
        }
        try{
            localDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern(DATE_FORMAT_YMD));
        }catch (Exception e){
        }

        return localDate;
    }

    public static String LocalDate2Date(LocalDate date){
        if (date == null) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = date.format(fmt);

        return dateStr;
    }

    public static long LocalDate2Day(LocalDate date1,LocalDate date2){
        if(date1 == null || date2 == null){
            return 0;
        }
        long daysDiff = ChronoUnit.DAYS.between(date1, date2);
        return daysDiff;
    }

    /**
     * "2018-1-12" 也能转成localDate ,而localDateParseByYMD会抛异常
     *
     * @param dateStr
     * @return
     */
    public static LocalDate localDateParseLenientlyByYMD(String dateStr) {
        Date date = convertStrToDate(dateStr, DATE_FORMAT_YMD);
        if (Objects.nonNull(date)) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }


    public static String dateIYMDFormat(LocalDate date) {
        if (date == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.atStartOfDay().atZone(zone).toInstant();
        Date dateDateTime = Date.from(instant);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMD);
        return dateFormat.format(dateDateTime);
    }

    /**
     * 把时间转换为 formatter 格式的字符串
     * 例如： "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String dateFormat(LocalDateTime date, String formatter) {
        if (date == null) {
            return null;
        }
        Date dateDateTime = ofDate(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatter);
        return dateFormat.format(dateDateTime);
    }

    public static Date long2Date(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return c.getTime();
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy-MM-dd hh:MM:ss"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateToYMDHMS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy-MM-dd hh:MM"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateToYMDHHmm(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return f.format(date);
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy-MM-dd hh:MM:ss"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateToYMDHM(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return f.format(date);
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy年MM月dd日"
     *
     * @param date
     * @return
     */
    public static String convertDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        if (date != null) {
            return df.format(date);
        } else {
            return "";
        }
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy.MM.dd"
     *
     * @param date
     * @return
     */
    public static String convertDate1(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        if (date != null) {
            return df.format(date);
        } else {
            return "";
        }
    }

    /**
     * M月d日 HH:ss格式
     *
     * @param date
     * @return
     */
    public static String convertsDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("M月d日 HH:mm");
        if (date != null) {
            return df.format(date);
        } else {
            return "";
        }
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy-MM-dd"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateToYMD(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(date);
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy-MM"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateToYM(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        return f.format(date);
    }

    public static String convertDateToStr(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat(formatStr);
        return f.format(date);
    }

    public static Date convertStrToDate(String dateStr, String formatStr) {
        SimpleDateFormat f = new SimpleDateFormat(formatStr);
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }
        return null;
    }

    public static String convertDateToYM1(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy_MM");
        return f.format(date);
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "MM-dd hh:MM"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateToMD(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("MM-dd");
        return f.format(date);
    }

    public static Date convertStringToDate(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }
        return null;
    }

    public static Date convertStringToHMS(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }
        return null;
    }

    public static Date addMonth(Date d, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MONTH, diff);
        return c.getTime();
    }

    public static Date addDate(Date d, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, diff);
        return c.getTime();
    }

    //added by mahe 2017/03/22
    public static Date addSecond(Date d, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.SECOND, diff);
        return c.getTime();
    }

    //added by mahe 2017/03/19
    public static Date addHour(Date d, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR, diff);
        return c.getTime();
    }

    //added by mahe 2017/03/19
    public static Date addMinute(Date d, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(c.MINUTE, diff);
        return c.getTime();
    }

    public static String getMonthLastDay(Date d) {

        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        return convertDateToYMD(c.getTime());
    }

    public static Date convertStringToDateYMD(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }
        return null;
    }

    /**
     * String -> date
     *
     * @param dateStr
     * @param format  <br/>
     *                1. yyyy-MM-dd<br/>
     *                2. yyyy-MM-dd HH:mm
     *                ...
     * @return date
     */
    public static Date convertStringToDate(String dateStr, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }

        return null;
    }

    public static Date convertStringToDateYM(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }
        return null;
    }


    /**
     * @param dateStr
     * @return
     */
    public static Date convertStringToDateYMDHMS(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return f.parse(dateStr);
        } catch (ParseException e) {
            logger.error("转化为时间失败", e);
        }
        return null;
    }

    /**
     * 获取Calendar的日期
     * "yyyy-MM-dd"
     *
     * @param cal 时间
     * @return 格式化以后的时间字符串
     */
    public static String getDate(Calendar cal) {
        String v_strDate = "";
        int v_intYear = cal.get(Calendar.YEAR);
        int v_intMonth = cal.get(Calendar.MONTH) + 1;
        int v_intDAY = cal.get(Calendar.DAY_OF_MONTH);

        v_strDate = v_strDate + v_intYear + "-";

        if (v_intMonth < 10) {
            v_strDate = v_strDate + "0" + v_intMonth + "-";
        } else {
            v_strDate = v_strDate + v_intMonth + "-";
        }
        if (v_intDAY < 10) {
            v_strDate = v_strDate + "0" + v_intDAY + "";
        } else {
            v_strDate = v_strDate + v_intDAY + "";
        }

        return v_strDate;
    }

    /**
     * 获取选定日期的目标间隔日期
     *
     * @param date   选定日期
     * @param number 间隔日期
     */
    public static String getDateStr(Date date, int number) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, number);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /**
     * 获取选定日期的目标间隔日期
     *
     * @param date   选定日期
     * @param number 间隔日期
     */
    public static Date getDateBy(Date date, int number) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, number);
        return calendar.getTime();
    }

    /**
     * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 将日期本周内转化为星期，本周以外转化为xxxx年xx月xx日
     *
     * @param date
     * @return
     */
    public static String getWeeksOfDate(Date date) {
        if (date != null) {
            String da = DateUtil.getWeekOfDate(new Date());
            try {
                int count = daysBetween(date, new Date());
                if ("周一".equals(da)) {
                    if (count == 0) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
                if ("周二".equals(da)) {
                    if (count <= 1) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
                if ("周三".equals(da)) {
                    if (count <= 2) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
                if ("周四".equals(da)) {
                    if (count <= 3) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
                if ("周五".equals(da)) {
                    if (count <= 4) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
                if ("周六".equals(da)) {
                    if (count <= 5) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
                if ("周日".equals(da)) {
                    if (count <= 6) {
                        return DateUtil.getWeekOfDate(date);
                    } else {
                        return DateUtil.convertDate(date);
                    }
                }
            } catch (ParseException p) {
                logger.error("日期解析错误", p);
            }
        }
        return null;

    }


    /**
     * 计算两个日期之间的天数
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * @param date
     * @param number
     * @return
     */
    public static String getDateStr2(Date date, int number) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /**
     * 指定日期格式化 yyyy-MM
     *
     * @param date
     * @param number
     * @return
     */
    public static String getDateStr3(Date date, int number) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, number);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(calendar.getTime());
    }


    /**
     * 比较两个时间的间隔天数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static int getSpaceByCompareTwoDate(Date startTime, Date endTime) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(startTime);
        end.setTime(endTime);
        int betweenYears = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int betweenDays = end.get(Calendar.DAY_OF_YEAR)
                - start.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            start.set(Calendar.YEAR, (start.get(Calendar.YEAR) + 1));
            betweenDays += start.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }


    /**
     * 把时间类型转化为规定格式的字符串
     * "yyyy.MM.dd hh:MM:ss"
     *
     * @param date 时间
     * @return 格式化以后的时间字符串
     */
    public static String convertDateYMDHMS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return f.format(date);
    }

    /**
     * 把时间类型转化为规定格式的字符串
     * "HH:mm"
     *
     * @param date
     * @return
     */
    public static String convertDateToTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        return f.format(date);
    }

    public static String getISO8601Time(Date date) {
        Date nowDate = date;
        if (null == date) {
            nowDate = new Date();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(nowDate);
    }

    /**
     * create by jason 2015-07-20
     * 获得今天的开始时间 e.g. 2015-07-10 00 :00:00
     */
    public static LocalDateTime getStartTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * create by jason 2015-07-20
     * 获得该时间所处的开始时间 e.g. 2015-07-10 00 :00:00
     */
    public static LocalDateTime getStartTime(Date startTime) {
        if (startTime == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        startTime = calendar.getTime();
        return ofLocalDateTime(startTime);
    }

    /**
     * create by jieming 2017-04-28
     * 获得该时间所处的开始时间 e.g. 2015-07-10 00 :00:00
     */
    public static LocalDateTime getStartTime(LocalDateTime startTime) {
        if (startTime == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ofDate(startTime));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return ofLocalDateTime(calendar.getTime());
    }

    /**
     * create by jieming 2017-04-27
     * 获得该时间所处的开始时间戳（精确到毫秒） e.g. 1495814400000
     */
    public static long getStartTimeInMills(LocalDate startTime) {
        if (startTime == null) {
            return 0l;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(startTime.getYear(), startTime.getMonthValue() - 1, startTime.getDayOfMonth(), 0, 0, 0);

        return calendar.getTimeInMillis();
    }

    /**
     * create by jieming 2017-04-27
     * 获得当前时间的时间戳（精确到毫秒） e.g. 1495814412593
     */
    public static long getCurrentTimeInMills() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    /**
     * create by jason 2015-07-20
     * 获得昨天的开始时间 e.g. 2015-07-10 00 :00:00
     */
    public static Date getYstdStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.DATE, todayStart.get(Calendar.DATE) - 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date startTime = todayStart.getTime();
        return startTime;
    }

    /**
     * create by jason 2015-07-20
     * 获得该时间所处的结束时间 e.g. 2015-07-10 23 :59:59
     */
    public static LocalDateTime getEndTime(Date endTime) {
        if (endTime == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        endTime = calendar.getTime();
        return ofLocalDateTime(endTime);
    }

    /**
     * 获取当天的结束时间 e.g 2016-09-18 23:59:59
     *
     * @return
     */
    public static LocalDateTime getEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }


    /**
     * create by jason 2015-11-07
     * 获得昨天的结束时间 e.g. 2015-11-07 59:59:59
     */
    public static Date getYstdEndTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.DATE, todayStart.get(Calendar.DATE) - 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 23);
        todayStart.set(Calendar.MINUTE, 59);
        todayStart.set(Calendar.SECOND, 59);
        todayStart.set(Calendar.MILLISECOND, 59);
        Date startTime = todayStart.getTime();
        return startTime;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date currentDate() {
        return new Date();
    }

    /**
     * 获取制定时间的月初时间，默认当月
     *
     * @param dateTime
     * @return
     */
    public static Date getStartMonth(Date dateTime) {
        Calendar calendar = Calendar.getInstance();
        if (dateTime != null) {
            calendar.setTime(dateTime);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取制定时间的月末时间，默认当月
     *
     * @param dateTime
     * @return
     */
    public static Date getEndMonth(Date dateTime) {
        Calendar calendar = Calendar.getInstance();
        if (dateTime != null) {
            calendar.setTime(dateTime);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取指定月份的第一天
     * 如现在是2015-12-14，
     * day = 1  ：返回2016-01-01
     * day = -1 ：返回2015-11-01
     * day = 0  ：返回2015-12-01
     *
     * @param day
     * @return
     */
    public static String getFirstMonthDay(Integer day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, day);//设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String day_first = df.format(calendar.getTime());
        return day_first;
    }

    /**
     * 获取指定月份的最后一天
     * 如现在是2015-12-14，
     * day = 1  ：返回2016-01-31
     * day = -1 ：返回2015-11-30
     * day = 0  ：返回2015-12-31
     *
     * @param day
     * @return
     */
    public static String getLastMonthDay(Integer day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, day);//设置月份
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号
        calendar.add(Calendar.MONTH, 1);//月增加1天
        calendar.add(Calendar.DAY_OF_MONTH, -1);//日期倒数一日,既得到此月份的最后一天
        String day_last = df.format(calendar.getTime());
        return day_last;
    }

    /**
     * 获取上一年
     *
     * @param date   当前日期
     * @param number 间隔
     * @return
     */
    public static Date getLastYear(Date date, int number) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, number);
        return calendar.getTime();
    }

    public static String convertDateToHMS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        return f.format(date);
    }

    public static String convertDateToHM(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        return f.format(date);
    }


    /**
     * 返回给定date的小时数字, 24小时制
     *
     * @param date 给定的date对象
     * @return 小时数字
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static String getDHBetween(Date startDate, Date endDate) {
        String str = "%s天%s小时";
        long m = endDate.getTime() - startDate.getTime();
        long days = m / (1000 * 3600 * 24);
        long hours = (m % (1000 * 3600 * 24)) / (1000 * 3600);
        return String.format(str, days, hours);
    }

//    /**
//     * 把date转化为新版的LocalDate
//     *
//     * @param date
//     * @return
//     */
//    public static LocalDate ofLocalDate(Date date) {
//        if (date == null) {
//            return null;
//        } else {
//            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
//        }
//    }

    /**
     * 把date转化为新版的LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime ofLocalDateTime(Date date) {
        if (date == null) {
            return null;
        } else {
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }
    }

    /**
     * ms时间轴转化为LocalDateTime
     */
    public static LocalDateTime ofLocalDateTime(Long time) {
        if (time == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone
                .getDefault().toZoneId());
    }

    /**
     * String转LocalDateTime
     */
    public static LocalDateTime String2Local(String string, String format) {
        DateTimeFormatter formatter;
        if (format == null || "".equals(format)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter = DateTimeFormatter.ofPattern(format);
        }

        try{
            LocalDateTime time = LocalDateTime.from(LocalDate.parse(string, formatter).atStartOfDay());
            return time;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * LocalDateTime转String
     */
    public static String local2String(LocalDateTime localDateTime,String format) {
        if(localDateTime == null){
            return "";
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
        String dateStr = localDateTime.format(fmt);
        return dateStr;
    }
//    /**
//     * 将localDate转成date
//     *
//     * @param ldt
//     * @return
//     */
//    public static Date ofDate(LocalDateTime ldt) {
//        if (ldt == null) {
//            return null;
//        } else {
//            return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
//        }
//    }

    /**
     * 将localDateTime转成date
     *
     * @param ldt
     * @return
     */
    public static Date ofDate(LocalDateTime ldt) {
        if (ldt == null) {
            return null;
        } else {
            return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    /**
     * 获取当前周的第一天
     *
     * @return
     */
    public static LocalDateTime getFirstDayOfWeek() {
        LocalDateTime date = LocalDateTime.now();
        date = date.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        return date;
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static LocalDateTime getFirstDayOfMonth() {
        LocalDateTime date = LocalDateTime.now();
        date = date.with(TemporalAdjusters.firstDayOfMonth());
        return date;
    }

    /**
     * 获取上个月的第一天
     *
     * @return
     */
    public static LocalDateTime getFirstDayOfLastMonth() {
        LocalDateTime date = LocalDateTime.now();
        date = date.minusMonths(1);
        date = date.with(TemporalAdjusters.firstDayOfMonth());
        return date;
    }


    /**
     * 获取上个月的最后一天
     *
     * @return
     */
    public static LocalDateTime getLastDayOfLastMonth() {
        LocalDateTime date = LocalDateTime.now();
        date = date.minusMonths(1);
        date = date.with(TemporalAdjusters.lastDayOfMonth());
        return date;
    }

    /**
     * 将13位的毫秒数时间戳转化为ZonedDateTime
     *
     * @param mill
     * @return
     */
    public static ZonedDateTime convertMillisToZonedDateTime(Long mill) {
        return Instant.ofEpochMilli(mill).atZone(ZoneId.systemDefault());
    }

    /**
     * 比较两个日期
     * @param date1
     * @param date2
     * @return 前者大于后者返回true,前者小于后者返回false，前者等于后者返回false
     */
    public static boolean compare2Date(LocalDate date1,LocalDate date2){
        if(date1 == null || date2 == null){
            return false;
        }

        String str1 = LocalDate2Date(date1);
        String str2 = LocalDate2Date(date2);
        if(str1 == null || str2 == null){
            return false;
        }

        if(str1.compareTo(str2) >= 0){
            return true;
        }
        return false;
    }

    /**
     * 获取当天0点时间
     */

    public static LocalDateTime getToday0Time(){
        LocalDateTime time = LocalDateTime.now();
        return LocalDateTime.of(time.getYear(),time.getMonth(),time.getDayOfMonth(),0,0,0);
    }

    /**
     * 获取当天23:59点时间
     */

    public static LocalDateTime getTodayLastTime(){
        LocalDateTime time = LocalDateTime.now();
        return LocalDateTime.of(time.getYear(),time.getMonth(),time.getDayOfMonth(),23,59,59);
    }

    /**
     * 获取30天之前的日期 LocalDate
     * @return
     */
    public static LocalDateTime getBefore30Day(LocalDateTime date){
        return date.plusDays(-30);
    }

}
