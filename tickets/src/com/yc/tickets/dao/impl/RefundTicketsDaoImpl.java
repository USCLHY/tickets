package com.yc.tickets.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.IRefundTicketsDao;
import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.MyRefund;
import com.yc.tickets.domain.RefundTicket;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月17日 下午12:04:54
*/
public class RefundTicketsDaoImpl implements IRefundTicketsDao{

	private JdbcTemplate jdbcTemplate = new JdbcTemplate( DBHelper_Mysql.getDs() );

	@Override
	public int total() {
		String sql = "select count( rid ) from refund_ticket " ;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<MyRefund> findByPage(String page, int rows) {
		String sql = "select ods.oid, tn.tname,s1.sname startsite,s2.sname endsite,date_format( date,\"%Y-%m-%d\") date,"
				+ "date_format(starttime,'%H:%i') starttime , ods.seattype,ods.type,rt.refundtime,ods.money,rt.status  from "
				+ "orderitems ods, operate op ,site s1,site s2 , train_number tn , train_type t,refund_ticket rt where "
				+ " rt.oid = ods.oid and ods.operateid=op.oid "
				+ "and op.tid=tn.tid and tn.typeid=t.tid and tn.startsite=s1.sid and tn.endsite=s2.sid order by buytime limit ? ,? " ;
		
		List<MyRefund> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MyRefund>(MyRefund.class), Integer.parseInt(page) -1 , rows ) ;
		 
		return list;
	}

	
	@Override
	public List<RefundTicket> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select rid, tn.tname, u.uname, name, refundtime, identify, rt.status from refund_ticket rt inner join"
				+ " operate op on rt.operateid=op.oid inner join train_number tn on op.tid=tn.tid inner join"
				+ " users u on u.uid=rt.uid order by rid limit ?, ?";
		return db.finds(RefundTicket.class, sql, (page-1)*rows, rows);
	}

	@Override
	public int lhytotal(boolean flag) {
		DBHelper db = new DBHelper();
		String sql = "select count(rid) from refund_ticket";
		if(!flag) {
			sql = "select count(rid) from refund_ticket where status != 0";
		}
		return db.total(sql);
	}

	@Override
	public List<RefundTicket> lhyfindByCondition(String tid, String uname, String name, String refundtime, String status,
			int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select rid, tn.tname, u.uname, name, refundtime, identify, rt.status from refund_ticket rt inner join"
				+ " operate op on rt.operateid=op.oid inner join train_number tn on op.tid=tn.tid inner join"
				+ " users u on u.uid=rt.uid where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and op.tid=?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(uname)) {
			sql += " and u.uname like concat('%', ?, '%')";
			params.add(uname);
		}
		if(!StringUtil.checkNull(name)) {
			sql += " and rt.name like concat('%', ?, '%')";
			params.add(name);
		}
		if(!StringUtil.checkNull(refundtime)) {
			sql += " and SUBSTRING_INDEX(refundtime, ' ', 1)=?";
			params.add(refundtime);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and rt.status=?";
			params.add(status);
		}
		sql += " order by oid limit ?, ?";
		params.add((page-1)*rows);
		params.add(rows);
		return db.finds(RefundTicket.class, sql, params);
	}

	@Override
	public int lhytotal(String tid, String uname, String name, String refundtime, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(rid) from refund_ticket rt inner join"
				+ " operate op on rt.operateid=op.oid inner join train_number tn on op.tid=tn.tid inner join"
				+ " users u on u.uid=rt.uid where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and op.tid=?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(uname)) {
			sql += " and u.uname like concat('%', ?, '%')";
			params.add(uname);
		}
		if(!StringUtil.checkNull(name)) {
			sql += " and rt.name like concat('%', ?, '%')";
			params.add(name);
		}
		if(!StringUtil.checkNull(refundtime)) {
			sql += " and SUBSTRING_INDEX(refundtime, ' ', 1)=?";
			params.add(refundtime);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and rt.status=?";
			params.add(status);
		}
		return db.total(sql, params);
	}

	@Override
	public int lhyupdateStatus(String rid, String status) {
		DBHelper db = new DBHelper();
		String sql01 = "update refund_ticket set status=? where rid=?";
		String sql02 = "update operate INNER JOIN refund_ticket on operate.oid=refund_ticket.oid " + 
				"inner join orderitems on orderitems.oid=refund_ticket.oid SET " + 
				"rworemain= case seattype when '软卧' then rworemain+1 else rworemain end, " + 
				"rzuoremain = case seattype when '软座' then rzuoremain+1 else rzuoremain end,  " + 
				"yworemain = case seattype when '硬卧' then yworemain+1 else yworemain end,  " + 
				"yzuoremain = case seattype when '硬座' then yzuoremain+1 else yzuoremain end " + 
				"where refund_ticket.rid=?";
		String sql03 = "update orderitems od set od.status = 3 where od.oid in (select rt.oid from refund_ticket rt where rid=?)";
		List<String> sqls = new ArrayList<String>();
		sqls.add(sql01);
		sqls.add(sql02);
		sqls.add(sql03);
		List<List<Object>> params = new ArrayList<List<Object>>();
		List<Object> param01 = new ArrayList<Object>();
		param01.add(status);
		param01.add(rid);
		List<Object> param02 = new ArrayList<Object>();
		param02.add(rid);
		List<Object> param03 = new ArrayList<Object>();
		param03.add(rid);
		params.add(param01);
		params.add(param02);
		params.add(param03);
		return db.updates(sqls, params);
	}
	
}
