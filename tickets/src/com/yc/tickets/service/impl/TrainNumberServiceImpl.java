package com.yc.tickets.service.impl;

import com.yc.tickets.dao.ITrainNumberDao;
import com.yc.tickets.dao.impl.TrainNumberDaoImpl;
import com.yc.tickets.domain.TrainNumber;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.ITrainNumberService;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:32:51
*/
public class TrainNumberServiceImpl implements ITrainNumberService{
	@Override
	public int lhyadd(String tname, String typeid, String startsite, String endsite, String duration, int rwo,
			double rwoprice, int rzuo, double rzuoprice, int ywo, double ywoprice, int yzuo, double yzuoprice, int zhan,
			double zhanprice, double wostudentprice, double zuostudentprice, int status) {
		ITrainNumberDao dao = new TrainNumberDaoImpl();
		if(StringUtil.checkNull(tname, typeid, startsite, endsite, duration, String.valueOf(rwo), String.valueOf(rwoprice), String.valueOf(rzuo), String.valueOf(rzuoprice), String.valueOf(ywo), String.valueOf(ywoprice), String.valueOf(yzuo), String.valueOf(yzuoprice), String.valueOf(zhan), String.valueOf(zhanprice), String.valueOf(wostudentprice), String.valueOf(zuostudentprice), String.valueOf(status))) {
			return -1;
		}
		return dao.lhyadd(tname, typeid, startsite, endsite, duration, rwo, rwoprice, rzuo, rzuoprice, ywo, ywoprice, yzuo, yzuoprice, zhan, zhanprice, wostudentprice, zuostudentprice, status);
	}

	@Override
	public int lhyupdate(String tid, String tname, String typeid, String startsite, String endsite, String duration, int rwo,
			double rwoprice, int rzuo, double rzuoprice, int ywo, double ywoprice, int yzuo, double yzuoprice, int zhan,
			double zhanprice, double wostudentprice, double zuostudentprice) {
		ITrainNumberDao dao = new TrainNumberDaoImpl();
		if(StringUtil.checkNull(tname, typeid, startsite, endsite, duration, String.valueOf(rwo), String.valueOf(rwoprice), String.valueOf(rzuo), String.valueOf(rzuoprice), String.valueOf(ywo), String.valueOf(ywoprice), String.valueOf(yzuo), String.valueOf(yzuoprice), String.valueOf(zhan), String.valueOf(zhanprice), String.valueOf(wostudentprice), String.valueOf(zuostudentprice))) {
			return -1;
		}
		return dao.lhyupdate(tid, tname, typeid, startsite, endsite, duration, rwo, rwoprice, rzuo, rzuoprice, ywo, ywoprice, yzuo, yzuoprice, zhan, zhanprice, wostudentprice, zuostudentprice);
	}

	@Override
	public int lhyupdateStatus(String status, String tid) {
		ITrainNumberDao dao = new TrainNumberDaoImpl();
		if(StringUtil.checkNull(status, tid)) {
			return -1;
		}
		return dao.lhyupdateStatus(status, tid);
	}

	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		ITrainNumberDao dao = new TrainNumberDaoImpl();
		return new JsonObject(dao.lhytotal(), dao.lhyfindByPage(page, rows));
	}

	@Override
	public JsonObject lhyfindByCondition(String tname, String typeid, String startsite, String endsite, int page,
			int rows) {
		ITrainNumberDao dao = new TrainNumberDaoImpl();
		return new JsonObject(dao.lhytotal(tname, typeid, startsite, endsite), dao.lhyfindByCondition(tname, typeid, startsite, endsite, page, rows));
	}

	@Override
	public TrainNumber lhyfindByTid(String tid) {
		ITrainNumberDao dao = new TrainNumberDaoImpl();
		if(StringUtil.checkNull(tid)) {
			return null;
		}
		return dao.lhyfindByTid(tid);
	}
}
