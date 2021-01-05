package com.yc.tickets.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ietf.jgss.Oid;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.OrderItem;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.domain.Users;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IOperateService;
import com.yc.tickets.service.IOrderItemService;
import com.yc.tickets.service.impl.OperateServiceImpl;
import com.yc.tickets.service.impl.OrderItemServiceImpl;
import com.yc.tickets.utils.BaseServlet;
import com.yc.tickets.utils.DBToExcel;
import com.yc.tickets.utils.SessionKeyConstant;

@SuppressWarnings("all")
@WebServlet("/OrderItemServlet/*")
public class OrderItemServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	/**
	 * 将数据导出excel表
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ExportExcel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filePath = "G:\\Users\\Administrator\\eclipse-workspance02\\tickets\\WebContent\\files\\" ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hhmmss") ;
		filePath += sdf.format( new Date() ) ;
		filePath += UUID.randomUUID() ;
		
		String sql = "select tn.tname as '车次名称', SUM(ods.money) as '营业总额' from orderitems ods, operate op ,train_number tn where ods.operateid=op.oid and op.tid=tn.tid group by ods.operateid" ;
		int result = DBToExcel.exportExcel(sql, filePath , "营业额", 0);
		
		if ( result > 0 ) {
			this.send(resp, 200, "成功");
			return ;
		}
		
		this.send(resp, 500, "失败");
		
		
	}
	
	
	/**
	 * 将乘客信息存入session域中
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void savePassengersToSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 将前台数组对象数据封装到类的集合中
		List<UserInfo> passengers = JSON.parseArray(req.getParameter("passengers"), UserInfo.class);
		// 车次运营id
		String oid = req.getParameter("oid");   
		
		// 将信息存入session中
		req.getSession().setAttribute(SessionKeyConstant.PASSENGERS	, passengers);
		req.getSession().setAttribute(SessionKeyConstant.OID, oid); 
		
		this.send(resp, 200);
	}
	
	public void findPassengersFromSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 将前台数组对象数据封装到类的集合中
		List<UserInfo> passengers = (List<UserInfo>) req.getSession().getAttribute(SessionKeyConstant.PASSENGERS) ;  
		
		this.send(resp, 200, passengers);
	}
	
	
	/**
	 * 计算总价
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void calTotalPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 将前台数组对象数据封装到类的集合中
		List<UserInfo> array = JSON.parseArray(req.getParameter("array"), UserInfo.class);
		// 车次运营id
		String oid = req.getParameter("oid");   
		 
		IOperateService service = new OperateServiceImpl() ;
		double totalPrice = service.totalPrice( oid ,  array );
		 
		this.send(resp, 200, totalPrice);
	}
	
	/**
	 * 添加订单
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 IOrderItemService service = new OrderItemServiceImpl();
		 
		 List<UserInfo> passengers = (List<UserInfo>) req.getSession().getAttribute( SessionKeyConstant.PASSENGERS ) ;
		 String oid = (String) req.getSession().getAttribute( SessionKeyConstant.OID );
		 Users user = (Users) req.getSession().getAttribute( SessionKeyConstant.MEMBERINFOLOGIN );
		 
		 int result = service.add(passengers , oid , user.getUid() ) ;
		 
	}
	
	/**
	 * 分页查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 IOrderItemService service = new OrderItemServiceImpl();
		 
		 String page = req.getParameter("page");
		 
		 PageBean<MyOrders> pageBean = service.findByPage( page );
		 
		 if ( pageBean == null  ) {
			this.send(resp, 500, pageBean);
			return ;
		}
		 
		 this.send(resp, 200,pageBean); 
	}
	
	/**
	 * 退票
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void backTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 IOrderItemService service = new OrderItemServiceImpl();
		 
		 // 获取订单id
		 String oid = req.getParameter("oid"); 
		  
		 int i = service.backTickets( oid );
		 
		 if ( i > 0 ) {
			this.send(resp, 200,"成功");
			return ;
		}
		 
		 this.send(resp, 500,"失败");
	}
	
	
	public void lhysumByDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
		String date = request.getParameter("date");
		List<Map<String, String>> list = Service.lhysumByDate(date);
		if(list != null && list.size()!=0) {
			this.send(response, list);
			return;
		}
		this.send(response, 500, "失败");
	}


	public void lhysumByMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		List<Map<String, String>> list = Service.lhysumByMonth(year, month);
		if(list != null && list.size()!=0) {
			this.send(response, list);
			return;
		}
		this.send(response, 500, "失败");
	}


	public void lhysumByYear(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
		String year = request.getParameter("year");
		List<Map<String, String>> list = Service.lhysumByYear(year);
		if(list != null && list.size()!=0) {
			this.send(response, list);
			return;
		}
		this.send(response, 500, "失败");
	}


	public void lhysumByTid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
		List<Map<String, String>> list = Service.lhysumByTid();
		if(list != null && list.size()!=0) {
			this.send(response, list);
			return;
		}
		this.send(response, 500, "失败");
	}


	public void lhyfindByOid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
		String oid = request.getParameter("oid");
		OrderItem item = Service.lhyfindByOid(oid);
		if(item != null) {
			this.send(response, 200, item);
			return;
		}
		this.send(response, 500, "失败");
	}


	public void lhyfindByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
		String tid = request.getParameter("tid");
		String uname = request.getParameter("uname");
		String buytime = request.getParameter("buytime");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByCondition(tid, uname, buytime, name, status, page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}


	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IOrderItemService Service = new OrderItemServiceImpl();
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