package com.yc.tickets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.tickets.domain.Operate;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IOperateService;
import com.yc.tickets.service.impl.OperateServiceImpl;
import com.yc.tickets.utils.BaseServlet;

@SuppressWarnings("all")
@WebServlet("/OperateServlet/*")
public class OperateServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	/**
	 * 通过类型查询运营信息
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findByType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数，车票类型id
		String tid = req.getParameter("tid");
		// 第几页
		String page = req.getParameter("page") ;
		// 起始站点
		String startsite = req.getParameter( "startsite" ) ;
		// 到达站点
		String endsite = req.getParameter( "endsite" ) ;
		// 出发日期
		String date = req.getParameter( "date" ) ;
		
		if ( "".equals( page ) || page == null ) {
			page = 1 + "" ;
		} 
		
		IOperateService service = new OperateServiceImpl() ;
		PageBean bean = service.findByType( tid , page , startsite,endsite,date ) ;
		
		this.send(resp, 200 , bean );
		
	}
	
	/**
	 * 查询是否有余票
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findRemain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数
		String oid = req.getParameter("oid");
		String siteType = req.getParameter("siteType");  
		
		IOperateService service = new OperateServiceImpl() ; 
		int remainNumber = service.findRemain( oid , siteType );
		
		if ( remainNumber < 1 ) {
			this.send(resp, 200, "没有余票");
			return ;
		}
		
		this.send(resp, 200,"成功");  
	}
	
	public void lhyupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		String oid = request.getParameter("oid");
		String date = request.getParameter("date");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		int rworemain = Integer.parseInt(request.getParameter("rworemain"));
		int yworemain = Integer.parseInt(request.getParameter("yworemain"));
		int rzuoremain = Integer.parseInt(request.getParameter("rzuoremain"));
		int yzuoremain = Integer.parseInt(request.getParameter("yzuoremain"));
		int zhanremain = Integer.parseInt(request.getParameter("zhanremain"));
		int rs = Service.lhyupdate(oid, rworemain, yworemain, rzuoremain, yzuoremain, zhanremain, date, starttime, endtime);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByOid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		String oid = request.getParameter("oid");
		Operate obj = Service.lhyfindByOid(oid);
		if(obj != null) {
			this.send(response, 200, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		String tid = request.getParameter("tid");
		String date = request.getParameter("date");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByCondition(tid, date, status, page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByPage(page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyupdateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		String oid = request.getParameter("oid");
		String status = request.getParameter("status");
		int rs = Service.lhyupdateStatus(oid, status);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyupdateRemain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		String tid = request.getParameter("tid");
		String type = request.getParameter("type");
		int remain = Integer.parseInt(request.getParameter("reamin"));
		int rs = Service.lhyupdateRemain(tid, type, remain);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOperateService Service = new OperateServiceImpl();
		String tid = request.getParameter("tid");
		int rworemain = Integer.parseInt(request.getParameter("rworemain"));
		int yworemain = Integer.parseInt(request.getParameter("yworemain"));
		int rzuoremain = Integer.parseInt(request.getParameter("rzuoremain"));
		int yzuoremain = Integer.parseInt(request.getParameter("yzuoremain"));
		int zhanremain = Integer.parseInt(request.getParameter("zhanremain"));
		String date = request.getParameter("date");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		int rs = Service.lhyadd(tid, rworemain, yworemain, rzuoremain, yzuoremain, zhanremain, date, starttime, endtime);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}
	
}























