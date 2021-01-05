package com.yc.tickets.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.IOrderItemDao;
import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.OrderItem;
import com.yc.tickets.domain.RefundTicket;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月9日 上午11:36:28
*/
public class OrderItemDaoImpl implements IOrderItemDao{
	private static JdbcTemplate jdbcTemplate = new JdbcTemplate( DBHelper_Mysql.getDs() ) ;

	@Override
	public List<String> findSeatNumber(int oid) {
		String sql = " select seatnumber from orderitems where operateid = ? " ;
		List<String> seatNumber = new ArrayList<String>() ;
		try {
			seatNumber = jdbcTemplate.queryForList(sql, String.class, oid) ;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return seatNumber ;
	}

	public void add(UserInfo user, String oid, int uid, String seat) {
		String sql = " insert into orderitems(operateid,uid,identify,buytime,name,money,type,seattype,seatnumber,status)"
				+ "values(?,?,?,now(),?,?,?,?,?,?)" ;
		jdbcTemplate.update(sql, oid,uid,user.getCardNumber(),user.getName(),user.getPrice(),user.getTicketType(),user.getSiteType(),seat,1) ;
	}

	/**
	 * 查询总记录数
	 */
	@Override
	public int total() {
		String sql = "select count( oid ) from orderitems " ;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/**
	 * 查询当前页的数据
	 */
	@Override
	public List<MyOrders> findByPage(String page, int rows) {
		
		String sql = "select ods.oid, tn.tname,s1.sname startsite,s2.sname endsite,date_format( date,\"%Y-%m-%d\") date,"
				+ "date_format(starttime,'%H:%i') starttime , ods.seattype,ods.type,ods.buytime,ods.money,ods.status  from "
				+ "orderitems ods, operate op ,site s1,site s2 , train_number tn , train_type t where ods.operateid=op.oid "
				+ "and op.tid=tn.tid and tn.typeid=t.tid and tn.startsite=s1.sid and tn.endsite=s2.sid  order by buytime limit ? ,? " ;
		
		List<MyOrders> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MyOrders>(MyOrders.class), Integer.parseInt(page) -1 , rows ) ;
		  
		return list;
	}

	/**
	 * 添加
	 */
	@Override
	public int add(RefundTicket rt) {
		String sql = "insert into refund_ticket(operateid,oid,uid,identify,name,refundtime,money,status) values(?,?,?,?,?,now(),?,0)" ;
		
		return jdbcTemplate.update(sql, rt.getOperateid(),rt.getOid(),rt.getUid(),rt.getIdentify(),rt.getName(),rt.getMoney()) ;
	}

	/**
	 * 通过订单id查询
	 */ 
	public OrderItem findById(int oid,Connection con) {  
		
		String sql = "select operateid,uid,identify,name,money,seattype from orderitems where oid = ? " ;
		
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<OrderItem>(OrderItem.class), oid);
	}

	/**
	 * 删除
	 */
	@Override
	public void delete(int oid) {
		String sql = "delete from orderitems where oid = ? " ;
		
		jdbcTemplate.update(sql, oid) ;
	}

	/**
	 * 增加座位
	 */
	@Override
	public void addSeat(Integer operateid, String seattype) {
		String param = "" ; 
		
		if ( "软卧".equals(seattype) ) {
			param = "rworemain" ; 
		}else if ( "软座".equals(seattype) ) {
			param = "rzuoremain" ; 
		}else if ( "硬卧".equals(seattype) ) {
			param = "yworemain" ; 
		}else if ( "硬座".equals(seattype) ) {
			param = "yzuoremain" ; 
		}else if ( "站票".equals(seattype) ) {
			param = "zhanremain" ; 
		}
		
		String sql = "update operate set "+ param +" = "+ param +" + 1 where oid = ? " ;
		jdbcTemplate.update(sql, operateid ) ;
	}

	/**
	 * 修改状态为待审核
	 */
	@Override
	public void updateState(String oid) {
		String sql = "update orderitems set status = 0 where oid = ? " ;
		jdbcTemplate.update(sql, Integer.parseInt(oid)) ;
	}

