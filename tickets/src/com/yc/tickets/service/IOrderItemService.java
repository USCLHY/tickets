package com.yc.tickets.service;

import java.util.List;
import java.util.Map;

import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.OrderItem;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.dto.JsonObject;

/**
* 
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月3日 下午8:25:50
*/
public interface IOrderItemService {
	/**
	 * 添加订单
	 * @param passengers 
	 * @param uid 
	 * @param oid 
	 * @return
	 */
	public int add(List<UserInfo> passengers, String oid, int uid);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	public PageBean<MyOrders> findByPage(String page);

	/**
	 * 退票
	 * @param oid
	 * @return
	 */
	public int backTickets(String oid);
	
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
	 * @param tname 列车名
	 * @param uname 用户名称
	 * @param buytime 购买时间
	 * @param name 乘车人姓名
	 * @param status 状态 0-未发车  1-已发车
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByCondition(String tid, String uname, String buytime, String name, String status, int page, int rows);
	/**
	 * 根据编号查询
	 * @param oid
	 * @return
	 */
	public OrderItem lhyfindByOid(String oid);
	/**
	 * 报表统计 根据列车查询总营业额
	 * @return
	 */
	public List<Map<String, String>> lhysumByTid();
	/**
	 * 根据年统计各列车营业额
	 * @param year
	 * @return
	 */
	public List<Map<String, String>> lhysumByYear(String year);
	/**
	 * 根据月份统计各列车营业额
	 * @param month
	 * @return
	 */
	public List<Map<String, String>> lhysumByMonth(String year, String month);
	/**
	 * 根据日期统计各列车营业额
	 * @param date
	 * @return
	 */
	public List<Map<String, String>> lhysumByDate(String date);

}
