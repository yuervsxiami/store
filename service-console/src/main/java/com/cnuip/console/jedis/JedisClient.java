package com.cnuip.console.jedis;

import java.util.List;

/**
 * redis client interface
 * 
 * @author allen
 */
public interface JedisClient {

	String set(String key, String value);

	String get(String key);

	Boolean exists(String key);

	Long expire(String key, int seconds);

	Long ttl(String key);

	Long incr(String key);

	Long hset(String key, String field, String value);

	String hget(String key, String field);

	Long hdel(String key, String... field);

	Boolean hexists(String key, String field);

	List<String> hvals(String key);

	Long del(String key);

	String setExpire(String key, String value, Long expireSecond);

	String setExpire(String key, Object value, Long expireSecond);

	 <T> T get(String key,Class<T> clazz);
}
