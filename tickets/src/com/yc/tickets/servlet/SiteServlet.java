package com.yc.tickets.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.tickets.domain.Site;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.ISiteService;
import com.yc.tickets.service.impl.SiteServiceImpl;
import com.yc.tickets.utils.BaseServlet;
import com.yc.tickets.utils.RequestParamUtil;

@SuppressWarnings("all")
@WebServlet("/SiteServlet/*")
public class SiteServlet extends BaseServlet {
	private static final long serialVersionUID = 2984960054933251931L;

	public void lhyfinds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ISiteService Service = new SiteServiceImpl();
		JsonObject obj = Service.lhyfinds();
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyfindByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		ISiteService Service = new SiteServiceImpl();
		JsonObject obj = Service.lhyfindByPage(page, rows);
		if(obj != null) {
			this.send(response, obj);
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyupdateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ISiteService Service = new SiteServiceImpl();
		Site site = (Site)RequestParamUtil.getParams(Site.class, request);
		String status = site.getStatus().toString();
		String sid = site.getSid().toString();
		System.out.println(status + "--" + sid);
		int rs = Service.lhyupdateStatus(status, sid);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyupdateSname(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ISiteService Service = new SiteServiceImpl();
		Site site = (Site)RequestParamUtil.getParams(Site.class, request);
		String sname = site.getSname().toString();
		String sid = site.getSid().toString();
		System.out.println(sname + "--" + sid);
		int rs = Service.lhyupdateSname(sname, sid);
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}

	public void lhyadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ISiteService Service = new SiteServiceImpl();
		Site site = (Site)RequestParamUtil.getParams(Site.class, request);
		String sname = site.getSname().toString();
		int rs = Service.lhyadd(sname, "1");
		if(rs > 0) {
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
	}
	
}