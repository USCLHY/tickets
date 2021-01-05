package com.yc.tickets.dao;

import java.util.List;

import com.yc.tickets.domain.TrainType;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午8:02:42
*/
public interface ITrainTypeDao {

	/**
	 * 查询所有车票类型
	 * @return
	 */
	List<TrainType> findAll();

	/**
	 * 添加类型
	 * @param tname
	 * @return
	 */
	public int lhyadd(String tname);
	/**
	 * 总记录数
	 * @return
	 */
	public int lhytotal();
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<TrainType> lhyfindByPage(int page, int rows);
	/**
	 * 查询所有车次类型
	 * @return
	 */
	public List<TrainType> lhyfinds();
}