	@Override
	public List<OrderItem> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select o.oid, tn.tname tname, o.uid, u.uname uname, buytime, name, o.status from orderitems o" + 
				" inner join operate op on o.operateid=op.oid inner join users u on u.uid=o.uid inner join" + 
				" train_number tn on op.tid=tn.tid order by o.oid limit ?, ?";
		return db.finds(OrderItem.class, sql, (page-1)*rows, rows);
	}

	@Override
	public int lhytotal(boolean flag) {
		DBHelper db = new DBHelper();
		String sql = "select count(oid) from orderitems";
		if(!flag) {
			sql = "select count(oid) from orderitems where status != 0";
		}
		return db.total(sql);
	}

	@Override
	public List<OrderItem> lhyfindByCondition(String tid, String uname, String buytime, String name, String status,
			int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select o.oid, tn.tname tname, o.uid, u.uname uname, buytime, name, o.status from orderitems o" + 
				" inner join operate op on o.operateid=op.oid inner join users u on u.uid=o.uid inner join" + 
				" train_number tn on op.tid=tn.tid where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and op.tid=?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(uname)) {
			sql += " and u.uname like concat('%', ?, '%')";
			params.add(uname);
		}
		if(!StringUtil.checkNull(buytime)) {
			sql += " and SUBSTRING_INDEX(buytime, ' ', 1)=?";
			params.add(buytime);
		}
		if(!StringUtil.checkNull(name)) {
			sql += " and name like concat('%', ?, '%')";
			params.add(name);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and o.status=?";
			params.add(status);
		}
		sql += " order by oid limit ?, ?";
		params.add((page-1)*rows);
		params.add(rows);
		return db.finds(OrderItem.class, sql, params);
	}

	@Override
	public int lhytotal(String tid, String uname, String buytime, String name, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(o.oid) from orderitems o" + 
				" inner join operate op on o.operateid=op.oid inner join users u on u.uid=o.uid inner join" + 
				" train_number tn on op.tid=tn.tid where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and op.tid=?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(uname)) {
			sql += " and u.uname like concat('%', ?, '%')";
			params.add(uname);
		}
		if(!StringUtil.checkNull(buytime)) {
			sql += " and SUBSTRING_INDEX(buytime, ' ', 1)=?";
			params.add(buytime);
		}
		if(!StringUtil.checkNull(name)) {
			sql += " and name like concat('%', ?, '%')";
			params.add(name);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and o.status=?";
			params.add(status);
		}
		return db.total(sql, params);
	}

	@Override
	public OrderItem lhyfindByOid(String oid) {
		DBHelper db = new DBHelper();
		String sql = "select o.oid, tn.tname tname, o.uid, identify, type, seattype, seatnumber, money, u.uname uname, buytime, name, o.status"
				+ " from orderitems o" + 
				" inner join operate op on o.operateid=op.oid inner join users u on u.uid=o.uid inner join" + 
				" train_number tn on op.tid=tn.tid where o.oid=?";
		return db.find(OrderItem.class, sql, oid);
	}

	@Override
	public List<Map<String, String>> lhysumByTid() {
		DBHelper db = new DBHelper();
		String sql = "select ord.operateid, tn.tname, sum(money) sum from orderitems ord inner join operate op ON" + 
				" op.oid=ord.operateid inner join train_number tn on tn.tid=op.tid GROUP BY ord.operateid";
		return db.gets(sql);
	}

	@Override
	public List<Map<String, String>> lhysumByYear(String year) {
		DBHelper db = new DBHelper();
		String sql = "select tn.tid, SUBSTRING_INDEX(buytime, '-', 1) year, tn.tname, sum(money) sum from orderitems ord inner join operate op on ord.operateid=op.oid" + 
				" inner join train_number tn on op.tid=tn.tid where SUBSTRING_INDEX(buytime, '-', 1)=? GROUP BY tn.tid";
		return db.gets(sql, year);
	}

	@Override
	public List<Map<String, String>> lhysumByMonth(String year, String month) {
		String dyear = year + "-" + month;
		DBHelper db = new DBHelper();
		String sql = "select tn.tid, SUBSTRING_INDEX(buytime, '-', 2) month, tn.tname, sum(money) sum from orderitems ord inner join operate op on ord.operateid=op.oid" + 
				" inner join train_number tn on op.tid=tn.tid where SUBSTRING_INDEX(buytime, '-', 2)=? GROUP BY tn.tid";
		return db.gets(sql, dyear);
	}

	@Override
	public List<Map<String, String>> lhysumByDate(String date) {
		DBHelper db = new DBHelper();
		String sql = "select tn.tid, SUBSTRING_INDEX(buytime, ' ', 1) date, tn.tname, sum(money) sum from orderitems ord inner join operate op on ord.operateid=op.oid" + 
				" inner join train_number tn on op.tid=tn.tid where SUBSTRING_INDEX(buytime, ' ', 1)=? GROUP BY tn.tid";
		return db.gets(sql, date);
	}

}
