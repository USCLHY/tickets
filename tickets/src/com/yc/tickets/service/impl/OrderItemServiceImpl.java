package com.yc.tickets.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.IOperateDao;
import com.yc.tickets.dao.IOrderItemDao;
import com.yc.tickets.dao.impl.OperateDaoImpl;
import com.yc.tickets.dao.impl.OrderItemDaoImpl;
import com.yc.tickets.domain.MyOrders;
import com.yc.tickets.domain.OrderItem;
import com.yc.tickets.domain.PageBean;
import com.yc.tickets.domain.RefundTicket;
import com.yc.tickets.domain.UserInfo;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IOrderItemService;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月9日 上午11:34:26
*/
public class OrderItemServiceImpl implements IOrderItemService{ 
	private IOrderItemDao dao = new OrderItemDaoImpl() ; 
	private IOperateDao operateDao = new OperateDaoImpl() ; 

	/**
	 * 购票
	 */
	@Override
	public int add(List<UserInfo> passengers, String oid, int uid) {
		
		List<String> seatNumbers = dao.findSeatNumber(Integer.parseInt( oid )) ;
		int number = 0 ;
		boolean flag = false ;
		List<Integer> secord= new ArrayList<Integer>() ;
		
		if ( seatNumbers.isEmpty()) {
			// 如果之前没有座位号
			seatNumbers.add("SN1")  ;
			number = 1 ;
		} 
		
		// 循环添加，需要额外给一个座位号
		for (UserInfo userInfo : passengers) {
			seatNumbers = dao.findSeatNumber(Integer.parseInt( oid )) ;
			
			// 循环座位号，把空缺的座位号补齐
			for (int i = 0; i < seatNumbers.size() ; i++) {
				number = Integer.parseInt( seatNumbers.get(i).substring(2) );
				secord.add(number) ; 
			}
			
			for (int i = 0; i < secord.size() ; i++) {
				if ( !secord.contains( i + 1 )) {
					number = i + 1 ;
					flag = true ;
					break ;
				}  
			}
			
			if ( !flag ) {
				number =  secord.size() + 1 ;
			}
			
			String seat = "SN" + number ;  
			
			// 开启事务
			Connection conn = null  ;
			try {
				conn = DBHelper_Mysql.getDs().getConnection();
				conn.setAutoCommit(false);
				
				// 添加订单
				dao.add( userInfo , oid , uid , seat );
				
				// 如果订单添加一个，则相应的运营车次的座位就要减一
				operateDao.subSeat( userInfo , oid );
				
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		
		return 0;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean<MyOrders> findByPage(String page) {
		
		// 查询总记录
		int totalCount = dao.total();
		
		// 总页数
		int totalPage =  (int) Math.ceil( totalCount / 5.0 ) ; 
		
		// 每一页的数据
		List<MyOrders> list = dao.findByPage( page , 5 );
		
		// 当前页码,就是形参page 
		// 每页显示的条数,指定为5条
		
		PageBean<MyOrders> pageBean = new PageBean<MyOrders>() ;
		pageBean.setCurrentPage(Integer.parseInt(page));
		pageBean.setlist(list);
		pageBean.setRows(5);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	
	/**
	 * 退票
	 */
	@Override
	public int backTickets(String oid) {
		/*
		 * 退票需要进行的操作,使用事务,后两步需要后台管理员的操作
		 * 	1. 将退票信息添加到退票表中，相应的信息应该通过订单id即oid来获取
		 * 	2. 在订单表中删除这个订单信息
		 * 	3. 在运营表中给相应的座位数量加一
		 */
		Connection con = null;
		int result = 0 ; 
		//	1. 将退票信息添加到退票表中 
		OrderItem oItem = dao.findById( Integer.parseInt( oid ) , con ); 
		
		RefundTicket rTicket = new RefundTicket() ;
		rTicket.setOid(Integer.parseInt( oid ));
		rTicket.setOperateid( oItem.getOperateid() );
		rTicket.setUid( oItem.getUid() );
		rTicket.setIdentify( oItem.getIdentify() );
		rTicket.setName( oItem.getName() );
		rTicket.setMoney( oItem.getMoney() );
		result = dao.add( rTicket );
		
		// 将相应订单的状态改为0，即待审核
		dao.updateState( oid );
		
		//2. 在订单表中删除这个订单信息
		//dao.delete( Integer.parseInt( oid ));
		
		// 3. 在运营表中给相应的座位数量加一
		//dao.addSeat( oItem.getOperateid(),oItem.getSeattype() ); 
		
		return result;
	}
	
	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		IOrderItemDao dao = new OrderItemDaoImpl();
		return new JsonObject(dao.lhytotal(true), dao.lhyfindByPage(page, rows));
	}

	@Override
	public JsonObject lhyfindByCondition(String tid, String uname, String buytime, String name, String status, int page,
			int rows) {
		IOrderItemDao dao = new OrderItemDaoImpl();
		return new JsonObject(dao.lhytotal(tid, uname, buytime, name, status), dao.lhyfindByCondition(tid, uname, buytime, name, status, page, rows));
	}

	@Override
	public OrderItem lhyfindByOid(String oid) {
		IOrderItemDao dao = new OrderItemDaoImpl();
		return dao.lhyfindByOid(oid);
	}

	@Override
	public List<Map<String, String>> lhysumByTid() {
		IOrderItemDao dao = new OrderItemDaoImpl();
		return dao.lhysumByTid();
	}

	@Override
	public List<Map<String, String>> lhysumByYear(String year) {
		if(StringUtil.checkNull(year)) {
			return null;
		}
		IOrderItemDao dao = new OrderItemDaoImpl();
		return dao.lhysumByYear(year);
	}

	@Override
	public List<Map<String, String>> lhysumByMonth(String year, String month) {
		if(StringUtil.checkNull(year, month)) {
			return null;
		}
		IOrderItemDao dao = new OrderItemDaoImpl();
		return dao.lhysumByMonth(year, month);
	}

	@Override
	public List<Map<String, String>> lhysumByDate(String date) {
		if(StringUtil.checkNull(date)) {
			return null;
		}
		IOrderItemDao dao = new OrderItemDaoImpl();
		return dao.lhysumByDate(date);
	}
	
}
