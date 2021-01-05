package com.yc.tickets.dao;

import java.util.List;

import com.yc.tickets.domain.Operate;
import com.yc.tickets.domain.UserInfo;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:30:03
*/
public interface IOperateDao {
	
	/**
	 * 根据条件查询总记录数
	 * @param tid
	 * @return
	 */
	Integer total( Integer tid , String startsite,String endsite , String date ) ;
	
	/**
	 * 根据条件分页查询数据
	 * @param tid
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Operate> findByType( Integer tid, Integer page, Integer rows , String startsite,String endsite , String date ) ;

	/**
	 * 查询当前座位的价格
	 * @param oid
	 * @param userInfo
	 * @return
	 */
	double totalPrice(String oid, UserInfo userInfo);

	/**
	 * 查询余票
	 * @param oid
	 * @param siteType
	 * @return
	 */
	int findRemain(String oid, String siteType);

	void subSeat(UserInfo userInfo, String oid);


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
	 * 总记录数
	 * @param flag
	 * @return
	 */
	public int lhytotal(boolean flag);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Operate> lhyfindByPage(int page, int rows);
	/**
	 * 条件查询，根据列车号、发车日期和状态
	 * @param tid
	 * @param date
	 * @param status
	 * @return
	 */
	public int lhytotal(String tid, String date, String status);
	/**
	 * 根据条件查询的总记录数
	 * @param tid
	 * @param date
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Operate> lhyfindByCondition(String tid, String date, String status, int page, int rows);
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
