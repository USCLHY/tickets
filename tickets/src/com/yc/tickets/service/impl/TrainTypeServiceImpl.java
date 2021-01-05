package com.yc.tickets.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.tickets.dao.ITrainTypeDao;
import com.yc.tickets.dao.impl.TrainTypeDaoImpl;
import com.yc.tickets.domain.TrainType;
import com.yc.tickets.dto.JsonObject;
import com.yc.tickets.service.ITrainTypeService;
import com.yc.tickets.utils.DBHelper_Jedis;
import com.yc.tickets.utils.StringUtil;

import redis.clients.jedis.Jedis;

/**
*
* @author : 外哥
* 邮箱 ： liwai2012220663@163.com
* 创建时间:2020年11月25日 下午8:01:04
*/
public class TrainTypeServiceImpl implements ITrainTypeService {
	
	private ITrainTypeDao dao = new TrainTypeDaoImpl();

	/**
	 * 查询所有车票类型
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Override
	public List<TrainType> findAll() throws JsonParseException, JsonMappingException, IOException { 
		List<TrainType> list = new ArrayList<TrainType>() ;
		
		// 得到jedis对象
		Jedis jedis = DBHelper_Jedis.getJedis() ;
		
		// 首先查询redis中是否有存在 
		String ty = jedis.get("list") ;
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		if ( ty != null && !"".equals(ty) ) {  
			// 如果存在，将json字符串转成对象数组 
			 TrainType[] trainTypes = objectMapper.readValue( ty , TrainType[].class );
			 // 将对象数组转成对象集合
			 list = Arrays.asList(trainTypes) ;
			  
			 jedis.close();
			return list ;
		} 
		
		// 查询数据库
		list = dao.findAll() ; 
		// 如果不存在，则查询数据库，并将查询到的结果存入redis中 
		String jackson = objectMapper.writeValueAsString(list) ;
		jedis.set("list", jackson) ;  
		
		return list ;
	}
	
	@Override
	public int lhyadd(String tname) {
		if(StringUtil.checkNull(tname)) {
			return -1;
		}
		ITrainTypeDao dao = new TrainTypeDaoImpl();
		return dao.lhyadd(tname);
	}

	@Override
	public JsonObject lhyfindByPage(int page, int rows) {
		ITrainTypeDao dao = new TrainTypeDaoImpl();
		return new JsonObject(dao.lhytotal(), dao.lhyfindByPage(page, rows));
	}

	@Override
	public JsonObject lhyfinds() {
		ITrainTypeDao dao = new TrainTypeDaoImpl();
		return new JsonObject(dao.lhytotal(), dao.lhyfinds());
	}

}
