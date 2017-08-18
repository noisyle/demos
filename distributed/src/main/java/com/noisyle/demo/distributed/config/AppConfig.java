package com.noisyle.demo.distributed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

import com.noisyle.demo.distributed.lock.core.DistributedLock;
import com.noisyle.demo.distributed.lock.redis.RedisDistributedLock;

@Configuration
@PropertySource({"classpath:spring-context.properties"})
@ComponentScan(basePackages = { "com.noisyle.demo.distributed.lock" })
public class AppConfig {
	@Value("${host:127.0.0.1}")
	private String host;
	@Value("${port:6379}")
	private int port;
	@Value("${maxIdle:5}")
	private int maxIdle;
	@Value("${maxTotal:10}")
	private int maxTotal;
	@Value("${maxWaitMillis:100}")
	private int maxWaitMillis;

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		return poolConfig;
	}
	
	@Bean(destroyMethod="destroy")
	@DependsOn("jedisPoolConfig")
	public JedisConnectionFactory jedisConnectionFactory(@Autowired JedisPoolConfig jedisPoolConfig) {
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		jedis.setHostName(host);
		jedis.setPort(port);
		jedis.setPoolConfig(jedisPoolConfig);
		jedis.setUsePool(true);
		return jedis;
	}

	@Bean
	@DependsOn("jedisConnectionFactory")
	@SuppressWarnings({ "rawtypes" })
	public RedisTemplate redisTemplate(@Autowired JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory);
		return template;
	}
	
	@Bean
	@DependsOn("redisTemplate")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DistributedLock distributedLock(@Autowired RedisTemplate redisTemplate) {
		DistributedLock distributedLock = new RedisDistributedLock(redisTemplate);
		return distributedLock;
	}
	
	
}
