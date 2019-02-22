package com.cnuip.console.jedis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * redis client pool
 * 
 * @author allen
 */
@Service("jedisClientPool")
public class JedisClientPool implements JedisClient {

	Logger logger = LoggerFactory.getLogger(JedisClientPool.class);

	@Autowired
	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.set(key, value);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public String get(String key) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.get(key);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.exists(key);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.expire(key, seconds);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.ttl(key);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.incr(key);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.hset(key, field, value);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.hget(key, field);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.hdel(key, field);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Boolean hexists(String key, String field) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.hexists(key, field);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public List<String> hvals(String key) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.hvals(key);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public Long del(String key) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.del(key);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}


	@Override
	public String setExpire(String key,String value,Long time) {
		Jedis jedis = new Jedis();
		try{
			jedis= jedisPool.getResource();
			return jedis.set(key, value, "NX", "EX", time);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public String setExpire(String key,Object value,Long time) {
		Jedis jedis = new Jedis();
		try{
			String objectJson = JSON.toJSONString(value);
			jedis= jedisPool.getResource();
			return jedis.set(key, objectJson, "NX", "EX", time);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return null;
		}finally {
			jedis.close();
		}
	}

	@Override
	public <T> T get(String key,Class<T> clazz){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return JSON.parseObject(value, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedis.close();
		}
	}
}
