package com.linguistic.datacollector;

import com.linguistic.datacollector.config.AppProperties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class JedisFactory {
	
	private static JedisPool pool = null;

	public JedisFactory(){
		pool = new JedisPool(AppProperties.getProperty("redis.host"),Integer.parseInt(AppProperties.getProperty("redis.port")));
	}
	
	public void pushToList(String key, String value){
		Jedis jedis = pool.getResource();
		
		try {
			jedis.lpush(key, value);
			
		} catch (JedisException e) {
			if(null != jedis){
				pool.returnBrokenResource(jedis);
				jedis = null;
			}
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	
	public void addToSet(String key, String value){
		Jedis jedis = pool.getResource();
		
		try {
			jedis.sadd(key, value);
			
		} catch (JedisException e) {
			if(null != jedis){
				pool.returnBrokenResource(jedis);
				jedis = null;
			}
		}finally{
			if(null != jedis){
				pool.returnBrokenResource(jedis);
			}
		}
	}
	
}
