package com.yc.tickets.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.IOperateDao;
import com.yc.tickets.domain.Operate;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午9:32:45
*/
public class OperateDaoImpl implements IOperateDao {
	private JdbcTemplate template= new JdbcTemplate( DBHelper_Mysql.getDs() ) ;

	/**
	 * 查询符合条件的总数量
	 */
	@Override
	public Integer total(Integer tid , String startsite,String endsite , String date ) {
		String sql =  "select count(oid) from operate op ,site s1,site s2 , train_number tn ,"
					+ " train_type t where op.tid=tn.tid and tn.typeid=t.tid and tn.startsite=s1.sid and"
					+ " tn.endsite=s2.sid " ;
		
		List<Object> params= new ArrayList<Object>() ;
			
		if ( tid != null ) {
			sql += " and t.tid = ? " ;
			params.add( tid );
		}
		if ( startsite != null && !"".equals(startsite)) {
			sql += " and s1.sname like concat('%',?,'%') " ;
			params.add( startsite );
		}
		if (endsite != null && !"".equals(endsite) ) {
			sql += " and s2.sname like concat('%',?,'%') " ;
			params.add( endsite );
		}
		if (date != null && !"".equals(date) ) {
			sql += " and date_format( date,'%Y-%m-%d') like concat('%',?,'%') " ;
			params.add( date );
		} 
		
		SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		// 开始日期
		String startdate = sdf01.format(new Date() ).split(" ")[0] ; 
		
		// 结束日期的月份
		int endMonth = ( ( Integer.parseInt( startdate.split("-")[1] ) + 1 )  % 12 )  ;
		String endmouth = "";
		if ( endMonth < 10 ) {
			endmouth = "0" + endMonth ;
		}else {
			endmouth = endMonth + "" ;
		}
		// 结束日期的年份
		int endYear =Integer.parseInt( startdate.split("-")[0] ) + ( ( Integer.parseInt( startdate.split("-")[1] ) + 1 )  / 12 ) ;
		// 结束日期的年天数 
		int endDay = Integer.parseInt( startdate.split("-")[2] ) ;
		String endday = "" ;
		if ( endDay < 10 ) {
			endday = "0" + endDay ;
		}else {
			endday = endDay + "" ;
		}
		
		String enddate = endYear+"-"+endmouth+"-"+endday ;
		params.add( startdate ) ;
		params.add( enddate ) ;
		
		sql += " and date_format( date,'%Y-%m-%d') between ? and ?" ;
		
		
		return template.queryForObject(sql, params.toArray(), Integer.class) ;
	}

	/**
	 * 分页查询数据
	 */
	@Override
	public List<Operate> findByType(Integer tid, Integer page, Integer rows, String startsite,String endsite , String date ) {
		String sql = "select oid, tn.tname,s1.sname startsite,s2.sname endsite, date_format(date,'%Y-%m-%d') date, "
				+ "date_format(starttime,'%H:%i') starttime,date_format(endtime,'%H:%i') endtime,tn.duration,"
				+ "rworemain,yworemain,rzuoremain,yzuoremain,zhanremain , op.status from operate op ,site s1,site s2 , "
				+ "train_number tn , train_type t where op.tid=tn.tid and tn.typeid=t.tid and tn.startsite=s1.sid"
				+ " and tn.endsite=s2.sid " ; 
		
		List<Object> params= new ArrayList<Object>() ;
		
		if ( tid != null ) {
			sql += " and t.tid = ? " ;
			params.add( tid );
		}
		if ( startsite != null && !"".equals(startsite)) {
			sql += " and s1.sname like concat('%',?,'%') " ;
			params.add( startsite );
		}
		if (endsite != null && !"".equals(endsite) ) {
			sql += " and s2.sname like concat('%',?,'%') " ;
			params.add( endsite );
		}
		if (date != null && !"".equals(date) ) {
			sql += " and date_format( date,'%Y-%m-%d') like concat('%',?,'%') " ;
			params.add( date );
		}
		
		SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		// 开始日期
		String startdate = sdf01.format(new Date() ).split(" ")[0] ; 
		
		// 结束日期的月份
		int endMonth = ( ( Integer.parseInt( startdate.split("-")[1] ) + 1 )  % 12 )  ;
		String endmouth = "";
		if ( endMonth < 10 ) {
			endmouth = "0" + endMonth ;
		}else {
			endmouth = endMonth + "" ;
		}
		// 结束日期的年份
		int endYear =Integer.parseInt( startdate.split("-")[0] ) + ( ( Integer.parseInt( startdate.split("-")[1] ) + 1 )  / 12 ) ;
		// 结束日期的年天数 
		int endDay = Integer.parseInt( startdate.split("-")[2] ) ;
		String endday = "" ;
		if ( endDay < 10 ) {
			endday = "0" + endDay ;
		}else {
			endday = endDay + "" ;
		}
		
		String enddate = endYear+"-"+endmouth+"-"+endday ;
		params.add( startdate ) ;
		params.add( enddate ) ;
		
		sql += " and date_format( date,'%Y-%m-%d') between ? and ? order by date limit ?, ? " ;
		params.add( (page-1)*rows ) ;
		params.add( rows ) ; 
		
		return template.query(sql, params.toArray(), new BeanPropertyRowMapper<Operate>(Operate.class)) ;
	}

