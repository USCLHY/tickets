package com.yc.tickets.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yc.tickets.utils.FileUploadUtil;
import com.yc.tickets.utils.StringUtil;
 

/**
 *  当程序启动时，此监听器创建文件上传的目录
 */
@WebListener
public class CreateUploadListener implements ServletContextListener {

   
    public void contextInitialized(ServletContextEvent sce)  { 

    	String path = sce.getServletContext().getInitParameter("uploadPath");
    	
    	if ( StringUtil.checkNull( path )) {
			path = "images" ;
		}
    	
    	String basePath = sce.getServletContext().getRealPath("/");
    	path = "../" + path ;
    	File fl = new File( basePath, path) ;
    	
    	if ( !fl.exists() ) {
			fl.mkdirs() ;
		}
    	
    	FileUploadUtil.uploadPath = path ;
    }
	
}
