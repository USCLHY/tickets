package com.yc.tickets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.tickets.domain.Admin;
import com.yc.tickets.domain.Users;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IAdminService;
import com.yc.tickets.service.IUsersService;
import com.yc.tickets.service.impl.AdminServiceImpl;
import com.yc.tickets.service.impl.UsersServiceImpl;
import com.yc.tickets.utils.BaseServlet;
import com.yc.tickets.utils.SessionKeyConstant;
import com.yc.tickets.utils.StringUtil;

@SuppressWarnings("all")
@WebServlet("/AdminServlet/*")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	/**
	 * 管理员登录
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
			System.out.println( CheckCode + "-->" + code );
			
			this.send(resp, 500, "验证码错误");
			return ;
		} 
		 
		IAdminService service = new AdminServiceImpl() ;
		
		Admin admin = service.login( account, password ); 
		if ( admin == null  ) {   
			this.send(resp, 500,"失败");
			return ;
		}  
		
		// 登陆成功，存入session
		req.getSession().setAttribute(SessionKeyConstant.BACKMEMBERINFOLOGIN, admin);
	 
		this.send(resp, 200,"成功"); 
	}
	
	public void lhysortByStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IAdminService biz = new AdminServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj =  biz.lhysortByStatus(page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhygetAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute(SessionKeyConstant.BACKMEMBERINFOLOGIN);
		if(obj != null) {
			this.send(response, 200, (Admin)obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyupdateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IAdminService biz = new AdminServiceImpl();
		String aid = request.getParameter("aid");
		int rs = biz.lhyupdateStatus(aid);
		if(rs > 0) {
			this.send(response, 200, rs);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IAdminService biz = new AdminServiceImpl();
		String aname = request.getParameter("aname");
		String apassword = request.getParameter("apassword");
		String aemail = request.getParameter("aemail");
		String status = request.getParameter("status");
		int rs = biz.lhyadd(aname, apassword, aemail, status);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IAdminService biz = new AdminServiceImpl();
		String aname = request.getParameter("aname");
		String aemail = request.getParameter("aemail");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = biz.lhyfindByCondition(aname, aemail, status, page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByAid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IAdminService biz = new AdminServiceImpl();
		String aid = request.getParameter("aid");
		Admin admin = biz.lhyfindByAid(aid);
		if(admin != null) {
			this.send(response, 200, admin);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IAdminService biz = new AdminServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj =  biz.lhyfindByPage(page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}
}