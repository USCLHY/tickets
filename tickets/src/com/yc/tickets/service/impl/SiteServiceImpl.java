package com.yc.tickets.service.impl;

import com.yc.tickets.dao.ISiteDao;
import com.yc.tickets.dao.impl.SiteDaoImpl;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.ISiteService;
import com.yc.tickets.utils.StringUtil;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年12月20日 下午9:31:12
*/
public class SiteServiceImpl implements ISiteService  {
	@Override
	public int lhyadd(String sname, String status) {
		if(StringUtil.checkNull(sname, status)) {
			return -1;
		}
		ISiteDao dao = new SiteDaoImpl();
		return dao.lhyadd(sname, status);
	}

	@Override
	public int lhyupdateSname(String sname, String sid) {
		if(StringUtil.checkNull(sname, sid)) {
			return -1;
		}
		ISiteDao dao = new SiteDaoImpl();
		return dao.lhyupdateSname(sname, sid);
	}

	@Override
	public int lhyupdateStatus(String status, String sid) {
		if(StringUtil.checkNull(status, sid)) {
			return -1;
		}
		ISiteDao dao = new SiteDaoImpl();
		return dao.lhyupdateStatus(status, sid);
	}

	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		ISiteDao dao = new SiteDaoImpl();
		return new JsonObject(dao.lhytotal(false), dao.lhyfindByPage(page, rows));
	}

	@Override
	public JsonObject lhyfinds() {
		ISiteDao dao = new SiteDaoImpl();
		return new JsonObject(dao.lhytotal(true), dao.lhyfinds());
	}
}
