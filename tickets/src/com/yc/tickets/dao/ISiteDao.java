package com.yc.tickets.dao;

import java.util.List;

import com.yc.tickets.domain.Site;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:38:16
*/
public interface ISiteDao {
	/**
	 * 添加站点
	 * @param sname
	 * @param status
	 * @return
	 */
	public int lhyadd(String sname, String status);
	/**
	 * 修改站点名
	 * @param sname
	 * @return
	 */
	public int lhyupdateSname(String sname, String sid);
	/**
	 * 修改站点状态
	 * @param status
	 * @return
	 */
	public int lhyupdateStatus(String status, String sid);
	/**
	 * 查询记录总数
	 * @param flag true总记录  false可用总记录
	 * @return
	 */
	public int lhytotal(boolean flag);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Site> lhyfindByPage(int page, int rows);
	/**
	 * 查询所有可用站点
	 * @return
	 */
	public List<Site> lhyfinds();
}
