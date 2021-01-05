package com.yc.tickets.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.IUsersDao;
import com.yc.tickets.domain.Users;
import com.yc.tickets.utils.StringUtil; 

/**
* 用户的持久层实现类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午9:02:23
*/
public class UsersDaoImpl implements IUsersDao{
	private JdbcTemplate template = new JdbcTemplate( DBHelper_Mysql.getDs() ) ; 

	/**
	 * 用户注册
	 */
	@Override
	public int register(Users user) {
		String sql = "insert into users values(null,?,?,'images/man.png',?,?,?,0)" ;
		return template.update(  sql, user.getUname(),user.getUpassword(),user.getUtel(),user.getUidentify(),user.getUemail());
	}

	/**
	 * 用户登录
	 */
	@Override
	public Users login(String account, String pwd) {
		String sql = "select uid,uname,upic,utel,uemail,uidentify,status from users where ( utel=? or uidentify=? or uemail=?) and upassword=? and status != 0" ;
		return template.queryForObject(sql, new BeanPropertyRowMapper<Users>(Users.class), account,account,account,pwd);
		
	}

	/**
	 * 用户激活
	 */
	@Override
	public int active(String identify) {
		String sql = " update users set status = 1 where uidentify = ? " ;
		return template.update(sql, identify);
	}

	/**
	 * 通过邮箱查询用户
	 */
	@Override
	public Users findByEmail(String email) {
		String sql = "select upassword from users where uemail = ? and status != 0 " ;
		return template.queryForObject(sql, new BeanPropertyRowMapper<Users>(Users.class), email);
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public int update(Users user) {
		String sql = "update users set uname = ?,utel=?,uidentify=?,uemail=? where uid = ? " ;
		return template.update(sql, user.getUname(),user.getUtel(),user.getUidentify(),user.getUemail(),user.getUid());
	}

	@Override
	public int lhytotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(uid) from users";
		return db.total(sql);
	}

	@Override
	public List<Users> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select uid, uname, upic, utel, uidentify, uemail, status from users order by uid limit ?, ?";
		return db.finds(Users.class, sql, (page - 1)*rows, rows);
	}

	@Override
	public List<Users> lhyfindByCondition(String uname, String utel, String status, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select uid, uname, upic, utel, uidentify, uemail, status from users where 1=1";
		if(!StringUtil.checkNull(uname)) {
			sql += " and uname like concat('%', ?, '%')";
			params.add(uname);
		}
		if(!StringUtil.checkNull(utel)) {
			sql += " and utel=?";
			params.add(utel);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and status=?";
			params.add(status);
		}
		sql += " order by uid limit ?, ?";
		params.add((page-1)*rows);
		params.add(rows);
		return db.finds(Users.class, sql, params);
	}

	@Override
	public int lhytotal(String uname, String utel, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(uid) from users where 1=1";
		if(!StringUtil.checkNull(uname)) {
			sql += " and uname like concat('%', ?, '%')";
			params.add(uname);
		}
		if(!StringUtil.checkNull(utel)) {
			sql += " and utel=?";
			params.add(utel);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and status=?";
			params.add(status);
		}
		return db.total(sql, params);
	}

	@Override
	public int lhyupdateStatus(String uid, int status) {
		DBHelper db = new DBHelper();
		String sql = "";
		if(status == 1) {
			sql = "update users set status=0 where uid=?";//根据用户编号和状态修改用户状态
		}
		if(status == 0) {
			sql = "update users set status=1 where uid=?";
		}
		return db.update(sql, uid);
	}

	@Override
	public Users lhyfindByUid(String uid) {
		DBHelper db = new DBHelper();
		String sql = "select uid, uname, upic, utel, uidentify, uemail, status from users where uid=?";
		return db.find(Users.class, sql, uid);
	}
}	
