package com.yc.tickets.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import sun.misc.BASE64Encoder;
 

/**
 * 根据不同的浏览器，给文件名设置不同的编码方式
 * @author 外哥
 *
 */
public class DownloadUtils {  
	
	public static String getFileName( String agent , String filename ){
		
		try {
			if ( agent.contains("MSIE") ) {
//				IE浏览器 
				filename = URLEncoder.encode(filename, "UTF-8");
				filename = filename.replaceAll("+", " ") ;
			} else if ( agent.contains("Firefox")) {
//				火狐浏览器
				BASE64Encoder base64Encoder = new BASE64Encoder() ;
				filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=" ;
				
//				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
			}else {
//				其他浏览器
				filename = URLEncoder.encode(filename, "utf-8") ;  
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return filename ;
		
		
	} 
}
