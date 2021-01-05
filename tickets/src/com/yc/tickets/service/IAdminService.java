package com.yc.tickets.service;

import com.yc.tickets.domain.Admin;
import com.yc.tickets.dto.JsonObject;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:18:25
*/
public interface IAdminService {
	/**
	 * 由超级管理员添加普通管理员
	 * @param aname
	 * @param apassword
	 * @param aemail
	 * @param status
	 * @return
	 */
	public int lhyadd(String aname, String apassword, String aemail, String status);
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public JsonObject lhyfindByPage(int page, int rows);
	/**
	 * 根据编号查询
	 * @param aid
	 * @return
	 */
	public Admin lhyfindByAid(String aid);
	/**
	 * 条件查询
	 * @param aname
	 * @param aemail
	 * @param status
	 * @return
	 */
	public JsonObject lhyfindByCondition(String aname, String aemail, String status, int page, int rows);
	/**
	 * 升级管理员权限
	 * @param aid
	 * @return
	 */
	public int lhyupdateStatus(String aid);
	/**
	 * 根据管理员类型排序
	 * @return
	 */
	public JsonObject lhysortByStatus(int page, int rows);
	
	/**
	 * 管理员登录
	 * @param account
	 * @param password
	 * @return
	 */
	public Admin login(String account, String password);

}
