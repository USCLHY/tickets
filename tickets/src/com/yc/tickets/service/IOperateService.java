package com.yc.tickets.service;

import java.util.List;

import com.yc.tickets.domain.Operate;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.dto.JsonObject;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:24:25
*/
public interface IOperateService {

	/**
	 * 根据车次类型分页查询
	 * @param tid
	 * @param page
	 * @return
	 */
	PageBean<?> findByType(String tid, String page ,  String startsite,String endsite , String date);

	/**
	 * 计算总价
	 * @param oid
	 * @param array
	 * @return
	 */
	double totalPrice(String oid, List<UserInfo> array);

	/**
	 * 查询余票
	 * @param oid
	 * @param siteType
	 * @return
	 */
	int findRemain(String oid, String siteType);
 
	
	/*
	 * 李杭沅 
	 */
	/**
	 * 添加运营信息
	 * @param tid
	 * @param rworemain
	 * @param yworemain
	 * @param rzuoremain
	 * @param yzuoremain
	 * @param zhanremain
	 * @param date
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public int lhyadd(String tid, int rworemain, int yworemain, int rzuoremain, int yzuoremain, int zhanremain, String date, String starttime, String endtime);
	/**
	 * 修改座位数   ----- 根据订单修改->下单相应类型座位数-1  退单响应类型座位数+1
	 * @param tid
	 * @param type
	 * @param reamin
	 * @return
	 */
	public int lhyupdateRemain(String tid, String type, int reamin);
	/**
	 * 修改运营状态  0--误点  1--正常
	 * @param tid
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(String oid, String status);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByPage(int page, int rows);
	/**
	 * 条件查询，根据列车号、发车日期和状态
	 * @param tid
	 * @param date
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByCondition(String tid, String date, String status, int page, int rows);
	/**
	 * 根据编号查询
	 * @param oid
	 * @return
	 */
	public Operate lhyfindByOid(String oid);
	/**
	 * 修改
	 * @param oid
	 * @param rworemain
	 * @param yworemain
	 * @param rzuoremain
	 * @param yzuoremain
	 * @param zhanremain
	 * @param date
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public int lhyupdate(String oid, int rworemain, int yworemain, int rzuoremain, int yzuoremain, int zhanremain, String date, String starttime, String endtime);

}
