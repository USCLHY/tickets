package com.yc.tickets.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yc.tickets.dao.DBHelper;
import com.yc.tickets.dao.DBHelper_Mysql;
import com.yc.tickets.dao.ITrainTypeDao;
import com.yc.tickets.domain.TrainType;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午8:03:22
*/
public class TrainTypeDaoImpl implements ITrainTypeDao{
	private JdbcTemplate template = new JdbcTemplate( DBHelper_Mysql.getDs() ) ;

	/**
	 * 查询所有车票类型
	 * @return
	 */
	@Override
	public List<TrainType> findAll() {
		String sql = "select tid ,tname from train_type " ;
		return template.query(sql, new BeanPropertyRowMapper<TrainType>(TrainType.class)) ;
	}

	@Override
	public int lhyadd(String tname) {
		DBHelper db = new DBHelper();
		String sql = "insert into train_type values(0, ?)";
		return db.update(sql, tname);
	}

	@Override
	public int lhytotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(tid) from train_type";
		return db.total(sql);
	}

	@Override
	public List<TrainType> lhyfindByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select tid, tname from train_type order by tid limit ?, ?";
		return db.finds(TrainType.class, sql, (page-1)*rows, rows);
	}

	@Override
	public List<TrainType> lhyfinds() {
		DBHelper db = new DBHelper();
		String sql = "select tid, tname from train_type";
		return db.finds(TrainType.class, sql);
	}
}
