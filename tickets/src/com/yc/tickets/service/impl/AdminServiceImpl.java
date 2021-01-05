package com.yc.tickets.service.impl;

import com.yc.tickets.dao.IAdminDao;
import com.yc.tickets.dao.impl.AdminDaoImpl;
import com.yc.tickets.domain.Admin;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IAdminService;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:22:55
*/
public class AdminServiceImpl implements IAdminService{

	@Override
	public int lhyadd(String aname, String apassword, String aemail, String status) {
		IAdminDao dao = new AdminDaoImpl();
		if(StringUtil.checkNull(aname, apassword, aemail, status)) {
			return -1;
		}
		return dao.lhyadd(aname, apassword, aemail, status);
	}

	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		IAdminDao dao = new AdminDaoImpl();
		return new JsonObject(dao.lhytotal(), dao.lhyfindByPage(page, rows));
	}

	@Override
	public Admin lhyfindByAid(String aid) {
		IAdminDao dao = new AdminDaoImpl();
		return dao.lhyfindByAid(aid);
	}

	@Override
	public JsonObject lhyfindByCondition(String aname, String aemail, String status, int page, int rows) {
		IAdminDao dao = new AdminDaoImpl();
		return new JsonObject(dao.lhytotal(aname, aemail, status), dao.lhyfindByCondition(aname, aemail, status, page, rows));
	}

	@Override
	public int lhyupdateStatus(String aid) {
		if(StringUtil.checkNull(aid)) {
			return -1;
		}
		IAdminDao dao = new AdminDaoImpl();
		return dao.lhyupdateStatus(aid);
	}

	@Override
	public JsonObject lhysortByStatus(int page, int rows) {
		IAdminDao dao = new AdminDaoImpl();
		return new JsonObject(dao.lhytotal(), dao.lhysortByStatus(page, rows));

	}

	/**
	 * 管理员登录
	 */
	@Override
	public Admin login(String account, String password) {
		IAdminDao dao = new AdminDaoImpl();
		return dao.login(account, password);
	}
}
