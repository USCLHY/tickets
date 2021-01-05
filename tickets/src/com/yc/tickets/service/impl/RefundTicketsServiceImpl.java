package com.yc.tickets.service.impl;

import java.util.List;

import com.yc.tickets.dao.IRefundTicketsDao;
import com.yc.tickets.dao.impl.RefundTicketsDaoImpl; 
import com.yc.tickets.domain.MyRefund;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IRefundTicketsService;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:02:51
*/
public class RefundTicketsServiceImpl implements IRefundTicketsService {
	
	private IRefundTicketsDao dao = new RefundTicketsDaoImpl();

	@Override
	public PageBean<MyRefund> findByPage(String page) {
		// 查询总记录
		int totalCount = dao.total();
		
		// 总页数
		int totalPage =  (int) Math.ceil( totalCount / 5.0 ) ; 
		
		// 每一页的数据
		List<MyRefund> list = dao.findByPage( page , 5 );
		
		// 当前页码,就是形参page 
		// 每页显示的条数,指定为5条
		
		PageBean<MyRefund> pageBean = new PageBean<MyRefund>() ;
		pageBean.setCurrentPage(Integer.parseInt(page));
		pageBean.setlist(list);
		pageBean.setRows(5);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		IRefundTicketsDao dao = new RefundTicketsDaoImpl(); 
		return new JsonObject(dao.lhytotal(true), dao.lhyfindByPage(page, rows));
	}

	@Override
	public JsonObject lhyfindByCondition(String tid, String uname, String name, String refundtime, String status, int page,
			int rows) {
		IRefundTicketsDao dao = new RefundTicketsDaoImpl(); 
		return new JsonObject(dao.lhytotal(tid, uname, name, refundtime, status), dao.lhyfindByCondition(tid, uname, name, refundtime, status, page, rows));
	}

	@Override
	public int lhyupdateStatus(String rid, String status) {
		if(StringUtil.checkNull(rid, status)) {
			return -1;
		}
		IRefundTicketsDao dao = new RefundTicketsDaoImpl(); 
		
		
		int result = dao.lhyupdateStatus(rid, status);
		return result ;
	}
}
