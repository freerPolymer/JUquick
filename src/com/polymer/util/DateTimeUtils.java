package com.polymer.util;

import com.polymer.ibase.IConstant;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 *      时间格式统一使用 yyy-MM-dd HH:mm:ss
 * @since 2022年7月18日
 * @author polymer
 */
public class DateTimeUtils {

    /**
     *  获取时间
     * @param format 时间格式字符串
     * @see IConstant.DATE_FORMAT
     * @see DateTimeFormatter
     * @see LocalDateTime
     * @return 当前时间
     */
    public static String getNowFormatTime(IConstant.DATE_FORMAT format) {
        //格式校验
        if(IConstant.DATE_FORMAT.getValus(format.name()).length()<1)return IConstant.STR_EMPTY_VALUE;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(IConstant.DATE_FORMAT.getValus(format.name()));
        return dateTimeFormatter.format(dateTime);
    }

    /**
     * 在指定时间操作(加减)
     *      包含 年、月、日、时、分、秒
     * @param dateTime 操作时间。不能为空，格式 yyyy-MM-dd HH:mm:ss
     * @param dateType 时间指定类型
     * @param operateCount 操作数
     * @param operateType 操作类型
     * @return 计算结果
     */
    public static String operateDateTime(String dateTime, IConstant.DATE_TYPE dateType, int operateCount, IConstant.OPERATE_TYPE operateType){
        // 为保证我的程序效率和安全，执行校验
        if(dateTime==null||dateTime.length()<=0||operateCount<1)return dateTime;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(IConstant.DATE_FORMAT.DATE_YMDHMS.toString()));
        LocalDateTime newTime=null;
        switch (dateType){
            case YEAR:
                if(IConstant.OPERATE_TYPE.PLUS==operateType)
                    newTime= localDateTime.plusYears(operateCount); // 增加年
                if(IConstant.OPERATE_TYPE.MINUS==operateType)
                    newTime= localDateTime.minusYears(operateCount); // 减去年
                break;
            case MONTH:
                if(IConstant.OPERATE_TYPE.PLUS==operateType)
                    newTime = localDateTime.plusMonths(operateCount); // 增加月
                if(IConstant.OPERATE_TYPE.MINUS==operateType)
                    newTime = localDateTime.minusMonths(operateCount); // 减去月
                break;
            case DATE:
                if(IConstant.OPERATE_TYPE.PLUS==operateType)
                    newTime = localDateTime.plusDays(operateCount); // 增加日
                if(IConstant.OPERATE_TYPE.MINUS==operateType)
                    newTime = localDateTime.minusDays(operateCount); // 减去日
                break;
            case HOURS:
                if(IConstant.OPERATE_TYPE.PLUS==operateType)
                    newTime = localDateTime.plusHours(operateCount); // 增加时
                if(IConstant.OPERATE_TYPE.MINUS==operateType)
                    newTime = localDateTime.minusHours(operateCount); // 减去时
                break;
            case MINUTES:
                if(IConstant.OPERATE_TYPE.PLUS==operateType)
                    newTime = localDateTime.plusMinutes(operateCount); // 增加分
                if(IConstant.OPERATE_TYPE.MINUS==operateType)
                    newTime = localDateTime.minusMinutes(operateCount); // 减去分
                break;
            case SECONDS:
                if(IConstant.OPERATE_TYPE.PLUS==operateType)
                    newTime = localDateTime.plusSeconds(operateCount); // 增加秒
                if(IConstant.OPERATE_TYPE.MINUS==operateType)
                    newTime = localDateTime.minusSeconds(operateCount); // 减去秒
                break;
            default:
                break;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(IConstant.DATE_FORMAT.getValus(IConstant.DATE_FORMAT.DATE_YMD.name()));
        return newTime!=null?dateTimeFormatter.format(newTime):IConstant.STR_EMPTY_VALUE;
    }

    /**
     * 判断某天是否闰年
     * @param date 时间
     * @return true|false
     */
    public static boolean isLeapYear(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(IConstant.DATE_FORMAT.getValus(IConstant.DATE_FORMAT.DATE_YMD.name()));
        LocalDate localDate = LocalDate.parse(date, df);
        return localDate.isLeapYear();
    }

    /**
     * 计算两个时间的差
     *      包含日、时、分、秒
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param dateType 计算类型
     * @return 相差值
     */
    public static long dayDiff(String startDate, String endDate, IConstant.DATE_TYPE dateType) {
        //校验
        if(startDate==null||endDate==null||startDate.length()<1||endDate.length()<1)return 0;
        DateTimeFormatter sf = DateTimeFormatter.ofPattern(IConstant.DATE_FORMAT.getValus(IConstant.DATE_FORMAT.DATE_YMD.name()));
        DateTimeFormatter ef = DateTimeFormatter.ofPattern(IConstant.DATE_FORMAT.getValus(IConstant.DATE_FORMAT.DATE_YMD.name()));
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, sf);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, ef);
        Duration duration = Duration.between(startDateTime, endDateTime);
        long days = 0;
        switch (dateType){
            case DATE:
                days = duration.toDays(); // 相差的天数(所有的)
                break;
            case HOURS:
                days=duration.toHours();// 相差小时
                break;
            case MINUTES:
                days=duration.toMinutes();//分钟数
                break;
            case SECONDS:
                days=duration.toMillis();//毫秒
                break;
            default:
                break;
        }
        return Math.abs(days);
    }

}
