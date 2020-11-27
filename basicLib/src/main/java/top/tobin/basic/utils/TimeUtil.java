package top.tobin.basic.utils;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lijunbin
 * @date 2020/7/23
 * @email 616041023@qq.com
 * @description 时间工具类
 */
public class TimeUtil {

    public static boolean adTimeIsEnable(String startTime, String endTime){
        String time = String.valueOf(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date().getTime()));
        if (TimeUtil.timeCompare(startTime,time) == 3 && TimeUtil.timeCompare(time,endTime) == 3){
            return true;
        }
        return  false;
    }

    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式（自己可以修改成想要的时间格式）
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static int timeCompare(String startTime, String endTime) {
        int i = 0;
        //注意：传过来的时间格式必须要和这里填入的时间格式相同
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime() < date1.getTime()) {
                //结束时间小于开始时间
                i = 1;
            } else if (date2.getTime() == date1.getTime()) {
                //开始时间与结束时间相同
                i = 2;
            } else if (date2.getTime() > date1.getTime()) {
                //结束时间大于开始时间
                i = 3;
            }
        } catch (Exception e) {

        }
        return i;
    }
}
