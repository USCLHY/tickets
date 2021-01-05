package com.yc.tickets.dao.impl;

import java.util.List;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.ISiteDao;
import com.yc.tickets.domain.Site;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:42:56
*/
public class SiteDaoImpl implements ISiteDao{
	@Override
	public int lhyadd(String sname, String status) {
		DBHelper db = new DBHelper();
		String sql = "insert into site values(0, ?, ?)";
		return db.update(sql, sname, status);
	}

	@Override
	public int lhyupdateSname(String sname, String sid) {
		DBHelper db = new DBHelper();
		String sql = "update site set sname=? where sid=?";
		return db.update(sql, sname, sid);
	}

	@Override
	public int lhyupdateStatus(String status, String sid) {
		DBHelper db = new DBHelper();
		String sql = "";
		if(status.equals("1")) {
			sql = "update site set status=0 where sid=?";
		}
		if(status.equals("0")) {
			sql = "update site set status=1 where sid=?";
		}
		return db.update(sql, sid);
	}

	@Override
	public int lhytotal(boolean flag) {
		DBHelper db = new DBHelper();
		String sql = "";
		if(flag) {
			sql = "select count(sid) from site";
		}else {
			sql = "select count(sid) from site where status != 0";
		}
		return db.total(sql);
	}

	@Override
	public List<Site> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select sid, sname, status from site order by sid limit ?, ?";
		return db.finds(Site.class, sql, (page-1)*rows, rows);
	}

	@Override
	public List<Site> lhyfinds() {
		DBHelper db = new DBHelper();
		String sql = "select sid, sname, status from site where status != 0";
		return db.finds(Site.class, sql);
	}
}
