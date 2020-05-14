package com.yuan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.pojo.User;
import com.yuan.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisspringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;


    @Test
    void contextLoads() {


//        RedisConnectionFactory connection= redisTemplate.getConnectionFactory();
        redisTemplate.opsForValue().set("name","yuan");
        //相当于操纵字符串
        System.out.println(redisTemplate.opsForValue().get("name"));


    }


    @Test
    public void test() throws JsonProcessingException {

        //真实的开发，一般采用json传值

        User user = new User("袁堂波",3);
//        String string = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));


        redisUtil.addKey("yuan","节能主义",10, TimeUnit.SECONDS);
        System.out.println(redisUtil.get("袁堂波"));


    }

}
