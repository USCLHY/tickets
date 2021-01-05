package com.yc.tickets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.tickets.domain.TrainNumber;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.ITrainNumberService;
import com.yc.tickets.service.impl.TrainNumberServiceImpl;
import com.yc.tickets.utils.BaseServlet;
import com.yc.tickets.utils.RequestParamUtil;

@SuppressWarnings("all")
@WebServlet("/TrainNumberServlet/*")
public class TrainNumberServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	public void lhyupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainNumberService Service = new TrainNumberServiceImpl();
		String tid = request.getParameter("tid"); 
		String tname = request.getParameter("tname");
		String typeid = request.getParameter("typeid");
		String duration = request.getParameter("duration");
		String startsite =request.getParameter("startsite");
		String endsite = request.getParameter("endsite");
		int rwo =Integer.parseInt(request.getParameter("rwo"));
		int ywo = Integer.parseInt(request.getParameter("ywo"));
		int rzuo =Integer.parseInt(request.getParameter("rzuo"));
	    int yzuo = Integer.parseInt(request.getParameter("yzuo"));
		int zhan =Integer.parseInt(request.getParameter("zhan")); 
		double rwoprice =Double.parseDouble(request.getParameter("rwoprice"));
		double ywoprice =Double.parseDouble(request.getParameter("ywoprice")); 
		double rzuoprice =Double.parseDouble(request.getParameter("rzuoprice"));
		double yzuoprice =Double.parseDouble(request.getParameter("yzuoprice"));
		double zhanprice =Double.parseDouble(request.getParameter("zhanprice"));
		double wostudentprice =Double.parseDouble(request.getParameter("wostudentprice"));
		double zuostudentprice =Double.parseDouble(request.getParameter("zuostudentprice"));
		 
		int rs = Service.lhyupdate(tid, tname, typeid, startsite, endsite, duration, rwo, rwoprice, rzuo, rzuoprice, ywo, ywoprice, yzuo, yzuoprice, zhan, zhanprice, wostudentprice, zuostudentprice);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		
	}

	public void lhyupdateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainNumberService Service = new TrainNumberServiceImpl();
		TrainNumber site = (TrainNumber)RequestParamUtil.getParams(TrainNumber.class, request);
		String status = site.getStatus().toString();
		String tid = site.getTid().toString();
		int rs = Service.lhyupdateStatus(status, tid);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainNumberService Service = new TrainNumberServiceImpl();
		String tname = request.getParameter("tname");
		String typeid = request.getParameter("typeid");
		String startsite = request.getParameter("startsite");
		String endsite = request.getParameter("endsite");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByCondition(tname, typeid, startsite, endsite, page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByTid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainNumberService Service = new TrainNumberServiceImpl();
		String tid = request.getParameter("tid");
		TrainNumber trainNumber = Service.lhyfindByTid(tid);
		if(trainNumber != null) {
			this.send(response, 200, trainNumber);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainNumberService Service = new TrainNumberServiceImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		JsonObject obj = Service.lhyfindByPage(page, rows);
		 
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITrainNumberService Service = new TrainNumberServiceImpl();
		TrainNumber obj = (TrainNumber)RequestParamUtil.getParams(TrainNumber.class, request);
		String tname = obj.getTname();
		String typeid = obj.getTypeid().toString();
		String startsite = obj.getStartsite().toString();
		String endsite = obj.getEndsite().toString();
		int status = obj.getStatus();
		String duration = obj.getDuration();
		int rwo = obj.getRwo();
		int ywo = obj.getYwo();
		int rzuo = obj.getRzuo();
		int yzuo = obj.getYzuo();
		int zhan = obj.getZhan();
		double rwoprice = obj.getRwoprice();
		double ywoprice = obj.getYwoprice();
		double rzuoprice = obj.getRzuoprice();
		double yzuoprice = obj.getYzuoprice();
		double zhanprice = obj.getZhanprice();
		double wostudentprice = obj.getWostudentprice();
		double zuostudentprice = obj.getZuostudentprice();
		int rs = Service.lhyadd(tname, typeid, startsite, endsite, duration, rwo, rwoprice, rzuo, rzuoprice, ywo, ywoprice, yzuo, yzuoprice, zhan, zhanprice, wostudentprice, zuostudentprice, status);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}
}