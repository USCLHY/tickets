package com.yc.tickets.service;

import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.MyRefund;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.dto.JsonObject;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:02:21
*/
public interface IRefundTicketsService {

	PageBean<MyRefund> findByPage(String page);
	
	/*
	 * 李杭沅
	 */
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByPage(int page, int rows);
	/**
	 * 条件查询
	 * @param tname
	 * @param uname
	 * @param name
	 * @param refundtime
	 * @param status 0-待审核 1-通过 2-失败
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByCondition(String tid, String uname, String name, String refundtime, String status, int page, int rows);
	/**
	 * 修改状态 1-通过 2-失败
	 * @param rid
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(String rid, String status);

}
