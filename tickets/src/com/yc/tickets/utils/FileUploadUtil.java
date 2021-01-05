package com.yc.tickets.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * 文件上传的工具类
 * @author lh
 *
 */
@SuppressWarnings("all")
public class FileUploadUtil {

	public static String uploadPath = "../images"; //文件默认的上传路径
	private static final String ALLOWEDLIST = "gif,jpg,png,doc,docx,xls,xlsx,txt,zip,rar,mp3,mp4"; // 允许上传的文件类型
	private static final String DENIEDLIST = "exe, bat, jsp"; // 不允许上传的文件类型
	private static final int MAXFILESIZE = 10 * 1024 * 1024 ; //单个上传文件的最大值
	private static final int TOTALMAXFILESIZE = 100 * 1024 * 1024 ; //总上传文件最大值
	
	/**
	 * 获取文件上传的对象
	 * @param pageContext
	 * @return
	 */
	private SmartUpload getSmartUpload(PageContext pageContext) {
		SmartUpload su = new SmartUpload();
		try {
			// 初始化上传组件
			su.initialize(pageContext);
			
			// 设置参数
			su.setAllowedFilesList(ALLOWEDLIST);
			su.setDeniedFilesList(DENIEDLIST);
			su.setMaxFileSize(MAXFILESIZE);
			su.setTotalMaxFileSize(TOTALMAXFILESIZE);
			
			// 设置上传数据的编码集
			su.setCharset("UTF-8");
			
			// 开始上传
			su.upload(); 
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		return su ;
		
	}
	
	/**
	 * 文件上传
	 * @param pageContext
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SmartUploadException
	 */
	public Map<String, String> uploads(PageContext pageContext) throws ServletException, IOException, 
	SQLException, SmartUploadException{
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 获取文件上传对象
		SmartUpload su = getSmartUpload(pageContext) ;
		
		// 获取上传的参数信息，非文件参数 此时的Request是jspsmartupload中的
		Request rq = su.getRequest(); 
		
		// 获取所有表单控件的名字
		Enumeration<String> enus = rq.getParameterNames();
		
		// 单个控件名称 name属性
		String name = null;
		
		while(enus.hasMoreElements()) {		
			name = enus.nextElement();
			map.put(name, rq.getParameter(name));
		}
		
		// 获取所有上传的文件
		Files files = su.getFiles();
		
		// 如果无文件，则直接返回文本信息
		if( files == null || files.getCount() <= 0) {
			return map;
		}
		
		//有就循环取出用户上传的文件
		Collection<File> fls = files.getCollection();
		
		//TODO 文件存储服务器的详细位置
	    //获取保存文件夹的绝对路径 -> tomcat在服务器的绝对路径  C...\webapps\项目名\
		String basePath = pageContext.getServletContext().getRealPath("/");
		
		String fileName = null; // 上传后的文件名称
		String filedName = null; // 文件控件名
		String filePath = null; // 文件保存路径
		
		String pathStr = ""; // 一个文件域内多文件拼接路径
		String temp = null; // 网页文件中文件上传的控件
		
		// 遍历所有上传的文件
		for(File fl : fls) {
			if( !fl.isMissing() ) {
				// 文件未丢失
				temp = fl.getFieldName(); //photos files 页面多个文件域控件
				if( StringUtil.checkNull(filedName) ) { //如果这个变量为空，说明这是第一次要上传的文件
					filedName = temp;
				}else { // 不是第一次上传的文件
					if( !temp.equals(filedName) ) {
						// 下一个文件域
						map.put(filedName, pathStr);
						pathStr = ""; //初始化 	清空上一个上传路径 准备放下一个文件上传路径
						filedName = temp;
					}
				}
				
				//为了避免命名重复覆盖，自定义文件名
				filedName = fl.getFieldName();
				fileName = uploadPath + "/" + new Date().getTime() + "_" + fl.getFileName();
				
				if( StringUtil.checkNull(pathStr) ) { 
					// 说明这是这个文本域中第一个要上传的文件
					pathStr = fileName;
				}else {
					// 多图片字符串拼接
					pathStr += ";" + fileName; 
				}
				
				// 存储在服务器中  自定义路径 + 自定义名称
				filePath = basePath + fileName;
				
				// 存储文件
				fl.saveAs(filePath, SmartUpload.SAVE_PHYSICAL);			
			}		
		}		
		map.put(filedName, pathStr);
		return map; 
	}
	
	
	/**
	 * 基于对象的文件上传 ， 将参数封装到对象中
	 * @param <T>
	 * @param cls
	 * @param pageContext
	 * @return
	 */
	public <T> T uploads( Class<T> cls , PageContext pageContext ) {
		T t = null;
		try {
			t = cls.newInstance();
			
//		获取给定的类中的所有的setter方法
			Method[] methods = cls.getMethods();
			
//		存储所有的setter方法，已方法名为键，对应的方法对象为值
			Map<String, Method> setters = new HashMap<String, Method>() ;
			
			String methodName = null ;
			for (Method method : methods) {
				methodName = method.getName() ;
				if ( methodName.startsWith("set")) {
					setters.put(methodName, method) ;
				}
			}
			
			// 得到所有的文件的map集合
			Map<String, String> map = this.uploads(pageContext);
			
			String name = null ;
			Method method = null ;
			Class<?>[] types = null ;
			Class<?> type = null ;
			String val = null ;
			
			t = cls.newInstance() ;
			
			// 遍历所有的文件
			for (Entry<String , String> entry : map.entrySet() ) {
				// 此时的值为文件的路径，不同的路径之前通过分号(;)隔开
				val = entry.getValue() ;
				name = entry.getKey() ;
				
				if (val == null || "".equals(val)) {
					continue ;
				}
				
				methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1) ;
				method = setters.getOrDefault(methodName, null) ;
						
				if ( method == null ) {
					continue ;
				}
				
				types = method.getParameterTypes() ;
				
				if ( types == null || types.length <= 0 ) {
					continue ;
				}
				
				// 一般来说，setter方法只要一个参数
				type = types[0] ;
				
				if (Integer.TYPE == type || Integer.class == type) {
					method.invoke(t, Integer.parseInt(val));
				} else if (Float.TYPE == type || Float.class == type) {
					method.invoke(t, Float.parseFloat(val));
				} else if (Double.TYPE == type || Double.class == type) {
					method.invoke(t, Double.parseDouble(val));
				} else {
					method.invoke(t, val);
				}  
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return t ;
	}  
}
