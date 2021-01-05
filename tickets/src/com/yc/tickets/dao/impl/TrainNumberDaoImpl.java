package com.yc.tickets.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.ITrainNumberDao;
import com.yc.tickets.domain.TrainNumber;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:43:47
*/
public class TrainNumberDaoImpl implements ITrainNumberDao {
	@Override
	public int lhyadd(String tname, String typeid, String startsite, String endsite, String duration, int rwo,
			double rwoprice, int rzuo, double rzuoprice, int ywo, double ywoprice, int yzuo, double yzuoprice, int zhan,
			double zhanprice, double wostudentprice, double zuostudentprice, int status) {
		DBHelper db = new DBHelper();
		String sql = "insert into train_number values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return db.update(sql, tname, typeid, startsite, endsite, duration, rwo, rwoprice, rzuo, rzuoprice, ywo, ywoprice, yzuo, yzuoprice, zhan, zhanprice, wostudentprice, zuostudentprice, status);
	}

	@Override
	public int lhyupdate(String tid, String tname, String typeid, String startsite, String endsite, String duration, int rwo,
			double rwoprice, int rzuo, double rzuoprice, int ywo, double ywoprice, int yzuo, double yzuoprice, int zhan,
			double zhanprice, double wostudentprice, double zuostudentprice) {
		DBHelper db = new DBHelper();
		String sql = "update train_number set tname=?, typeid=?, startsite=?, endsite=?, duration=?, rwo=?, "
				+ "rwoprice=?, rzuo=?, rzuoprice=?, ywo=?, ywoprice=?, yzuo=?, yzuoprice=?, zhan=?, zhanprice=?, wostudentprice=?, "
				+ "zuostudentprice=? where tid=?";
		return db.update(sql, tname, typeid, startsite, endsite, duration, rwo, rwoprice, rzuo, rzuoprice, ywo, ywoprice, yzuo, yzuoprice, zhan, zhanprice, wostudentprice, zuostudentprice, tid);
	}

	@Override
	public int lhyupdateStatus(String status, String tid) {
		DBHelper db = new DBHelper();
		String sql = "";
		if(status.equals("1")) {
			sql = "update train_number set status=0 where tid=?";
		}
		if(status.equals("0")) {
			sql = "update train_number set status=1 where tid=?";
		}
		return db.update(sql, tid);
	}

	@Override
	public int lhytotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(tid) from train_number";
		return db.total(sql);
	}

	@Override
	public List<TrainNumber> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select tn.tid, tn.tname, tn.typeid, tt.tname typename, tn.startsite, tn.endsite, s.sname startname, s2.sname endname, tn.status" + 
				"  from train_number tn INNER JOIN train_type tt on tn.typeid=tt.tid" + 
				" INNER JOIN site s on tn.startsite=s.sid INNER JOIN site s2 on s2.sid=tn.endsite order by tn.tid limit ?, ?";
		return db.finds(TrainNumber.class, sql, (page-1)*rows, rows);
	}

	@Override
	public int lhytotal(String tname, String typeid, String startsite, String endsite) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(tid) from train_number where 1=1";
		if(!StringUtil.checkNull(tname)) {
			sql += " and tname=?";
			params.add(tname);
		}
		if(!StringUtil.checkNull(typeid)) {
			sql += " and typeid=?";
			params.add(typeid);
		}
		if(!StringUtil.checkNull(startsite)) {
			sql += " and startsite=?";
			params.add(startsite);
		}
		if(!StringUtil.checkNull(endsite)) {
			sql += " and endsite=?";
			params.add(endsite);
		}
		return db.total(sql, params);
	}

	@Override
	public List<TrainNumber> lhyfindByCondition(String tname, String typeid, String startsite, String endsite, int page,
			int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select tn.tid, tn.tname, tn.typeid, tt.tname typename, tn.startsite, tn.endsite, s.sname startname, s2.sname endname, tn.status" + 
				"  from train_number tn INNER JOIN train_type tt on tn.typeid=tt.tid" + 
				" INNER JOIN site s on tn.startsite=s.sid INNER JOIN site s2 on s2.sid=tn.endsite where 1=1";
		if(!StringUtil.checkNull(tname)) {
			sql += " and tn.tname like concat('%', ?, '%')";
			params.add(tname);
		}
		if(!StringUtil.checkNull(typeid)) {
			sql += " and tn.typeid=?";
			params.add(typeid);
		}
		if(!StringUtil.checkNull(startsite)) {
			sql += " and tn.startsite=?";
			params.add(startsite);
		}
		if(!StringUtil.checkNull(endsite)) {
			sql += " and tn.endsite=?";
			params.add(endsite);
		}
		sql += " order by tid limit ?, ?";
		params.add((page-1)*rows);
		params.add(rows);
		return db.finds(TrainNumber.class, sql, params);
	}
	/**
	 * 多表查询
	 */
	@Override
	public TrainNumber lhyfindByTid(String tid) {
		DBHelper db = new DBHelper();
		String sql = "select tn.tid, tn.tname, tn.typeid, tt.tname typename, tn.startsite startname, tn.endsite endname, tn.duration, tn.rwo, tn.rwoprice,"
				+ " tn.rzuo, tn.rzuoprice, tn.ywo, tn.ywoprice, tn.yzuo, tn.yzuoprice, tn.zhan, tn.zhanprice, tn.wostudentprice, tn.zuostudentprice, tn.status from train_number tn" 
				+ " INNER JOIN train_type tt on tn.typeid=tt.tid"
				+ " INNER JOIN site s on tn.startsite=s.sid INNER JOIN site s2 on s2.sid=tn.endsite where tn.tid=?";
		return db.find(TrainNumber.class, sql, tid);
	}
}
