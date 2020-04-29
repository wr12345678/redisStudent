package com.test;

import com.example.stu.util.RedisUtile;
import com.example.stu.util.StringDateUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.util.Date;


public class TestRedis {

    @Test
    public void test() throws ParseException {
       Jedis jedis =  RedisUtile.getJedis();

        StringDateUtil.dateToString(new Date());
        System.out.println(StringDateUtil.stringToDate("2016-01-01","yyyy-MM-dd").toString());
        RedisUtile.close(jedis);
    }
}
