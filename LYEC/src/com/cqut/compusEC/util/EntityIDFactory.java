package com.cqut.compusEC.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntityIDFactory {

//	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static String preId = "";
	
	public static synchronized String createId(){
/*		String id = format.format(Calendar.getInstance().getTime());
		while(id.equals(preId)){
			id = format.format(Calendar.getInstance().getTime());
		}*/
		String id = String.valueOf(System.currentTimeMillis());
		while(id.equals(preId)){
//			id = format.format(Calendar.getInstance().getTime());
			id=String.valueOf(System.currentTimeMillis());
		}

		preId = id;
		return id;
	}
}
