package com.yc.tickets.dao;

import java.util.List;

import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.MyRefund;
import com.yc.tickets.domain.RefundTicket;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:04:19
*/
public interface IRefundTicketsDao {

	int total();

	List<MyRefund> findByPage(String page, int rows);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<RefundTicket> lhyfindByPage(int page, int rows);
	/**
	 * 根据分页查询的总记录数
	 * @param flag
	 * @return
	 */
	public int lhytotal(boolean flag);
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
	public List<RefundTicket> lhyfindByCondition(String tid, String uname, String name, String refundtime, String status, int page, int rows);
	/**
	 * 根据条件查询的总记录数
	 * @param tname
	 * @param uname
	 * @param name
	 * @param refundtime
	 * @param status
	 * @return
	 */
	public int lhytotal(String tid, String uname, String name, String refundtime, String status);
	/**
	 * 修改状态 1-通过 2-失败
	 * @param rid
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(String rid, String status);
}
