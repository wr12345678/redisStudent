package com.example.stu.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisUtile {

    private static JedisPool pool = null;

    static {
        InputStream in = RedisUtile.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取配置文件中的值，加载JedisPoolConfig
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        //加载Redis最大连接数
        poolConfig.setMaxTotal(Integer.parseInt(properties.get("redis.maxTotal").toString()));


        //最大空闲连接数
        poolConfig.setMaxIdle(Integer.parseInt(properties.get("redis.maxIdle").toString()));

        //最小空闲连接数
        poolConfig.setMinIdle(Integer.parseInt(properties.get("redis.minIdle").toString()));

        pool = new JedisPool(poolConfig, properties.getProperty("redis.url")
                ,Integer.parseInt(properties.get("redis.port").toString()),5000,properties.getProperty("redis.password"));
    }

    public  static Jedis getJedis(){
        return pool.getResource();
    }
    public static  void close(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }
}
