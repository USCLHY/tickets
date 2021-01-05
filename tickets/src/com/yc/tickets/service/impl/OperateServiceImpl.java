package com.yc.tickets.service.impl;

import java.util.List;

import com.yc.tickets.dao.IOperateDao;
import com.yc.tickets.dao.impl.OperateDaoImpl;
import com.yc.tickets.domain.Operate;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IOperateService;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:24:37
*/
public class OperateServiceImpl implements IOperateService {
	private IOperateDao dao= new OperateDaoImpl() ;

	/**
	 * 分页查询
	 */
	@Override
	public PageBean<Operate> findByType(String tid, String page,  String startsite,String endsite , String date) {
		PageBean<Operate> bean = new PageBean<Operate>() ;
		bean.setRows( 5 ); 
		bean.setCurrentPage( Integer.parseInt(page) );
		
		// 总记录数
		int totalCount = 0 ;
		totalCount = dao.total(Integer.parseInt(tid), startsite, endsite, date ) ;
		bean.setTotalCount(totalCount);
		
		// 总页数
		int totalPage = (int) Math.ceil( totalCount / 5.0 ) ;
		bean.setTotalPage(totalPage);
		
		// 每一页的数据
		List<Operate> list = dao.findByType(Integer.parseInt(tid), Integer.parseInt(page),5 , startsite, endsite, date);
		bean.setlist(list); 
		
		return bean;
	}

	/**
	 * 计算总价
	 */
	@Override
	public double totalPrice(String oid, List<UserInfo> array) {
		
		double totalprice = 0.0d ;
		
		if (  array.isEmpty() || StringUtil.checkNull( oid ) ) { 
			return 0.0d ;
		}
		
		for (int i = 0; i < array.size() ; i++) {
			UserInfo userInfo = array.get(i); 
			if ( StringUtil.checkNull( userInfo.getSiteType() , userInfo.getTicketType() ) ) { 
				continue ;
			}
			totalprice += dao.totalPrice( oid , userInfo  );
		}
		
		return totalprice;
	}

	/**
	 * 查询余票
	 */
	@Override
	public int findRemain(String oid, String siteType) {
		int remainNumber = dao.findRemain( oid , siteType ); 
		
		return remainNumber ;
	}
	
	@Override
	public int lhyadd(String tid, int rworemain, int yworemain, int rzuoremain, int yzuoremain, int zhanremain,
			String date, String starttime, String endtime) {
		if(StringUtil.checkNull(tid, String.valueOf(rworemain), String.valueOf(yworemain), String.valueOf(rzuoremain), String.valueOf(yzuoremain), String.valueOf(zhanremain), date, starttime, endtime)) {
			return -1;
		}
		IOperateDao dao = new OperateDaoImpl();
		return dao.lhyadd(tid, rworemain, yworemain, rzuoremain, yzuoremain, zhanremain, date, starttime, endtime);
	}

	@Override
	public int lhyupdateRemain(String tid, String type, int remain) {
		if(StringUtil.checkNull(tid, type, String.valueOf(remain))) {
			return -1;
		}
		IOperateDao dao = new OperateDaoImpl();
		return dao.lhyupdateRemain(tid, type, remain);
	}

	@Override
	public int lhyupdateStatus(String oid, String status) {
		if(StringUtil.checkNull(oid, status)) {
			return -1;
		}
		IOperateDao dao = new OperateDaoImpl();
		return dao.lhyupdateStatus(oid, status);
	}


	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		if(StringUtil.checkNull(String.valueOf(page), String.valueOf(rows))) {
			return null;
		}
		IOperateDao dao = new OperateDaoImpl();
		return new JsonObject(dao.lhytotal(true), dao.lhyfindByPage(page, rows));
	}


	@Override
	public JsonObject lhyfindByCondition(String tid, String date, String status, int page, int rows) {
		IOperateDao dao = new OperateDaoImpl();
		return new JsonObject(dao.lhytotal(tid, date, status), dao.lhyfindByCondition(tid, date, status, page, rows));
	}

	@Override
	public Operate lhyfindByOid(String oid) {
		if(StringUtil.checkNull(oid)) {
			return null;
		}
		IOperateDao dao = new OperateDaoImpl();
		return dao.lhyfindByOid(oid);
	}

	@Override
	public int lhyupdate(String oid, int rworemain, int yworemain, int rzuoremain, int yzuoremain, int zhanremain,
			String date, String starttime, String endtime) {
		if(StringUtil.checkNull(oid, String.valueOf(rworemain), String.valueOf(yworemain), String.valueOf(rzuoremain), String.valueOf(yzuoremain), String.valueOf(zhanremain), date, starttime, endtime)) {
			return -1;
		}
		IOperateDao dao = new OperateDaoImpl();
		return dao.lhyupdate(oid, rworemain, yworemain, rzuoremain, yzuoremain, zhanremain, date, starttime, endtime);
	}

}














