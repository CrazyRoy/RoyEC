package com.cqut.compusEC.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间工具
 * */
public class DateUtil {
	
	public static String oracleDateFormateYyyy_MM_dd_HH_mm_ss="yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 * */	
	public static String dateFormatYyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss.SSSSSS
	 * */	
	public static String dateFormatYyyy_MM_dd_HH_mm_ss_SSSSSS= "yyyy-MM-dd HH:mm:ss.SSSSSS";
	
	/**
	 * 日期格式：yyyy-MM-dd E (E为星期几)
	 * */	
	public static String dateFormatYyyy_MM_dd_E = "yyyy-MM-dd E";
	
	/**
	 * 日期格式：yyyy-MM-dd
	 */
	public static String dateFormatYyyy_MM_dd = "yyyy-MM-dd";
	
	/**
	 * 日期格式：yyyy-MM-dd HH-mm-ss
	 * */	
	public static String dateFormatYyyy_MM_dd_HH_mm_ss_h = "yyyy-MM-dd HH-mm-ss";
	
	/**
	 * 日期格式：yyyyMMddHHmmss
	 * */	
	public static String dateFormatYyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	/**
	 * 日期格式：yyyyMMdd
	 * */	
	public static String dateFormatYyyyMMdd = "yyyyMMdd";
	
	/**
	 * 获取指定日期格式的系统时间字符串
	 * @param format 日期格式：取值从本类中日期格式静态变量中选取
	 * @return 指定日期格式的系统时间字符串
	 * */
	public static String getSysDateString(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 将日期转成指定格式的字符串
	 * @param date 日期
	 * @param format 日期格式：取值从本类中日期格式静态变量中选取
	 * @return 指定日期格式的时间字符串
	 * */	
	public static String getDateString(Long date, String format) {
		DateFormat dateFormat= new SimpleDateFormat(format);
		return dateFormat.format(new Date(date));
	}	
	
	/**
	 * 将日期转成中文格式（年月日时分秒）的字符串
	 * @param date Long 日期
	 * @return 指定日期格式的时间字符串
	 * */	
	public static String getDateStringZh(Long date) {
		DateFormat dateFormat= new SimpleDateFormat(dateFormatYyyy_MM_dd_HH_mm_ss);
		String formatString= dateFormat.format(new Date(date));
		return formatString.substring(0, 4)+ "年"+ formatString.substring(5, 7)+ "月"+ formatString.substring(8, 10)+ "日"+ formatString.substring(11, 13)+ "点"+ formatString.substring(14, 16)+ "分"+ formatString.substring(17, 19)+ "秒";
	}
	
	/**
	 * 将日期转成中文格式（年月日 星期几）的字符串
	 * 
	 * @return 指定日期格式的时间字符串
	 * */
	public static String getDateStringWithE() {
		DateFormat dateFormat= new SimpleDateFormat(dateFormatYyyy_MM_dd_E);
		String formatString = dateFormat.format(new Date());
		return formatString.substring(0, 4)+ "年"+ formatString.substring(5, 7)+ "月"+ formatString.substring(8, 10)+ "日   "+ formatString.substring(11, 14);
	}
	
	/**
	 * 将日期转成指定格式的字符串
	 * @param date 日期
	 * @param format 日期格式：取值从本类中日期格式静态变量中选取
	 * @return 指定日期格式的时间字符串
	 * */	
	public static String chngDateString(Date date, String format) {
		DateFormat dateFormat= new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * 
	 * @author yangwen 2014-8-13 上午9:26:36
	 * @Method: getNextMonthFirst 
	 * @Description: TODO 获得下个月第一天的日期
	 * @return 下个月第一天的日期字符串
	 */
	public static String getNextMonthFirst(){
		String nextMonDay = "";
		DateFormat dateFormat= new SimpleDateFormat(dateFormatYyyy_MM_dd);
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1); //加一个月
		lastDate.set(Calendar.DATE, 1);  //把日期设置为当月第一天
		nextMonDay = dateFormat.format(lastDate.getTime());
		return nextMonDay;
	}
	
	
	/**
	 * 
	 * @author yangwen 2014-8-13 上午9:26:36
	 * @Method: strToDate 
	 * @Description: TODO 将指定的时间格式字符串转换为时间
	 * @param strDate 指定日期字符串
	 * @param format 日期格式
	 * @return 返回Date类型的日期
	 */
	public static Date strToDate(String strDate,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date strToDate = formatter.parse(strDate, pos);
		return strToDate;
	}
	
	/**
	 * 
     * @author yangwen 2014-8-13 上午9:26:36
	 * @Method: getAfterMonth 
	 * @Description: TODO 得到指定月后（前）的日期 参数传负数即可
	 * @param date 指定日期
	 * @param month 月份偏移量
	 * @return 返回指定月后（前）的日期字符串
	*/
	public static String getAfterMonth(Date date,int month) {
	        Calendar c = Calendar.getInstance();//获得一个日历的实例   
	        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatYyyy_MM_dd_HH_mm_ss);
	        c.setTime(date);//设置日历时间   
	        c.add(Calendar.MONTH,month);//在日历的月份上增加6个月   
	        String strDate = sdf.format(c.getTime());//得到你想要的6个月后的日期   
	        return strDate;
	}
	
