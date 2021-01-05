package com.yc.tickets.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.IAdminDao;
import com.yc.tickets.domain.Admin;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月16日 上午10:39:16
*/
public class AdminDaoImpl implements IAdminDao{
	private JdbcTemplate template = new JdbcTemplate( DBHelper_Mysql.getDs() ) ;

	/**
	 * 管理员登陆
	 */
	@Override
	public Admin login(String account, String pwd) {
		String sql = "select aid,aname,aemail,status from admin where aemail = ? and apassword = ? and status != 0  " ;
		return template.queryForObject(sql, new BeanPropertyRowMapper<Admin>( Admin.class), account,pwd );
	}

	
	@Override
	public int lhyadd(String aname, String apassword, String aemail, String status) {
		DBHelper db = new DBHelper();
		String sql = "insert into admin values(0, ?, ?, ?, ?)";
		return db.update(sql, aname, apassword, aemail, status);
	}

	@Override
	public List<Admin> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select aid, aname, apassword, aemail, status from admin order by aid limit ?, ?";
		return db.finds(Admin.class, sql, (page-1)*rows, rows);
	}

	@Override
	public int lhytotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(aid) from admin";
		return db.total(sql);
	}

	@Override
	public Admin lhyfindByAid(String aid) {
		DBHelper db = new DBHelper();
		String sql = "select aid, aname, apassword, aemail, status from admin where aid=?";
		return db.find(Admin.class, sql, aid);
	}

	@Override
	public List<Admin> lhyfindByCondition(String aname, String aemail, String status, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select aid, aname, apassword, aemail, status from admin where 1=1";
		if(!StringUtil.checkNull(aname)) {
			params.add(aname);
			sql += " and aname like concat('%', ?, '%')";
		}
		if(!StringUtil.checkNull(aemail)) {
			params.add(aemail);
			sql += " and aemail=?";
		}
		if(!StringUtil.checkNull(status)) {
			params.add(Integer.parseInt(status));
			sql += " and status=?";
		}
		sql += " order by aid limit ?, ?";
		params.add((page-1)*rows);
		params.add(rows);
		return db.finds(Admin.class, sql, params);
	}

	@Override
	public int lhytotal(String aname, String aemail, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(aid) from admin where 1=1";
		if(!StringUtil.checkNull(aname)) {
			params.add(aname);
			sql += " and aname like concat('%', ?, '%')";
		}
		if(!StringUtil.checkNull(aemail)) {
			params.add(aemail);
			sql += " and aemail=?";
		}
		if(!StringUtil.checkNull(status)) {
			params.add(status);
			sql += " and status=?";
		}
		return db.total(sql, params);
	}

	@Override
	public int lhyupdateStatus(String aid) {
		DBHelper db = new DBHelper();
		String sql = "update admin set status=1 where aid=?";
		return db.update(sql, aid);
	}

	@Override
	public List<Admin> lhysortByStatus(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select aid, aname, apassword, aemail, status from admin order by status limit ?, ?";
		return db.finds(Admin.class, sql, (page-1)*rows, rows);
	}
}
