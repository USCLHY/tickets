package com.yc.tickets.dao;

import java.util.List;

import com.yc.tickets.domain.Admin;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月16日 上午10:38:21
*/
public interface IAdminDao {
	
	/**
	 * 管理员登录
	 * @param account
	 * @param pwd
	 * @return
	 */
    Admin login( String account, String pwd ) ;
    
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
	public List<Admin> lhyfindByPage(int page, int rows);
	/**
	 * 根据分页查询的总记录数
	 * @return
	 */
	public int lhytotal();
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
	public List<Admin> lhyfindByCondition(String aname, String aemail, String status, int page, int rows);
	/**
	 * 根据条件查询的总数
	 * @param aname
	 * @param aemail
	 * @param status
	 * @return
	 */
	public int lhytotal(String aname, String aemail, String status);
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
	public List<Admin> lhysortByStatus(int page, int rows);

}
