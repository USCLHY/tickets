package com.yc.tickets.service.impl;

import com.yc.tickets.dao.IUsersDao;
import com.yc.tickets.dao.impl.UsersDaoImpl;
import com.yc.tickets.domain.Users;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.IUsersService;
import com.yc.tickets.utils.StringUtil;

/**
* 用户的业务层实现类
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月15日 下午9:16:29
*/
public class UsersServiceImpl implements IUsersService{
	private IUsersDao dao = new UsersDaoImpl() ;

	/**
	 * 注册
	 */
	@Override
	public int register(Users users) {
		if ( StringUtil.checkNull( users.getUname(),users.getUpassword(),users.getUtel(),users.getUidentify(),users.getUemail() )) {
			// 信息为空
			return -1 ;
		} 
		return dao.register(users) ;
	}

	
	/**
	 * 登录
	 */
	@Override
	public Users login(String account, String pwd) {
		if ( StringUtil.checkNull( account,pwd  )) {
			// 信息为空
			return null ;
		} 
		return dao.login(account, pwd);
	}


	/**
	 * 激活
	 */
	@Override
	public int active(String identify) {
		return dao.active( identify )  ;
	}

	/**
	 * 通过邮箱查询用户
	 * @param email
	 * @return
	 */
	@Override
	public Users findByEmail(String email) {  
		return dao.findByEmail(email) ;
	}


	/**
	 * 修改用户信息
	 */
	@Override
	public int update(Users user) {
		return dao.update( user );
	}
	
	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		IUsersDao dao = new UsersDaoImpl();
		return new JsonObject(dao.lhytotal(), dao.lhyfindByPage(page, rows));
	}

	@Override
	public JsonObject lhyfindByCondition(String uname, String utel, String status, int page, int rows) {
		IUsersDao dao = new UsersDaoImpl();
		return new JsonObject(dao.lhytotal(uname, utel, status), dao.lhyfindByCondition(uname, utel, status, page, rows));
	}

	@Override
	public int lhyupdateStatus(String uid, int status) {
		IUsersDao dao = new UsersDaoImpl();
		if(StringUtil.checkNull(uid, String.valueOf(status))) {
			System.out.println("修改用户状态：信息不完整");
			return -1;
		}
		return dao.lhyupdateStatus(uid, status);
	}

	@Override
	public Users lhyfindByUid(String uid) {
		IUsersDao dao = new UsersDaoImpl();
		if(StringUtil.checkNull(uid)) {
			return null;
		}
		return dao.lhyfindByUid(uid);
	}


}
