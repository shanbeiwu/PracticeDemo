package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *   获取当月工作日，可排除周末，法定假日，可以添加补休假期。
 */
public class Holiday {

    public static void main(String[] args) {
        Holiday holiday = new Holiday();
        List<String> allDayOfMonth = holiday.getAllDays();
        List<String> publicHoliday = new ArrayList<>();
        publicHoliday.add("2020-03-10");
        publicHoliday.add("2020-03-12");
        List<String> otherWork = new ArrayList<>();
        otherWork.add("2020-03-15");
        holiday.getWorkDays(allDayOfMonth, publicHoliday, otherWork);
    }

    /**
     *
     * @param allDayOfMonth 当前月份所有不是周末的日期
     * @param publicHoliday 长度为2，index=0：公共假日开始日期，index=1：公共假日结束时间
     * @param otherWork 存放补休假期
     */
    public List<String> getWorkDays(List<String> allDayOfMonth, List<String> publicHoliday, List<String> otherWork) {
        int bIndex = 0, eIndex = 0;
        List<String> tmpAll = new ArrayList<>();
        try {
            for (int i = 0; i < allDayOfMonth.size(); i++) {
                String date = allDayOfMonth.get(i);
                if (date.equals(publicHoliday.get(0))) {
                    bIndex = i;
                } else if (date.equals(publicHoliday.get(1))) {
                    eIndex = i;
                }
            }
            List<String> tmpList1 = allDayOfMonth.subList(0,bIndex);
            List<String> tmpList2 = allDayOfMonth.subList(eIndex + 1, allDayOfMonth.size() - 1);
            tmpAll.addAll(tmpList1);
            tmpAll.addAll(tmpList2);
            tmpAll.addAll(otherWork);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(tmpAll);
        System.out.println(tmpAll);
        return tmpAll;
    }

    /**
     * 返回当前月份不是周末的日期列表
      */
    public List<String> getAllDays() {
        // 每个月第一天
        Calendar begin = Calendar.getInstance();// 得到一个Calendar的实例
        begin.clear();
        begin.setTime(new Date()); // 设置时间为当前时间
        begin.set(Calendar.DAY_OF_MONTH, 1);
        Date result = begin.getTime();
        // 每个月最后一天
        Calendar end = Calendar.getInstance();
        end.clear();
        end.setTime(new Date());
        int value = end.getActualMaximum(Calendar.DAY_OF_MONTH);
        end.set(Calendar.DAY_OF_MONTH, value);
        Date resend = end.getTime();

        Long startTime = begin.getTimeInMillis();
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;// 一天的时间转化为ms
        List<String> dates = new ArrayList<>();
        Long time = startTime;
        int i = 0;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                dates.add(i, df.format(d));
                i++;
            }
            time += oneDay;
        }
//        for (int j = 0; j < dates.size(); j++) {
//            System.err.println(dates.get(j));
//        }
        return dates;
    }
}
