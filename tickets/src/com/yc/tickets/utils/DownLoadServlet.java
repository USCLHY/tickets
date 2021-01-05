package com.yc.tickets.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("all")
@WebServlet("/DownLoadServlet/*")
public class DownLoadServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	/**
	 * 下载
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		获取请求参数，文件名称
		String filename = req.getParameter("filename") ;
		
		/*
		 * 使用字节输入流加载文件进内存
		 * 	1.找到文件服务器路径
		 * 	2，用字节流关联
		 */
		ServletContext context = this.getServletContext() ;
		
		String realPath = context.getRealPath("/files/" + filename ) ;
//		使用字节流关联
		FileInputStream fileInputStream = new FileInputStream(realPath) ;
		
//		设置response的响应头,设置文件的类型
		String mimeType = context.getMimeType(filename) ; 
		
		//获取MIME文件类型
		resp.setHeader("content-type", mimeType ); 
		
//		解决中文文件名问题
//		1.获取user-agent请求头
//		2.使用工具类方法编码文件名
		String agent = req.getHeader("user-agent") ; 
		filename = DownloadUtils.getFileName(agent, filename) ; 
		
//						设置资源的打开方式			附件			文件名		
		resp.setHeader("content-disposition", "attachment;filename=" + filename );
		
//		将输入流的数据写出到输出流中
		ServletOutputStream fOutputStream = resp.getOutputStream() ; 
		byte[] buff = new byte[ 1024 ] ;
		int len = 0 ;
		
		while ( ( len = fileInputStream.read(buff) ) != -1 ) {
			fOutputStream.write( buff , 0 , len );
		}
		 
		fileInputStream.close();
	}
}