	/**
	 * 查询当前座位的价格
	 */
	@Override
	public double totalPrice(String oid, UserInfo userInfo) {
		 
		// 获取座位类型和票的类型
		String siteType = userInfo.getSiteType();
		String ticketType = userInfo.getTicketType();
		
		String sql = "select " ;
		
		String param = "" ; 
		
		if ( ticketType.equals("成人票") ) {
			if ( siteType.equals("软卧") ) {
				param = "rwoprice" ; 
			}else if ( siteType.equals("软座") ) {
				param = "rzuoprice" ; 
			}else if ( siteType.equals("硬卧") ) {
				param = "ywoprice" ; 
			}else if ( siteType.equals("硬座") ) {
				param = "yzuoprice" ; 
			}else if ( siteType.equals("站票") ) {
				param = "zhanprice" ; 
			}
		}else if (ticketType.equals("学生票")) {
			switch (siteType) {
			case "软卧":
			case "硬卧":
				param = "wostudentprice" ;
				break;
			case "软座":
			case "硬座":
				param = "zuostudentprice" ;
				break;
			case "站票":
				param = "zhanprice" ;
				break;
			}
		}else {
			return 0.0d ;
		}
		
		sql = sql + param + " from operate op join train_number tn on op.tid = tn.tid where oid = ?" ;
		
		return template.queryForObject(sql, Double.class, oid );
	}

	/**
	 * 查询余票
	 */
	@Override
	public int findRemain(String oid, String siteType) {
		String param = "" ; 
		String sql = "select " ;
		
		if ( siteType.equals("软卧") ) {
			param = "rworemain" ; 
		}else if ( siteType.equals("软座") ) {
			param = "rzuoremain" ; 
		}else if ( siteType.equals("硬卧") ) {
			param = "yworemain" ; 
		}else if ( siteType.equals("硬座") ) {
			param = "yzuoremain" ; 
		}else if ( siteType.equals("站票") ) {
			param = "zhanremain" ; 
		}
		
		sql = sql + param + " from operate op where oid = ? " ;
		
		return template.queryForObject(sql, Integer.class, oid );
	}

	/**
	 * 减座
	 */
	@Override
	public void subSeat(UserInfo userInfo, String oid) {
		String siteType = userInfo.getSiteType() ;
		String param = "" ; 
		
		if ( siteType.equals("软卧") ) {
			param = "rworemain" ; 
		}else if ( siteType.equals("软座") ) {
			param = "rzuoremain" ; 
		}else if ( siteType.equals("硬卧") ) {
			param = "yworemain" ; 
		}else if ( siteType.equals("硬座") ) {
			param = "yzuoremain" ; 
		}else if ( siteType.equals("站票") ) {
			param = "zhanremain" ; 
		}
		
		String sql = "update operate set "+ param +" = "+ param +" - 1 where oid = ? " ;
		template.update(sql, oid ) ;
	}

	
	@Override
	public int lhyadd(String tid, int rworemain, int yworemain, int rzuoremain, int yzuoremain, int zhanremain, String date, String starttime, String endtime) {
		DBHelper db = new DBHelper();
		String sql = "insert into operate values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
		return db.update(sql, tid, rworemain, yworemain, rzuoremain, yzuoremain, zhanremain, date, starttime, endtime);
	}

