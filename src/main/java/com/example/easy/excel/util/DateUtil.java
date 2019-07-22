package com.example.easy.excel.util;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DateUtil:为时间转换提供服务的
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/21
 */
public class DateUtil {
    /**
     * 获取指定天数按指定格式转换后数据
     *
     * @param day    excel返回的时间整数
     * @param format 为空默认 yyyy-MM-dd
     * @return
     */
    @SuppressWarnings("static-access")
    public static String getFormat(String day, String format) {
        if (StringUtils.isBlank(format) || StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.set(1900, 0, 1);
            // 把日期往后增加一天.整数往后推,负数往前移动
            calendar.add(Calendar.DATE, Integer.valueOf(day));
            SimpleDateFormat sFormat = new SimpleDateFormat(format);
            return sFormat.format(calendar.getTime());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 取指定天数按指定格式转换后数据
     * (用户excel时间和数据库,java时间转换使用的,后期easyexcel会升级框架改善时间处理,这里由于官方暂时没有没有处理,所以写个工具用着先)
     *
     * @param excelNumber excel的时间(office用是距离某个时候(你百度吧)的天数来表示时间的,搞笑的很)
     * @return yyyy-MM-dd HH:mm:ss
     */
    @SuppressWarnings("static-access")
    public static LocalDateTime getLocalDateTime(String excelNumber) {
        LocalDateTime datetime = null;
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.set(1900, 0, 1);
            // 把日期往后增加一天.整数往后推,负数往前移动
            calendar.add(Calendar.DATE, Integer.valueOf(excelNumber));
            SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sFormat.format(calendar.getTime());
            //根据需要解析的日期、时间字符串定义解析所用的格式器
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            datetime = LocalDateTime.parse(format, df);
            return datetime;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return datetime;
    }
}
