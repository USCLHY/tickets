package com.yc.tickets.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis工具类 加载配置文件，配置连接池的参数 提供获取连接的方法
 * 
 * @author 外哥
 *
 */
public class DBHelper_Jedis {

	private static JedisPool jedisPool;

	static {
//		读取配置文件
		InputStream is = DBHelper_Jedis.class.getClassLoader().getResourceAsStream("jedis.properties");
//		创建Properties对象
		Properties properties = new Properties();
		try {
//			关联文件
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		读取数据，设置到jedisPool对象

		String host = properties.getProperty("host");
		int port = Integer.parseInt(properties.getProperty("port"));

		JedisPoolConfig config = new JedisPoolConfig(); 
		
		config.setMaxTotal( Integer.parseInt(properties.getProperty("maxTotal")));
		config.setMaxIdle( Integer.parseInt(properties.getProperty("maxIdle")));
	  
		// 初始化JedisPool对象 
		jedisPool = new JedisPool(config , host , port );
		 
	}

	/**
	 * 获取连接方法
	 */
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
}
