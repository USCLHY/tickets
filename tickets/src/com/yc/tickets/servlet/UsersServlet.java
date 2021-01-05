package com.yc.tickets.servlet;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;

import com.alibaba.fastjson.JSON;
import com.yc.tickets.domain.Users;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IUsersService;
import com.yc.tickets.service.impl.UsersServiceImpl;
import com.yc.tickets.utils.BaseServlet;
import com.yc.tickets.utils.MailUtils;
import com.yc.tickets.utils.SessionKeyConstant;
import com.yc.tickets.utils.StringUtil;

@SuppressWarnings("all")
@WebServlet("/UsersServlet/*")
public class UsersServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;
 
	/**
	 * 修改用户信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users user = (JSON.parseObject( req.getParameter("user"), Users.class) ) ;
		 
		IUsersService service = new UsersServiceImpl() ;
		int result = service.update(user) ;
		
		if ( result > 0 ) {
			this.send(resp,200,"成功" );
			
			// 跟新登录用户的信息
			Users loginUser = (Users) req.getSession().getAttribute( SessionKeyConstant.MEMBERINFOLOGIN );
			loginUser.setUname( user.getUname() );
			loginUser.setUtel( user.getUtel() );
			loginUser.setUemail( user.getUemail() );
			loginUser.setUidentify( user.getUidentify() );
			
			// 存储
			req.getSession().setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, loginUser);
			
			return ;
		}
		
		this.send(resp,500,"失败" );
	}
	
	/**
	 * 查询登录的用户信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Users loginUser = (Users) req.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		this.send(resp,loginUser );
	}
	
	 
	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数
		Map<String, String[]> map = new HashMap<String, String[]>( req.getParameterMap() ); 
				
		// 将参数封装到bean对象中
		Users user = new Users() ;
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		// 添加用户，此时用户的status为0，当点击激活后改为1
		IUsersService service = new UsersServiceImpl() ;
		int result = service.register(user) ;
		
		if ( result < 0 ) {
			req.getRequestDispatcher( "/WEB-INF/pages/error.html").forward(req, resp);
			return ;
		}
		
		// 根据邮箱地址发送邮箱
		boolean flag = MailUtils.sendMail(user.getUemail(), "<a href='http://localhost:8080/tickets/UsersServlet/activeUser?identify="+ user.getUidentify() +"'>点击激活账号</a>", "12307铁路网") ;
	}
	
	/**
	 * 激活账户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void activeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数identify
		String identify = req.getParameter("identify");
		
		if ( "".equals(identify) || identify == null ) {
			req.getRequestDispatcher( "/WEB-INF/pages/error.html").forward(req, resp);
			return ;
		}
		
		// 激活身份证号为identify的用户，将用户的状态修改为1
		IUsersService service = new UsersServiceImpl() ;
		int result = service.active( identify ) ;
		
		if ( result < 0 ) {  
			req.getRequestDispatcher( "/WEB-INF/pages/error.html").forward(req, resp);
			return ;
		} 
		
		// 跳转到激活界面
		req.getRequestDispatcher( "/WEB-INF/pages/active.html").forward(req, resp);
	}
	
	
	/**
	 * 登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数 
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String CheckCode = req.getParameter("CheckCode");
		
		// 首先判断验证码是否正确
		String code = (String) req.getSession().getAttribute(SessionKeyConstant.VERIFICATIONCODE );
		
		if ( StringUtil.checkNull( CheckCode ) || !CheckCode.equalsIgnoreCase( code )) {
			this.send(resp, 500, "验证码错误");
			return ;
		}
		
		// 判断用户是否已经登录
		Object obj = req.getSession().getAttribute( SessionKeyConstant.MEMBERINFOLOGIN );	
		if ( obj != null ) {
			this.send(resp, 500, "用户已经登录");
			return ;
		}
		 
		// 登录
		IUsersService service = new UsersServiceImpl() ;
		
		Users loginUser = service.login( account, password ); 
		if ( loginUser == null  ) {   
			this.send(resp, 500,"失败");
			return ;
		} 
		
		// 将用户存入session
		req.getSession().setAttribute( SessionKeyConstant.MEMBERINFOLOGIN , loginUser );
		
		this.send(resp, 200,"成功");
	}
	 
	
	/**
	 * 退出登录
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void reback(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		Object obj = req.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		
		if ( obj == null ) {
			this.send(resp, 500,"请先登录");
		}
		
		req.getSession().removeAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		this.send(resp, 200,"退出成功");
	}
	
	
	/**
	 * 通过邮箱查找用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findByEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		String email = req.getParameter("email") ;
		
		// 判空
		if ( email == null || "".equals( email ) ) {
			this.send(resp, 500,"邮箱地址为空");
			return ;
		}
		
		// 通过邮箱查询用户
		IUsersService service = new UsersServiceImpl() ;
		Users users = service.findByEmail( email ) ;
		
		if ( users == null ) {
			this.send(resp, 500,"用户不存在");
			return ;
		}
		
		// 发送邮箱
		MailUtils.sendMail( email , "你的密码为：" + users.getUpassword() + ",请妥善保管", "12307密码找回") ; 
		 
		this.send(resp, 200,"成功找回");
	}
	
	
	public void lhyfindByUid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IUsersService Service = new UsersServiceImpl();
		String uid = request.getParameter("uid");
		
		Users user = Service.lhyfindByUid(uid);
		if(user != null) {
			this.send(response, 200, user);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyupdateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IUsersService Service = new UsersServiceImpl();
		String uid = request.getParameter("uid");
		int status = Integer.parseInt(request.getParameter("status"));
		int rs = Service.lhyupdateStatus(uid, status);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IUsersService Service = new UsersServiceImpl();
		String uname = request.getParameter("uname");
		String utel = request.getParameter("utel");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByCondition(uname, utel, status, page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IUsersService Service = new UsersServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByPage(page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}
}






























