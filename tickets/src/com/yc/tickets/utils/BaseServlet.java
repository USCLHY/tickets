package com.yc.tickets.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * servlet的基类，以前执行servlet时，会首先执行httpServlet中的service方法，根据请求方式来分发方法
 * 当我们重写HttpServlet中的service方法后，当servlet继承自BaseServlet时，就会首先调用此类中的service方法，
 * 根据请求路径中的方法名来进行方法的分发。可以大大减少servlet的数量
 * @author 外哥
 *
 */
@SuppressWarnings("all")
public class BaseServlet extends HttpServlet {
	
	/**
	 * 重写HttpServlet的service方法
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 获取uri地址
		String uri = req.getRequestURI();  //  mybatis_day01/xxx/xx,例如mybatis_day01/user/add执行mybatis_day01项目中user下的add方法
		
		//2.获取方法名称
		String methodName = uri.substring( uri.lastIndexOf("/") + 1 ); // xx
		
	 
		try {
			// 3. 获取指定方法名称和参数的方法对象Method,利用反射
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//4 . 执行当前对象中的method方法
			method.invoke(this,req,resp);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		 
	}
	
	/**
	 * 响应一个int状态码 
	 * @param response
	 * @param code   状态码  
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, int code) throws IOException {
		// Access-Control-Allow-Origin  解决跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 解决前端网页  xml解析错误: 格式不佳    
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(code);
		out.flush();
		out.close();
	}

	/**
	 * 响应一个字符串状态码 
	 * @param response
	 * @param code   状态码   
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, String code) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setContentType("text/plain"); // 调用支付包沙箱会出错 注释
		PrintWriter out = response.getWriter();
		out.print(code);
		out.flush();
		out.close();
	}

	/**
	 * 响应一个obj对象数据 
	 * @param response
	 * @param code   状态码  
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, Object obj) throws IOException {
		// Access-Control-Allow-Origin  解决跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		out.print(gson.toJson(obj));
		out.flush();
		out.close();
	}

	/**
	 * 以JSON格式返回返回数据 :  状态码  和 提示信息
	 * @param response
	 * @param code	状态
	 * @param msg	返回的字符串提示信息
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, int code, String msg) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//解决前端网页  xml解析错误: 格式不佳
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		out.print(gson.toJson(map));
		out.flush();
		out.close();
	}

	/**
	 * 以JSON格式返回返回数据 :  状态码  和  详细数据
	 * @param response
	 * @param code	状态码  
	 * @param data	返回的详细数据
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void send(HttpServletResponse response, int code, Object data) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//解决前端网页  xml解析错误: 格式不佳
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", data);
		out.print(gson.toJson(map));
		out.flush();
		out.close();
	}

	
	/**
	 * 响应错误数据到界面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void error(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		//解决前端乱码
		response.setContentType("text/html,charset=utf-8");
		PrintWriter out = response.getWriter();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
		out.print("<script>alert('该请求不被支持...');location.href='" + basePath + "index.html;'</script>");
		out.flush();
		out.close();
	}
}




















