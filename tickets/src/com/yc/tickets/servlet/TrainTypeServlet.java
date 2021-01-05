package com.yc.tickets.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.tickets.domain.TrainType;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.ITrainTypeService;
import com.yc.tickets.service.impl.TrainTypeServiceImpl;
import com.yc.tickets.utils.BaseServlet;

@SuppressWarnings("all")
@WebServlet("/TrainTypeServlet/*")
public class TrainTypeServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	/**
	 * 查询所有车票类型
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ITrainTypeService service = new TrainTypeServiceImpl() ;
		List<TrainType> list = service.findAll() ;
		this.send(resp, 200, list);
		
	}
	
	public void lhyfinds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainTypeService Service = new TrainTypeServiceImpl();
		JsonObject obj = Service.lhyfinds();
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainTypeService Service = new TrainTypeServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj =  Service.lhyfindByPage(page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
		
	}

	public void lhyadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainTypeService Service = new TrainTypeServiceImpl();
		String tname = request.getParameter("tname");
		int rs = Service.lhyadd(tname);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}
}