package com.yc.tickets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.MyRefund;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IOrderItemService;
import com.yc.tickets.service.IRefundTicketsService;
import com.yc.tickets.service.impl.OrderItemServiceImpl;
import com.yc.tickets.service.impl.RefundTicketsServiceImpl;
import com.yc.tickets.utils.BaseServlet;

@SuppressWarnings("all")
@WebServlet("/RefundTicketsServlet/*")
public class RefundTicketsServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	public void findByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IRefundTicketsService service = new RefundTicketsServiceImpl();
		 
		 String page = req.getParameter("page");
		 
		 PageBean<MyRefund> pageBean = service.findByPage( page );
		 
		 if ( pageBean == null  ) {
			this.send(resp, 500, pageBean);
			return ;
		}
		 
		 this.send(resp, 200,pageBean); 
	}
	
	public void lhyupdateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IRefundTicketsService Service = new RefundTicketsServiceImpl();
		String rid = request.getParameter("rid");
		String status = request.getParameter("status");
		int rs = Service.lhyupdateStatus(rid, status);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IRefundTicketsService Service = new RefundTicketsServiceImpl();
		String tid = request.getParameter("tid");
		String uname = request.getParameter("uname");
		String name = request.getParameter("name");
		String refundtime = request.getParameter("refundtime");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByCondition(tid, uname, name, refundtime, status, page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IRefundTicketsService Service = new RefundTicketsServiceImpl();
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