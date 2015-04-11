package com.linguistic.network;

import java.util.Set;

import com.linguistic.network.config.AppProperties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class JedisFactory {
	
	private static JedisPool pool = null;

	public JedisFactory(){
		pool = new JedisPool(AppProperties.getProperty("redis.host"),Integer.parseInt(AppProperties.getProperty("redis.port")));
	}
	
	public Set<String> getRedisSet(String key){
		Jedis jedis = pool.getResource();
		return jedis.smembers("Haberturk");
	}
	
	public Set<String> addToSet(String key, String value){
		Jedis jedis = pool.getResource();
		
		try {
			return jedis.smembers("Haberturk");
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
		return null;
	}
	
}