	@Override
	public int lhyupdateRemain(String tid, String type, int reamin) {
		DBHelper db = new DBHelper();
		String sql = "";
		if(type.equals("rworemain")) {
			sql = "update operate set rworemain=? where tid=?";
		}
		if(type.equals("yworemain")) {
			sql = "update operate set yworemain=? where tid=?";
		}
		if(type.equals("rzuoremain")) {
			sql = "update operate set rzuoremain=? where tid=?";
		}
		if(type.equals("yzuoremain")) {
			sql = "update operate set yzuoremain=? where tid=?";
		}
		if(type.equals("zhanremain")) {
			sql = "update operate set zhanremain=? where tid=?";
		}
		return db.update(sql, reamin, tid);
	}

	@Override
	public int lhyupdateStatus(String oid, String status) {
		DBHelper db = new DBHelper();
		String sql = "";
		if(status.equals("0")) {
			sql = "update operate set status=1 where oid=?";
		}
		if(status.equals("1")) {
			sql = "update operate set status=0 where oid=?";
		}
		return db.update(sql, oid);
	}

	@Override
	public int lhytotal(boolean flag) {
		DBHelper db = new DBHelper();
		String sql = "select count(oid) from operate";
		if(!flag) {
			sql = "select count(oid) from operate where status != 0";
		}
		return db.total(sql);
	}

	@Override
	public List<Operate> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select oid, o.tid, tn.tname tname, o.date, starttime, endtime, o.status"
				+ " from operate o inner join train_number tn on o.tid=tn.tid order by oid limit ?, ?";
		return db.finds(Operate.class, sql, (page-1)*rows, rows);
	}

	@Override
	public int lhytotal(String tid, String date, String status) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(oid) from operate where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and tid=?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(date)) {
			sql += " and date=?";
			params.add(date);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and status=?";
			params.add(status);
		}
		return db.total(sql, params);
	}

	@Override
	public List<Operate> lhyfindByCondition(String tid, String date, String status, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select oid, o.tid, tn.tname tname, date_format(o.date, '%Y-%m-%d') date, starttime, endtime, o.status"
				+ " from operate o inner join train_number tn on o.tid=tn.tid where 1=1";
		if(!StringUtil.checkNull(tid)) {
			sql += " and o.tid=?";
			params.add(tid);
		}
		if(!StringUtil.checkNull(date)) {
			sql += " and o.date=?";
			params.add(date);
		}
		if(!StringUtil.checkNull(status)) {
			sql += " and o.status=?";
			params.add(status);
		}
		sql += " order by o.oid limit ?, ?";
		params.add((page-1)*rows);
		params.add(rows);
		return db.finds(Operate.class, sql, params);
	}

	@Override
	public Operate lhyfindByOid(String oid) {
		DBHelper db = new DBHelper();
		String sql = "select oid, o.tid, tn.tname tname, date_format(o.date, '%Y-%m-%d') date, starttime, endtime, rworemain, yworemain, rzuoremain,"
				+ " yzuoremain, zhanremain, o.status from operate o inner join train_number tn on o.tid=tn.tid where oid=?";
		return db.find(Operate.class, sql, oid);
	}

	@Override
	public int lhyupdate(String oid, int rworemain, int yworemain, int rzuoremain, int yzuoremain, int zhanremain,
			String date, String starttime, String endtime) {
		DBHelper db = new DBHelper();
		String sql = "update operate set rworemain=?, yworemain=?, rzuoremain=?, yzuoremain=?, zhanremain=?, date=?,"
				+ " starttime=?, endtime=? where oid=?";
		return db.update(sql, rworemain, yworemain, rzuoremain, yzuoremain, zhanremain, date, starttime, endtime, oid);
	}

}















