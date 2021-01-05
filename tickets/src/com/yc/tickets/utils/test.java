package com.yc.tickets.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import redis.clients.jedis.Jedis;

public class test {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia.getHostAddress());
		System.out.println(jedis.ping());
		jedis.close();
	}
}