	public static String getAfterMonth(Date date,int month,String format) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例   
        SimpleDateFormat sdf = null;
        if (null != format && !"".equals(format)) {
        	sdf = new SimpleDateFormat(format);
        } else {
        	sdf = new SimpleDateFormat(dateFormatYyyy_MM_dd_HH_mm_ss);
        }
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,month);//在日历的月份上增加6个月   
        String strDate = sdf.format(c.getTime());//得到你想要的6个月后的日期   
        return strDate;
}
	
	/**
	 * 
     * @author yangwen 2014-8-13 上午9:26:36
	 * @Method: getAfterDate 
	 * @Description: TODO 得到指定天后（前）的日期 参数传负数即可
	 * @param date 指定日期
	 * @param day 天数偏移量
	 * @return 返回指定天后（前）的日期字符串
	*/
	public static String getAfterDate(Date date, int day) {   
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatYyyy_MM_dd_HH_mm_ss);
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day); 
        String strDate = sdf.format(now.getTime());//得到你想要的多少天后的日期   
        return strDate;
    } 
	
	public static String getAfterDate(Date date, int day,String format) {   
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = null;
        if (null != format && !"".equals(format)) {
        	sdf = new SimpleDateFormat(format);
        } else {
        	sdf = new SimpleDateFormat(dateFormatYyyy_MM_dd_HH_mm_ss);
        }
        
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day); 
        String strDate = sdf.format(now.getTime());//得到你想要的多少天后的日期   
        return strDate;
    } 
	
	/**
	 * 
     * @author liujian 2015-1-13 上午9:26:36
	 * @Method: getLastDayOfMonth 
	 * @Description: TODO 得到当月最后一天
	*/
	public static String getLastDayOfMonth(){
		String nextMonDay = "";
		DateFormat dateFormat= new SimpleDateFormat(dateFormatYyyy_MM_dd);
		Calendar lastDate = Calendar.getInstance();    
		lastDate.set(Calendar.DAY_OF_MONTH,lastDate.getActualMaximum(Calendar.DATE));
		nextMonDay = dateFormat.format(lastDate.getTime());
		return nextMonDay + " 23:59:59";
	}
	
	public static String getLastDayOfMonth2(){
		String nextMonDay = "";
		DateFormat dateFormat= new SimpleDateFormat(dateFormatYyyy_MM_dd);
		Calendar lastDate = Calendar.getInstance();    
		lastDate.set(Calendar.DAY_OF_MONTH,lastDate.getActualMaximum(Calendar.DATE));
		nextMonDay = dateFormat.format(lastDate.getTime());
		return nextMonDay + " 00:00:01";
	}
	/**
	 * 
     * @author libowen 2015年1月19日16:33:28
	 * @Method: getAfterMonthOfLastDay 
	 * @Description: TODO 协议失效时间修改为当月最后一天的 23:59:59
	 * @param date 指定日期
	 * @param day 天数偏移量
	 * @return 返回指定天后（前）的日期字符串
	*/
	public static String getAfterMonthOfLastDay(Date date, int month) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		SimpleDateFormat sdf = new SimpleDateFormat(
				dateFormatYyyy_MM_dd_HH_mm_ss);
		c.setTime(date);// 设置日历时间
		c.add(Calendar.MONTH, month);// 在日历的月份上增加6个月
		c.set(Calendar.DAY_OF_MONTH,1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.add(Calendar.DAY_OF_MONTH,-1);
		String strDate = sdf.format(c.getTime());// 得到你想要的6个月后的日期
		return strDate;
	}

/*	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				dateFormatYyyy_MM_dd_HH_mm_ss);
	    System.out.println(sdf.format(new Date()));
    }*/
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @Description: TOD 判断时间data1是否在date2之前
	 * @return
	 */
	
	public static boolean isDateBefore(String date1,String date2){
		  try{
		   DateFormat df = new SimpleDateFormat(
					dateFormatYyyy_MM_dd_HH_mm_ss);
		   return df.parse(date1).before(df.parse(date2)); 
		  }catch(Exception e){
		   System.out.print("[SYS] " + e.getMessage());
		   return false;
		  }
		 }
/*	public static void main(String[] args) {
		System.out.println(isDateBefore("2016-01-01 11:11:11","2017-01-01 11:11:11"));
		
	}*/
}
